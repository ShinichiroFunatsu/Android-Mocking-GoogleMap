package com.example.googlemapmock.core.location

import android.location.Location


private val locationProvider = LocationProviderImpl()

val here: Location
    get() = locationProvider.myLocation()


interface LocationProvider {
    fun myLocation(): Location
}

class LocationProviderImpl: LocationProvider {

    override fun myLocation(): Location {
        TODO("Not yet implemented")
    }

}