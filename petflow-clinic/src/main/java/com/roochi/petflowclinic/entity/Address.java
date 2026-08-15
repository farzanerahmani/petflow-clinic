package com.roochi.petflowclinic.entity;

import jakarta.persistence.Embeddable;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Embeddable
public class Address {

    private String province;
    private String city;
    private String street;
    private String postalCode;
    private Double latitude;
    private Double longitude;
}
