package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapMoveQuery(fromToBlock: (MapMoveQuery) -> MapMoveQuery.FromTo) {
    val formTo = fromToBlock(MapMoveQuery)
}


object MapMoveQuery {
    data class From(val location: Location)
    data class FromTo(val from: Location, val to: Location)
    infix fun from(location: Location): From = From(location)
    infix fun From.to(direction: Location): FromTo = FromTo(this.location, direction)
}

