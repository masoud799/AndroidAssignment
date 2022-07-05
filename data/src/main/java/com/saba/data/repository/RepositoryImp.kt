package com.saba.data.repository

import com.saba.common.Mapper
import com.saba.common.Resource
import com.saba.data.model.SearchDataModel
import com.saba.domain.entity.SearchEntityModel
import com.saba.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val searchMapper : Mapper<SearchDataModel, SearchEntityModel>
) : Repository {

    override suspend fun search(query: String): Flow<Resource<SearchEntityModel>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.search(query = query)
                // Emit data
                emit(Resource.Success(searchMapper.from(data)))
            } catch (ex : Exception) {
                // Emit error
                emit(Resource.Error(ex))
            }
        }
    }
}