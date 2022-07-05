package com.saba.remote.mapper


import com.saba.common.Mapper
import com.saba.data.model.SearchDataModel
import com.saba.remote.model.SearchNetworkModel
import javax.inject.Inject

/**
 * Mapper class for convert [SearchNetworkModel] to [SearchDataModel] and vice versa
 */
class SearchNetworkDataMapper @Inject constructor() : Mapper<SearchNetworkModel, SearchDataModel> {

    override fun from(i: SearchNetworkModel?): SearchDataModel {
        return SearchDataModel(
            data = List(i?.data?.size ?: 0) { index ->
                MovieNetworkDataMapper().from(i?.data?.get(index))
            }
        )
    }

    override fun to(o: SearchDataModel?): SearchNetworkModel {
        return SearchNetworkModel(
            data = List(o?.data?.size ?: 0) { index ->
                MovieNetworkDataMapper().to(o?.data?.get(index))
            }
        )
    }
}