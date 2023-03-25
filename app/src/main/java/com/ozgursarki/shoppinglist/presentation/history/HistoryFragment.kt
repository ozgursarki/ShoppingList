package com.ozgursarki.shoppinglist.presentation.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHistoryBinding
import com.ozgursarki.shoppinglist.databinding.FragmentHomeScreenBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.HistoryListAdapter
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.HistoryListViewHolder
import com.ozgursarki.shoppinglist.presentation.home.HomeScreenViewModel
import com.ozgursarki.shoppinglist.util.SwipeToDeleteCallback
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

        adapter = HistoryListAdapter() {
            val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.historyRV.adapter = adapter

        val swipeGesture = object: SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val shoppingList = mutableListOf<ShoppingList>()
                adapter.getHistoryItemList().toMutableList().map { shoppingListFromAdapter ->
                    val localeShoppingList = shoppingListFromAdapter.copy()
                    shoppingList.add(localeShoppingList)
                }

                (viewHolder as HistoryListViewHolder).getShoppingList()
                    ?.let {
                        historyViewModel.removeListFromDatabase(it.listID)
                    }
                shoppingList.removeAt(position)
                val newArraylist = arrayListOf<ShoppingList>()
                shoppingList.forEach {
                    newArraylist.add(it)
                }
                adapter.setHistoryShoppingList(newArraylist)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.historyRV)

        historyViewModel.shoppingList.observe(viewLifecycleOwner) { shoppingListFromDatabase ->
            val shoppingArrayList = arrayListOf<ShoppingList>()
            shoppingListFromDatabase.forEach {
                Log.e("asşklaöfşas",it.toString())
                shoppingArrayList.add(it)
            }
            adapter.setHistoryShoppingList(shoppingArrayList)
        }

        binding.createShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_homeScreenFragment)
        }
    }

    override fun onResume() {
        super.onResume()

        historyViewModel.getAllShoppingList()
    }

}