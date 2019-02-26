package Player

import androidx.lifecycle.ViewModel
import com.mtechviral.mplaylib.MusicFinder

/**
 * Created by hafthorg on 22/02/2019.
 */
class PlayerViewModel(private val repo: Repo) : ViewModel() {
    fun getCurrentSong() = repo.getCurrentSong()

    fun getAllSongs() = repo.getAllSongs()

    fun playNext() = repo.playNext()

    fun play(songId: Long) = repo.play(songId)
}