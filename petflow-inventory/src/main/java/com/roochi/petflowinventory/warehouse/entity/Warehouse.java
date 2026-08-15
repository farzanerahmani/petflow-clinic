package com.roochi.petflowinventory.warehouse.entity;


import com.roochi.petflowshared.entity.ClinicSoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/26/2026
 */


@Entity
@Table(
        name = "warehouse",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "code"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse extends ClinicSoftDeleteEntity {

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean defaultWarehouse;

}
