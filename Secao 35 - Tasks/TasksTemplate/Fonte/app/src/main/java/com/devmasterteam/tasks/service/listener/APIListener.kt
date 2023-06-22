package com.devmasterteam.tasks.service.listener

import android.graphics.ColorSpace

interface APIListener<T> {

    fun onSucess(model:T)

    fun onFailue(message: String)
}