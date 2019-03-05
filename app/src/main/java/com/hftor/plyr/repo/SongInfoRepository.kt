package com.hftor.plyr.repo

import android.content.Context
import com.hftor.plyr.dao.SongInfoDao
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.entity.SongInfo
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SongInfoRepository(private val context: Context) {


    private var db: AppDataBase? = null
    private var songInfoDao: SongInfoDao? = null

    init {
        db = AppDataBase.getAppDataBase(context)
        songInfoDao = db?.songInfoDao()
    }

    fun saveSongsState(songToSave : MusicFinder.Song?, songPosition : Int?){
        if(songToSave == null || songPosition == null || songPosition == 0){
            return
        }

        GlobalScope.launch {
            var s1 = SongInfo(songToSave.id, songPosition)
            songInfoDao?.insertSongInfo(s1)
        }
    }

    suspend fun getSongPosition(songId: Long) : Int{
        var songPosDeferred = GlobalScope.async {
            songInfoDao?.getSongInfo(songId)?.position ?: 0
        }

        return songPosDeferred.await()
    }
}