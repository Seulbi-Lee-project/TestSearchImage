package com.example.testsearchimage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsearchimage.databinding.ActivityMainBinding
import com.example.testsearchimage.databinding.FragmentMyBoxBinding

class MyBoxFragment : Fragment() {

    private val binding by lazy { FragmentMyBoxBinding.inflate(layoutInflater) }
    private var datalist = mutableListOf<ImageModel>()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        datalist = mainActivity.likedItems
        val adapter = MyBoxAdapter(datalist, mContext)
        binding.myBoxRecyclerView.adapter = adapter
        binding.myBoxRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        return binding.root
    }
}