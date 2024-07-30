package com.example.winiynews.ui.fragment.feature

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.winiynews.R
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.SentenceBean.JokeBean
import com.example.winiynews.databinding.FragmentJokeBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.JokeContract
import com.example.winiynews.mvp.presenter.JokeDailyPresenter
import com.google.android.material.transition.MaterialContainerTransform
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Author winiymissl
 * @Date 2024-07-22 20:55
 * @Version 1.0
 */
class JokeDailyFragment : BaseFragment(), JokeContract.View {
    private lateinit var binding: FragmentJokeBinding
    private val mPresenter by lazy {
        JokeDailyPresenter()
    }

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
        binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke
    }

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = binding.multipleStatusView
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.constraintlayout, it.getString("transitionName")
            )
        }
    }

    override fun lazyLoad() {
        val job = Job()
        val coroutineScope = CoroutineScope(job)
        coroutineScope.launch {
            mPresenter.requestSentenceData()
            Logger.d("JokeDailyFragment")
        }
    }

    override suspend fun setSentenceData(bean: JokeBean) {
        withContext(Dispatchers.Main) {
            mLayoutStatusView?.showContent()
            binding.textViewSentence.text = bean.data.toString()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}