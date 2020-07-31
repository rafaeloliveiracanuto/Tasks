package com.example.tasks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.button_save
import kotlinx.android.synthetic.main.activity_task_form.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        listeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = edit_name.text.toString()
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            mViewModel.create(name, email, password)
        } else if (id == R.id.button_save) {
            showDatePicker()
        }
    }

    private fun observe() {
    }

    private fun listeners() {
        button_save.setOnClickListener(this)
        button_date.setOnClickListener(this)
    }

}
