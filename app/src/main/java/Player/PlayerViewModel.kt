package Player

import androidx.lifecycle.ViewModel

/**
 * Created by hafthorg on 22/02/2019.
 */
class PlayerViewModel(private val repo: Repo) : ViewModel() {
    fun getData() = repo.getData()
}