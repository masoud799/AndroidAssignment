package com.saba.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureNetworkModel(
    @SerialName("movie_img_s")
    val smallImage: String?
)