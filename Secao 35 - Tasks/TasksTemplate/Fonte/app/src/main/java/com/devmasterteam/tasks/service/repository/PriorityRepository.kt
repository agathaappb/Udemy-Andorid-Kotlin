package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class PriorityRepository(val context: Context) {

    val remote = RetrofitClient.getService(PersonService::class.java)

    fun list(){

    }
}