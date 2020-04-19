package com.example.googlemapmock.core.sandbox

import android.location.Location
import com.example.googlemapmock.core.googlemap.from
import com.example.googlemapmock.core.googlemap.mapDirectionQuery
import com.example.googlemapmock.core.googlemap.mapMoveQuery
import com.example.googlemapmock.core.googlemap.to
import com.example.googlemapmock.core.location.here

fun moveToMyLocation() {
    mapMoveQuery { move ->
        move to here
    }
}

fun moveTo(destination: Location) {
    mapMoveQuery { move ->
        move to destination
    }
}

fun directTo(destination: Location) {
    mapDirectionQuery { move ->
        move from here to destination
    }
}

fun direct(origin: Location, destination: Location) {
    mapDirectionQuery { move ->
        move from origin to destination
    }
}
