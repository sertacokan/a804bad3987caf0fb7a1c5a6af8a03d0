package com.example.spacedeliveryman.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.databinding.ListItemFavoriteStationBinding
import com.example.spacedeliveryman.utils.FavoriteSelectionListener

class FavoriteStationHolder private constructor(private val binding: ListItemFavoriteStationBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): FavoriteStationHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemFavoriteStationBinding.inflate(layoutInflater, parent, false)
            return FavoriteStationHolder(binding)
        }
    }

    fun bind(spaceStationEntity: SpaceStationEntity, favoriteSelectionListener: FavoriteSelectionListener) {
        with(binding) {
            stationEntity = spaceStationEntity
            this.favoriteSelectionListener = favoriteSelectionListener
            executePendingBindings()
        }
    }
}