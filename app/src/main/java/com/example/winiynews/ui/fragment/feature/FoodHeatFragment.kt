package com.example.winiynews.ui.fragment.feature

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.winiynews.R
import com.example.winiynews.adapter.ItemSearchResult
import com.example.winiynews.adapter.SearchFoodResultAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.FoodHeatBean.SearchFoodHeatBean
import com.example.winiynews.databinding.FragmentFoodheatSearchBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.SearchFoodHeatContract
import com.example.winiynews.mvp.presenter.SearchFoodHeatPresenter
import com.example.winiynews.utils.MyOnItemTouchListener
import com.google.android.material.transition.MaterialContainerTransform


/**
 * @Author winiymissl
 * @Date 2024-06-01 17:19
 * @Version 1.0
 */
class FoodHeatFragment : BaseFragment(), SearchFoodHeatContract.View {
    private lateinit var binding: FragmentFoodheatSearchBinding
    private val mPresenter by lazy { SearchFoodHeatPresenter() }
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 500
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_foodheat_search, container, false)
        binding = FragmentFoodheatSearchBinding.bind(view)
        return binding.root
    }

    override fun initView() {
        mLayoutStatusView = binding.multipleStatusView
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.coordinatorLayoutFoodheat, it.getString("transitionName")
            )
        }
        /**用于enter键直接返回到searchbar所在页面，与我的逻辑不同
         * binding.searchView
        .getEditText()
        .setOnEditorActionListener { v, actionId, event ->
        binding.searchBar.setText(binding.searchView.getText())
        binding.searchView.hide()
        false
        }*/
        mPresenter.attachView(this)


        binding.searchView.apply {
            setupWithSearchBar(binding.searchBar)
            /**
             * 这里出现过多次点击的bug
             */
//        binding.searchView.editText.setOnEditorActionListener(object :
//            TextView.OnEditorActionListener {
//            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//                if (KeyEvent.KEYCODE_ENTER == event?.keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
////                    mPresenter.requestFoodHeatData(binding.searchView.editText.text.toString(), 1)
//                    return true
//                }
//                Logger.d("KEYCODE_ENTER")
//                return false
//            }
//        })
//        binding.searchView.editText.setOnEditorActionListener({ v, id, event ->
//            mPresenter.requestFoodHeatData(binding.searchView.editText.text.toString(), 1)
//            true
//        })
            editText.setOnEditorActionListener { _, _, event ->
                if (KeyEvent.KEYCODE_ENTER == event?.keyCode && event.action == KeyEvent.ACTION_DOWN) {
                    page = 1
                    mPresenter.requestFoodHeatData(
                        binding.searchView.editText.text.toString(), page
                    )
                    true
                }
                false
            }
        }


        /*    binding.searchResultsRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val lastVisibleItemPosition = layoutManager!!.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager!!.itemCount
                    // 判断是否滚动到列表底部
                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        // 加载更多数据
    //                    mPresenter.requestFoodHeatData(
    //                        binding.searchView.editText.text.toString(), ++page
    //                    )
                        Logger.d(page)
                    }
                }
            })*/

        binding.searchResultsRecyclerView.apply {
            addOnItemTouchListener(
                MyOnItemTouchListener(this@FoodHeatFragment.context,
                    this,
                    object : MyOnItemTouchListener.OnItemClickListener {
                        override fun onItemClick(view: View?, position: Int) {
                            var adapterNow = adapter as SearchFoodResultAdapter
                            NavHostFragment.findNavController(this@FoodHeatFragment)
                                .navigate(R.id.foodHeatDetailBottomSheet, Bundle().apply {
                                    putString("foodId", adapterNow.getData()[position].foodId)
                                })
                        }

                        override fun onLongItemClick(view: View?, position: Int) {
                        }
                    })
            )
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_foodheat_search

    override fun setSearchFoodHeatData(bean: SearchFoodHeatBean) {
        mLayoutStatusView?.showContent()
        val adapter = SearchFoodResultAdapter()
        bean.data.list.forEach {
            adapter.submitItem(ItemSearchResult(it.foodId, it.name, it.healthLevel, it.calory))
        }
//        adapter.submitList()
        binding.searchResultsRecyclerView.apply {
            this.layoutManager = GridLayoutManager(this@FoodHeatFragment.context, 1)
            this.adapter = adapter
        }
    }

    override fun setFoodDetailData(id: String) {

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