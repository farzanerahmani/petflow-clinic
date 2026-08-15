package com.roochi.petflowinventory.supplier.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */


@Entity
@Table(
        name = "supplier",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "code"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier extends SoftDeleteEntity {

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 100)
    private String contactPerson;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String email;

    @Column(length = 300)
    private String address;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active;

}
