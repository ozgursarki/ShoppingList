package com.ozgursarki.shoppinglist.util

import android.content.Context
import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorRes
import androidx.lifecycle.LifecycleOwner
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.presentation.enum.ToolTipLocation
import com.skydoves.balloon.*

object BalloonHelper {

    fun createToolTip(
        context: Context,
        viewLifeCycleOwner: LifecycleOwner,
        view: View,
        @ColorRes textColor: Int,
        text: String,
        toolTipLocation: ToolTipLocation,
        onDismiss: () -> Unit
        ) {
        val balloon = Balloon.Builder(context)
            .setWidthRatio(1.0f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setWidth(BalloonSizeSpec.WRAP)
            .setText(text)
            .setTextColorResource(textColor)
            .setTextTypeface(Typeface.BOLD)
            .setTextSize(15f)
            .setIconDrawableResource(R.drawable.ic_shopping)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.RedPink)
            .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
            .setLifecycleOwner(viewLifeCycleOwner)
            .setOnBalloonDismissListener {
                onDismiss.invoke()
            }
            .build()

        if (toolTipLocation == ToolTipLocation.TOP) {
            view.showAlignTop(balloon)
        }else {
            view.showAlignBottom(balloon)
        }
    }
}