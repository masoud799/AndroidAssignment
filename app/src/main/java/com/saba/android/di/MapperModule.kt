package com.saba.android.di


import com.saba.common.Mapper
import com.saba.data.mapper.MovieDataDomainMapper
import com.saba.data.mapper.SearchDataDomainMapper
import com.saba.data.model.MovieDataModel
import com.saba.data.model.SearchDataModel
import com.saba.domain.entity.MovieEntityModel
import com.saba.domain.entity.SearchEntityModel
import com.saba.presentation.mapper.MovieDomainUiMapper
import com.saba.presentation.mapper.SearchDomainUiMapper
import com.saba.presentation.model.MovieUiModel
import com.saba.presentation.model.SearchUiModel
import com.saba.remote.mapper.MovieNetworkDataMapper
import com.saba.remote.mapper.SearchNetworkDataMapper
import com.saba.remote.model.MovieNetworkModel
import com.saba.remote.model.SearchNetworkModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Remote Mappers
    @Binds
    abstract fun bindsSearchNetworkDataMapper(mapper: SearchNetworkDataMapper): Mapper<SearchNetworkModel, SearchDataModel>

    @Binds
    abstract fun bindsMovieNetworkDataMapper(mapper: MovieNetworkDataMapper): Mapper<MovieNetworkModel, MovieDataModel>
    //endregion

    //region Data Mappers
    @Binds
    abstract fun bindsSearchDataDomainMapper(mapper : SearchDataDomainMapper) : Mapper<SearchDataModel, SearchEntityModel>

    @Binds
    abstract fun bindsMovieDataDomainMapper(mapper : MovieDataDomainMapper) : Mapper<MovieDataModel, MovieEntityModel>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsSearchDomainUiMapper(mapper : SearchDomainUiMapper) : Mapper<SearchEntityModel, SearchUiModel>

    @Binds
    abstract fun bindsMovieDomainUiMapper(mapper : MovieDomainUiMapper) : Mapper<MovieEntityModel, MovieUiModel>
    //endregion

}