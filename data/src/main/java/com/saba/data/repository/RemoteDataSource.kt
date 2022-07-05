package com.saba.data.repository

import com.saba.data.model.SearchDataModel

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun search(query : String) : SearchDataModel

}