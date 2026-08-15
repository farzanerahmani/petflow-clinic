package com.roochi.petflowaudit.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Service
@RequiredArgsConstructor
public class AuditSnapshotService {

    private final ObjectMapper objectMapper;

    public String snapshot(Object object) {

        if (object == null) {
            return null;
        }

        try {

            return objectMapper.writeValueAsString(
                    object
            );

        } catch (JsonProcessingException e) {

            throw new IllegalStateException(
                    "Could not create audit snapshot",
                    e
            );
        }
    }
}