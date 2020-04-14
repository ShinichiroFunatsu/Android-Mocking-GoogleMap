package com.example.googlemapmock.core.googlemap

import android.location.Location

interface GoogleMapMoveActionQuery {
    operator fun invoke(fromToBlock: (LocationFrom) -> FromTo) {
        val formTo = fromToBlock(LocationFrom)
    }

    data class From(val location: Location)
    data class FromTo(val from: Location, val to: Location)

    object LocationFrom {
        infix fun from(location: Location): From = From(location)
    }

}

infix fun GoogleMapMoveActionQuery.From.to(direction: Location): GoogleMapMoveActionQuery.FromTo =
    GoogleMapMoveActionQuery.FromTo(this.location, direction)
