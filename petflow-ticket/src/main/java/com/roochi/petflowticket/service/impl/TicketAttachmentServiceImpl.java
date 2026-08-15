package com.roochi.petflowticket.service.impl;

import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowticket.dto.response.TicketAttachmentResponseDto;
import com.roochi.petflowticket.entity.Ticket;
import com.roochi.petflowticket.entity.TicketAttachment;
import com.roochi.petflowticket.entity.enums.TicketStatus;
import com.roochi.petflowticket.repository.TicketAttachmentRepository;
import com.roochi.petflowticket.repository.TicketRepository;
import com.roochi.petflowticket.service.TicketAttachmentService;
import com.roochi.petflowticket.service.TicketFileStorageService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TicketAttachmentServiceImpl implements TicketAttachmentService {

    private static final long MAX_FILE_SIZE =
            10 * 1024 * 1024L;

    private static final Set<String> ALLOWED_CONTENT_TYPES =
            Set.of(
                    "image/jpeg",
                    "image/png",
                    "image/webp",
                    "application/pdf",
                    "text/plain",
                    "application/zip"
            );

    private final TicketRepository ticketRepository;

    private final TicketAttachmentRepository attachmentRepository;

    private final TicketFileStorageService fileStorageService;


    @Override
    @Transactional
    public TicketAttachmentResponseDto uploadForClinic(
            Long ticketId,
            MultipartFile file) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        Long userId =
                authentication.getUserId();

        Ticket ticket =
                ticketRepository
                        .findByIdAndClinicIdAndDeletedFalse(
                                ticketId,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        validateTicketCanReceiveAttachment(ticket);
        validateFile(file);

        String storageKey = null;

        try {

            // 1. اول فایل را روی Storage ذخیره می‌کنیم
            storageKey =
                    fileStorageService.store(
                            ticketId,
                            file
                    );

            // 2. بعد رکورد DB را ایجاد می‌کنیم
            TicketAttachment attachment =
                    TicketAttachment.builder()
                            .ticket(ticket)
                            .originalFileName(
                                    file.getOriginalFilename()
                            )
                            .storedFileName(
                                    extractFileName(storageKey)
                            )
                            .storageKey(storageKey)
                            .contentType(
                                    file.getContentType()
                            )
                            .fileSize(
                                    file.getSize()
                            )
                            .uploadedByUserId(userId)
                            .build();

            TicketAttachment savedAttachment =
                    attachmentRepository.save(
                            attachment
                    );

            // 3. اگر همه‌چیز موفق بود Response
            return toResponse(
                    savedAttachment
            );

        } catch (RuntimeException e) {

            /*
             * اگر فایل ذخیره شده ولی DB شکست خورد،
             * فایل فیزیکی را پاک می‌کنیم.
             */
            if (storageKey != null) {

                try {

                    fileStorageService.delete(
                            storageKey
                    );

                } catch (RuntimeException cleanupException) {

                    /*
                     * خطای Cleanup نباید خطای اصلی
                     * را مخفی کند.
                     */
                    log.error(
                            "Could not cleanup attachment after database failure. storageKey={}",
                            storageKey,
                            cleanupException
                    );
                }
            }

            throw e;
        }
    }


    @Override
    @Transactional
    public TicketAttachmentResponseDto uploadForSupport(
            Long ticketId,
            MultipartFile file) {

        JwtAuthentication authentication =
                getAuthentication();

        Long userId =
                authentication.getUserId();

        Ticket ticket =
                ticketRepository
                        .findByIdAndDeletedFalse(
                                ticketId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        validateTicketCanReceiveAttachment(ticket);
        validateFile(file);

        String storageKey = null;

        try {

            storageKey =
                    fileStorageService.store(
                            ticketId,
                            file
                    );

            TicketAttachment attachment =
                    TicketAttachment.builder()
                            .ticket(ticket)
                            .originalFileName(
                                    file.getOriginalFilename()
                            )
                            .storedFileName(
                                    extractFileName(storageKey)
                            )
                            .storageKey(storageKey)
                            .contentType(
                                    file.getContentType()
                            )
                            .fileSize(
                                    file.getSize()
                            )
                            .uploadedByUserId(userId)
                            .build();

            TicketAttachment savedAttachment =
                    attachmentRepository.save(
                            attachment
                    );

            return toResponse(
                    savedAttachment
            );

        } catch (RuntimeException e) {

            if (storageKey != null) {

                try {

                    fileStorageService.delete(
                            storageKey
                    );

                } catch (RuntimeException cleanupException) {

                    log.error(
                            "Could not cleanup attachment after database failure. storageKey={}",
                            storageKey,
                            cleanupException
                    );
                }
            }

            throw e;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<TicketAttachmentResponseDto>
    getClinicAttachments(Long ticketId) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        ticketRepository
                .findByIdAndClinicIdAndDeletedFalse(
                        ticketId,
                        clinicId
                )
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.USER_NOT_FOUND
                        )
                );

        return attachmentRepository
                .findAllByTicketId(ticketId)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<TicketAttachmentResponseDto>
    getSupportAttachments(Long ticketId) {

        ticketRepository
                .findByIdAndDeletedFalse(ticketId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.USER_NOT_FOUND
                        )
                );

        return attachmentRepository
                .findAllByTicketId(ticketId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadForClinic(
            Long ticketId,
            Long attachmentId) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        TicketAttachment attachment =
                attachmentRepository
                        .findByIdAndTicketIdAndClinicId(
                                attachmentId,
                                ticketId,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        return fileStorageService.load(
                attachment.getStorageKey()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadForSupport(
            Long ticketId,
            Long attachmentId) {

        TicketAttachment attachment =
                attachmentRepository
                        .findByIdAndTicketId(
                                attachmentId,
                                ticketId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        return fileStorageService.load(
                attachment.getStorageKey()
        );
    }

    @Override
    @Transactional
    public void deleteForClinic(
            Long ticketId,
            Long attachmentId) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        TicketAttachment attachment =
                attachmentRepository
                        .findByIdAndTicketIdAndClinicId(
                                attachmentId,
                                ticketId,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        /*
         * فعلاً DB را حذف می‌کنیم.
         * فایل فیزیکی را بعد از موفقیت Transaction
         * پاک می‌کنیم.
         */
        String storageKey =
                attachment.getStorageKey();

        attachmentRepository.delete(
                attachment
        );

        /*
         * اینجا هنوز داخل Transaction هستیم.
         *
         * برای نسخه فعلی می‌توانیم cleanup را انجام دهیم،
         * ولی اگر Storage مشکل داشت نباید حذف DB را
         * خراب کنیم.
         */
        try {

            fileStorageService.delete(
                    storageKey
            );

        } catch (RuntimeException e) {

            log.error(
                    "Attachment DB record deleted but physical file cleanup failed. storageKey={}",
                    storageKey,
                    e
            );
        }
    }

    @Override
    @Transactional
    public void deleteForSupport(
            Long ticketId,
            Long attachmentId) {

        TicketAttachment attachment =
                attachmentRepository
                        .findByIdAndTicketId(
                                attachmentId,
                                ticketId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        String storageKey =
                attachment.getStorageKey();

        attachmentRepository.delete(
                attachment
        );

        try {

            fileStorageService.delete(
                    storageKey
            );

        } catch (RuntimeException e) {

            log.error(
                    "Attachment DB record deleted but physical file cleanup failed. storageKey={}",
                    storageKey,
                    e
            );
        }
    }

    private void validateFile(
            MultipartFile file) {

        if (file == null || file.isEmpty()) {

            throw new IllegalArgumentException(
                    "Attachment file cannot be empty"
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {

            throw new IllegalArgumentException(
                    "Attachment size cannot exceed 10 MB"
            );
        }

        String contentType =
                file.getContentType();

        if (contentType == null
                || !ALLOWED_CONTENT_TYPES
                .contains(contentType)) {

            throw new IllegalArgumentException(
                    "File type is not allowed"
            );
        }
    }


    private void validateTicketCanReceiveAttachment(Ticket ticket) {

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException(
                    "Closed ticket cannot receive attachments"
            );
        }
    }


    private String extractFileName(String storageKey) {
        int index = storageKey.lastIndexOf('/');

        if (index < 0) {
            return storageKey;
        }

        return storageKey.substring(
                index + 1
        );
    }


    private JwtAuthentication getAuthentication() {

        return (JwtAuthentication)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
    }


    private TicketAttachmentResponseDto toResponse(
            TicketAttachment attachment) {

        return TicketAttachmentResponseDto.builder()
                .id(attachment.getId())
                .originalFileName(
                        attachment.getOriginalFileName()
                )
                .contentType(
                        attachment.getContentType()
                )
                .fileSize(
                        attachment.getFileSize()
                )
                .uploadedByUserId(
                        attachment.getUploadedByUserId()
                )
                .createdAt(
                        attachment.getCreatedAt()
                )
                .build();
    }
}