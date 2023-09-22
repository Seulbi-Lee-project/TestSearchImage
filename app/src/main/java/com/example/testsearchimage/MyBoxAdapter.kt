package com.example.testsearchimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testsearchimage.databinding.SearchItemBinding
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class MyBoxAdapter (val dataList: MutableList<ImageModel>, private val mContext: Context) : RecyclerView.Adapter<MyBoxAdapter.Holder>() {


    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    inner class Holder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.searchTitle
        val image = binding.searchImage
        val isLike = binding.searchIsLike
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: MyBoxAdapter.Holder, position: Int) {
        lateinit var bitmap: Bitmap

        holder.isLike.setImageResource(R.drawable.ic_heart_fill)

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
            dataList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.title.text = dataList[position].dataTime
        val mThread = Thread {
            try {
                val url: URL = URL(dataList[position].url)
                val conn = url.openConnection()
                conn.doInput
                conn.connect()
                val inputStream = conn.getInputStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                holder.image.setImageBitmap(bitmap)

            }catch (e: MalformedURLException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }
        }

        mThread.start()

        try {
            mThread.join()

        }catch (e:InterruptedException){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}