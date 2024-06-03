package com.example.winiynews.ui.fragment.feature

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.winiynews.R
import com.example.winiynews.adapter.BeautyRecyclerviewAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.beautyBean.BeautyBean
import com.example.winiynews.databinding.FragmentBeautyBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.BeautyContract
import com.example.winiynews.mvp.presenter.BeautyPresenter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.transition.MaterialContainerTransform


class BeautyFragment : BaseFragment(), BeautyContract.View {
    private lateinit var binding: FragmentBeautyBinding
    private val mPresenter by lazy { BeautyPresenter() }

    override fun getLayoutId(): Int = R.layout.fragment_beauty


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration =1000
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beauty, container, false)
        binding = FragmentBeautyBinding.bind(view)
        return binding.root
    }

    override fun initView() {
        /**
         * 将view附着在一起
         */
        mPresenter.attachView(this)
        /**
         * 处理传递的参数
         */
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.constraintBeauty, it.getString("transitionName")
            )
        }

        mLayoutStatusView = binding.MultipleStatusViewBeauty

        binding.bottonAgain.setOnClickListener {
            mPresenter.requestBeautyData()
        }
    }

    override fun lazyLoad() {
        mPresenter.requestBeautyData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun setBeautyData(bean: BeautyBean) {
        mLayoutStatusView?.showContent()

        val adapter = BeautyRecyclerviewAdapter().apply {
            setData(bean.data)
            notifyDataSetChanged()
        }
        binding.recyclerviewBeauty.apply {
            CarouselSnapHelper().attachToRecyclerView(binding.recyclerviewBeauty)
            this.layoutManager = CarouselLayoutManager(
                FullScreenCarouselStrategy(), CarouselLayoutManager.HORIZONTAL
            )
            this.adapter = adapter
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }
}
