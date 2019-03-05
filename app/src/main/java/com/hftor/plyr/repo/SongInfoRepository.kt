package com.hftor.plyr.repo

import android.content.Context
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.entity.SongInfo
import com.mtechviral.mplaylib.MusicFinder

class SongInfoRepository(private val context: Context) {

    fun saveSongsState(songToSave : MusicFinder.Song?, songPosition : Int?){
        if(songToSave == null || songPosition == null || songPosition == 0){
            return
        }

        var s1 = SongInfo(songToSave.id, songPosition)
        AppDataBase.getInstance(context)?.insertSongInfo(s1)
    }

    fun getSongPosition(songId: Long) : Int{
        return AppDataBase.getInstance(context)?.getSongInfo(songId)?.position ?: 0
    }
}