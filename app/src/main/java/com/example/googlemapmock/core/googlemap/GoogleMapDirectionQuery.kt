package com.example.googlemapmock.core.googlemap

import android.location.Location

fun mapDirectionQuery(block: (MapDirectionQuery) -> MapDirectionQuery.FromTo) {
    val dir = block(MapDirectionQuery)
}

infix fun MapDirectionQuery.from(origin: Location): MapDirectionQuery.From =
    MapDirectionQuery.From(origin)

infix fun MapDirectionQuery.From.to(destination: Location): MapDirectionQuery.FromTo =
    MapDirectionQuery.FromTo(this.origin, destination)


object MapDirectionQuery {
    data class From(val origin: Location)
    data class FromTo(val origin: Location, val destination: Location)
}