package com.saba.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieNetworkModel(
    @SerialName("movie_title")
    val movieTitle: String?,
    @SerialName("movie_title_en")
    val movieTitleEn: String?,
    @SerialName("uuid")
    val id: String
)