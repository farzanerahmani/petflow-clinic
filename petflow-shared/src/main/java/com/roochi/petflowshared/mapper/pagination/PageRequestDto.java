package com.roochi.petflowshared.mapper.pagination;


//import com.roochi.petflow.validation.FieldDescriptor;
//import com.roochi.petflow.validation.ValidationTypeNames;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author farzane.rahmani
 * @created 6/3/2026
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Schema(title = "SearchRequestDto")
public class PageRequestDto {

    //  @FieldDescriptor(businessType = ValidationTypeNames.PAGE_SIZE)
    @Positive
    @Min(1)
    @Max(100)
    @Schema(description = "${SearchRequestDto.pageSize}")
    protected int pageSize = 20;

    // @FieldDescriptor(businessType = ValidationTypeNames.PAGE_NUMBER)
    @Min(0)
    @Schema(description = "${SearchRequestDto.pageNumber}")
    protected int pageNumber = 0;

    @Schema(description = "${SearchRequestDto.descOrdering}")
    protected Boolean descOrdering;

}
