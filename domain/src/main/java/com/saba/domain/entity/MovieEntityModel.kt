package com.saba.domain.entity

data class MovieEntityModel(
    val movieTitle: String?,
    val movie_title_en: String?,
    val images: ImageNetworkModel?,
    val id: String
) {
    data class ImageNetworkModel(
        val smallImage: String?
    )
}