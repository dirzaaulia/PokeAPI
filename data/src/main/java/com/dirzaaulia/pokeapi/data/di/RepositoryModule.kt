package com.dirzaaulia.pokeapi.data.di

import com.dirzaaulia.pokeapi.data.di.qualifier.LocalDataSource
import com.dirzaaulia.pokeapi.data.di.qualifier.RemoteDataSource
import com.dirzaaulia.pokeapi.data.source.ServiceDataSource
import com.dirzaaulia.pokeapi.data.source.ServiceRepositoryImpl
import com.dirzaaulia.pokeapi.domain.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideServiceRepository(
    @LocalDataSource localDataSource: ServiceDataSource,
    @RemoteDataSource remoteDataSource: ServiceDataSource
  ): ServiceRepository {
    return ServiceRepositoryImpl(localDataSource, remoteDataSource)
  }
}