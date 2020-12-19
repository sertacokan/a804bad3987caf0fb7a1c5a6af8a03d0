package com.example.spacedeliveryman.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacedeliveryman.R
import com.example.spacedeliveryman.adapters.FavoriteStationListAdapter
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.databinding.FavoritesFragmentBinding
import com.example.spacedeliveryman.extensions.dataBinding
import com.example.spacedeliveryman.utils.FavoriteSelectionListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), FavoriteSelectionListener {

    private val viewModel: FavoritesViewModel by viewModel()
    private val binding: FavoritesFragmentBinding by dataBinding(R.layout.favorites_fragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.favoriteItems.apply {
            setHasFixedSize(true)
            adapter = FavoriteStationListAdapter(this@FavoritesFragment)
        }
    }

    override fun onFavoriteClicked(spaceStationEntity: SpaceStationEntity) {
        viewModel.removeFromFavoriteStation(spaceStationEntity)
    }
}