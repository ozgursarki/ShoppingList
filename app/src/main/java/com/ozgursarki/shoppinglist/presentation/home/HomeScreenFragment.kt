package com.ozgursarki.shoppinglist.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.FragmentHomeScreenBinding
import com.ozgursarki.shoppinglist.presentation.adapter.ShoppingListAdapter
import com.ozgursarki.shoppinglist.util.DummyData

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var adapter : ShoppingListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingListAdapter(DummyData.dummyShoppingItems())
        binding.shoppingListRV.adapter = adapter
    }

}