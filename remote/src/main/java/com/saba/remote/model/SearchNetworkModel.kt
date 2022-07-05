package com.saba.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchNetworkModel(
    @SerialName("data")
    val data: List<MovieNetworkModel>?
)