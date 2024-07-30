package com.example.winiynews.ui.fragment.feature.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.winiynews.R
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.StoryBean.StoryDetailBean
import com.example.winiynews.databinding.FragmentStoryDetailBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.StoryDetailContract
import com.example.winiynews.mvp.presenter.StoryDetailPresenter
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Author winiymissl
 * @Date 2024-07-29 21:27
 * @Version 1.0
 */
class StoryDetailFragment : BaseFragment(), StoryDetailContract.View {
    private val mPresenter by lazy {
        StoryDetailPresenter()
    }
    private var mStoryId: String? = null

    private val binding by lazy {
        FragmentStoryDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story_detail
    }

    override fun initView() {
        Logger.d("StoryDetailFragment")
        mPresenter.attachView(this)
        /**
         * 输入到fragment的参数
         * */
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.constraintlayoutStoryDetail, it.getString("transitionName")
            )
            mStoryId = it.getString("StoryId")
        }
        mLayoutStatusView = binding.multipleStatusViewStoryDetail
    }

    override fun lazyLoad() {
        val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {
            if (mStoryId != null) {
                mPresenter.requestStoryDetailData(mStoryId!!)
            }
        }
    }

    override suspend fun setStoryDetailData(data: StoryDetailBean) {
        withContext(Dispatchers.Main) {
            mLayoutStatusView?.showContent()
            binding.storyTextView.text = data.data.content
        }
    }

    override suspend fun showError(msg: String, errorCode: Int) {
        withContext(Dispatchers.Main) {
            if (errorCode == ErrorStatus.NETWORK_ERROR) {
                mLayoutStatusView?.showNoNetwork()
            } else {
                mLayoutStatusView?.showError()
            }
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {

    }
}