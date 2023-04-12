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
import com.ozgursarki.shoppinglist.util.DateUtil
import com.ozgursarki.shoppinglist.util.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

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

        val swipeGesture = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val shoppingList = mutableListOf<ShoppingList>()
                historyListAdapter.getHistoryItemList().toMutableList()
                    .map { shoppingListFromAdapter ->
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
                historyViewModel.getListByDate()
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

        historyViewModel.historyUIState.observe(viewLifecycleOwner) { shoppingListFromDatabase ->
            handleShoppingList(shoppingListFromDatabase)
        }

        binding.backButton.setOnClickListener {
            historyViewModel.changeDate(false)
            historyViewModel.getListByDate()
        }

        binding.forwardButton.setOnClickListener {
            historyViewModel.changeDate(true)
            historyViewModel.getListByDate()
        }

        binding.createShoppingList.setOnClickListener {
            historyViewModel.deleteShoppingList(historyViewModel.getListID())
            historyViewModel.deleteShoppingItemsFromDatabase()
            val action = historyViewModel.historyUIState.value?.date?.let { it1 ->
                HistoryFragmentDirections.actionHistoryFragmentToHomeScreenFragment(
                    it1.timeInMillis
                )
            }
            action?.let { it1 -> findNavController().navigate(it1) }
        }
    }

    private fun handleShoppingList(historyUIState: HistoryUIState) {
        val shoppingArrayList = arrayListOf<ShoppingList>()
        historyUIState.list.forEach {
            shoppingArrayList.add(it.copy())
        }

        if (shoppingArrayList.isEmpty()) {
            binding.apply {
                historyRV.visibility = View.GONE
                viewNoData.visibility = View.VISIBLE
                date.text = DateUtil.parseDateText(requireContext(), historyUIState.date)
            }
        } else {
            binding.apply {
                historyRV.visibility = View.VISIBLE
                viewNoData.visibility = View.GONE
                date.text = DateUtil.parseDateText(requireContext(), historyUIState.date)
            }
            historyListAdapter.setHistoryShoppingList(shoppingArrayList)

        }
    }

    override fun onResume() {
        super.onResume()

        historyViewModel.getListByDate()
    }


}