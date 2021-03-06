package com.example.spacedeliveryman.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.holders.FavoriteStationHolder
import com.example.spacedeliveryman.utils.FavoriteSelectionListener

class FavoriteStationListAdapter(private val favoriteSelectionListener: FavoriteSelectionListener) :
    ListAdapter<SpaceStationEntity, FavoriteStationHolder>(FAVORITE_ITEM_DIFF_UTIL) {

    companion object {
        private val FAVORITE_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<SpaceStationEntity>() {
            override fun areItemsTheSame(oldItem: SpaceStationEntity, newItem: SpaceStationEntity): Boolean {
                return oldItem.stationId == newItem.stationId
            }

            override fun areContentsTheSame(oldItem: SpaceStationEntity, newItem: SpaceStationEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteStationHolder {
        return FavoriteStationHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: FavoriteStationHolder, position: Int) {
        val favoriteStationItem = getItem(position)
        holder.bind(favoriteStationItem, favoriteSelectionListener)
    }
}