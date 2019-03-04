package com.hftor.plyr.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongInfo(
        @PrimaryKey(autoGenerate = false)
        val songId: Long,
        val position: Int
)