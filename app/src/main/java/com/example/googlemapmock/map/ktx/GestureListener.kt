package com.example.googlemapmock.map.ktx

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.googlemapmock.BuildConfig
import com.example.googlemapmock.map.LoggingOnGestureListenerImpl

@Suppress("FunctionName")
fun GestureDetectorCompat(
    context: Context
): LifecycleAwareGestureDetector {
    return LifecycleAwareGestureDetector(
        context,
        SimpleOnGestureListenerImpl()
    )
}

class LifecycleAwareGestureDetector(
    context: Context,
    private val listener: SimpleOnGestureListenerImpl
) {

    private val gestureDetectorCompat: GestureDetectorCompat by lazy {
        GestureDetectorCompat(context, listener)
    }

    val onSingleTapUp: LiveData<Unit>
        get() = listener.onSingleTapUp

    fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetectorCompat.onTouchEvent(event)
        return true
    }
}

class SimpleOnGestureListenerImpl(
    private val listener: GestureDetector.SimpleOnGestureListener =
        if (BuildConfig.DEBUG) LoggingOnGestureListenerImpl(DEBUG_TAG)
        else GestureDetector.SimpleOnGestureListener()
) : GestureDetector.OnGestureListener by listener,
    GestureDetector.OnDoubleTapListener by listener {

    private val _onSingleTapUp = MutableLiveData<Unit>()
    val onSingleTapUp: LiveData<Unit>
        get() = _onSingleTapUp

    private val _onLongPress = MutableLiveData<Unit>()
    val onLongPress: LiveData<Unit>
        get() = _onLongPress

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
        _onLongPress.value = Unit
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        _onSingleTapUp.value = Unit
        return true
    }

    companion object {
        private const val DEBUG_TAG = "SimpleOnGestureListenerImpl"
    }
}
