package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.SchedulingDTO;
import com.MediServe.apiMediServe.model.Scheduling;
import com.MediServe.apiMediServe.service.SchedulingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchedulingDTO createScheduling(@RequestBody SchedulingDTO schedulingDTO){
        return schedulingService.createScheduling(schedulingDTO);
    }
}