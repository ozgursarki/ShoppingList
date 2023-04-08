package com.ozgursarki.shoppinglist.presentation.detail

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ozgursarki.shoppinglist.databinding.FragmentDetailBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.ShoppingListAdapter
import com.ozgursarki.shoppinglist.presentation.enum.ViewHolderType
import com.ozgursarki.shoppinglist.util.DateUtil
import com.ozgursarki.shoppinglist.util.ListHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: ShoppingListAdapter
    private val args: DetailFragmentArgs by navArgs()
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

        adapter = ShoppingListAdapter(
            viewHolderType = ViewHolderType.DETAIL_VIEWHOLDER,
            buttonCallback = {},
            itemChecked = {
                detailViewModel.updateShoppingItem(it)
            })
        binding.detailRV.adapter = adapter

        val shoppingList = args.shoppingList
        if (Resources.getSystem().configuration.locales.get(0).language == "tr") {
            binding.toolBar.title = DateUtil.getDateInTurkish(shoppingList.listID)
        }else {
            binding.toolBar.title = DateUtil.getDate(shoppingList.listID)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailViewModel.shoppingList.collect {
                        handleDetailUIState(it)
                    }
                }
            }
        }


        detailViewModel.getShoppingListWithItems(shoppingList.listID)


    }

    private fun handleDetailUIState(shoppingList: List<Any>) {
        adapter.setShoppingList(ListHelper.convertToList(shoppingList))
        val ratio = adapter.calculateRatio()
        binding.progressBarRate.text = "%$ratio"
        detailViewModel.updateShoppingList(ratio)
        val progressAnimator = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, ratio)
        progressAnimator.duration = 2000
        progressAnimator.start()

    }

}