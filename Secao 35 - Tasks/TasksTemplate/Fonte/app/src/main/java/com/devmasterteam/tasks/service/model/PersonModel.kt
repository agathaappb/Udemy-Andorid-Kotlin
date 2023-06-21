package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    @SerializedName("token")
    var token: String,
    @SerializedName("personKey")
    var personKey: String,
    @SerializedName("name")
    var name: String
)
