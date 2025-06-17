package com.example.waveoffood

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waveoffood.databinding.CartItemBinding


class CartAdapter (
   private var cartList: MutableList<FoodItem>
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
      val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
      val item = cartList[position]
        holder.binding.cartTitle.text = item.name
        holder.binding.cartPrice.text = item.price
        holder.binding.imageCart.setImageResource(item.imageResourceId)
        holder.binding.textQuantity.text = item.quantity.toString()

        holder.binding.buttonPlus.setOnClickListener {
           increaseQuantity(position)
        }
        holder.binding.buttonMinus.setOnClickListener {
            decreaseQuantity(position)
        }
        holder.binding.buttonDelete.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                deleteItem(itemPosition)
            }
        }
    }
    private fun deleteItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartList.size )
    }
    private fun decreaseQuantity(position: Int) {
        val item = cartList[position]
        if (item.quantity > 1) {
            item.quantity--
            notifyItemChanged(position)
        }
    }
    private fun increaseQuantity(position: Int) {
        val item = cartList[position]
        if (item.quantity < 10){
            item.quantity++
            notifyItemChanged(position)
        }
    }


    class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

