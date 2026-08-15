package com.roochi.petflowinventory.inventoryalert.service;

import com.roochi.petflowinventory.inventoryalert.dto.InventoryAlertDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */

public interface InventoryAlertService {

    List<InventoryAlertDto> getAlerts();

}

