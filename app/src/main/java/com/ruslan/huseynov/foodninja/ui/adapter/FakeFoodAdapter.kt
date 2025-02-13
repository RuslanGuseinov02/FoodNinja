package com.ruslan.huseynov.foodninja.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ruslan.huseynov.foodninja.data.entity.FakeFood
import com.ruslan.huseynov.foodninja.databinding.RecyclerRowFakeBinding

class FakeFoodAdapter(private val fakeFoodList : List<FakeFood>) : RecyclerView.Adapter<FakeFoodAdapter.FakeFoodHolder>() {

    class FakeFoodHolder(val binding: RecyclerRowFakeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FakeFoodHolder {

        val binding = RecyclerRowFakeBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FakeFoodHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FakeFoodHolder, position: Int) {

        val fakeFood = fakeFoodList[position]
        holder.binding.tvFakeResName.text = fakeFood.restaurantName
        holder.binding.tvFakeName.text = fakeFood.foodName
        holder.binding.tvFakePrice.text = "${fakeFood.foodPrice} AZN"

        Glide.with(holder.itemView.context).load(fakeFood.foodPhoto).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.binding.ivFakePhoto)
    }

    override fun getItemCount(): Int = fakeFoodList.size
}