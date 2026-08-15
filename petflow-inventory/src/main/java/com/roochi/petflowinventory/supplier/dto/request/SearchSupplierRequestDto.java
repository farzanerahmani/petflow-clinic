package com.roochi.petflowinventory.supplier.dto.request;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchSupplierRequestDto extends PageRequestDto {

    private String code;
    private String name;
    private Boolean active;
}
