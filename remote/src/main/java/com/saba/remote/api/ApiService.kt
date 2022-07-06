package com.saba.remote.api

import com.saba.remote.model.SearchNetworkModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The main services that handles all endpoint processes
 */
interface ApiService {

    /**
     * Get search result
     */
    @GET("api/en/v1/movie/movie/list/tagid/1000300/text/{Query}/sug/on")
    suspend fun search(@Path("Query") query: String): SearchNetworkModel

}