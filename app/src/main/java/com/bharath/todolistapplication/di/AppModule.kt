package com.bharath.todolistapplication.di


import android.app.Application
import androidx.room.Room
import com.bharath.todolistapplication.data.repo.TodoRepositoryImpl
import com.bharath.todolistapplication.data.source.TodoDatabase
import com.bharath.todolistapplication.domain.repo.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @property AppModule provides us the required classes for the application
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /*
     * The method provides the TodoDatabase
     */

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, "todo_db").build()
    }

    /**
     * The method provides the TodoRepository
     */


    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }
}