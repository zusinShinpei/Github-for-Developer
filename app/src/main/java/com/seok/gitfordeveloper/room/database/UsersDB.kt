package com.seok.gitfordeveloper.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.seok.gitfordeveloper.room.dao.UserDao
import com.seok.gitfordeveloper.room.dao.UserInfoDao
import com.seok.gitfordeveloper.room.model.User
import com.seok.gitfordeveloper.room.model.UserInfo

@Database(entities = [User::class, UserInfo::class], version = 5)
abstract class UsersDB: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userInfoDao(): UserInfoDao
    companion object {
        private var INSTANCE: UsersDB? = null
        fun getInstance(context: Context): UsersDB? {
            if (INSTANCE == null) {
                synchronized(UsersDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UsersDB::class.java, "user.db")
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