package com.dirzaaulia.pokeapi.domain.di

import com.dirzaaulia.pokeapi.domain.repository.ServiceRepository
import com.dirzaaulia.pokeapi.domain.usecase.ServiceUseCase
import com.dirzaaulia.pokeapi.domain.usecase.ServiceUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideServiceUseCase(repository: ServiceRepository): ServiceUseCase {
    return ServiceUseCaseImpl(repository)
  }
}