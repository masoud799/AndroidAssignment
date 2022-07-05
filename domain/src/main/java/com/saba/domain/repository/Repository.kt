package com.saba.domain.repository


import com.saba.common.Resource
import com.saba.domain.entity.SearchEntityModel
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {

    suspend fun search(query : String) : Flow<Resource<SearchEntityModel>>

}