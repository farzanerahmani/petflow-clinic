package com.roochi.petflowshared.mapper.pagination;

import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
public final class PageMapper {
    private PageMapper(){}
    public static <E,D> PageResponseDto<D> map(Page<E> page, Function<E,D> mapper){
        PageResponseDto<D> responseDto = new PageResponseDto<>();
        responseDto.setResults(
                page.getContent()
                        .stream().map(mapper).toList());
        responseDto.setPageSize(page.getSize());
        responseDto.setCurrentPage(page.getNumber());
        responseDto.setTotalPages(page.getTotalPages());
        responseDto.setTotalRecords(page.getTotalElements());
        return responseDto;
    }
}
