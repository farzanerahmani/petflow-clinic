package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.invoice.*;
import com.roochi.petflowvisit.invoice.facade.InvoiceFacade;
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
@RequestMapping("api/Invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceFacade invoiceFacade;

    @Service(name = "addInvoice")
    @Operation(operationId = "addInvoice", description = "${InvoiceController.addInvoice}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public AddInvoiceResponseDto addInvoice(@RequestBody AddInvoiceRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.addInvoice(requestDto);
    }

    @Service(name = "updateInvoice")
    @Operation(operationId = "updateInvoice", description = "${InvoiceController.updateInvoice}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public UpdateInvoiceResponseDto updateInvoice(@RequestBody UpdateInvoiceRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.updateInvoice(requestDto);
    }

    @Service(name = "deleteInvoice")
    @Operation(operationId = "deleteInvoice", description = "${InvoiceController.deleteInvoice}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public DeleteInvoiceResponseDto deleteInvoice(@RequestBody DeleteInvoiceRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.deleteInvoice(requestDto);
    }

    @Service(name = "getInvoiceById")
    @Operation(operationId = "getInvoiceById", description = "${InvoiceController.getInvoiceById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public InvoiceResponseDto getInvoiceById(@RequestBody GetInvoiceByIdRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.getInvoiceById(requestDto);
    }

    @Service(name = "getInvoiceForUpdate")
    @Operation(operationId = "getInvoiceForUpdate", description = "${InvoiceController.getInvoiceForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public InvoiceResponseDto getInvoiceForUpdate(@RequestBody GetInvoiceForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.getInvoiceForUpdate(requestDto);
    }

    @Service(name = "searchInvoice")
    @Operation(operationId = "searchInvoice", description = "${InvoiceController.searchInvoice}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "InvoiceController.invalidRequest")
    public SearchInvoiceResponseDto searchInvoice(@RequestBody SearchInvoiceRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return invoiceFacade.searchInvoice(requestDto);
    }
}
