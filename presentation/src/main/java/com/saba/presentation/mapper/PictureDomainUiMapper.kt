package com.saba.presentation.mapper

import com.saba.common.Mapper
import com.saba.domain.entity.MovieEntityModel
import com.saba.domain.entity.PictureEntityModel
import com.saba.presentation.model.MovieUiModel
import com.saba.presentation.model.PictureUiModel
import javax.inject.Inject

/**
 * Mapper class for convert [MovieEntityModel] to [MovieUiModel] and vice versa
 */
class PictureDomainUiMapper @Inject constructor() : Mapper<PictureEntityModel, PictureUiModel> {

    override fun from(i: PictureEntityModel?): PictureUiModel {
        return PictureUiModel(
            smallImage = i?.smallImage
        )
    }

    override fun to(o: PictureUiModel?): PictureEntityModel {
        return PictureEntityModel(
            smallImage = o?.smallImage
        )
    }
}