package com.example.testsearchimage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.Callback
import android.widget.Toast.makeText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsearchimage.databinding.ActivityMainBinding
import com.example.testsearchimage.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Response

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
        binding.searchButton.setOnClickListener {
            retrofitWork()
        }

        return binding.root
    }

    private fun retrofitWork() {
        val service = RetrofitApi.kakaoService
        service.getKakaoImage(getString(R.string.api_key), "포도").enqueue(object : retrofit2.Callback<KakaoResponse> {
            override fun onResponse(call: Call<KakaoResponse>, response: Response<KakaoResponse>) {
                if(response.isSuccessful){
                    //Log.d("TAG", response.body().toString())
                    val result = response.body()?.documents
                    val adapter = result?.let { SearchAdapter(it) }
                    binding.searchRecyclerView.adapter = adapter
                    binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }

            override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
}