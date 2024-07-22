package com.example.winiynews.ui.fragment.feature.story

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winiynews.R
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.databinding.FragmentStoryListBinding
import com.google.android.material.transition.MaterialContainerTransform

/**
 * @Author winiymissl
 * @Date 2024-07-22 17:13
 * @Version 1.0
 */
class StoryListFragment : BaseFragment() {
    private lateinit var binding: FragmentStoryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 400
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story_list
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}