package com.dirzaaulia.pokeapi.data.di

import android.content.Context
import com.dirzaaulia.pokeapi.data.database.AppDatabase
import com.dirzaaulia.pokeapi.data.database.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Singleton
  @Provides
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return AppDatabase.getInstance(context)
  }

  @Provides
  fun provideDatabaseDao(appDatabase: AppDatabase): DatabaseDao {
    return appDatabase.databaseDao()
  }
}