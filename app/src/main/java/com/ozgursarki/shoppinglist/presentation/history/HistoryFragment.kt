package com.ozgursarki.shoppinglist.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHistoryBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.HistoryListAdapter
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.HistoryListViewHolder
import com.ozgursarki.shoppinglist.util.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyListAdapter: HistoryListAdapter
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

        historyListAdapter = HistoryListAdapter {
            val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        val swipeGesture = object: SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val shoppingList = mutableListOf<ShoppingList>()
                historyListAdapter.getHistoryItemList().toMutableList().map { shoppingListFromAdapter ->
                    val localeShoppingList = shoppingListFromAdapter.copy()
                    shoppingList.add(localeShoppingList)
                }

                (viewHolder as HistoryListViewHolder).getShoppingList()
                    ?.let {
                        historyViewModel.deleteShoppingList(it.listID)
                        historyViewModel.removeAllRelatedItems(it.listID)
                    }
                shoppingList.removeAt(position)
                val newArraylist = arrayListOf<ShoppingList>()
                shoppingList.forEach {
                    newArraylist.add(it)
                }
                historyListAdapter.setHistoryShoppingList(newArraylist)
                historyViewModel.getAllShoppingList()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.historyRV)
        val mDividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        with(binding.historyRV) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = historyListAdapter
            addItemDecoration(mDividerItemDecoration)
        }

        historyViewModel.shoppingList.observe(viewLifecycleOwner) { shoppingListFromDatabase ->
            handleShoppingList(shoppingListFromDatabase)
        }

        binding.createShoppingList.setOnClickListener {
            historyViewModel.deleteShoppingList(historyViewModel.getListID())
            historyViewModel.deleteShoppingItemsFromDatabase()
            findNavController().navigate(R.id.action_historyFragment_to_homeScreenFragment)
        }
    }

    private fun handleShoppingList(shoppingList: List<ShoppingList>) {
        val shoppingArrayList = arrayListOf<ShoppingList>()
        shoppingList.forEach {
            shoppingArrayList.add(it.copy())
        }

        if (shoppingArrayList.isEmpty()) {
            binding.apply {
                historyRV.visibility = View.GONE
                viewNoData.visibility = View.VISIBLE
            }
        }else {
            binding.apply {
                historyRV.visibility = View.VISIBLE
                viewNoData.visibility = View.GONE
            }
            historyListAdapter.setHistoryShoppingList(shoppingArrayList)

        }
    }

    override fun onResume() {
        super.onResume()

        historyViewModel.getAllShoppingList()
    }

}