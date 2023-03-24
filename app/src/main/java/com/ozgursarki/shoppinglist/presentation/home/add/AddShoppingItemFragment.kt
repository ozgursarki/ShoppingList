package com.ozgursarki.shoppinglist.presentation.home.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozgursarki.shoppinglist.databinding.FragmentAddShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.util.DummyData


class AddShoppingItemFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddShoppingItemBinding
    private val addShoppingItemViewModel: AddShoppingItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoppingItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            val item = ShoppingItem(
                name = binding.shoppingItemName.text.toString(),
                count = 4,
                listID = DummyData.listID
            )
            addShoppingItemViewModel.insertShoppingItem(item)
        }
    }

    companion object {
        private const val BOTTOM_SHEET_TAG = "com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment"
        fun show(fragmentManager: FragmentManager) {
            val bottomSheet = AddShoppingItemFragment()
            bottomSheet.show(fragmentManager, BOTTOM_SHEET_TAG)
        }
    }



}