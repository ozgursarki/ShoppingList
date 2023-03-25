package com.ozgursarki.shoppinglist.presentation.home

import com.ozgursarki.shoppinglist.presentation.home.add.AddShoppingItemFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHomeScreenBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.ShoppingListAdapter
import com.ozgursarki.shoppinglist.util.DateUtil
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingListAdapter() {
            homeScreenViewModel.updateShoppingItem(it)
        }
        binding.shoppingListRV.adapter = adapter

        binding.addShoppingItemButton.setOnClickListener {
            AddShoppingItemFragment.show(
                requireActivity().supportFragmentManager,
                homeScreenViewModel.shoppingList.listID
            )
        }

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
            } else {
                //Show Error
            }
        }

        binding.toolBar.findViewById<ActionMenuItemView>(R.id.delete).setOnClickListener {
            adapter.setShoppingList(arrayListOf())
            homeScreenViewModel.deleteShoppingList()
        }

    }

    override fun onResume() {
        super.onResume()

        if (!homeScreenViewModel.isListCreated()) {
            val date = Calendar.getInstance(DateUtil.LOCALE)
            val shoppingList = ShoppingList(date.timeInMillis)
            homeScreenViewModel.insertShoppingList(shoppingList)
            homeScreenViewModel.shoppingList = shoppingList
        }
        homeScreenViewModel.getShoppingListWithItems(homeScreenViewModel.shoppingList.listID)

    }

    private fun handleHomeUIState(homeScreenUiState: HomeScreenUIState) {
        val shoppingArrayList = arrayListOf<ShoppingItem>()
        homeScreenUiState.shoppingList.forEach {
            shoppingArrayList.add(it.copy()) //reference bug fixed
        }
        adapter.setShoppingList(shoppingArrayList)
    }

}