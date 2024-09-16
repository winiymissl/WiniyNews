package com.example.winiynews.ui.fragment.feature.story

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.winiynews.R
import com.example.winiynews.adapter.StoryCategoryRecyclerviewAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.StoryBean.StoryCategoryBean
import com.example.winiynews.databinding.FragmentStoryBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.StoryContract
import com.example.winiynews.mvp.presenter.StoryPresenter
import com.google.android.material.transition.MaterialContainerTransform

/**
 * @Author winiymissl
 * @Date 2024-07-22 16:16
 * @Version 1.0
 */
class StoryFragment : BaseFragment(), StoryContract.View {
    private lateinit var binding: FragmentStoryBinding
    private val mPresenter by lazy { StoryPresenter() }
    private var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 400
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_story, container, false)

        }
        binding = FragmentStoryBinding.bind(rootView!!)
        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story
    }

    override fun initView() {
        mPresenter.attachView(this)
        /**
         * 输入到fragment的参数
         * */
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.constraintlayoutStory, it.getString("transitionName")
            )
        }
        mLayoutStatusView = binding.multipleStatusViewCategory
    }

    override fun lazyLoad() {
        mPresenter.requestStoryData()
    }

    override fun setStoryData(data: StoryCategoryBean) {
        mLayoutStatusView?.showContent()
        val adapter = StoryCategoryRecyclerviewAdapter(object :
            StoryCategoryRecyclerviewAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {

                NavHostFragment.findNavController(this@StoryFragment)
                    .navigate(R.id.storyListFragment, Bundle().apply {
                        putString("typeId", data.data[position].type_id.toString())
                    })
            }

            override fun onLongItemClick(view: View?, position: Int): Boolean {/*
                * 长按点击
                * */
                return true
            }

        }).apply {
            submitList(data.data)
        }
        binding.recyclerViewCategory.apply {
            this.layoutManager = GridLayoutManager(this@StoryFragment.context, 2)
            this.adapter = adapter
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError(msg)
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}