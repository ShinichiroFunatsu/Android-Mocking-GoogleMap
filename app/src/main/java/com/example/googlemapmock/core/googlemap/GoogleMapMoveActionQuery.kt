package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapMoveQuery(block: (MapMoveQuery) -> MapMoveQuery.HasDirection) {
    val hasDirection = block(MapMoveQuery)
}

infix fun MapMoveQuery.to(location: Location): MapMoveQuery.To =
    MapMoveQuery.To(location)

infix fun MapMoveQuery.from(location: Location): MapMoveQuery.From =
    MapMoveQuery.From(location)

infix fun MapMoveQuery.From.to(direction: Location): MapMoveQuery.FromTo =
    MapMoveQuery.FromTo(this.location, direction)


object MapMoveQuery {
    interface HasDirection {
        val to: Location
    }
    data class From(val location: Location)
    data class To(override val to: Location): HasDirection
    data class FromTo(val from: Location, override val to: Location): HasDirection
}

