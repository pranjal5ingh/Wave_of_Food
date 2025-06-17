package com.example.waveoffood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waveoffood.CartAdapter
import com.example.waveoffood.FoodItem
import com.example.waveoffood.R
import com.example.waveoffood.databinding.FragmentCartBinding


@Suppress("UNREACHABLE_CODE")
class CartFragment : Fragment() {

    private val binding: FragmentCartBinding by lazy {
        FragmentCartBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = binding.root


        val foodItem = mutableListOf(
            FoodItem("Burger", "With cheese", "₹120", R.drawable.menu1,1),
            FoodItem("Pizza", "Cheesy Delight", "₹250", R.drawable.menu6,1),
            FoodItem("Pasta", "Creamy white sauce pasta", "₹200", R.drawable.menu2,1),
            FoodItem("Sandwich", "Grilled veg sandwich", "₹80", R.drawable.menu3,1),
            FoodItem("Biryani", "Spicy chicken biryani", "₹180", R.drawable.menu4,1),
            FoodItem("Fries", "Crispy French fries", "₹70", R.drawable.menu5,1),
        )

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CartAdapter(foodItem)
        binding.cartRecyclerView.adapter = adapter

        return view
    }

    companion object {

    }
}