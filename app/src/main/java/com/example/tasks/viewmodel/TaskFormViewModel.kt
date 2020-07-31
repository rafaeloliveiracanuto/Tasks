package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tasks.service.repository.PriorityRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mPriorityRepository = PriorityRepository(application)

    fun listPriorities() {
        mPriorityRepository.list()
    }
}