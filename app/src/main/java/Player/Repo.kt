package Player

import android.content.Context

/**
 * Created by hafthorg on 22/02/2019.
 */
class Repo(private val context: Context) {
    fun getMessage() : String{
        return "This is a repo message from context: " + context.applicationContext.toString()
    }
}