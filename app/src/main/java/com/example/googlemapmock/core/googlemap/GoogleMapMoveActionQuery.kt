package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapMoveQuery(fromToBlock: (MapMoveQuery) -> MapMoveQuery.FromTo) {
    val formTo = fromToBlock(MapMoveQuery)
}

infix fun MapMoveQuery.from(location: Location): MapMoveQuery.From =
    MapMoveQuery.From(location)

infix fun MapMoveQuery.From.to(direction: Location): MapMoveQuery.FromTo =
    MapMoveQuery.FromTo(this.location, direction)

object MapMoveQuery {
    data class From(val location: Location)
    data class FromTo(val from: Location, val to: Location)
}

