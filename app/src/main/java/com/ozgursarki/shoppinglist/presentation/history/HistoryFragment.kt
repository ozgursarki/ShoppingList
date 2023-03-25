package com.ozgursarki.shoppinglist.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHistoryBinding
import com.ozgursarki.shoppinglist.databinding.FragmentHomeScreenBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.HistoryListAdapter
import com.ozgursarki.shoppinglist.presentation.home.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryListAdapter
    private val historyViewModel: HistoryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryListAdapter()
        binding.historyRV.adapter = adapter

        historyViewModel.shoppingList.observe(viewLifecycleOwner) { shoppingListFromDatabase ->
            val shoppingArrayList = arrayListOf<ShoppingList>()
            shoppingListFromDatabase.forEach {
                shoppingArrayList.add(it)
            }
            adapter.setHistoryShoppingList(shoppingArrayList)
        }
    }

    override fun onResume() {
        super.onResume()

        historyViewModel.getAllShoppingList()
    }

}