package com.ozgursarki.shoppinglist.presentation.home.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozgursarki.shoppinglist.databinding.FragmentAddShoppingItemBinding


class AddShoppingItemFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddShoppingItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoppingItemBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        private const val BOTTOM_SHEET_TAG = "com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment"
        fun show(fragmentManager: FragmentManager) {
            val bottomSheet = AddShoppingItemFragment()
            bottomSheet.show(fragmentManager, BOTTOM_SHEET_TAG)
        }
    }

}