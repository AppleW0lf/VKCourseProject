package com.example.vkcourseproject.presentation

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.sampleApps

class AppDetailsRepository {
    fun getApps(): List<AppItem> {
        return sampleApps;
    }
}