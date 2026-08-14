package com.roochi.petflowticket.service.impl;

import com.roochi.petflowticket.repository.TicketAttachmentRepository;
import com.roochi.petflowticket.service.TicketFileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketAttachmentCleanupService {

    private final TicketAttachmentRepository attachmentRepository;

    private final TicketFileStorageService fileStorageService;


    @Scheduled(
            fixedDelayString =
                    "${petflow.ticket.storage.cleanup-delay:86400000}"
    )
    public void cleanupOrphanFiles() {

        log.info(
                "Starting ticket attachment orphan cleanup..."
        );

        try {

            /*
             * فایل‌هایی که واقعاً در Storage وجود دارند
             */
            List<String> storageFiles =
                    fileStorageService
                            .listStorageKeys();


            /*
             * فایل‌هایی که DB می‌شناسد
             */
            List<String> databaseFiles =
                    attachmentRepository
                            .findAllStorageKeys();


            Set<String> databaseStorageKeys =
                    new HashSet<>(
                            databaseFiles
                    );


            int deletedCount = 0;


            for (String storageFile :
                    storageFiles) {

                /*
                 * اگر فایل در Storage وجود دارد
                 * ولی DB رکوردی برای آن ندارد،
                 * orphan است.
                 */
                if (!databaseStorageKeys
                        .contains(storageFile)) {

                    try {

                        fileStorageService.delete(
                                storageFile
                        );

                        deletedCount++;

                        log.info(
                                "Deleted orphan ticket attachment: {}",
                                storageFile
                        );

                    } catch (RuntimeException e) {

                        log.error(
                                "Could not delete orphan ticket attachment: {}",
                                storageFile,
                                e
                        );
                    }
                }
            }


            log.info(
                    "Ticket attachment orphan cleanup finished. " +
                            "Storage files={}, DB files={}, deleted={}",
                    storageFiles.size(),
                    databaseStorageKeys.size(),
                    deletedCount
            );

        } catch (RuntimeException e) {

            log.error(
                    "Ticket attachment orphan cleanup failed",
                    e
            );
        }
    }
}