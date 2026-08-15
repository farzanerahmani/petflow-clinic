package com.roochi.petflowshared.utility;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author farzane.rahmani
 * @created 7/1/2026
 */
public final class PaginationUtils {

    private PaginationUtils() {
    }

    public static Pageable createPageRequest(PageRequestDto requestDto) {
        Sort.Direction direction = requestDto.getDescOrdering() ? Sort.Direction.DESC :
                Sort.Direction.ASC;
        return PageRequest.of(
                requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(direction, "id")

        );
    }
}
