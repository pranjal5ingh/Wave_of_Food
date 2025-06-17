package com.example.waveoffood.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.example.waveoffood.FoodItem
import com.example.waveoffood.MenuAdapter
import com.example.waveoffood.R
import com.example.waveoffood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    private val originalMenuList = mutableListOf(
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

    private val filteredMenuList = mutableListOf<FoodItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = MenuAdapter(filteredMenuList)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        setupSearchView()
        showMenuItems()

        return binding.root
    }

    private fun showMenuItems() {
        filteredMenuList.clear()
        filteredMenuList.addAll(originalMenuList)
        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String?) {
        filteredMenuList.clear()

        if (query.isNullOrEmpty()) {
            filteredMenuList.addAll(originalMenuList)
        } else {
            val filtered = originalMenuList.filter {
                it.name.contains(query, ignoreCase = true)
            }
            filteredMenuList.addAll(filtered)
        }

        adapter.notifyDataSetChanged()
    }

    companion object
}
