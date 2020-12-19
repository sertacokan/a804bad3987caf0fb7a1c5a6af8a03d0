package com.example.spacedeliveryman.ui.station

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.spacedeliveryman.R
import com.example.spacedeliveryman.adapters.SpaceStationAdapter
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.databinding.StationsFragmentBinding
import com.example.spacedeliveryman.extensions.dataBinding
import com.example.spacedeliveryman.utils.FavoriteSelectionListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationsFragment : Fragment(), FavoriteSelectionListener {

    private val viewModel: StationsViewModel by viewModel()
    private val binding: StationsFragmentBinding by dataBinding(R.layout.stations_fragment)
    private val pageSnapHelper: PagerSnapHelper by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.stationList.apply {
            setHasFixedSize(true)
            adapter = SpaceStationAdapter(this@StationsFragment)
        }

        pageSnapHelper.attachToRecyclerView(binding.stationList)
    }

    override fun onFavoriteClicked(spaceStationEntity: SpaceStationEntity) {
        viewModel.addStationToFavorite(spaceStationEntity)
    }
}