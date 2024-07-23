package com.example.winiynews.ui.fragment.feature

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.winiynews.R
import com.example.winiynews.adapter.BeautyRecyclerviewAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.beautyBean.BeautyBean
import com.example.winiynews.databinding.FragmentBeautyBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.BeautyContract
import com.example.winiynews.mvp.presenter.BeautyPresenter
import com.example.winiynews.service.ImageDownloadAndSaveWithProgress
import com.example.winiynews.utils.showDioLog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialContainerTransform
import com.orhanobut.logger.Logger


class BeautyFragment<T> : BaseFragment(), BeautyContract.View {
    private lateinit var binding: FragmentBeautyBinding
    private val mPresenter by lazy { BeautyPresenter() }
    private var rootView: View? = null
    override fun getLayoutId(): Int = R.layout.fragment_beauty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 400
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
        /**
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        }
        val supervisorScope = CoroutineScope(SupervisorJob() + exceptionHandler)
        with(supervisorScope) {
        launch {}
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView =
                inflater.inflate(com.example.winiynews.R.layout.fragment_beauty, container, false)
        }
        binding = FragmentBeautyBinding.bind(rootView!!)
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
        binding.bottonDownload.setOnClickListener {
            NavHostFragment.findNavController(this)
        }
//        binding.refreshlayoutBeauty.setOnRefreshListener { layout ->
//
//        }
//        binding.refreshlayoutBeauty.setOnLoadMoreListener { layout ->
//
//        }
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
        val adapter =
            BeautyRecyclerviewAdapter(object : BeautyRecyclerviewAdapter.OnItemClickListener {

                override fun onItemClick(view: View?, position: Int) {
                    Logger.d("正在点击$position")
                }

                override fun onLongItemClick(view: View?, position: Int): Boolean {
                    Logger.d("正在长按$position")
                    showDioLog(this@BeautyFragment.requireContext()) {
                        val builder =
                            MaterialAlertDialogBuilder(this@BeautyFragment.requireContext())
                        builder.setTitle("提示").setMessage("是否下载?")
                        builder.setNeutralButton(
                            "取消"
                        ) { dialog: DialogInterface?, which: Int -> }
                        builder.setPositiveButton(
                            "下载"
                        ) { dialog: DialogInterface?, which: Int ->
                            if (activity != null && view != null) {
                                ImageDownloadAndSaveWithProgress.downloadAndSaveImage(
                                    activity!!,
                                    bean.data[position].imageUrl,
                                    binding.recyclerviewBeauty.getChildViewHolder(view).itemView.findViewById(
                                        R.id.item_progress_indicator
                                    )
                                )
                            }
                            dialog?.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }
                    return true
                }
            }).apply {
                setData(bean.data)
            }

        binding.recyclerviewBeauty.apply {
            this.layoutManager = GridLayoutManager(this@BeautyFragment.context, 1)
            this.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }


    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun refresh() {
        mLayoutStatusView?.showContent()
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }
}