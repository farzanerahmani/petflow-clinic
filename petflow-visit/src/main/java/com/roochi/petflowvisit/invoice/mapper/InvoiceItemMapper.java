package com.roochi.petflowvisit.invoice.mapper;
import com.roochi.petflowvisit.dto.cmmon.InvoiceItemDto;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceItemResponseDto;
import com.roochi.petflowvisit.invoice.entity.InvoiceItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Mapper(componentModel = "spring")
public interface InvoiceItemMapper {

    @Mapping(target = "invoiceId", source = "invoice.id")
    InvoiceItemResponseDto toResponseDto(InvoiceItem entity);

    InvoiceItemDto toInvoiceItemDto(InvoiceItem entity);

    List<InvoiceItemResponseDto> toResponseDtos(List<InvoiceItem> entities);

    List<InvoiceItemDto> toInvoiceItemDtos(List<InvoiceItem> entities);

}
