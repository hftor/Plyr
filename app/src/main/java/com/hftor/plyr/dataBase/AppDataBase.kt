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

        var TEST_MODE = false

        var db: AppDataBase? = null
        var dbInstance: SongInfoDao? = null

        fun getInstance(context: Context): SongInfoDao? {
            if (db == null){
                if(TEST_MODE){
                    db = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDataBase::class.java)
                            .allowMainThreadQueries()
                            .build()
                    dbInstance = db?.songInfoDao()
                }
                else{
                    db = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB")
                            .build()
                    dbInstance = db?.songInfoDao()
                }
            }

            return dbInstance!!
        }

        fun destroyDataBase(){
            db = null
        }
    }
}