package com.roochi.petflowclinic.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@AllArgsConstructor
public enum ContactType {
    MOBILE("Mobile"),
    PHONE("Phone"),
    EMAIL("Email"),
    WEBSITE("Website"),
    WHATSAPP("Whatsapp"),
    TELEGRAM("Telegram"),
    INSTAGRAM("Instagram"),
    LINKEDIN("Linkedin"),
    OTHER("Other");
    private final String title;
}
