package com.roochi.petflowticket.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketFileStorageService {

    String store(
            Long ticketId,
            MultipartFile file
    );

    Resource load(
            String storageKey
    );

    void delete(
            String storageKey
    );

    List<String> listStorageKeys();
}
