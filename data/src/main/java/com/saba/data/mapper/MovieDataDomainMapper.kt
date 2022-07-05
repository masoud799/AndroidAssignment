package com.saba.data.mapper


import com.saba.common.Mapper
import com.saba.data.model.MovieDataModel
import com.saba.domain.entity.MovieEntityModel
import javax.inject.Inject

/**
 * Mapper class for convert [MovieDataModel] to [MovieEntityModel] and vice versa
 */
class MovieDataDomainMapper @Inject constructor() : Mapper<MovieDataModel, MovieEntityModel> {

    override fun from(i: MovieDataModel?): MovieEntityModel {
        return MovieEntityModel(
            movieTitle = i?.movieTitle,
            movieTitleEn = i?.movieTitleEn,
            images = PictureDataDomainMapper().from(i?.images),
            id = i?.id!!
        )
    }

    override fun to(o: MovieEntityModel?): MovieDataModel {
        return MovieDataModel(
            movieTitle = o?.movieTitle,
            movieTitleEn = o?.movieTitleEn,
            images = PictureDataDomainMapper().to(o?.images),
            id = o?.id!!
        )
    }
}