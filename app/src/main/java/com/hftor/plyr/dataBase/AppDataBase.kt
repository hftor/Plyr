package com.hftor.plyr.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hftor.plyr.dao.SongInfoDao
import com.hftor.plyr.entity.SongInfo

@Database(entities = [SongInfo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun songInfoDao() : SongInfoDao

    companion object {
        var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}