package com.example.waveoffood.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.waveoffood.FoodItem
import com.example.waveoffood.PopularAdapter
import com.example.waveoffood.R
import com.example.waveoffood.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view = binding.root
        binding.textViewEnd.setOnClickListener {
            val bottomSheetFragment = MenuBottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1 , ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3 , ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.startSliding()

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
            }
            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "Selected Image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })

        val foodItem = listOf(
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

        val adapter = PopularAdapter(foodItem)
        binding.popularItemView.layoutManager = LinearLayoutManager(requireContext())
        binding.popularItemView.adapter = adapter
    }

    companion object {
    }
}