package com.example.googlemapmock.core.sandbox

import android.location.Location
import com.example.googlemapmock.core.googlemap.mapMoveQuery
import com.example.googlemapmock.core.googlemap.MapMoveQuery.to
import com.example.googlemapmock.core.location.here

fun moveToMyLocation(direction: Location) {
    mapMoveQuery { move ->
        move from here to direction
    }
}