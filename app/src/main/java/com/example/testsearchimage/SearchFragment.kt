package com.example.testsearchimage

import android.content.Context
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
    private var imageItems: ArrayList<ImageModel> = ArrayList()
    private lateinit var mContext:Context

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

        binding.searchButton.setOnClickListener {
            val query = binding.searchEdit.text.toString()
            retrofitWork(query)
        }

        return binding.root
    }

    private fun retrofitWork(query:String) {
        val service = RetrofitApi.kakaoService
        service.getKakaoImage(getString(R.string.api_key), query).enqueue(object : retrofit2.Callback<KakaoResponse> {
            override fun onResponse(call: Call<KakaoResponse>, response: Response<KakaoResponse>) {
                if(response.isSuccessful){
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime.slice(0..9)
                                val url = document.thumbnailUrl
                                imageItems.add(ImageModel(title, datetime, url, false))
                            }
                        }
                    }
                    var adapter: SearchAdapter = SearchAdapter(imageItems, mContext)
                    binding.searchRecyclerView.adapter = adapter
                    binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
}