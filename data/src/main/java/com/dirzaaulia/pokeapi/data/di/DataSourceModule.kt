package com.dirzaaulia.pokeapi.data.di

import com.dirzaaulia.pokeapi.data.database.DatabaseDao
import com.dirzaaulia.pokeapi.data.di.qualifier.LocalDataSource
import com.dirzaaulia.pokeapi.data.di.qualifier.RemoteDataSource
import com.dirzaaulia.pokeapi.data.service.Service
import com.dirzaaulia.pokeapi.data.source.ServiceDataSource
import com.dirzaaulia.pokeapi.data.source.ServiceLocalDataSourceImpl
import com.dirzaaulia.pokeapi.data.source.ServiceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

  @Provides
  @Singleton
  @LocalDataSource
  fun provideServiceLocalDataSource(
    databaseDao: DatabaseDao
  ): ServiceDataSource {
    return ServiceLocalDataSourceImpl(databaseDao)
  }

  @Provides
  @Singleton
  @RemoteDataSource
  fun provideServiceRemoteDataSource(
    service: Service
  ): ServiceDataSource {
    return ServiceRemoteDataSourceImpl(service)
  }
}