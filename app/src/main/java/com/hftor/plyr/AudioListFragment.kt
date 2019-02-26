package com.hftor.plyr

import Player.PlayerViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_audio_list.*
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AudioList.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AudioList.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AudioList : Fragment() {

    private val p : PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        p.getAllSongs().observe(this, Observer {
            songs ->
            if(!songs.isEmpty()){
                testText.text = songs[1].title
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sending data from one fragment to another fragment
        letsGoButton.setOnClickListener{
            val action = AudioListDirections.actionAudioListToPlayer()
            findNavController().navigate(action)
        }
    }
}
