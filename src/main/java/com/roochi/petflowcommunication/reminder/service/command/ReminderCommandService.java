package com.roochi.petflowcommunication.reminder.service.command;

import com.roochi.petflowcommunication.reminder.dto.request.CreateReminderRequestDto;
import com.roochi.petflowcommunication.reminder.dto.request.UpdateReminderRequestDto;
import com.roochi.petflowcommunication.reminder.dto.response.ReminderResponseDto;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


public interface ReminderCommandService {

    ReminderResponseDto create(CreateReminderRequestDto request);

    ReminderResponseDto update(UpdateReminderRequestDto request);

    void cancel(Long id);
}
