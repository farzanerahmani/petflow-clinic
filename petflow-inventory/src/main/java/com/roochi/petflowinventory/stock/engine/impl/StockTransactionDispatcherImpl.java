package com.roochi.petflowinventory.stock.engine.impl;

import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.engine.StockTransactionDispatcher;
import com.roochi.petflowinventory.stock.engine.StockTransactionHandler;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */


@Component
@RequiredArgsConstructor
public class StockTransactionDispatcherImpl
        implements StockTransactionDispatcher {



    private final List<StockTransactionHandler> handlers;



    private Map<StockTransactionType, StockTransactionHandler> handlerMap;



    @PostConstruct
    public void init() {


        handlerMap =
                new EnumMap<>(StockTransactionType.class);



        handlers.forEach(handler ->
                handlerMap.put(
                        handler.supportedType(),
                        handler
                )
        );

    }



    @Override
    public void dispatch(
            TransactionCommand command
    ) {


        StockTransactionHandler handler =
                handlerMap.get(
                        command.getTransactionType()
                );



        if(handler == null) {

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }



        handler.handle(command);

    }

}