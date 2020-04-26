@file:Suppress("unused") // from string resource calling

package com.example.googlemapmock.map.behavior

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.animation.AnimationUtils
import kotlin.properties.Delegates

class SearchOmniBoxBehavior(
    context: Context,
    attributeSet: AttributeSet? = null
) : CoordinatorLayout.Behavior<FragmentContainerView>(context, attributeSet), OnSingleTapBehavior {

    private lateinit var searchOmniBoxContainerAnimator: SearchOmniBoxContainerAnimator
    private var currentState = STATE_SHOWN

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: FragmentContainerView,
        layoutDirection: Int
    ): Boolean {
        searchOmniBoxContainerAnimator =
            SearchOmniBoxContainerAnimator {
                val paramsCompat = child.layoutParams as ViewGroup.MarginLayoutParams
                height = child.measuredHeight + paramsCompat.topMargin
                omniSearchBoxContainer = child
            }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onSingleTap(): Boolean {
        toggleShowHide()
        return false
    }

    private fun toggleShowHide() {
        if (currentState == STATE_SHOWN) {
            slideUp()
        } else {
            slideDown()
        }
    }

    private fun slideUp() {
        if (currentState == STATE_HIDDEN) {
            return
        }
        searchOmniBoxContainerAnimator.slideUp()
        currentState =
            STATE_HIDDEN
    }

    private fun slideDown() {
        if (currentState == STATE_SHOWN) {
            return
        }
        searchOmniBoxContainerAnimator.slideDown()
        currentState =
            STATE_SHOWN
    }

    companion object {
        private const val STATE_SHOWN = 1
        private const val STATE_HIDDEN = 2
        private const val DEBUG_TAG = "OmniSearchBoxBehavior"
    }
}

private class SearchOmniBoxContainerAnimator(block: SearchOmniBoxContainerAnimator.() -> Unit) {
    var height by Delegates.notNull<Int>()
    private var currentAnimator: ViewPropertyAnimator? = null
    private lateinit var _child: FragmentContainerView
    var omniSearchBoxContainer: FragmentContainerView
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
            -height,
            ENTER_ANIMATION_DURATION.toLong(),
            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
        )
    }

    fun slideDown() {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            _child.clearAnimation()
        }
        animateChildTo(
            _child,
            0,
            EXIT_ANIMATION_DURATION.toLong(),
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(
        child: FragmentContainerView,
        targetY: Int,
        duration: Long,
        interpolator: TimeInterpolator
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
    }
}
