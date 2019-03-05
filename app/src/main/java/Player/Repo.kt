package Player

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hftor.plyr.dao.SongInfoDao
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.entity.SongInfo
import com.mtechviral.mplaylib.MusicFinder
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

    private var db: AppDataBase? = null
    private var songInfoDao: SongInfoDao? = null

    private var songPosition: Int = 0

    init {
        var songFinder = MusicFinder(context.contentResolver)
        songFinder.prepare()
        setSongs(songFinder.allSongs)

        db = AppDataBase.getAppDataBase(context)
        songInfoDao = db?.songInfoDao()
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

    private fun saveSongsState(songToSave : MusicFinder.Song?){
        GlobalScope.launch {
            if(songToSave != null && mediaPlayer != null){
                var s1 = SongInfo(songToSave.id, mediaPlayer!!.currentPosition)
                songInfoDao?.insertSongInfo(s1)
            }
        }
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

        saveSongsState(_currentSong)

        var songPosDefered = GlobalScope.async {
            songInfoDao?.getSongInfo(songId)?.position
        }

        GlobalScope.launch(Dispatchers.Main.immediate) {
            setCurrentSong(songId)

            mediaPlayer?.stop()
            mediaPlayer = MediaPlayer.create(context, _currentSong!!.uri)
            mediaPlayer?.seekTo(songPosDefered.await() ?: 0)
            mediaPlayer?.start()
        }
    }
}