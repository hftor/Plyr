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

    private var songs = mutableListOf<MusicFinder.Song>()
    private val liveTesting : MutableLiveData<List<MusicFinder.Song>> = MutableLiveData()

    private var mediaPlayer : MediaPlayer? = null

    init {
        liveTesting.value = songs
        val deferred = GlobalScope.async{
            val songFinder = MusicFinder(context.contentResolver)
            songFinder.prepare()
            songFinder.allSongs
            songs = songFinder.allSongs
        }

        runBlocking {
            deferred.await()

            liveTesting.value = songs

            var firstSong = songs?.get(0)
            if(firstSong != null)
            {
                mediaPlayer = MediaPlayer.create(context, firstSong.uri)
                mediaPlayer?.start()
            }
        }
    }

    fun getData() = liveTesting as LiveData<List<MusicFinder.Song>>
}