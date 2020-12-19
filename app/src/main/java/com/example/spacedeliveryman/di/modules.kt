package com.example.spacedeliveryman.di

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.room.Room
import com.example.spacedeliveryman.database.MIGRATION_1_2
import com.example.spacedeliveryman.database.MIGRATION_2_3
import com.example.spacedeliveryman.database.SpaceDeliveryDatabase
import com.example.spacedeliveryman.network.SpaceStationService
import com.example.spacedeliveryman.repositories.FavoriteRepository
import com.example.spacedeliveryman.repositories.SpaceStationRepository
import com.example.spacedeliveryman.ui.favorites.FavoritesViewModel
import com.example.spacedeliveryman.ui.ship.SpaceshipViewModel
import com.example.spacedeliveryman.ui.station.StationsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(SpaceStationService::class.java) }
}


val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), SpaceDeliveryDatabase::class.java, "SpaceDeliveryDB")
            .addMigrations(MIGRATION_2_3)
            .build()
    }
    single { get<SpaceDeliveryDatabase>().spaceStationDao() }
}


val viewModelModule = module {
    viewModel { SpaceshipViewModel() }
    viewModel { FavoritesViewModel(favoriteRepository = get()) }
    viewModel { StationsViewModel(spaceStationRepository = get()) }
}

val repositoryModule = module {
    single { FavoriteRepository(spaceStationDao = get()) }
    single { SpaceStationRepository(spaceStationService = get(), spaceStationDao = get()) }
}

val utilsModule = module {
    factory { PagerSnapHelper() }
}