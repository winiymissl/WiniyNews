package com.example.winiynews.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.winiynews.R
import com.example.winiynews.adapter.ItemLife
import com.example.winiynews.adapter.LifeRecyclerviewAdapter
import com.example.winiynews.databinding.FragmentLifeBinding
import com.google.android.material.transition.MaterialFade

class LifeFragment : Fragment() {
    private lateinit var binding: FragmentLifeBinding

    companion object {
        fun newInstance() = LifeFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFade()
        exitTransition = MaterialFade()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter: LifeRecyclerviewAdapter = LifeRecyclerviewAdapter()
        var list = listOf(
            ItemLife("1", R.drawable.ic_home_beauty),
            ItemLife("2", R.drawable.ic_home_beauty),
            ItemLife("3", R.drawable.ic_home_beauty),
            ItemLife("4", R.drawable.ic_home_beauty),
            ItemLife("5", R.drawable.ic_home_beauty),
        )
        adapter.submitList(list)
        binding.recyclerviewLife.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewLife.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_life, container, false)
        binding = FragmentLifeBinding.bind(view)
        return binding.root
    }
}