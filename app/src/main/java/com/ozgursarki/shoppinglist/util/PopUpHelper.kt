package com.ozgursarki.shoppinglist.util

import android.content.Context
import android.content.DialogInterface
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ozgursarki.shoppinglist.R

object PopUpHelper {
    fun showErrorPopUp(errorMessage: String, context: Context) {
        val builder = MaterialAlertDialogBuilder(context, R.style.MyDialogTheme)
        builder.setTitle("HATA!")
        builder.setMessage(errorMessage)
        builder.setPositiveButton("OK") { dialog, which ->

        }
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(
            ContextCompat.getColor(context,
                R.color.black))
    }
}