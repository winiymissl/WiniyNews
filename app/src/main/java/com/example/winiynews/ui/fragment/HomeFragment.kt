package com.example.winiynews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.winiynews.R
import com.example.winiynews.adapter.HomeRecyclerviewAdapter
import com.example.winiynews.adapter.ItemHome
import com.example.winiynews.databinding.FragmentHomeBinding
import com.example.winiynews.utils.MyOnItemTouchListener
import com.google.android.material.transition.MaterialElevationScale
import com.orhanobut.logger.Logger


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(/* growing= */ true)
        reenterTransition = MaterialElevationScale(/* growing= */ true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(
            ItemHome("菜谱", R.drawable.ic_home_beauty),
            ItemHome("热量查询", R.drawable.ic_home_beauty),
            ItemHome("身份证验证", R.drawable.ic_home_beauty),
            ItemHome("美女福利", R.drawable.ic_home_beauty),
        )
        val adapter = HomeRecyclerviewAdapter()
        /**
         * 使用lateinit关键字初始化变量，必须确保变量在第一次被使用的时候被初始化，否则会抛出异常。
         */
        adapter.submitList(list)
        binding.recyclerviewLife.apply {

            /**
             * 滑动
             * */
            LinearSnapHelper().attachToRecyclerView(this)
            /**
             * 绘制布局
             */
            this.layoutManager = LinearLayoutManager(
                this@HomeFragment.context, GridLayoutManager.HORIZONTAL, false
            )
            /**
             * 事件监听
             */
            addOnItemTouchListener(
                MyOnItemTouchListener(this@HomeFragment.context,
                    this,
                    object : MyOnItemTouchListener.OnItemClickListener {
                        override fun onItemClick(view: View?, position: Int) {
                            view?.transitionName = "shared_element_container_$position"
                            val extras =
                                FragmentNavigatorExtras(view!! to "shared_element_container_$position")
                            /**
                             * Find the topmost view under the given point.因此是根布局
                             */
                            if (view is ConstraintLayout) {
                                Logger.d("view is ConstraintLayout")
                            }
                            if (position == 2) {
                                NavHostFragment.findNavController(this@HomeFragment)
                                    .navigate(R.id.idCardIdentifyFragment, Bundle().apply {
                                        putString("transitionName", view.transitionName)
                                    }, null, extras)
                            } else if (position == 1) {
                                NavHostFragment.findNavController(this@HomeFragment)
                                    .navigate(R.id.foodHeatFragment, Bundle().apply {
                                        putString("transitionName", view.transitionName)
                                    }, null, extras)
                            } else if (position == 3) {
                                NavHostFragment.findNavController(this@HomeFragment)
                                    .navigate(R.id.beautyFragment, Bundle().apply {
                                        putString("transitionName", view.transitionName)
                                    }, null, extras)

                            } else if (position == 0) {
                                NavHostFragment.findNavController(this@HomeFragment)
                                    .navigate(R.id.recipeFragment, Bundle().apply {
                                        putString("transitionName", view.transitionName)
                                    }, null, extras)
                            }
                        }

                        override fun onLongItemClick(view: View?, position: Int) {
                            /**
                             * 长按的操作*/
                        }
                    })
            )
            /**
             * 设置adapter
             */
            this.adapter = adapter
        }
        /**
         * The below code is required to animate correctly when the user returns from [DetailFragment].
         */
        postponeEnterTransition()
        (requireView().parent as ViewGroup).viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
        binding.chipLife.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.lifeFragment)
        }
    }
}

