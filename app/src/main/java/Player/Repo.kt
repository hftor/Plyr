package Player

import android.content.Context
import android.media.MediaPlayer;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.*

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
        runBlocking {
            initializeMediaPlayer()
        }
    }

    suspend fun initializeMediaPlayer(){
        val deferred = GlobalScope.async{
            val songFinder = MusicFinder(context.contentResolver)
            songFinder.prepare()
            songFinder.allSongs
            _songs = songFinder.allSongs
        }


        runBlocking {
            deferred.await()

            songs.value = _songs

            setCurrentSong()
        }
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
}