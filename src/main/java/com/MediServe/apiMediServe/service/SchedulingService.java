package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.SchedulingDTO;
import com.MediServe.apiMediServe.model.Scheduling;

public interface SchedulingService {
    SchedulingDTO createScheduling(SchedulingDTO schedulingDTO);
}
