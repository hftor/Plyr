package com.hftor.plyr

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtechviral.mplaylib.MusicFinder

class SongViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.recycler_view_item, parent, false))
{
    private var mImageView : ImageView? = null
    private var mTextView : TextView? = null

    init {
        mImageView = itemView.findViewById(R.id.imageView1)
        mTextView = itemView.findViewById(R.id.textView1)
    }

    fun bind(song: MusicFinder.Song){
        mTextView?.text = song.title
        mImageView?.setImageURI(song.albumArt)
    }
}