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
                    date = DateUtil.getDateWithoutHour(Calendar.getInstance().timeInMillis),
                    type = spinner.selectedItem.toString()
                )
                addShoppingItemViewModel.insertShoppingItem(item)
            } else {
                it.second?.let { shoppingItem ->
                    addShoppingItemViewModel.updateShoppingItem(shoppingItem.copy(count = binding.countText.text.convertToInt() + shoppingItem.count))
                }
            }
            this.dismiss()
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
                binding.titleText.setBackgroundResource(R.color.BabyBlue)
                binding.bottomSheetConstrain.setBackgroundResource(R.color.BabyBlue)
                binding.minusButton.setBackgroundResource(R.color.BabyBlue)
                binding.plusButton.setBackgroundResource(R.color.BabyBlue)
                binding.countText.setBackgroundResource(R.color.BabyBlue)
                binding.shoppingItemName.setBackgroundResource(R.color.BabyBlue)
                binding.addButton.setBackgroundResource(R.color.BabyBlue)

            }
            1 -> {
                view?.setBackgroundResource(R.color.LightGreen)
                binding.titleText.setBackgroundResource(R.color.LightGreen)
                binding.bottomSheetConstrain.setBackgroundResource(R.color.LightGreen)
                binding.minusButton.setBackgroundResource(R.color.LightGreen)
                binding.plusButton.setBackgroundResource(R.color.LightGreen)
                binding.countText.setBackgroundResource(R.color.LightGreen)
                binding.shoppingItemName.setBackgroundResource(R.color.LightGreen)
                binding.addButton.setBackgroundResource(R.color.LightGreen)

            }
            2 -> {
                view?.setBackgroundResource(R.color.RedPink)
                binding.titleText.setBackgroundResource(R.color.RedPink)
                binding.bottomSheetConstrain.setBackgroundResource(R.color.RedPink)
                binding.minusButton.setBackgroundResource(R.color.RedPink)
                binding.plusButton.setBackgroundResource(R.color.RedPink)
                binding.countText.setBackgroundResource(R.color.RedPink)
                binding.shoppingItemName.setBackgroundResource(R.color.RedPink)
                binding.addButton.setBackgroundResource(R.color.RedPink)
            }
            3 -> {
                view?.setBackgroundResource(R.color.RedOrange)
                binding.titleText.setBackgroundResource(R.color.RedOrange)
                binding.bottomSheetConstrain.setBackgroundResource(R.color.RedOrange)
                binding.minusButton.setBackgroundResource(R.color.RedOrange)
                binding.plusButton.setBackgroundResource(R.color.RedOrange)
                binding.countText.setBackgroundResource(R.color.RedOrange)
                binding.shoppingItemName.setBackgroundResource(R.color.RedOrange)
                binding.addButton.setBackgroundResource(R.color.RedOrange)
            }
            4 -> {
                view?.setBackgroundResource(R.color.Violet)
                binding.titleText.setBackgroundResource(R.color.Violet)
                binding.bottomSheetConstrain.setBackgroundResource(R.color.Violet)
                binding.minusButton.setBackgroundResource(R.color.Violet)
                binding.plusButton.setBackgroundResource(R.color.Violet)
                binding.countText.setBackgroundResource(R.color.Violet)
                binding.shoppingItemName.setBackgroundResource(R.color.Violet)
                binding.addButton.setBackgroundResource(R.color.Violet)
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}