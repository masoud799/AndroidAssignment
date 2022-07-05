package com.saba.data.mapper


import com.saba.common.Mapper
import com.saba.data.model.SearchDataModel
import com.saba.domain.entity.SearchEntityModel
import javax.inject.Inject

/**
 * Mapper class for convert [SearchDataModel] to [SearchEntityModel] and vice versa
 */
class SearchDataDomainMapper @Inject constructor() : Mapper<SearchDataModel, SearchEntityModel> {

    override fun from(i: SearchDataModel?): SearchEntityModel {
        return SearchEntityModel(
            data = List(i?.data?.size ?: 0) { index ->
                MovieDataDomainMapper().from(i?.data?.get(index))
            }
        )
    }

    override fun to(o: SearchEntityModel?): SearchDataModel {
        return SearchDataModel(
            data = List(o?.data?.size ?: 0) { index ->
                MovieDataDomainMapper().to(o?.data?.get(index))
            }
        )
    }
}