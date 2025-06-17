package com.example.waveoffood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waveoffood.databinding.PopularItemBinding

class PopularAdapter(
    private val popularList:List<FoodItem>
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
            val item = popularList[position]
            holder.binding.itemTitle.text = item.name
            holder.binding.itemDescription.text = item.description
            holder.binding.itemPrice.text = item.price
            holder.binding.itemImage.setImageResource(item.imageResourceId)
            holder.binding.buttonAddToCart.setOnClickListener {
                // Handle add to cart
            }


    }

    class PopularViewHolder(val binding : PopularItemBinding):RecyclerView.ViewHolder(binding.root) {


    }
}

