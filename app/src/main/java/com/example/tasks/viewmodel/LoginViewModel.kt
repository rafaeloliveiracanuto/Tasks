package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.PersonRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository()

    private val mLogin = MutableLiveData<Boolean>()
    var login: LiveData<Boolean> = mLogin

    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email, password, object : APIListener {
            override fun onSuccess(model: HeaderModel) {
                mLogin.value = true
            }

            override fun onFailure(str: String) {
                mLogin.value = false
            }

        })
    }

    fun verifyLoggedUser() {
    }

}