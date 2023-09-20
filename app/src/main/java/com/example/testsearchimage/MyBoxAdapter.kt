package com.example.testsearchimage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testsearchimage.databinding.SearchItemBinding

class MyBoxAdapter (val mItems: MutableList<MyImage>) : RecyclerView.Adapter<MyBoxAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    inner class Holder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.searchTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: MyBoxAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.title.text = mItems[position].title
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}