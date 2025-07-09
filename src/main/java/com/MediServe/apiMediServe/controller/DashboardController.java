package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.dashboardDTO.DashboardDTO;
import com.MediServe.apiMediServe.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/dashboard")
public class DashboardController {

    private final DashboardService  dashboardService;

    @GetMapping
    public DashboardDTO getDashboard(){
        return dashboardService.getDashboardData();
    }

}
