package com.example.what2watch_svitlik_odunsi_f23.ui.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.what2watch_svitlik_odunsi_f23.R

class RecyclerSplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        return inflater.inflate(R.layout.recycler_splashscreen, container, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
     }
}
