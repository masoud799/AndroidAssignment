package com.saba.data.model

data class MovieDataModel(
    val movieTitle: String?,
    val movie_title_en: String?,
    val images: ImageNetworkModel?,
    val id: String
) {
    data class ImageNetworkModel(
        val smallImage: String?
    )
}