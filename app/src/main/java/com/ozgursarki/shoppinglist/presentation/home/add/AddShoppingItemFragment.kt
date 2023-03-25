package com.ozgursarki.shoppinglist.presentation.home.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozgursarki.shoppinglist.databinding.FragmentAddShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.extension.convertToInt
import com.ozgursarki.shoppinglist.util.DummyData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddShoppingItemFragment(
    private val listID: Long
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddShoppingItemBinding
    private val addShoppingItemViewModel: AddShoppingItemViewModel by viewModels()
    private lateinit var countText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoppingItemBinding.inflate(inflater)
        countText = binding.countText
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            if (binding.shoppingItemName.text.toString().isNotEmpty()) {
                val item = ShoppingItem(
                    name = binding.shoppingItemName.text.toString(),
                    count = countText.text.convertToInt(),
                    listID = listID
                )
                addShoppingItemViewModel.insertShoppingItem(item)
            }else {
                //Show Error
            }

        }

        binding.minusButton.setOnClickListener {
            if (countText.text.convertToInt() > 1) {
                countText.text = (countText.text.convertToInt() - 1).toString()
            }

        }

        binding.plusButton.setOnClickListener {
            countText.text = (countText.text.convertToInt() + 1).toString()
        }
    }

    companion object {
        private const val BOTTOM_SHEET_TAG = "com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment"
        fun show(fragmentManager: FragmentManager, listID: Long) {
            val bottomSheet = AddShoppingItemFragment(listID)
            bottomSheet.show(fragmentManager, BOTTOM_SHEET_TAG)
        }
    }



}