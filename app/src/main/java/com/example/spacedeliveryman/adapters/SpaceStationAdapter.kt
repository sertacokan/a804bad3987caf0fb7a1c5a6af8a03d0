package com.example.spacedeliveryman.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.holders.StationHolder
import com.example.spacedeliveryman.utils.FavoriteSelectionListener
import com.example.spacedeliveryman.utils.StationTravelClickListener

class SpaceStationAdapter(
    private val favoriteSelectionListener: FavoriteSelectionListener,
    private val stationTravelClickListener: StationTravelClickListener
) : ListAdapter<SpaceStationEntity, StationHolder>(STATION_DIFF_UTIL) {

    companion object {
        private val STATION_DIFF_UTIL = object : DiffUtil.ItemCallback<SpaceStationEntity>() {
            override fun areItemsTheSame(oldItem: SpaceStationEntity, newItem: SpaceStationEntity): Boolean {
                return oldItem.stationId == newItem.stationId
            }

            override fun areContentsTheSame(oldItem: SpaceStationEntity, newItem: SpaceStationEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationHolder {
        return StationHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: StationHolder, position: Int) {
        val stationItem = getItem(position)
        holder.bind(stationItem, favoriteSelectionListener, stationTravelClickListener)
    }
}