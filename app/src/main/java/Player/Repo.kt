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
        val deferred = GlobalScope.async{
            val songFinder = MusicFinder(context.contentResolver)
            songFinder.prepare()
            songFinder.allSongs
            songs = songFinder.allSongs
        }

        runBlocking {
            deferred.await()
            var firstSong = songs?.get(0)
            if(firstSong != null)
            {
                mediaPlayer = MediaPlayer.create(context, firstSong.uri)
                mediaPlayer?.start()
            }
        }
    }

    fun getMessage() : String{
        return "This is a repo message from repo"
    }
}