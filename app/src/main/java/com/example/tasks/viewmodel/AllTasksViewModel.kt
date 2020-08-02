package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.TaskRepository

class AllTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val mTaskRepository = TaskRepository(application)

    private val mList = MutableLiveData<List<TaskModel>>()
    var tasks: LiveData<List<TaskModel>> = mList

    fun list() {
        mTaskRepository.all(object : APIListener<List<TaskModel>> {
            override fun onSuccess(model: List<TaskModel>) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
            }

        })
    }

    fun complete(id: Int) {
        mTaskRepository.updateStatus(id, true, object : APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                list()
            }

            override fun onFailure(str: String) {
                TODO("Not yet implemented")
            }

        })
    }

    fun undo(id: Int) {
        mTaskRepository.updateStatus(id, false, object : APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onFailure(str: String) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun updateStatus() {
        
    }

}