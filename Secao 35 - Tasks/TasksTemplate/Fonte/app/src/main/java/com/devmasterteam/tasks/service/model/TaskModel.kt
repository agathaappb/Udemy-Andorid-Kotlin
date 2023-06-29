package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName

data class TaskModel(
    @SerializedName("Id")
    var id: Int,
    @SerializedName("PriorityId")
    var priority:Int,
    @SerializedName("Description")
    var description: String,
    @SerializedName("DueDate")
    var date: String,
    @SerializedName("Complete")
    var status: Boolean
)