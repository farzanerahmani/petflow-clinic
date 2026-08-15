package com.roochi.petflowvisit.labresult.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */


@Entity
@Table(name = "lab_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResult extends SoftDeleteEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_request_id", nullable = false, unique = true)
    private LabRequest labRequest;

    @Column(nullable = false)
    private LocalDate resultDate;

    @Lob
    private String report;

    @Column(length = 500)
    private String attachmentPath;

    @Column(length = 1000)
    private String note;

    @OneToMany(mappedBy = "labResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<LabResultItem> items = new ArrayList<>();
}