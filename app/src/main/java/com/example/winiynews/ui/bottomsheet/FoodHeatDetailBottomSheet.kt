package com.example.winiynews.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winiynews.R
import com.example.winiynews.base.BaseBottomSheet
import com.example.winiynews.bean.FoodHeatBean.FoodHeatDetail
import com.example.winiynews.databinding.BottomsheetFragmentFoodHeatDetailBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.BottomFoodHeatDetailContract
import com.example.winiynews.mvp.presenter.BottomFoodHeatDetailPresenter

/**
 * @Author winiymissl
 * @Date 2024-06-02 13:16
 * @Version 1.0
 */
class FoodHeatDetailBottomSheet : BaseBottomSheet(), BottomFoodHeatDetailContract.View {
    private lateinit var binding: BottomsheetFragmentFoodHeatDetailBinding
    private val mPresenter by lazy { BottomFoodHeatDetailPresenter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        binding = BottomsheetFragmentFoodHeatDetailBinding.bind(view)
        return binding.root
    }

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = binding.multipleStatusView
        arguments?.let {
            val s = it.getString("foodId")
            mPresenter.requestFoodHeatDetailData(s!!)
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.bottomsheet_fragment_food_heat_detail
    override fun setFoodDetailData(detail: FoodHeatDetail) {
        mLayoutStatusView?.showContent()
        binding.textViewFoodHeatDetail.text = detail.toString()
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
    }
}