package com.example.googlemapmock.map

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class LoggingOnGestureListenerImpl(tag: String? = null) :
    GestureDetector.SimpleOnGestureListener() {
    private val tag = tag ?: DEFAULT_TAG

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(tag, "onDown: $event")
        return false
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(tag, "onLongPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(tag, "onSingleTapUp: $event")
        return false
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d(tag, "onFling: $event1 $event2")
        return false
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(tag, "onScroll: $event1 $event2")
        return false
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(tag, "onShowPress: $event")
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(tag, "onDoubleTap: $event")
        return false
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(tag, "onDoubleTapEvent: $event")
        return false
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(tag, "onSingleTapConfirmed: $event")
        return false
    }

    companion object {
        const val DEFAULT_TAG = "LoggingOnGestureListenerImpl"
    }
}
