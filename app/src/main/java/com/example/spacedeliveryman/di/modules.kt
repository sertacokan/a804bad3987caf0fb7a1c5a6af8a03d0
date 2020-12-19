package com.example.spacedeliveryman.di

import androidx.room.Room
import com.example.spacedeliveryman.database.SpaceDeliveryDatabase
import com.example.spacedeliveryman.network.SpaceStationService
import com.example.spacedeliveryman.ui.ship.SpaceshipViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(SpaceStationService::class.java)
    }
}


val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), SpaceDeliveryDatabase::class.java, "SpaceDeliveryDB").build() }
    factory { get<SpaceDeliveryDatabase>().spaceStationDao() }
}


val viewModelModule = module {
    viewModel { SpaceshipViewModel() }
}