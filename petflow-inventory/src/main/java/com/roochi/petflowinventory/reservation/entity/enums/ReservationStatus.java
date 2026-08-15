package com.roochi.petflowinventory.reservation.entity.enums;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
public enum ReservationStatus {
    ACTIVE,       // ایجاد شده ولی هنوز رزرو نشده

    RESERVED,     // موجودی برای آن کنار گذاشته شده

    COMPLETED,    // دارو تحویل داده شده

    RELEASED,     // رزرو آزاد شده

    CANCELLED
}