package com.roochi.petflowvisit.labresult.entity;


import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Entity
@Table(name = "lab_result_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResultItem extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_result_id", nullable = false)
    private LabResult labResult;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_test_parameter_id", nullable = false)
    private LabTestParameter labTestParameter;

    @Column(nullable = false, length = 100)
    private String resultValue;

    @Column(length = 20)
    private String flag;

    @Column(length = 500)
    private String note;
}
