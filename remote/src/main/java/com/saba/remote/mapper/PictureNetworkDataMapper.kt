package com.saba.remote.mapper


import com.saba.common.Mapper
import com.saba.data.model.PictureDataModel
import com.saba.remote.model.PictureNetworkModel
import javax.inject.Inject

/**
 * Mapper class for convert [PictureNetworkModel] to [PictureDataModel] and vice versa
 */
class PictureNetworkDataMapper @Inject constructor() : Mapper<PictureNetworkModel, PictureDataModel> {

    override fun from(i: PictureNetworkModel?): PictureDataModel {
        return PictureDataModel(
            smallImage = i?.smallImage
        )
    }

    override fun to(o: PictureDataModel?): PictureNetworkModel {
        return PictureNetworkModel(
            smallImage = o?.smallImage
        )
    }
}