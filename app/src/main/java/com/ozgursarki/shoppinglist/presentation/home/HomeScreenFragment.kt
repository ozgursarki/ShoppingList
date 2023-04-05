package com.ozgursarki.shoppinglist.presentation.home


import com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHomeScreenBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.ShoppingListAdapter
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.DetailListViewHolder
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.HeaderViewHolder
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.ShoppingListViewHolder
import com.ozgursarki.shoppinglist.presentation.enum.ToolTipLocation
import com.ozgursarki.shoppinglist.presentation.enum.ViewHolderType
import com.ozgursarki.shoppinglist.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var adapter: ShoppingListAdapter
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater)

        if (homeScreenViewModel.getListID() == -1L) {
            val date = Calendar.getInstance(DateUtil.LOCALE)
            val shoppingList = ShoppingList(date.timeInMillis)
            homeScreenViewModel.insertShoppingList(shoppingList)
            homeScreenViewModel.saveListID(shoppingList.listID)
            homeScreenViewModel.getShoppingListWithItems(homeScreenViewModel.getListID()) {
                binding.animationView.visibility = View.VISIBLE
            }
        } else {
            homeScreenViewModel.insertShoppingList(ShoppingList(homeScreenViewModel.getListID()))
            homeScreenViewModel.getShoppingListWithItems(homeScreenViewModel.getListID()) {
                binding.animationView.visibility = View.VISIBLE
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingListAdapter(
            viewHolderType = ViewHolderType.SHOPPING_VIEWHOLDER,
            buttonCallback = {
                homeScreenViewModel.updateShoppingItem(it)
            })
        binding.shoppingListRV.adapter = adapter

        binding.addShoppingItemButton.setOnClickListener {
            AddShoppingItemFragment.show(
                requireActivity().supportFragmentManager,
                homeScreenViewModel.getListID()
            )
        }

        val swipeGesture = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is HeaderViewHolder) {
                    return
                }
                val position = viewHolder.adapterPosition
                val shoppingList = mutableListOf<Any>()
                adapter.getShoppingList().toMutableList().map { shoppingListFromAdapter ->
                    if (shoppingListFromAdapter is ShoppingHeader) {
                        shoppingList.add(ShoppingHeader((shoppingListFromAdapter as ShoppingHeader).title))
                    } else {
                        shoppingList.add((shoppingListFromAdapter as ShoppingItem).copy())
                    }
                }
                when (viewHolder) {
                    is ShoppingListViewHolder -> {
                        viewHolder.getShoppingItem()
                            ?.let {
                                homeScreenViewModel.deleteShoppingItemFromDatabase(it.itemID)
                            }

                        shoppingList.removeAt(position)
                        val newArraylist = arrayListOf<Any>()
                        shoppingList.forEach {
                            newArraylist.add(it)
                        }
                        adapter.setShoppingList(newArraylist)
                    }
                    is DetailListViewHolder -> {
                        viewHolder.getShoppingItem()
                            ?.let {
                                homeScreenViewModel.deleteShoppingItemFromDatabase(it.itemID)
                            }

                        shoppingList.removeAt(position)
                        val newArraylist = arrayListOf<Any>()
                        shoppingList.forEach {
                            newArraylist.add(it)
                        }
                        adapter.setShoppingList(newArraylist)
                    }
                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.shoppingListRV)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeScreenViewModel.uiState.collect {
                        handleHomeUIState(it)
                    }
                }
            }
        }

        binding.toolBar.findViewById<ActionMenuItemView>(R.id.save).setOnClickListener {
            if (!adapter.isListEmpty()) {
                findNavController().navigate(R.id.action_homeScreenFragment_to_historyFragment)
                homeScreenViewModel.saveListID(-1L)
            } else {
                PopUpHelper.showErrorPopUp(
                    requireActivity().getString(R.string.error_empty_list),
                    requireContext()
                )
            }
        }

        binding.toolBar.findViewById<ActionMenuItemView>(R.id.navigate).setOnClickListener {
            homeScreenViewModel.deleteShoppingList()
            findNavController().navigate(R.id.action_homeScreenFragment_to_historyFragment)
        }

        binding.toolBar.findViewById<ActionMenuItemView>(R.id.delete).setOnClickListener {
            homeScreenViewModel.deleteShoppingItemsFromDatabase(homeScreenViewModel.getListID())
        }

    }

    override fun onResume() {
        super.onResume()

        if (!homeScreenViewModel.getAdd()) {
            BalloonHelper.createToolTip(
                requireContext(),
                viewLifecycleOwner,
                binding.addShoppingItemButton,
                R.color.black,
                getString(R.string.add_button_tooltip),
                toolTipLocation = ToolTipLocation.TOP,
                onDismiss = {
                    homeScreenViewModel.saveAdd()
                }
            )
        }





    }

    private fun handleHomeUIState(homeScreenUiState: HomeScreenUIState) {
        val shoppingArrayList = arrayListOf<Any>()
        homeScreenUiState.shoppingList.forEach {
            if (it is ShoppingItem) {
                shoppingArrayList.add(it.copy()) //reference bug fixed
            } else {
                shoppingArrayList.add(ShoppingHeader((it as ShoppingHeader).title))
            }

        }
        var check = false
        homeScreenUiState.shoppingList.forEach {
            if (it is ShoppingItem) {
                check = true
            }
        }
        if (!check) {
            binding.apply {
                shoppingListRV.visibility = View.GONE
                viewNoData.visibility = View.VISIBLE
                toolBar.findViewById<ActionMenuItemView>(R.id.delete).visibility = View.GONE
            }
        } else {
            binding.animationView.visibility = View.GONE
            binding.apply {
                shoppingListRV.visibility = View.VISIBLE
                viewNoData.visibility = View.GONE
                toolBar.findViewById<ActionMenuItemView>(R.id.delete).visibility = View.VISIBLE

                if (!homeScreenViewModel.getDelete() && !homeScreenViewModel.getHistory() && !homeScreenViewModel.getSave()) {
                    BalloonHelper.createToolTip(
                        requireContext(),
                        viewLifecycleOwner,
                        binding.toolBar.findViewById(R.id.save),
                        R.color.black,
                        getString(R.string.save_button_tooltip),
                        toolTipLocation = ToolTipLocation.BOTTOM,
                        onDismiss = {
                            BalloonHelper.createToolTip(
                                requireContext(),
                                viewLifecycleOwner,
                                binding.toolBar.findViewById(R.id.navigate),
                                R.color.black,
                                getString(R.string.navigate_tooltip),
                                toolTipLocation = ToolTipLocation.BOTTOM,
                                onDismiss = {
                                    BalloonHelper.createToolTip(
                                        requireContext(),
                                        viewLifecycleOwner,
                                        binding.toolBar.findViewById(R.id.delete),
                                        R.color.black,
                                        getString(R.string.delete_button_tooltip),
                                        toolTipLocation = ToolTipLocation.BOTTOM,
                                        onDismiss = {
                                            homeScreenViewModel.apply {
                                                saveDelete()
                                                saveHistory()
                                                saveSave()
                                            }
                                        }
                                    )
                                }
                            )
                        }
                    )
                }

            }
            adapter.setShoppingList(shoppingArrayList)

        }
    }


}