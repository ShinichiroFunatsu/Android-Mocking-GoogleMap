package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapMoveQuery(fromToBlock: (LocationFrom) -> FromTo) {
    val formTo = fromToBlock(LocationFrom)
}

data class From(val location: Location)
data class FromTo(val from: Location, val to: Location)

object LocationFrom {
    infix fun from(location: Location): From = From(location)
}

infix fun From.to(direction: Location): FromTo = FromTo(this.location, direction)
