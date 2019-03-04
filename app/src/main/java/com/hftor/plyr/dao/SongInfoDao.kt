package com.hftor.plyr.dao

import androidx.room.*
import com.hftor.plyr.entity.SongInfo

@Dao
interface SongInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongInfo(songInfo: SongInfo)

    @Update
    fun updateSongInfo(songInfo: SongInfo)

    @Delete
    fun deleteSongInfo(songInfo: SongInfo)

    @Query("SELECT * FROM SongInfo WHERE songId == :songId")
    fun getSongInfo(songId: Long) : SongInfo
}