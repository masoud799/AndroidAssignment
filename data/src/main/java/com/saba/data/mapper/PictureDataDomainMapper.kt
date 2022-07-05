package com.saba.data.mapper


import com.saba.common.Mapper
import com.saba.data.model.PictureDataModel
import com.saba.domain.entity.PictureEntityModel
import javax.inject.Inject

/**
 * Mapper class for convert [PictureDataModel] to [PictureEntityModel] and vice versa
 */
class PictureDataDomainMapper @Inject constructor() : Mapper<PictureDataModel, PictureEntityModel> {

    override fun from(i: PictureDataModel?): PictureEntityModel {
        return PictureEntityModel(
            smallImage = i?.smallImage
        )
    }

    override fun to(o: PictureEntityModel?): PictureDataModel {
        return PictureDataModel(
            smallImage = o?.smallImage
        )
    }
}