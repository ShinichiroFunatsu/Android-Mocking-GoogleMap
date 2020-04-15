package com.example.googlemapmock.core.model

enum class Zoom(val level: Int) {
     WORLD(1),
     LANDMASS(5), //or continent
     CITY(10),
     STREETS(15),
     BUILDINGS(20),
}