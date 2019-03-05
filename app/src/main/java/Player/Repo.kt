package Player

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hftor.plyr.dao.SongInfoDao
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.repo.SongInfoRepository
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.*

/**
 * Created by hafthorg on 22/02/2019.
 */
class Repo(private val context: Context, private val songInfoRepository: SongInfoRepository) {

    private var _currentSong : MusicFinder.Song? = null
    private var currentSong : MutableLiveData<MusicFinder.Song> = MutableLiveData()


    private var _songs = mutableListOf<MusicFinder.Song>()
    private var songs : MutableLiveData<List<MusicFinder.Song>> = MutableLiveData()

    private var mediaPlayer : MediaPlayer? = null

    init {
        var songFinder = MusicFinder(context.contentResolver)
        songFinder.prepare()
        setSongs(songFinder.allSongs)
    }

    private fun setSongs(songsToSet : MutableList<MusicFinder.Song>){
        _songs = songsToSet
        songs.value = songsToSet
    }

    private fun setCurrentSong(songId: Long){
        _currentSong = _songs.first {
            song -> song.id == songId
        }

        currentSong.value = _currentSong
    }

    private fun setCurrentSong(songToSet : MusicFinder.Song?){
        _currentSong = songToSet
        currentSong.value = songToSet
    }

    fun getCurrentSong() = currentSong as LiveData<MusicFinder.Song>

    fun getAllSongs() = songs as LiveData<List<MusicFinder.Song>>

    fun playNext(){
        val i = _songs.indexOf(_currentSong)
        setCurrentSong(_songs[i])
    }

    fun play(songId: Long){
        GlobalScope.launch {
            play2(songId)
        }
    }

    private suspend fun play2(songId: Long){
        if(_currentSong?.id == songId){
            return
        }

        songInfoRepository.saveSongsState(_currentSong, mediaPlayer?.currentPosition)

        GlobalScope.launch(Dispatchers.Main.immediate) {
            setCurrentSong(songId)

            mediaPlayer?.stop()
            mediaPlayer = MediaPlayer.create(context, _currentSong!!.uri)
            mediaPlayer?.seekTo(songInfoRepository.getSongPosition(songId))
            mediaPlayer?.start()
        }
    }
}