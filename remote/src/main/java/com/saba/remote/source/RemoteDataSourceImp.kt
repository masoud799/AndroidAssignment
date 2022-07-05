package com.saba.remote.source

import com.saba.common.Mapper
import com.saba.data.model.SearchDataModel
import com.saba.data.repository.RemoteDataSource
import com.saba.remote.api.ApiService
import com.saba.remote.model.SearchNetworkModel
import javax.inject.Inject

/**
 * Implementation of [RemoteDataSource] class
 */
class RemoteDataSourceImp @Inject constructor(
    private val apiService : ApiService,
    private val searchMapper : Mapper<SearchNetworkModel, SearchDataModel>
    ) : RemoteDataSource {
    override suspend fun search(query: String): SearchDataModel {
        val networkData = apiService.search(query = query)
        return searchMapper.from(networkData)
    }
}