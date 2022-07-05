package com.saba.domain.usecase


import com.saba.common.Resource
import com.saba.domain.entity.SearchEntityModel
import com.saba.domain.qualifiers.IoDispatcher
import com.saba.domain.repository.Repository
import com.saba.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get search result
 */
class SearchUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<SearchEntityModel, String>() {

    override suspend fun buildRequest(params: String?): Flow<Resource<SearchEntityModel>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("query can not be null")))
            }.flowOn(dispatcher)
        }
        return repository.search(query = params).flowOn(dispatcher)
    }
}