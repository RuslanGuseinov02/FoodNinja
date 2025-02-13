package com.ruslan.huseynov.foodninja.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ruslan.huseynov.foodninja.data.entity.Restaurant
import com.ruslan.huseynov.foodninja.databinding.RecyclerRowRestaurantBinding

class RestaurantAdapter(private val restaurantList: List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder>() {

    class RestaurantHolder(val binding: RecyclerRowRestaurantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {

        val binding = RecyclerRowRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {

        val restaurant = restaurantList[position]
        holder.binding.ivName.text = restaurant.restaurantName

        Glide.with(holder.itemView.context).load(restaurant.image).diskCacheStrategy(
            DiskCacheStrategy.NONE).override(100,100).into(holder.binding.ivLogo)
    }

    override fun getItemCount(): Int = restaurantList.size
}
