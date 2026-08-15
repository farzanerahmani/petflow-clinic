package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;
import com.roochi.petflowvisit.invoice.facade.InvoiceItemFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@RestController
@RequestMapping("api/Invoice-items")
@RequiredArgsConstructor
public class InvoiceItemController {

    private final InvoiceItemFacade invoiceItemFacade;

    @Service(name = "addInvoiceItem")
    @Operation(operationId = "addInvoiceItem", description = "${InvoiceItemController.addInvoiceItem}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public AddInvoiceItemResponseDto addInvoiceItem(@RequestBody AddInvoiceItemRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.addInvoiceItem(requestDto);
    }

    @Service(name = "updateInvoiceItem")
    @Operation(operationId = "updateInvoiceItem", description = "${InvoiceItemController.updateInvoiceItem}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public UpdateInvoiceItemResponseDto updateInvoiceItem(@RequestBody UpdateInvoiceItemRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.updateInvoiceItem(requestDto);
    }

    @Service(name = "deleteInvoiceItem")
    @Operation(operationId = "deleteInvoiceItem", description = "${InvoiceItemController.deleteInvoiceItem}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public DeleteInvoiceItemResponseDto deleteInvoiceItem(@RequestBody DeleteInvoiceItemRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.deleteInvoiceItem(requestDto);
    }

    @Service(name = "getInvoiceItemById")
    @Operation(operationId = "getInvoiceItemById", description = "${InvoiceItemController.getInvoiceItemById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public InvoiceItemResponseDto getInvoiceById(@RequestBody GetInvoiceItemByIdRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.getInvoiceItemById(requestDto);
    }

    @Service(name = "getInvoiceItemForUpdate")
    @Operation(operationId = "getInvoiceItemForUpdate", description = "${InvoiceItemController.getInvoiceItemForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public InvoiceItemResponseDto getInvoiceItemForUpdate(@RequestBody GetInvoiceItemForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.getInvoiceItemForUpdate(requestDto);
    }

    @Service(name = "searchInvoiceItem")
    @Operation(operationId = "searchInvoiceItem", description = "${InvoiceItemController.searchInvoiceItem}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceItemController.invalidRequest")
    public SearchInvoiceItemResponseDto searchInvoiceItem(@RequestBody SearchInvoiceItemRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceItemFacade.searchInvoiceItem(requestDto);
    }
}
