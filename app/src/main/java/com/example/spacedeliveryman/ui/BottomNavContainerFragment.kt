package com.example.spacedeliveryman.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.spacedeliveryman.R
import com.example.spacedeliveryman.databinding.FragmentBottomNavContainerBinding
import com.example.spacedeliveryman.extensions.dataBinding


class BottomNavContainerFragment : Fragment() {

    private val binding: FragmentBottomNavContainerBinding by dataBinding(R.layout.fragment_bottom_nav_container)

    private val navController by lazy {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.tab_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}