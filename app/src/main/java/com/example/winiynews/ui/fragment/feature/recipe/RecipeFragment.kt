package com.example.winiynews.ui.fragment.feature.recipe

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.adapter.ItemSearchData
import com.example.winiynews.adapter.RRV_ItemClickListener
import com.example.winiynews.adapter.RecipeCategoryRecyclerviewAdapter
import com.example.winiynews.adapter.RecipeSearchRecyclerviewAdapter
import com.example.winiynews.base.BaseFragment
import com.example.winiynews.bean.RecipeBean.RecipeCategoryBean
import com.example.winiynews.bean.RecipeBean.SearchRecipeBean
import com.example.winiynews.databinding.FragmentRecipeBinding
import com.example.winiynews.http.exception.ErrorStatus
import com.example.winiynews.mvp.contract.RecipeContract
import com.example.winiynews.mvp.presenter.RecipePresenter
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.hjq.toast.Toaster
import com.orhanobut.logger.Logger
import com.scwang.smart.refresh.footer.ClassicsFooter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator


/**
 * @Author winiymissl
 * @Date 2024-06-02 14:37
 * @Version 1.0
 */
class RecipeFragment : BaseFragment(), RecipeContract.View {
    private lateinit var binding: FragmentRecipeBinding
    private val mPresenter: RecipePresenter by lazy { RecipePresenter() }
    private var page: Int = 1
    private var totalPage: Int = 1
    private var rooView: View? = null

    private lateinit var adapterRecyclerview: RecipeSearchRecyclerviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        if (rooView == null) {
            rooView = inflater.inflate(getLayoutId(), container, false)
        }
        binding = FragmentRecipeBinding.bind(rooView!!)
        return binding.root
    }

    override fun getLayoutId(): Int = R.layout.fragment_recipe

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = binding.multipleStatusViewCategory
        binding.searchView.setupWithSearchBar(binding.searchBar)
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.coordinatorLayoutFoodheat, it.getString("transitionName")
            )
        }
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 400
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }
        binding.searchView.apply {
            this.setupWithSearchBar(binding.searchBar)
            this.editText.setOnEditorActionListener { _, _, event ->
                if (KeyEvent.KEYCODE_ENTER == event?.keyCode && event.action == KeyEvent.ACTION_DOWN) {
                    page = 1
                    mPresenter.requestSearchRecipeData(
                        binding.searchView.editText.text.toString(), page.toString()
                    )
                    true
                }
                false
            }
        }
        binding.smartLayout.setRefreshFooter(ClassicsFooter(this@RecipeFragment.context))
        binding.smartLayout.setOnLoadMoreListener {
            if (totalPage == page) {
                binding.smartLayout.finishLoadMore()
                Toaster.show("这就是全部数据了哦")
            } else {
                mPresenter.requestSearchRecipeData(
                    binding.searchView.editText.text.toString(), page.toString()
                )
            }
        }
        binding.smartLayout.setEnableRefresh(false)

    }

    override fun lazyLoad() {
        mPresenter.requestRecipeCategoryData("-1")
    }

    override fun setRecipeCategoryData(data: RecipeCategoryBean) {
        mLayoutStatusView?.showContent()
        val adapter = RecipeCategoryRecyclerviewAdapter()
        var temp: MutableList<String> = mutableListOf()
        data.data.forEach {
            temp.add(it.name)
        }
        binding.recyclerViewCategory.apply {
            this.itemAnimator = ScaleInAnimator()
            this.layoutManager = GridLayoutManager(this@RecipeFragment.context, 2)
            this.adapter = adapter
        }
        adapter.submitList(temp)
    }

    override fun setSearchRecipeData(data: SearchRecipeBean) {
        binding.multipleStatusViewCategoryRecyclerView.showContent()
        adapterRecyclerview = RecipeSearchRecyclerviewAdapter(object : RRV_ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                NavHostFragment.findNavController(this@RecipeFragment)
                    .navigate(R.id.recipeDetailFragment, Bundle().apply {
                        putString("id", data.data.list[position].id.toString())
                    })
            }

            override fun onLongItemClick(view: View?, position: Int): Boolean {
                try {
                    val temp: ArrayList<String> = arrayListOf()
                    Toaster.show(position)
                    adapterRecyclerview.getData()[position].ingredient.forEach {
                        temp.add(it.name)
                    }
                    NavHostFragment.findNavController(this@RecipeFragment)
                        .navigate(R.id.ingredientBottomSheet, Bundle().apply {
                            putStringArrayList(
                                "recipeData", temp
                            )
                        })
                } catch (e: Exception) {
                    Logger.d(e)
                }
                return true
            }
        }).apply {
            var temp: MutableList<ItemSearchData> = mutableListOf()
            data.data.list.forEach {
                temp.add(ItemSearchData(it.cover, it.desc, it.id, it.ingredient, it.name))
            }
            submitList(temp)
        }
        binding.searchResultsRecyclerView.apply {
            this.layoutManager = GridLayoutManager(this@RecipeFragment.context, 2)
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                }
            })
            this.adapter = adapterRecyclerview

        }
        totalPage = data.data.totalPage
        if (totalPage > page) {
            page++
        }
    }

    override fun setSearchRecipeDataMore(data: SearchRecipeBean) {
        binding.smartLayout.finishLoadMore()
        adapterRecyclerview.apply {
            var temp: MutableList<ItemSearchData> = mutableListOf()
            data.data.list.forEach {
                temp.add(ItemSearchData(it.cover, it.desc, it.id, it.ingredient, it.name))
            }
            addData(temp)
        }
        val adapterTemp = RecipeSearchRecyclerviewAdapter(object : RRV_ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                try {
                    NavHostFragment.findNavController(this@RecipeFragment)
                        .navigate(R.id.recipeDetailFragment, Bundle().apply {
                            putString("id", adapterRecyclerview.getData()[position].id.toString())
                        })
                } catch (e: Exception) {
                }
            }

            override fun onLongItemClick(view: View?, position: Int): Boolean {
                try {
                    val temp: ArrayList<String> = arrayListOf()
                    adapterRecyclerview.getData()[position].ingredient.forEach {
                        temp.add(it.name)
                    }
                    NavHostFragment.findNavController(this@RecipeFragment)
                        .navigate(R.id.ingredientBottomSheet, Bundle().apply {
                            putStringArrayList(
                                "recipeData", temp
                            )
                        })
                } catch (e: Exception) {
                    Logger.d(e)
                }
                return true
            }
        }).apply {
            submitList(adapterRecyclerview.getData())
        }
        binding.searchResultsRecyclerView.apply {
            this.layoutManager = GridLayoutManager(this@RecipeFragment.context, 2)
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                }
            })
            this.adapter = adapterTemp
        }
        binding.searchResultsRecyclerView.scrollToPosition(adapterRecyclerview.getData().size - 12)
        Logger.d(adapterRecyclerview.getData())
        page++
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

    override fun showRecyclerviewLoading() {
        binding.multipleStatusViewCategoryRecyclerView.showLoading()
    }

    override fun showRecyclerviewError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            binding.multipleStatusViewCategoryRecyclerView.showNoNetwork()
        } else {
            binding.multipleStatusViewCategoryRecyclerView.showError()
        }
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}