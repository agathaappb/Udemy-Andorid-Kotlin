package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(val context: Context) {

    val remote = RetrofitClient.getService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener<PersonModel>){
       val call = remote.login(email, password)
        call.enqueue(object : Callback<PersonModel>{
            override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                val x = ""
                if(response.code() == TaskConstants.HTTP.SUCCESS){
                    response.body()?.let { listener.onSucess(it) }
                }else{
                    val json = response.errorBody()!!.string()
                    listener.onFailue(convertString(json))
                }

            }

            override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                val x = ""
                listener.onFailue(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }
    private fun convertString(str:String):String = Gson().fromJson(str,String::class.java)
}