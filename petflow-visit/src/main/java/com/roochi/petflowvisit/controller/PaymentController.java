package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.payment.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.payment.*;
import com.roochi.petflowvisit.payment.facade.PaymentFacade;
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
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @Service(name = "addPayment")
    @Operation(operationId = "addPayment", description = "${PaymentController.addPayment}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public AddPaymentResponseDto addPayment(@RequestBody AddPaymentRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return paymentFacade.addPayment(requestDto);
    }

    @Service(name = "updatePayment")
    @Operation(operationId = "updatePayment", description = "${PaymentController.updatePayment}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public UpdatePaymentResponseDto updatePayment(@RequestBody UpdatePaymentRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return paymentFacade.updatePayment(requestDto);
    }

    @Service(name = "deletePayment")
    @Operation(operationId = "deletePayment", description = "${PaymentController.deletePayment}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public DeletePaymentResponseDto deletePayment(@RequestBody DeletePaymentRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return paymentFacade.deletePayment(requestDto);
    }

    @Service(name = "getPaymentById")
    @Operation(operationId = "getPaymentById", description = "${PaymentController.getPaymentById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public PaymentResponseDto getPaymentById(@RequestBody GetPaymentByIdRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return paymentFacade.getPaymentById(requestDto);
    }

    @Service(name = "getPaymentForUpdate")
    @Operation(operationId = "getPaymentForUpdate", description = "${PaymentController.getPaymentForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public PaymentResponseDto getPaymentForUpdate(@RequestBody GetPaymentForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return paymentFacade.getPaymentForUpdate(requestDto);
    }

    @Service(name = "searchPayment")
    @Operation(operationId = "searchPayment", description = "${PaymentController.searchPayment}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PaymentController.invalidRequest")
    public SearchPaymentResponseDto searchPeyment(@RequestBody SearchPaymentRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return paymentFacade.searchPayment(requestDto);
    }
}
