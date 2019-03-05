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

        var db: AppDataBase? = null
        var dbInstance: SongInfoDao? = null

        fun getInstance(context: Context): SongInfoDao? {
            if (db == null){
                db = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB")
                        .allowMainThreadQueries()
                        .build()
                dbInstance = db?.songInfoDao()
            }

            return dbInstance!!
        }

        fun destroyDataBase(){
            db = null
        }
    }
}