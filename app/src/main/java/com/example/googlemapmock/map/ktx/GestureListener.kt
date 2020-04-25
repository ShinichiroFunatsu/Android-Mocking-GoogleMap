package com.example.googlemapmock.map.ktx

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.googlemapmock.BuildConfig
import com.example.googlemapmock.map.LoggingOnGestureListenerImpl

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
    private val onLongPressListener: OnLongPressListener? = null,
    private val listener: GestureDetector.SimpleOnGestureListener =
        if (BuildConfig.DEBUG) LoggingOnGestureListenerImpl(DEBUG_TAG)
        else GestureDetector.SimpleOnGestureListener()
) : GestureDetector.OnGestureListener by listener,
    GestureDetector.OnDoubleTapListener by listener {

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return onSingleTapListener != null
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

    companion object {
        private const val DEBUG_TAG = "SimpleOnGestureListenerImpl"
    }
}
