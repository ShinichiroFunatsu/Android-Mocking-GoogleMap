package com.example.googlemapmock.core.sandbox

import android.location.Location
import com.example.googlemapmock.core.googlemap.GoogleMapMoveActionQuery
import com.example.googlemapmock.core.googlemap.to
import com.example.googlemapmock.core.location.here

fun moveToMyLocation(mapMoveQuery: GoogleMapMoveActionQuery, direction: Location) {
    mapMoveQuery { move ->
        move from here to direction
    }
}