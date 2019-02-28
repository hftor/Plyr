package Player

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtechviral.mplaylib.MusicFinder

/**
 * Created by hafthorg on 22/02/2019.
 */
class Repo(private val context: Context) {

    private var _currentSong : MusicFinder.Song? = null
    private var currentSong : MutableLiveData<MusicFinder.Song> = MutableLiveData()


    private var _songs = mutableListOf<MusicFinder.Song>()
    private var songs : MutableLiveData<List<MusicFinder.Song>> = MutableLiveData()

    private var mediaPlayer : MediaPlayer? = null

    init {
        var songFinder = MusicFinder(context.contentResolver)
        songFinder.prepare()

        _songs = songFinder.allSongs
        songs.value = _songs
    }

    private fun setCurrentSong(songId: Long){
        _currentSong = _songs.first {
            song -> song.id == songId
        }

        currentSong.value = _currentSong
    }

    private fun setCurrentSong(){
        _currentSong = _songs.first()
        currentSong.value = _currentSong
    }

    fun getCurrentSong() = currentSong as LiveData<MusicFinder.Song>

    fun getAllSongs() = songs as LiveData<List<MusicFinder.Song>>

    fun playNext(){
        val i = _songs.indexOf(_currentSong)
        _currentSong = _songs[i+1]
        currentSong.value = _currentSong
    }

    fun play(songId: Long){
        if(_currentSong?.id == songId){
            return
        }

        setCurrentSong(songId)

        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, _currentSong!!.uri)
            mediaPlayer?.start()
            return
        }

        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer.create(context, _currentSong!!.uri)
        mediaPlayer?.start()
    }
}