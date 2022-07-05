package com.saba.remote.utils

import com.saba.remote.model.MovieNetworkModel
import com.saba.remote.model.SearchNetworkModel

/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateMovies() : List<MovieNetworkModel> {
            val item1 = MovieNetworkModel("title 1", "titleEn 1",null,"1")
            val item2 = MovieNetworkModel("title 2", "titleEn 2",null,"2")
            val item3 = MovieNetworkModel("title 3", "titleEn 3",null,"3")
            return listOf(item1, item2, item3)
        }

        fun generateSearchResult() : SearchNetworkModel {
            return SearchNetworkModel(data = generateMovies())
        }
    }

}