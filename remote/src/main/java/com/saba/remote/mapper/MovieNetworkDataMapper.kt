package com.saba.remote.mapper


import com.saba.common.Mapper
import com.saba.data.model.MovieDataModel
import com.saba.remote.model.MovieNetworkModel
import javax.inject.Inject

/**
 * Mapper class for convert [MovieNetworkModel] to [MovieDataModel] and vice versa
 */
class MovieNetworkDataMapper @Inject constructor() : Mapper<MovieNetworkModel, MovieDataModel> {

    override fun from(i: MovieNetworkModel?): MovieDataModel {
        return MovieDataModel(
            movieTitle = i?.movieTitle,
            movieTitleEn = i?.movieTitleEn,
            id = i?.id!!
        )
    }

    override fun to(o: MovieDataModel?): MovieNetworkModel {
        return MovieNetworkModel(
            movieTitle = o?.movieTitle,
            movieTitleEn = o?.movieTitleEn,
            id = o?.id!!
        )
    }
}