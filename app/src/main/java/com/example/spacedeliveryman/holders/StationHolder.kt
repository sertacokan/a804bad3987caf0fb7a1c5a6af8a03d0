package com.example.spacedeliveryman.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.databinding.ListItemStationsBinding
import com.example.spacedeliveryman.utils.FavoriteSelectionListener
import com.example.spacedeliveryman.utils.StationTravelClickListener

class StationHolder private constructor(private val binding: ListItemStationsBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): StationHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemStationsBinding.inflate(layoutInflater, parent, false)
            return StationHolder(binding)
        }
    }

    fun bind(
        spaceStationEntity: SpaceStationEntity,
        favoriteSelectionListener: FavoriteSelectionListener,
        stationTravelClickListener: StationTravelClickListener
    ) {
        with(binding) {
            stationEntity = spaceStationEntity
            travelClickListener = stationTravelClickListener
            favoriteListener = favoriteSelectionListener
            executePendingBindings()
        }
    }
}