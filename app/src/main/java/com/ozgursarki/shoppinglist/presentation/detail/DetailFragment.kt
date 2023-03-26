package com.ozgursarki.shoppinglist.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentDetailBinding
import com.ozgursarki.shoppinglist.databinding.FragmentHistoryBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.adapter.ShoppingListAdapter
import com.ozgursarki.shoppinglist.presentation.enum.ViewHolderType
import com.ozgursarki.shoppinglist.presentation.history.HistoryViewModel
import com.ozgursarki.shoppinglist.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: ShoppingListAdapter
    val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingListAdapter(viewHolderType = ViewHolderType.DETAIL_VIEWHOLDER) {

        }
        binding.detailRV.adapter = adapter

        val shoppingList = args.shoppingList
        binding.toolBar.title = DateUtil.getDateInTurkish(shoppingList.listID)

        detailViewModel.shoppingList.observe(viewLifecycleOwner) { shoppingListFromDatabase ->
            val shoppingArrayList = arrayListOf<ShoppingItem>()
            shoppingListFromDatabase.forEach {
                shoppingArrayList.add(it.copy())
            }
            adapter.setShoppingList(shoppingArrayList)
        }

        detailViewModel.getShoppingListWithItems(shoppingList.listID)


    }

}