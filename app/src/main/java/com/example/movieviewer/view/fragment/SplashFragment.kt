package com.example.movieviewer.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.movieviewer.R
import com.example.movieviewer.view.MainActivity


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed(Runnable { // This method will be executed once the timer is over
            val action =
                SplashFragmentDirections.actionGoToMain()
            Navigation.findNavController(view).navigate(action)
        }, 3000)
        return view;
    }

    override fun onResume() {
        super.onResume()
        (getActivity() as AppCompatActivity).supportActionBar!!.hide()
    }

}