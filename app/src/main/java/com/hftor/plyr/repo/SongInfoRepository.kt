package com.hftor.plyr.repo

import android.content.Context
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.entity.SongInfo
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SongInfoRepository(private val context: Context) {

    fun saveSongsState(songToSave : MusicFinder.Song?, songPosition : Int?){
        if(songToSave == null || songPosition == null || songPosition == 0){
            return
        }

        GlobalScope.launch {
            var s1 = SongInfo(songToSave.id, songPosition)
            AppDataBase.getInstance(context)?.insertSongInfo(s1)
        }
    }

    suspend fun getSongPosition(songId: Long) : Int{
        var songPosDeferred = GlobalScope.async {
            AppDataBase.getInstance(context)?.getSongInfo(songId)?.position ?: 0
        }

        return songPosDeferred.await()
    }
}