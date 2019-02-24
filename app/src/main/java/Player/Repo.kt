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

    fun getMessage() : String{
        val ss = GlobalScope.async{
            val songFinder = MusicFinder(context.contentResolver)
            songFinder.prepare()
            songFinder.allSongs
            songs = songFinder.allSongs
        }

        var a = runBlocking {
            ss.await()
            var firstSong = songs?.get(0)
            if(firstSong == null)
            {
                 "meee"
            }
            else{
                mediaPlayer = MediaPlayer.create(context, firstSong.uri)
                mediaPlayer?.start()

                "This is a repo message from async: " + songs?.get(0)?.artist
            }
        }

        return a
    }
}