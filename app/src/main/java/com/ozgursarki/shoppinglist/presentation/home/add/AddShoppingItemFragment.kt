package com.ozgursarki.shoppinglist.presentation.home.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentAddShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.extension.convertToInt
import com.ozgursarki.shoppinglist.util.DateUtil
import com.ozgursarki.shoppinglist.util.PopUpHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class AddShoppingItemFragment(
    private val listID: Long
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

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

        val spinner: Spinner = binding.bottomSheetSpinner

        spinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.item_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }


        binding.addButton.setOnClickListener {
            if (binding.shoppingItemName.text.toString().isNotEmpty()) {
                addShoppingItemViewModel.getShoppingItemsByListID(listID, binding.shoppingItemName.text.toString())
            }else {
                PopUpHelper.showErrorPopUp(requireActivity().getString(R.string.error_empty_name), requireContext())
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

        addShoppingItemViewModel.isItemExist.observe(viewLifecycleOwner) {
            if (it.first) {
                val item = ShoppingItem(
                    name = binding.shoppingItemName.text.toString(),
                    count = countText.text.convertToInt(),
                    listID = listID,
                    date = DateUtil.getDateInTurkishWithoutHour(Calendar.getInstance().timeInMillis),
                    type = spinner.selectedItem.toString()
                )
                addShoppingItemViewModel.insertShoppingItem(item)
            } else {
                it.second?.let { shoppingItem ->
                    addShoppingItemViewModel.updateShoppingItem(shoppingItem.copy(count = binding.countText.text.convertToInt() + shoppingItem.count))
                }
            }
        }
    }

    companion object {
        private const val BOTTOM_SHEET_TAG = "com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment"
        fun show(fragmentManager: FragmentManager, listID: Long) {
            val bottomSheet = AddShoppingItemFragment(listID)
            bottomSheet.setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
            bottomSheet.show(fragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position) {
            0 -> {
                view?.setBackgroundResource(R.color.BabyBlue)
            }
            1 -> {
                view?.setBackgroundResource(R.color.LightGreen)
            }
            2 -> {
                view?.setBackgroundResource(R.color.RedPink)
            }
            3 -> {
                view?.setBackgroundResource(R.color.RedOrange)
            }
            4 -> {
                view?.setBackgroundResource(R.color.Violet)
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}