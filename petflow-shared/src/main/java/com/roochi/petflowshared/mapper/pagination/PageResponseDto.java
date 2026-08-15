package com.roochi.petflowshared.mapper.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author farzane.rahmani
 * @created 6/1/2026
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(title = "SearchResponseDto")
public class PageResponseDto<E> implements Serializable {

    @Schema(description = "${SearchResponseDto.results}")
    protected Collection<E> results;

    @Schema(description = "${SearchResponseDto.totalRecords}")
    protected long totalRecords;

    @Schema(description = "${SearchResponseDto.currentPage}")
    protected int currentPage;

    @Schema(description = "${SearchResponseDto.totalPages}")
    protected int totalPages;

    @Schema(description = "${SearchResponseDto.pageSize}")
    protected int pageSize;

    public static <T extends PageResponseDto<E>, E> T of(Supplier<T> generator, Collection<E> source, int pageIndex, int pageSize) {

        var response = generator.get();
        pageSize = Math.max(pageSize, 1);

        response.setTotalRecords(source.size());
        response.setTotalPages((int) (response.getTotalRecords() / pageSize));

        if (response.getTotalRecords() % pageSize > 0)
            response.setTotalPages(response.getTotalPages() + 1);

        response.setPageSize(pageSize);
        response.setCurrentPage(pageIndex);

        Collection<E> result = source.stream().skip((long) (Math.max(pageIndex, 0)) * pageSize).limit(pageSize).toList();
        response.setResults(result);

        return response;
    }

    public static <T extends PageResponseDto<E>, E> T emptyResponse(Supplier<T> responseGenerator) {
        return emptyResponse(responseGenerator.get());
    }

    public static <T extends PageResponseDto<E>, E> T emptyResponse(T response) {
        response.setCurrentPage(0);
        response.setPageSize(0);
        response.setTotalRecords(0);
        response.setTotalPages(0);
        response.setResults(List.of());

        return response;
    }
}