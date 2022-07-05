package com.saba.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.saba.data.repository.RemoteDataSource
import com.saba.remote.api.ApiService
import com.saba.remote.mapper.SearchNetworkDataMapper
import com.saba.remote.source.RemoteDataSourceImp
import com.saba.remote.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService : ApiService
    private val searchNetworkDataMapper = SearchNetworkDataMapper()

    private lateinit var remoteDataSource : RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            searchMapper = searchNetworkDataMapper
        )
    }

    @Test
    fun test_get_search_result_success() = runBlockingTest {

        val searchResultNetwork = TestDataGenerator.generateSearchResult()

        // Given
        coEvery { apiService.search("123") } returns searchResultNetwork

        // When
        val result = remoteDataSource.search("123")

        // Then
        coVerify { apiService.search("123") }

        // Assertion
        val expected = searchNetworkDataMapper.from(searchResultNetwork)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_search_result_fail() = runBlockingTest {

        // Given
        coEvery { apiService.search(any()) } throws Exception()

        // When
        remoteDataSource.search("")

        // Then
        coVerify { apiService.search("") }

    }
}