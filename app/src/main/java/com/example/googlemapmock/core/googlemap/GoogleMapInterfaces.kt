package com.example.googlemapmock.core.googlemap

import android.location.Location

interface GoogleMapMoveActionQuery {
    operator fun invoke(fromToBlock: (LocationFrom) -> FromTo) {
        val formTo = fromToBlock(LocationFrom)
    }
}

interface LocationHolder {
    val location: Location
}

data class From(override val location: Location) : LocationHolder
data class FromTo(val from: Location, val to: Location)

object LocationFrom {
    infix fun from(location: Location): From = From(location)
}

infix fun From.to(direction: Location): FromTo = FromTo(this.location, direction)