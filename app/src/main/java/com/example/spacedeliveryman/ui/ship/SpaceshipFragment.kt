package com.example.spacedeliveryman.ui.ship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacedeliveryman.R
import com.example.spacedeliveryman.databinding.SpaceshipFragmentBinding
import com.example.spacedeliveryman.extensions.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpaceshipFragment : Fragment() {

    private val viewModel: SpaceshipViewModel by viewModel()
    private val binding: SpaceshipFragmentBinding by dataBinding(R.layout.spaceship_fragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}