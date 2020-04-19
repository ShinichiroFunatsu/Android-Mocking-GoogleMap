@file:Suppress("unused") // from string resource calling

package com.example.googlemapmock.map

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.properties.Delegates

/**
 * refer HideBottomViewOnScrollBehavior
 */
class BottomNavigationBehavior(
    context: Context,
    attributeSet: AttributeSet? = null
) : CoordinatorLayout.Behavior<BottomNavigationView>(context, attributeSet),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onLayoutChild(
        parent: CoordinatorLayout, child: BottomNavigationView, layoutDirection: Int
    ): Boolean {
        val paramsCompat = child.layoutParams as MarginLayoutParams
        height = child.measuredHeight + paramsCompat.bottomMargin
        bottomNavigationView = child
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: BottomNavigationView, ev: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(ev)) {
            true
        } else {
            super.onTouchEvent(parent, child, ev)
        }
    }

    private var currentAnimator: ViewPropertyAnimator? = null
    private var height by Delegates.notNull<Int>()
    private var currentState = STATE_SHOWN
    private val gestureDetector: GestureDetectorCompat by lazy {
        GestureDetectorCompat(context, this).apply {
            setOnDoubleTapListener(this@BottomNavigationBehavior)
        }
    }

    private fun onSingleTap(): Boolean {
        toggleShowHide()
        return false
    }

    private fun toggleShowHide() {
        if (currentState == STATE_SHOWN) {
            slideDown(bottomNavigationView)
        } else {
            slideUp(bottomNavigationView)
        }
    }

    private fun slideUp(child: BottomNavigationView) {
        if (currentState == STATE_SHOWN) {
            return
        }
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_SHOWN
        animateChildTo(
            child,
            0,
            ENTER_ANIMATION_DURATION.toLong(),
            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
        )
    }

    private fun slideDown(child: BottomNavigationView) {
        if (currentState == STATE_HIDDEN) {
            return
        }
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_HIDDEN
        animateChildTo(
            child,
            height,
            EXIT_ANIMATION_DURATION.toLong(),
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(
        child: BottomNavigationView, targetY: Int, duration: Long, interpolator: TimeInterpolator
    ) {
        currentAnimator = child
            .animate()
            .translationY(targetY.toFloat())
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        currentAnimator = null
                    }
                })
    }

    companion object {
        private const val ENTER_ANIMATION_DURATION = 225
        private const val EXIT_ANIMATION_DURATION = 175
        private const val STATE_SHOWN = 1
        private const val STATE_HIDDEN = 2
//        private const val DEBUG_TAG = "BottomNavigationBehavior"
    }

    override fun onDown(event: MotionEvent): Boolean {
//        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
//        Log.d(DEBUG_TAG, "onFling: $event1 $event2")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
//        Log.d(DEBUG_TAG, "onLongPress: $event")
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        Log.d(DEBUG_TAG, "onScroll: $event1 $event2")
        return true
    }

    override fun onShowPress(event: MotionEvent) {
//        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
//        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        onSingleTap()
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
//        Log.d(DEBUG_TAG, "onDoubleTap: $event")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
//        Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
//        Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
        return true
    }

}