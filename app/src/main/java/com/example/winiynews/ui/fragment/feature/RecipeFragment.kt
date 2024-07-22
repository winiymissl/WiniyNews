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
import com.example.winiynews.adapter.ItemSearchData
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
import com.orhanobut.logger.Logger
import jp.wasabeef.recyclerview.animators.ScaleInAnimator


/**
 * @Author winiymissl
 * @Date 2024-06-02 14:37
 * @Version 1.0
 */
class RecipeFragment : BaseFragment(), RecipeContract.View {
    private lateinit var binding: FragmentRecipeBinding
    private val mPresenter: RecipePresenter by lazy { RecipePresenter() }
    private var page: String? = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        binding = FragmentRecipeBinding.bind(view)
        return binding.root
    }

    override fun getLayoutId(): Int = R.layout.fragment_recipe

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = binding.multipleStatusViewCategory
        mPresenter.requestRecipeCategoryData("-1")
        binding.searchView.setupWithSearchBar(binding.searchBar)
        arguments?.let {
            ViewCompat.setTransitionName(
                binding.coordinatorLayoutFoodheat, it.getString("transitionName")
            )
        }
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 500
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
        }

        binding.searchView.apply {
            this.setupWithSearchBar(binding.searchBar)
            this.editText.setOnEditorActionListener { _, _, event ->
                if (KeyEvent.KEYCODE_ENTER == event?.keyCode && event.action == KeyEvent.ACTION_DOWN) {
                    page = "1"
                    mPresenter.requestSearchRecipeData(
                        binding.searchView.editText.text.toString(), page!!
                    )
                    true
                }
                false
            }
        }


    }

    override fun lazyLoad() {
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
        mLayoutStatusView?.showContent()
        val adapter = RecipeSearchRecyclerviewAdapter(object :
            RecipeSearchRecyclerviewAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
            }

            override fun onLongItemClick(view: View?, position: Int): Boolean {
                val temp: ArrayList<String> = arrayListOf()
                data.data.list
                    .forEach {
                        temp.add(it.name)
                    }
                Logger.d(data.data.list)
                NavHostFragment.findNavController(this@RecipeFragment)
                    .navigate(R.id.ingredientBottomSheet, Bundle().apply {
                        putStringArrayList(
                            "recipeData", temp
                        )
                    })
                return true
            }
        })
        var temp: MutableList<ItemSearchData> = mutableListOf()
        data.data.list.forEach {
            temp.add(ItemSearchData(it.cover, it.desc, it.id, it.ingredient, it.name))
        }
        Logger.d(data.data.list)
        binding.searchResultsRecyclerView.apply {
            this.layoutManager = GridLayoutManager(this@RecipeFragment.context, 1)
//            addOnItemTouchListener(
//                MyOnItemTouchListener(this@RecipeFragment.context,
//                    binding.searchResultsRecyclerView,
//                    object : MyOnItemTouchListener.OnItemClickListener {
//                        override fun onItemClick(view: View?, position: Int) {
//                            val temp: ArrayList<String> = arrayListOf()
//                            (this@apply.adapter as RecipeSearchRecyclerviewAdapter).getData()
//                                .forEach {
//                                temp.add(it.name)
//                            }
//                            Logger.d(data.data.list)
//                            NavHostFragment.findNavController(this@RecipeFragment)
//                                .navigate(R.id.ingredientBottomSheet, Bundle().apply {
//                                    putStringArrayList(
//                                        "recipeData", temp
//                                    )
//                                })
//                        }
//
//                        override fun onLongItemClick(view: View?, position: Int) {
//                            /**
//                             * 长按的操作*/
//                            /**
//                             * 长按的操作*/
//
//                        }
//                    })
//            )
            this.adapter = adapter
        }
        adapter.submitList(temp)
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