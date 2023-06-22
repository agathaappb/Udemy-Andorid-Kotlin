package com.devmasterteam.tasks.service.model

class ValidationModel(var message:String = "") {

    private var status: Boolean = true
    private var validateMessage = ""

    init {
        if (message != ""){
            validateMessage = message
            status = false
        }else{
            validateMessage = message
            status = true
        }


    }

    fun status() = status
    fun message() = validateMessage
}