package com.example.googlemapmock.map.behavior

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

val View.behavior: OnSingleTapBehavior?
    get() {
        val param: CoordinatorLayout.LayoutParams = layoutParams as CoordinatorLayout.LayoutParams
        return param.behavior as? OnSingleTapBehavior
    }

interface OnSingleTapBehavior {
    fun onSingleTap(): Boolean
}
