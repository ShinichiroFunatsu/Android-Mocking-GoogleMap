package com.example.googlemapmock.map.ktx

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

private typealias OnSingleTapListener = () -> Unit
private typealias OnLongPressListener = () -> Unit

@Suppress("FunctionName")
fun GestureDetectorCompat(
    context: Context,
    onSingleTapListener: OnSingleTapListener? = null,
    onLongPressListener: OnLongPressListener? = null
): GestureDetectorCompat {
    return GestureDetectorCompat(
        context,
        SimpleOnGestureListenerImpl(onSingleTapListener, onLongPressListener)
    )
}

private class SimpleOnGestureListenerImpl(
    private val onSingleTapListener: OnSingleTapListener? = null,
    private val onLongPressListener: OnLongPressListener? = null
) : GestureDetector.SimpleOnGestureListener() {

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
        onLongPressListener?.invoke()
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        onSingleTapListener?.invoke()
        return true
    }

//            override fun onFling(
//                event1: MotionEvent,
//                event2: MotionEvent,
//                velocityX: Float,
//                velocityY: Float
//            ): Boolean {
//                Log.d(DEBUG_TAG, "onFling: $event1 $event2")
//                return true
//            }
//
//            override fun onScroll(
//                event1: MotionEvent,
//                event2: MotionEvent,
//                distanceX: Float,
//                distanceY: Float
//            ): Boolean {
//                Log.d(DEBUG_TAG, "onScroll: $event1 $event2")
//                return true
//            }
//
//            override fun onShowPress(event: MotionEvent) {
//                Log.d(DEBUG_TAG, "onShowPress: $event")
//            }
//            override fun onDoubleTap(event: MotionEvent): Boolean {
//                Log.d(DEBUG_TAG, "onDoubleTap: $event")
//                return true
//            }
//
//            override fun onDoubleTapEvent(event: MotionEvent): Boolean {
//                Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
//                return true
//            }
//
//            override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
//                Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
//                return true
//            }

    companion object {
        private const val DEBUG_TAG = "SimpleOnGestureListenerImpl"
    }
}