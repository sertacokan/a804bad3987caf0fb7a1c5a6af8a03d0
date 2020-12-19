package com.example.spacedeliveryman.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spacedeliveryman.adapters.FavoriteStationListAdapter
import com.example.spacedeliveryman.database.station.SpaceStationEntity

@BindingAdapter("favoriteStationList")
fun RecyclerView.setFavoriteListAdapter(favorites: List<SpaceStationEntity>) {
    (adapter as? FavoriteStationListAdapter)?.submitList(favorites)
}