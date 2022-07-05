package com.saba.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieNetworkModel(
    @SerialName("movie_title")
    val movieTitle: String?,
    @SerialName("movie_title_en")
    val movie_title_en: String?,
    @SerialName("pic")
    val images: ImageNetworkModel?,
    @SerialName("uuid")
    val id: String
){
    @Serializable
    data class ImageNetworkModel(
        @SerialName("movie_img_s")
        val smallImage: String?
    )
}