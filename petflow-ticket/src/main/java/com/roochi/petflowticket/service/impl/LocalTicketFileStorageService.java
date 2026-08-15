package com.roochi.petflowticket.service.impl;

import com.roochi.petflowticket.service.TicketFileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Service
@Slf4j
public class LocalTicketFileStorageService
        implements TicketFileStorageService {

    private final Path rootLocation;

    public LocalTicketFileStorageService(
            @Value("${petflow.ticket.storage.location:./uploads/tickets}")
            String storageLocation) {

        this.rootLocation =
                Paths.get(storageLocation)
                        .toAbsolutePath()
                        .normalize();

        try {

            Files.createDirectories(
                    rootLocation
            );

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Could not initialize ticket file storage",
                    e
            );
        }
    }


    @Override
    public String store(
            Long ticketId,
            MultipartFile file) {

        if (file == null || file.isEmpty()) {

            throw new IllegalArgumentException(
                    "File cannot be empty"
            );
        }

        String originalFileName =
                StringUtils.cleanPath(
                        file.getOriginalFilename() == null
                                ? "file"
                                : file.getOriginalFilename()
                );

        String extension =
                extractExtension(
                        originalFileName
                );

        String storedFileName =
                UUID.randomUUID() + extension;

        Path ticketDirectory =
                rootLocation
                        .resolve(
                                String.valueOf(ticketId)
                        )
                        .normalize();

        try {

            Files.createDirectories(
                    ticketDirectory
            );

            Path target =
                    ticketDirectory
                            .resolve(storedFileName)
                            .normalize();

            if (!target.startsWith(ticketDirectory)) {

                throw new SecurityException(
                        "Invalid file path"
                );
            }

            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return ticketId
                    + "/"
                    + storedFileName;

        } catch (IOException e) {

            log.error(
                    "Could not store ticket attachment",
                    e
            );

            throw new IllegalStateException(
                    "Could not store ticket attachment",
                    e
            );
        }
    }


    @Override
    public Resource load(
            String storageKey) {

        try {

            Path file =
                    resolveStoragePath(
                            storageKey
                    );

            Resource resource =
                    new UrlResource(
                            file.toUri()
                    );

            if (!resource.exists()
                    || !resource.isReadable()) {

                throw new IllegalStateException(
                        "File does not exist or is not readable"
                );
            }

            return resource;

        } catch (MalformedURLException e) {

            throw new IllegalStateException(
                    "Could not load ticket attachment",
                    e
            );
        }
    }


    @Override
    public void delete(
            String storageKey) {

        if (!StringUtils.hasText(storageKey)) {
            return;
        }

        try {

            Path target =
                    resolveStoragePath(
                            storageKey
                    );

            Files.deleteIfExists(
                    target
            );

        } catch (IOException e) {

            log.error(
                    "Could not delete ticket attachment. storageKey={}",
                    storageKey,
                    e
            );

            throw new IllegalStateException(
                    "Could not delete ticket attachment",
                    e
            );
        }
    }


    @Override
    public List<String> listStorageKeys() {

        List<String> storageKeys =
                new ArrayList<>();

        if (!Files.exists(rootLocation)) {
            return storageKeys;
        }

        try (Stream<Path> paths =
                     Files.walk(rootLocation)) {

            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {

                        Path relativePath =
                                rootLocation.relativize(
                                        path
                                );

                        storageKeys.add(
                                relativePath
                                        .toString()
                                        .replace(
                                                FileSystems
                                                        .getDefault()
                                                        .getSeparator(),
                                                "/"
                                        )
                        );
                    });

        } catch (IOException e) {

            log.error(
                    "Could not list ticket storage files",
                    e
            );

            throw new IllegalStateException(
                    "Could not list ticket storage files",
                    e
            );
        }

        return storageKeys;
    }


    private Path resolveStoragePath(
            String storageKey) {

        if (!StringUtils.hasText(storageKey)) {

            throw new IllegalArgumentException(
                    "Storage key cannot be empty"
            );
        }

        Path resolved =
                rootLocation
                        .resolve(storageKey)
                        .normalize();

        if (!resolved.startsWith(rootLocation)) {

            throw new SecurityException(
                    "Invalid storage key"
            );
        }

        return resolved;
    }


    private String extractExtension(
            String fileName) {

        int index =
                fileName.lastIndexOf('.');

        if (index < 0) {
            return "";
        }

        return fileName
                .substring(index)
                .toLowerCase();
    }
}