package com.example.winiynews.ui.fragment.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.winiynews.R
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.IdentifyBean.IdCardIdentifyBean
import com.example.winiynews.databinding.FragmentIdcardidentifyBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.IdCardContract
import com.example.winiynews.mvp.presenter.IdCardIdentifyPresenter
import com.example.winiynews.utils.showToast
import com.google.android.material.transition.MaterialContainerTransform

/**
 * @Author winiymissl
 * @Date 2024-05-30 21:39
 * @Version 1.0
 */
class IdCardIdentifyFragment : BaseFragment(), IdCardContract.View {
    private val mPresenter by lazy { IdCardIdentifyPresenter() }

    private lateinit var binding: FragmentIdcardidentifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentIdcardidentifyBinding.bind(
            LayoutInflater.from(activity).inflate(getLayoutId(), container, false)
        )
        return binding.root
    }

    override fun getLayoutId(): Int = R.layout.fragment_idcardidentify

    override fun initView() {
        /**
         * 处理传递的参数
         */
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.contraintLayoutIdCard, it.getString("transitionName")
            )
        }
        /**
         * 将view附着在一起
         */
        mPresenter.attachView(this)
        mLayoutStatusView = binding.multipleStatusView
        binding.buttonQuery.setOnClickListener {
            /**
             * 从edit中获取信息
             */
            mPresenter.requestIdCardData(binding.editText.text.toString())
        }
    }

    override fun lazyLoad() {
    }

    override fun setIdCardData(bean: IdCardIdentifyBean) {
        mLayoutStatusView?.showContent()
        bean.data.apply {
            binding.textViewBirthday.text = birthday
            binding.textViewSex.text = sex
            binding.textViewAddress.text = address
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
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