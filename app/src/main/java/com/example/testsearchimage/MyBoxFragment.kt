package com.example.testsearchimage

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
    //임시 데이터
    private var datalist = mutableListOf<MyImage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        datalist.add(MyImage("title3", "2020", "url", false))
        val adapter = SearchAdapter(datalist)
        binding.myBoxRecyclerView.adapter = adapter
        binding.myBoxRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        adapter.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

            }
        }

        return binding.root
    }
}