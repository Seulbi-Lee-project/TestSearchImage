package com.example.testsearchimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsearchimage.databinding.ActivityMainBinding
import com.example.testsearchimage.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
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
        datalist.add(MyImage("title2", "2020", "url", false))
        val adapter = SearchAdapter(datalist)
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

            }
        }

        return binding.root
    }
}