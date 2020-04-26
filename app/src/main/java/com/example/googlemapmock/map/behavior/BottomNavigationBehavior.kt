@file:Suppress("unused") // from string resource calling

package com.example.googlemapmock.map.behavior

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewPropertyAnimator
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.animation.AnimationUtils
import kotlin.properties.Delegates

/**
 * refer HideBottomViewOnScrollBehavior
 */
class BottomNavigationBehavior(
    context: Context,
    attributeSet: AttributeSet? = null
) : CoordinatorLayout.Behavior<LinearLayout>(context, attributeSet), OnSingleTapBehavior {

    private lateinit var bottomNavigationViewAnimator: BottomNavigationViewAnimator
    private var currentState = STATE_SHOWN

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: LinearLayout,
        layoutDirection: Int
    ): Boolean {
        bottomNavigationViewAnimator = BottomNavigationViewAnimator {
            val paramsCompat = child.layoutParams as MarginLayoutParams
            height = child.measuredHeight + paramsCompat.bottomMargin
            bottomNavigationView = child
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onSingleTap(): Boolean {
        toggleShowHide()
        return false
    }

    private fun toggleShowHide() {
        if (currentState == STATE_SHOWN) {
            slideDown()
        } else {
            slideUp()
        }
    }

    private fun slideUp() {
        if (currentState == STATE_SHOWN) {
            return
        }
        bottomNavigationViewAnimator.slideUp()
        currentState = STATE_SHOWN
    }

    private fun slideDown() {
        if (currentState == STATE_HIDDEN) {
            return
        }
        bottomNavigationViewAnimator.slideDown()
        currentState = STATE_HIDDEN
    }

    companion object {
        private const val STATE_SHOWN = 1
        private const val STATE_HIDDEN = 2
        private const val DEBUG_TAG = "BottomNavigationBehavior"
    }
}

private class BottomNavigationViewAnimator(block: BottomNavigationViewAnimator.() -> Unit) {
    var height by Delegates.notNull<Int>()
    private var currentAnimator: ViewPropertyAnimator? = null
    private lateinit var _child: LinearLayout
    var bottomNavigationView: LinearLayout
        get() = _child
        set(value) {
            _child = value
        }

    init {
        this.block()
    }

    fun slideUp() {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            _child.clearAnimation()
        }
        animateChildTo(
            _child,
            0,
            ENTER_ANIMATION_DURATION.toLong(),
            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR,
            ENTER_START_DELAY.toLong()
        )
    }

    fun slideDown() {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            _child.clearAnimation()
        }
        animateChildTo(
            _child,
            height,
            EXIT_ANIMATION_DURATION.toLong(),
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(
        child: LinearLayout,
        targetY: Int,
        duration: Long,
        interpolator: TimeInterpolator,
        startDelay: Long = 0
    ) {
        currentAnimator = child
            .animate()
            .translationY(targetY.toFloat())
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setStartDelay(startDelay)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        currentAnimator = null
                    }
                })
    }

    companion object {
        private const val ENTER_START_DELAY = 120
        private const val ENTER_ANIMATION_DURATION = 260
        private const val EXIT_ANIMATION_DURATION = 275
    }
}
