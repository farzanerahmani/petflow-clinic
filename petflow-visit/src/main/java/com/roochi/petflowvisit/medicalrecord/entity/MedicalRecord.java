package com.roochi.petflowvisit.medicalrecord.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Entity
@Table(name = "medical_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecord  extends AuditingEntity {

    @Column(nullable = false)
    private Long clinicId;

    @Column(nullable = false)
    private Long visitId;

    @Lob
    private String history;//سابفه بیماری

    @Lob
    private String clinicalFinding;//نتیجه معاینه

    @Lob
    private String diagnosis;//تشخیص

    @Lob
    private String treatmentPlan;//برنامه درمان

    @Lob
    private String recommendation; // توصیه ها

    @Lob
    private String note;//یادداشت اضافه
}
