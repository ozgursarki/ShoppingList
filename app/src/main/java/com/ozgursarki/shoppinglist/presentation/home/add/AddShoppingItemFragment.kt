package com.ozgursarki.shoppinglist.presentation.home.add

import android.content.res.Resources
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
import com.ozgursarki.shoppinglist.presentation.enum.ItemType
import com.ozgursarki.shoppinglist.presentation.enum.generateItemType
import com.ozgursarki.shoppinglist.util.DateUtil
import com.ozgursarki.shoppinglist.util.PopUpHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


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
                var type = "en"
                type = if (Resources.getSystem().configuration.locales.get(0).language == "tr") {
                    generateItemType(spinner.selectedItem.toString()).type
                }else {
                    spinner.selectedItem.toString()
                }

                val item = ShoppingItem(
                    name = binding.shoppingItemName.text.toString(),
                    count = countText.text.convertToInt(),
                    listID = listID,
                    date = DateUtil.getDateWithoutHour(Calendar.getInstance().timeInMillis),
                    type = type
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
                binding.apply {
                    titleText.setBackgroundResource(R.color.BabyBlue)
                    bottomSheetConstrain.background.setTint(requireContext().getColor(R.color.BabyBlue))
                    minusButton.setBackgroundResource(R.color.BabyBlue)
                    plusButton.setBackgroundResource(R.color.BabyBlue)
                    countText.setBackgroundResource(R.color.BabyBlue)
                    shoppingItemName.setBackgroundResource(R.color.BabyBlue)
                }

                }

            1 -> {
                view?.setBackgroundResource(R.color.LightGreen)
                binding.apply {
                    titleText.setBackgroundResource(R.color.LightGreen)
                    bottomSheetConstrain.background.setTint(requireContext().getColor(R.color.LightGreen))
                    minusButton.setBackgroundResource(R.color.LightGreen)
                    plusButton.setBackgroundResource(R.color.LightGreen)
                    countText.setBackgroundResource(R.color.LightGreen)
                    shoppingItemName.setBackgroundResource(R.color.LightGreen)
                }

            }
            2 -> {
                view?.setBackgroundResource(R.color.RedPink)
                binding.apply {
                    titleText.setBackgroundResource(R.color.RedPink)
                    bottomSheetConstrain.background.setTint(requireContext().getColor(R.color.RedPink))
                    minusButton.setBackgroundResource(R.color.RedPink)
                    plusButton.setBackgroundResource(R.color.RedPink)
                    countText.setBackgroundResource(R.color.RedPink)
                    shoppingItemName.setBackgroundResource(R.color.RedPink)
                }
            }
            3 -> {
                view?.setBackgroundResource(R.color.RedOrange)
                binding.apply {
                    titleText.setBackgroundResource(R.color.RedOrange)
                    bottomSheetConstrain.background.setTint(requireContext().getColor(R.color.RedOrange))
                    minusButton.setBackgroundResource(R.color.RedOrange)
                    plusButton.setBackgroundResource(R.color.RedOrange)
                    countText.setBackgroundResource(R.color.RedOrange)
                    shoppingItemName.setBackgroundResource(R.color.RedOrange)
                }
            }
            4 -> {
                view?.setBackgroundResource(R.color.Violet)
                binding.apply {
                    titleText.setBackgroundResource(R.color.Violet)
                    bottomSheetConstrain.background.setTint(requireContext().getColor(R.color.Violet))
                    minusButton.setBackgroundResource(R.color.Violet)
                    plusButton.setBackgroundResource(R.color.Violet)
                    countText.setBackgroundResource(R.color.Violet)
                    shoppingItemName.setBackgroundResource(R.color.Violet)
                }
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}