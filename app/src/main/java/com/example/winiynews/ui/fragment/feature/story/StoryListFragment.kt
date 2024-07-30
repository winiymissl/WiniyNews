package com.example.winiynews.ui.fragment.feature.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.winiynews.R
import com.example.winiynews.adapter.RRV_ItemClickListener
import com.example.winiynews.adapter.StoryListRecyclerviewAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.StoryBean.StoryListBean
import com.example.winiynews.databinding.FragmentStoryListBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.StoryListContract
import com.example.winiynews.mvp.presenter.StoryListPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Author winiymissl
 * @Date 2024-07-22 17:13
 * @Version 1.0
 */
class StoryListFragment : BaseFragment(), StoryListContract.View {
    private lateinit var binding: FragmentStoryListBinding
    private val mPresenter by lazy { StoryListPresenter() }
    private var typeId: String? = null
    private var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = MaterialContainerTransform().apply {
//            duration = 400
//            scrimColor = Color.TRANSPARENT
//            setAllContainerColors(Color.TRANSPARENT)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_story_list, container, false)
        }
        binding = FragmentStoryListBinding.bind(rootView!!)
        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story_list
    }

    override fun initView() {
        mPresenter.attachView(this)
        /**
         * 输入到fragment的参数
         * */
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.constraintlayoutStoryList, it.getString("transitionName")
            )
            typeId = it.getString("typeId")
        }
        mLayoutStatusView = binding.multipleStatusViewStoryList
    }

    override fun lazyLoad() {
        val job = Job()
        val coroutineScope = CoroutineScope(job)
        coroutineScope.launch {
            mPresenter.requestStoryListData(typeId!!, 1)
        }
    }

    override suspend fun setStoryListData(data: StoryListBean) {
        withContext(Dispatchers.Main) {
            mLayoutStatusView?.showContent()
            val adapter = StoryListRecyclerviewAdapter(object : RRV_ItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    NavHostFragment.findNavController(this@StoryListFragment)
                        .navigate(R.id.storyDetailFragment, Bundle().apply {
                            putString("StoryId", data.data[position].storyId.toString())
                        })
                }

                override fun onLongItemClick(view: View?, position: Int): Boolean {
                    return true
                }
            }).apply {
                submitData(data.data)
            }
            binding.recyclerviewStoryList.apply {
                this.layoutManager = GridLayoutManager(this@StoryListFragment.context, 1)
                this.adapter = adapter
            }
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