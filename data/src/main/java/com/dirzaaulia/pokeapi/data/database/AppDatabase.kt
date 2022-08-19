package com.dirzaaulia.pokeapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dirzaaulia.pokeapi.data.util.DATABASE_NAME
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon

@Database(
  entities =[FavoritePokemon::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

  abstract fun databaseDao(): DatabaseDao

  companion object {
    @Volatile
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
      return instance ?: synchronized(this) {
        instance ?: buildDatabase(context).also { instance = it }
      }
    }

    private fun buildDatabase(context: Context): AppDatabase {
      return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
      )
        .fallbackToDestructiveMigration()
        .build()
    }
  }
}