package com.example.waveoffood.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waveoffood.FoodItem
import com.example.waveoffood.MenuAdapter
import com.example.waveoffood.R
import com.example.waveoffood.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        val foodItem = mutableListOf(
            FoodItem("Burger", "With cheese", "₹120", R.drawable.menu1),
            FoodItem("Pizza", "Cheesy Delight", "₹250", R.drawable.menu6),
            FoodItem("Pasta", "Creamy white sauce pasta", "₹200", R.drawable.menu2),
            FoodItem("Sandwich", "Grilled veg sandwich", "₹80", R.drawable.menu3),
            FoodItem("Biryani", "Spicy chicken biryani", "₹180", R.drawable.menu4),
            FoodItem("Fries", "Crispy French fries", "₹70", R.drawable.menu5),
            FoodItem("Momos", "Steamed chicken momos", "₹110", R.drawable.menu7),
            FoodItem("Paneer Tikka", "Tandoori paneer cubes", "₹160", R.drawable.menu1),
            FoodItem("Spring Roll", "Veg spring rolls with dip", "₹90", R.drawable.menu2),
            FoodItem("Taco", "Mexican style chicken taco", "₹140", R.drawable.menu3),
            FoodItem("Chowmein", "Hakka noodles with veggies", "₹100", R.drawable.menu4),
            FoodItem("Ice Cream", "Vanilla scoop with chocolate", "₹60", R.drawable.menu5),
            FoodItem("Cold Coffee", "Chilled coffee with ice cream", "₹90", R.drawable.menu6)
        )

        val adapter = MenuAdapter(foodItem)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        binding.menuRecyclerView.setHasFixedSize(true)


        binding.buttonBack.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    companion object {
    }
}