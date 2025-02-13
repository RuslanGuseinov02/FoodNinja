package com.ruslan.huseynov.foodninja.ui.adapter

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ruslan.huseynov.foodninja.data.entity.Food
import com.ruslan.huseynov.foodninja.databinding.RecyclerRowBinding
import com.ruslan.huseynov.foodninja.ui.view.HomeFragmentDirections

class FoodAdapter(private val foodList : List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    class FoodHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {

        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    @SuppressLint("CommitPrefEdits", "SetTextI18n")
    override fun onBindViewHolder(holder: FoodHolder, position: Int) {

        val food = foodList[position]
        val convertedPrice = food.foodPrice.toDouble() *  0.050

        holder.binding.foodName.text = food.foodName
        holder.binding.foodPrice.text = "$convertedPrice AZN"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodPhotoName}"
        Glide.with(holder.itemView.context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.binding.foodPhoto)

        holder.itemView.setOnClickListener {

            val action = HomeFragmentDirections.actionDetails(food)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.ivHeart.setOnClickListener {

            holder.binding.ivHeart.visibility = View.INVISIBLE
            holder.binding.ivHeartRed.visibility = View.VISIBLE

            val db = holder.itemView.context.getSharedPreferences("like", MODE_PRIVATE)
            val edit = db.edit()

            edit.putBoolean("heart_${position}", true)
            edit.apply()
        }

        holder.binding.ivHeartRed.setOnClickListener {

            holder.binding.ivHeart.visibility = View.VISIBLE
            holder.binding.ivHeartRed.visibility = View.INVISIBLE

            val db = holder.itemView.context.getSharedPreferences("like", MODE_PRIVATE)
            val edit = db.edit()

            edit.putBoolean("heart_${position}", false)
            edit.apply()
        }

        val db = holder.itemView.context.getSharedPreferences("like", MODE_PRIVATE)
        val heart = db.getBoolean("heart_${position}", false)

        if (heart) {
            holder.binding.ivHeart.visibility = View.INVISIBLE
            holder.binding.ivHeartRed.visibility = View.VISIBLE
        } else {
            holder.binding.ivHeart.visibility = View.VISIBLE
            holder.binding.ivHeartRed.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = foodList.size
}