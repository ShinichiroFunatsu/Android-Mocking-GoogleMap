package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapMoveQuery(block: (MapMoveQuery) -> MapMoveQuery.To) {
    val center = block(MapMoveQuery)
}

infix fun MapMoveQuery.to(location: Location): MapMoveQuery.To =
    MapMoveQuery.To(location)


object MapMoveQuery {
    data class To(val to: Location)
}

