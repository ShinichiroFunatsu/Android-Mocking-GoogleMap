package com.example.googlemapmock.core.googlemap

import android.location.Location
import android.view.MotionEvent
import com.example.googlemapmock.core.model.BaseMap
import com.example.googlemapmock.core.model.Zoom

interface MapController {
    fun direct(dir: MapDirectionQuery.FromTo)
    fun display(center: Location, zoom: Zoom)
    fun changeScreenMode(baseMap: BaseMap)
    fun searchKeywords(vararg word: String)
    fun searchLocation(location: Location)
    fun observePositionChanged(onChanged: (location: Location) -> Unit)
    fun observeUserTouchEvent(dispatchEvent: (MotionEvent) -> Unit)
}
