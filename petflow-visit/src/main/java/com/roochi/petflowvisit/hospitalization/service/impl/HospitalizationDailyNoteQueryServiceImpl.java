package com.roochi.petflowvisit.hospitalization.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.HospitalizationDailyNoteDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationDailyNoteByIdRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationDailyNoteForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.SearchHospitalizationDailyNoteRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.SearchHospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.HospitalizationDailyNote;
import com.roochi.petflowvisit.hospitalization.mapper.HospitalizationDailyNoteMapper;
import com.roochi.petflowvisit.hospitalization.repository.HospitalizationDailyNoteRepository;
import com.roochi.petflowvisit.hospitalization.service.query.HospitalizationDailyNoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalizationDailyNoteQueryServiceImpl implements HospitalizationDailyNoteQueryService {

    private final HospitalizationDailyNoteRepository hospitalizationDailyNoteRepository;

    private final HospitalizationDailyNoteMapper hospitalizationDailyNoteMapper;

    @Override
    public HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteById(GetHospitalizationDailyNoteByIdRequestDto responseDto) {
        HospitalizationDailyNote entity =
                hospitalizationDailyNoteRepository.findById(responseDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR));

        return hospitalizationDailyNoteMapper.toResponseDto(entity);
    }

    @Override
    public HospitalizationDailyNoteResponseDto getHHospitalizationDailyNoteForUpdate(GetHospitalizationDailyNoteForUpdateRequestDto requestDto) {
        HospitalizationDailyNote entity =
                hospitalizationDailyNoteRepository.findById(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR));//HospitalizationDailyNoteError.HOSPITALIZATION_DAILY_NOTE_NOT_FOUND));

        return hospitalizationDailyNoteMapper.toResponseDto(entity);
    }

    @Override
    public SearchHospitalizationDailyNoteResponseDto searchHospitalizationDailyNote(SearchHospitalizationDailyNoteRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<HospitalizationDailyNote> page = hospitalizationDailyNoteRepository.search(
                requestDto.getHospitalizationId(),
                requestDto.getVeterinarianId(),
                requestDto.getFromDate(),
                requestDto.getToDate()
                , pageRequest);

        List<HospitalizationDailyNoteDto> hospitalizationDailyNotes =
                page.getContent()
                        .stream()
                        .map(hospitalizationDailyNoteMapper::toHospitalizationDailyNoteDto).toList();

        SearchHospitalizationDailyNoteResponseDto response = new SearchHospitalizationDailyNoteResponseDto();
        response.setResults(hospitalizationDailyNotes);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
