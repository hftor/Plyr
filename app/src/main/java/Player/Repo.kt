package Player

import android.content.Context
import android.media.MediaPlayer;
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.*

/**
 * Created by hafthorg on 22/02/2019.
 */
class Repo(private val context: Context) {

    private var mediaPlayer : MediaPlayer? = null
    private var songs : MutableList<MusicFinder.Song>? = null
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
        }

        deferred.await()

        var firstSong = songs?.get(0)
        if(firstSong == null)
        {
            return
        }
        else{
            mediaPlayer = MediaPlayer.create(context, firstSong.uri)
            mediaPlayer?.start()
        }
    }

    fun getMessage() : String{
        return "This is a repo message from repo"
    }
}