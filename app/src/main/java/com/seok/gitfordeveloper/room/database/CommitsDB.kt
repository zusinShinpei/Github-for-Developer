package com.seok.gitfordeveloper.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.seok.gitfordeveloper.room.dao.CommitsDao
import com.seok.gitfordeveloper.room.model.Commits

@Database(entities = [Commits::class], version = 4)
abstract class CommitsDB: RoomDatabase() {
    abstract fun commitDao(): CommitsDao
    companion object {
        private var INSTANCE: CommitsDB? = null
        fun getInstance(context: Context): CommitsDB? {
            if (INSTANCE == null) {
                synchronized(CommitsDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CommitsDB::class.java, "commits.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}