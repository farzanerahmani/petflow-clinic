package com.roochi.petflowclinic.facade;

import com.roochi.petflowclinic.dto.request.*;
import com.roochi.petflowclinic.dto.response.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 7/7/2026
 */
public interface ClinicFacade {

    @PostMapping(value = "/createClinic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CreateClinicResponseDto createClinic(@RequestBody CreateClinicRequestDto requestDto,
                                         @RequestHeader Map<String, Object> headers) throws Exception;


    @PostMapping(value = "/updateClinic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateClinicResponseDto updateClinic(@RequestBody UpdateClinicRequestDto requestDto,
                                         @RequestHeader Map<String, Object> headers) throws Exception;


    @PostMapping(value = "/deleteClinic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeleteClinicResponseDto deleteClinic(@RequestBody DeleteClinicRequestDto requestDto,
                                         @RequestHeader Map<String, Object> headers) throws Exception;



    @PostMapping(value = "/activateClinic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ActivateClinicResponseDto activateClinic(@RequestBody ActivateClinicRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) throws Exception;


    @PostMapping(value = "/deactivateClinic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeactivateClinicResponseDto deactivateClinic(@RequestBody DeactivateClinicRequestDto requestDto,
                                                 @RequestHeader Map<String, Object> headers) throws Exception;



    @PostMapping(value = "/findClinicById",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ClinicResponseDto findClinicById(@RequestBody FindClinicByIdRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/findClinicByCode",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ClinicResponseDto findClinicByCode(@RequestBody FindClinicByCodeRequestDto requestDto,
                                       @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/findAllClinics",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClinicResponseDto> findAllClinics(@RequestBody FindAllClinicsRequestDto requestDto,
                                           @RequestHeader Map<String, Object> headers);
}
