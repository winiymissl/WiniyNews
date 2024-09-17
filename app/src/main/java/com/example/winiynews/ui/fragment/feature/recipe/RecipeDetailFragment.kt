package com.example.winiynews.ui.fragment.feature.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winiynews.R
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.RecipeBean.ElemeGroupedItem
import com.example.winiynews.bean.RecipeBean.ElemePrimaryAdapterConfig
import com.example.winiynews.bean.RecipeBean.ElemeSecondaryAdapterConfig
import com.example.winiynews.bean.RecipeBean.RecipeDetailBean
import com.example.winiynews.databinding.FragmentRecipeDetailBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.RecipeDetailContract
import com.example.winiynews.mvp.presenter.RecipeDetailPresenter
import com.google.android.material.transition.MaterialSharedAxis
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:04
 * @Version 1.0
 */
class RecipeDetailFragment : BaseFragment(), RecipeDetailContract.View {
    private var id: String? = null
    private val mPresenter: RecipeDetailPresenter by lazy { RecipeDetailPresenter() }
    private lateinit var binding: FragmentRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        binding = FragmentRecipeDetailBinding.bind(view)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
    }

    override fun getLayoutId(): Int = R.layout.fragment_recipe_detail

    override fun initView() {
        mPresenter.attachView(this)
        arguments?.let {
            id = it.getString("id")
        }
        mLayoutStatusView = binding.multipleStatusView
    }

    override fun lazyLoad() {
        if (id != null) {
            mPresenter.requestRecipeDetailData(id!!)
        }
    }

    override fun setRecipeDetailData(bean: RecipeDetailBean) {
        mLayoutStatusView?.showContent()
        binding.title.text = bean.data.name
        binding.duration.text = bean.data.duration
//        binding.tips.text = bean.data.tips
        binding.ingredient.text = bean.data.ingredient.toString()
        try {
            val items = mutableListOf<ElemeGroupedItem>()
            bean.data.instruction.forEach {
                items.add(ElemeGroupedItem(true, it.step))
                items.add(
                    ElemeGroupedItem(
                        ElemeGroupedItem.ItemInfo(
                            it.step, it.step, it.text, it.url
                        )
                    )
                )
            }
            binding.linkRecyclerview.init(
                items, ElemePrimaryAdapterConfig(), ElemeSecondaryAdapterConfig()
            )
            Logger.d(items.size)
        } catch (e: Exception) {
            Logger.d(e)
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