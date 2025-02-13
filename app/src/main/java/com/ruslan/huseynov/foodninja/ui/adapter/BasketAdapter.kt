package com.ruslan.huseynov.foodninja.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ruslan.huseynov.foodninja.data.entity.Basket
import com.ruslan.huseynov.foodninja.databinding.RecyclerRowBasketBinding
import com.ruslan.huseynov.foodninja.ui.viewmodel.BasketViewModel

class BasketAdapter(private val basketList: MutableList<Basket>,private val viewModel : BasketViewModel) : RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    class BasketHolder(val binding : RecyclerRowBasketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {

        val binding = RecyclerRowBasketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BasketHolder, position: Int) {

        val basket = basketList[position]
        val convertedPrice = basket.basketFoodPrice.toDouble() *  0.050
        val totalPrice = convertedPrice * basket.basketFoodOrderAmount.toInt()

        holder.binding.basketName.text = basket.basketFoodName
        holder.binding.basketPrice.text = "$convertedPrice AZN"
        holder.binding.basketPieces.text = "${basket.basketFoodOrderAmount} Pieces"
        holder.binding.basketTotalPrice.text = "$totalPrice AZN"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.basketFoodPhoto}"
        Glide.with(holder.itemView.context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.binding.basketPhoto)

        holder.binding.basketDelete.setOnClickListener {

            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Delete Food")
            alert.setMessage("Are you sure you want to delete this food?")
            alert.setPositiveButton("Yes"){ _, _ ->

                basketList.removeAt(position)

                notifyItemRemoved(position)
                notifyItemRangeChanged(position,basketList.size)

                viewModel.deleteBaskte(basket.basketFoodId.toInt(),"ruslan")
                viewModel.totalPrice(basketList)

                if (basketList.isEmpty()){
                    notifyDataSetChanged()
                }
            }
            alert.setNegativeButton("No"){ which, _ ->

                which.dismiss()
            }
            alert.show()
        }
    }

    override fun getItemCount(): Int = basketList.size
}