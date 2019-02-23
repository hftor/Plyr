package Player

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer;
import com.mtechviral.mplaylib.MusicFinder
import kotlinx.coroutines.*


/**
 * Created by hafthorg on 22/02/2019.
 */
class Repo(private val context: Context) {

    private var mediaPlayer : MediaPlayer? = null

    fun getMessage() : String{


        return "This is a repo message from context: " + context.applicationContext.toString()
    }

}