package com.example.waveoffood


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waveoffood.databinding.MenuItemBinding

class MenuAdapter(
    private val menuList: MutableList<FoodItem>
 ): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
      val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
       holder.binding.menuTitle.text = menuList[position].name
        holder.binding.menuDescription.text = menuList[position].description
        holder.binding.menuitemPrice.text = menuList[position].price
        holder.binding.menuImage.setImageResource(menuList[position].imageResourceId)

    }
    inner class MenuViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}