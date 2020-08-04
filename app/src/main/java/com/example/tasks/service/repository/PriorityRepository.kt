package com.example.tasks.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.repository.local.TaskDatabase
import com.example.tasks.service.repository.remote.PriorityService
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(PriorityService::class.java)
    private val mPriorityDatabase = TaskDatabase.getDatabase(context).priorityDAO()

    fun all() {
        val call: Call<List<PriorityModel>> = mRemote.list()
        call.enqueue(object : Callback<List<PriorityModel>> {
            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<PriorityModel>>, response: Response<List<PriorityModel>>) {
                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    mPriorityDatabase.clear()
                    response.body()?.let {
                        mPriorityDatabase.save(it)
                    }
                }
            }

        })
    }

    fun list() = mPriorityDatabase.list()

    fun getDescription(id: Int) = mPriorityDatabase.getDescription(id)

    fun isConnectionAvailable(context: Context): Boolean {

        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false

            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }
}