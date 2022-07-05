package com.saba.presentation.mapper

import com.saba.common.Mapper
import com.saba.domain.entity.SearchEntityModel
import com.saba.presentation.model.SearchUiModel
import javax.inject.Inject

/**
 * Mapper class for convert [SearchEntityModel] to [SearchUiModel] and vice versa
 */
class SearchDomainUiMapper @Inject constructor() : Mapper<SearchEntityModel, SearchUiModel> {

    override fun from(i: SearchEntityModel?): SearchUiModel {
        return SearchUiModel(
            data = List(i?.data?.size ?: 0) { index ->
                MovieDomainUiMapper().from(i?.data?.get(index))
            }
        )
    }

    override fun to(o: SearchUiModel?): SearchEntityModel {
        return SearchEntityModel(
            data = List(o?.data?.size ?: 0) { index ->
                MovieDomainUiMapper().to(o?.data?.get(index))
            }
        )
    }
}