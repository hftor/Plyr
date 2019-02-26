package com.hftor.plyr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtechviral.mplaylib.MusicFinder

class ListAdapter(private val list: List<MusicFinder.Song>)
    : RecyclerView.Adapter<SongViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return SongViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: MusicFinder.Song = list[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int = list.size
}

