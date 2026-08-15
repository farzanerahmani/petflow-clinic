package com.roochi.petflowvisit.invoice.mapper;


import com.roochi.petflowvisit.dto.cmmon.InvoiceDto;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mapping(target = "visitId", source = "visit.id")
    InvoiceResponseDto toResponseDto(Invoice entity);

    @Mapping(target = "visitId", source = "visit.id")
    InvoiceDto toInvoiceDto(Invoice entity);

    List<InvoiceResponseDto> toResponseDtos(List<Invoice> entities);

    List<InvoiceDto> toInvoiceDtos(List<Invoice> entities);

}
