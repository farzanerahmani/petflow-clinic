package com.roochi.petflowvisit.imaging.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Entity
@Table(name = "imaging_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagingRequest extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "imaging_service_id", nullable = false)
    private ImagingService imagingService;

    /**
     * Date the imaging was requested.
     */
    @Column(nullable = false)
    private LocalDate requestDate;

    /**
     * Clinical reason for requesting imaging.
     */
    @Column(length = 1000)
    private String indication;

    /**
     * Additional notes for the radiologist/operator.
     */
    @Column(length = 1000)
    private String note;


    @OneToOne(
            mappedBy = "imagingRequest",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private ImagingResult imagingResult;
}
