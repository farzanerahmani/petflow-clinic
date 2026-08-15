package com.roochi.petflowvisit.imaging.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Entity
@Table(name = "imaging_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagingResult extends SoftDeleteEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "imaging_request_id",
            nullable = false,
            unique = true
    )
    private ImagingRequest imagingRequest;

    @Column(nullable = false)
    private LocalDate resultDate;

    @Lob
    private String report;

    @Column(length = 500)
    private String attachmentPath;

    @Column(length = 1000)
    private String note;
}
