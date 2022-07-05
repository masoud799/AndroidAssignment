package com.saba.presentation.mapper

import com.saba.common.Mapper
import com.saba.domain.entity.MovieEntityModel
import com.saba.presentation.model.MovieUiModel
import javax.inject.Inject

/**
 * Mapper class for convert [MovieEntityModel] to [MovieUiModel] and vice versa
 */
class MovieDomainUiMapper @Inject constructor() : Mapper<MovieEntityModel, MovieUiModel> {

    override fun from(i: MovieEntityModel?): MovieUiModel {
        return MovieUiModel(
            movieTitle = i?.movieTitle,
            movieTitleEn = i?.movieTitleEn,
            id = i?.id!!
        )
    }

    override fun to(o: MovieUiModel?): MovieEntityModel {
        return MovieEntityModel(
            movieTitle = o?.movieTitle,
            movieTitleEn = o?.movieTitleEn,
            id = o?.id!!
        )
    }
}