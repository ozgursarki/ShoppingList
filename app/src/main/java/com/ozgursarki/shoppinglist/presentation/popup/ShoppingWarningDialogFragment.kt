package com.ozgursarki.shoppinglist.presentation.popup

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ozgursarki.shoppinglist.R

class ShoppingWarningDialogFragment(private val errorMessage: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_shopping_warning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.findViewById<TextView>(R.id.errorMessage).text = errorMessage
    }

    companion object {
        val TAG: String = ShoppingWarningDialogFragment::class.java.name
        fun newInstance(errorMessage: String, fragmentManager: FragmentManager) {
            val fragment = ShoppingWarningDialogFragment(errorMessage)
            fragment.show(fragmentManager, TAG)
        }
    }
}