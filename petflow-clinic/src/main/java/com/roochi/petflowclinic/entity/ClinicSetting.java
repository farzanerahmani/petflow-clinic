package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "clinic_setting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicSetting extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id",nullable = false,unique = true)
    private Clinic clinic;

    @Column(nullable = false,length = 100)
    private String key;

    @Column(length = 2000)
    private String value;

//    @Column(length = 100)
////    private String timeZone="Asia/Tehran";;
////
////    @Column(length = 10)
////    private String language="fa";
////
////    @Column(length = 10)
////    private String currency="IRR";
////
////    @Column(length = 50)
////    private String dateFormat ="yyyy-MM-dd";
////
////    @Column(length = 50)
////    private String timeFormat="HH:mm";
////
////    @Column(length = 50)
////    private String  logo;
////
////    @Column(length = 500)
////    private String favicon;
////
////    @Column(length = 500)
////    private String website;
////
////    @Column(length = 100)
////    private String email;
////
////    @Column(length = 20)
////    private String phone;
}
