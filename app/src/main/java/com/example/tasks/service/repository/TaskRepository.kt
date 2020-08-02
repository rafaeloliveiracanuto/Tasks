package com.example.tasks.service.repository

import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.repository.remote.TaskService

class TaskRepository {

    private val mRemote = RetrofitClient.createService(TaskService::class.java)

    fun create(task: TaskModel) {
        mRemote.create()
    }
}