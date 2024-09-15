package com.classic.common

import android.R
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * 类描述：  一个方便在多种状态切换的view
 *
 *
 * 创建人:   续写经典
 * 创建时间: 2016/1/15 10:20.
 */
@Suppress("unused")
class MultipleStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    RelativeLayout(context, attrs, defStyleAttr) {
    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mNoNetworkView: View? = null
    private var mContentView: View? = null

    private val mEmptyViewResId: Int
    private val mErrorViewResId: Int
    private val mLoadingViewResId: Int
    private val mNoNetworkViewResId: Int
    private var mContentViewResId: Int

    /**
     * 获取当前状态
     *
     * @return 视图状态
     */
    var viewStatus: Int = -1
        private set
    private val mInflater: LayoutInflater
    private var mOnRetryClickListener: OnClickListener? = null
    private var mViewStatusListener: OnViewStatusChangeListener? = null

    private val mOtherIds = ArrayList<Int>()

    init {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0)
        mEmptyViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view)
        mErrorViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view)
        mLoadingViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view)
        mNoNetworkViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.no_network_view)
        mContentViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID)
        a.recycle()
        mInflater = LayoutInflater.from(getContext())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView!!, mLoadingView!!, mErrorView!!, mNoNetworkView!!)
        if (!mOtherIds.isEmpty()) {
            mOtherIds.clear()
        }
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
        if (null != mViewStatusListener) {
            mViewStatusListener = null
        }
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    fun setOnRetryClickListener(onRetryClickListener: OnClickListener?) {
        this.mOnRetryClickListener = onRetryClickListener
    }

    /**
     * 显示空视图
     *
     * @param hintResId 自定义提示文本内容
     * @param formatArgs 占位符参数
     */
    fun showEmpty(hintResId: Int, vararg formatArgs: Any?) {
        showEmpty()
        setStatusHintContent(mEmptyView!!, hintResId, *formatArgs)
    }

    /**
     * 显示空视图
     *
     * @param hint 自定义提示文本内容
     */
    fun showEmpty(hint: String) {
        showEmpty()
        setStatusHintContent(mEmptyView!!, hint)
    }

    /**
     * 显示空视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    /**
     * 显示空视图
     */
    @JvmOverloads
    fun showEmpty(
        layoutId: Int = mEmptyViewResId,
        layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS
    ) {
        showEmpty((if (null == mEmptyView) inflateView(layoutId) else mEmptyView)!!, layoutParams)
    }

    /**
     * 显示空视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showEmpty(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Empty view is null.")
        checkNull(layoutParams, "Layout params is null.")
        changeViewStatus(STATUS_EMPTY)
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView = mEmptyView!!.findViewById<View>(R.id.empty_retry_view)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    /**
     * 显示错误视图
     *
     * @param hintResId 自定义提示文本内容
     * @param formatArgs 占位符参数
     */
    fun showError(hintResId: Int, vararg formatArgs: Any?) {
        showError()
        setStatusHintContent(mErrorView!!, hintResId, *formatArgs)
    }

    /**
     * 显示错误视图
     *
     * @param hint 自定义提示文本内容
     */
    fun showError(hint: String) {
        showError()
        setStatusHintContent(mErrorView!!, hint)
    }

    /**
     * 显示错误视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    /**
     * 显示错误视图
     */
    @JvmOverloads
    fun showError(
        layoutId: Int = mErrorViewResId,
        layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS
    ) {
        showError((if (null == mErrorView) inflateView(layoutId) else mErrorView)!!, layoutParams)
    }

    /**
     * 显示错误视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showError(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Error view is null.")
        checkNull(layoutParams, "Layout params is null.")
        changeViewStatus(STATUS_ERROR)
        if (null == mErrorView) {
            mErrorView = view
            val errorRetryView = mErrorView!!.findViewById<View>(R.id.error_retry_view)
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mErrorView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    /**
     * 显示加载中视图
     *
     * @param hintResId 自定义提示文本内容
     * @param formatArgs 占位符参数
     */
    fun showLoading(hintResId: Int, vararg formatArgs: Any?) {
        showLoading()
        setStatusHintContent(mLoadingView!!, hintResId, *formatArgs)
    }

    /**
     * 显示加载中视图
     *
     * @param hint 自定义提示文本内容
     */
    fun showLoading(hint: String) {
        showLoading()
        setStatusHintContent(mLoadingView!!, hint)
    }

    /**
     * 显示加载中视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    /**
     * 显示加载中视图
     */
    @JvmOverloads
    fun showLoading(
        layoutId: Int = mLoadingViewResId,
        layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS
    ) {
        showLoading(
            (if (null == mLoadingView) inflateView(layoutId) else mLoadingView)!!,
            layoutParams
        )
    }

    /**
     * 显示加载中视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showLoading(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Loading view is null.")
        checkNull(layoutParams, "Layout params is null.")
        changeViewStatus(STATUS_LOADING)
        if (null == mLoadingView) {
            mLoadingView = view
            mOtherIds.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    /**
     * 显示无网络视图
     *
     * @param hintResId 自定义提示文本内容
     * @param formatArgs 占位符参数
     */
    fun showNoNetwork(hintResId: Int, vararg formatArgs: Any?) {
        showNoNetwork()
        setStatusHintContent(mNoNetworkView!!, hintResId, *formatArgs)
    }

    /**
     * 显示无网络视图
     *
     * @param hint 自定义提示文本内容
     */
    fun showNoNetwork(hint: String) {
        showNoNetwork()
        setStatusHintContent(mNoNetworkView!!, hint)
    }

    /**
     * 显示无网络视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    /**
     * 显示无网络视图
     */
    @JvmOverloads
    fun showNoNetwork(
        layoutId: Int = mNoNetworkViewResId,
        layoutParams: ViewGroup.LayoutParams = DEFAULT_LAYOUT_PARAMS
    ) {
        showNoNetwork(
            (if (null == mNoNetworkView) inflateView(layoutId) else mNoNetworkView)!!,
            layoutParams
        )
    }

    /**
     * 显示无网络视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showNoNetwork(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "No network view is null.")
        checkNull(layoutParams, "Layout params is null.")
        changeViewStatus(STATUS_NO_NETWORK)
        if (null == mNoNetworkView) {
            mNoNetworkView = view
            val noNetworkRetryView = mNoNetworkView!!.findViewById<View>(R.id.no_network_retry_view)
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mNoNetworkView!!.id)
            addView(mNoNetworkView, 0, layoutParams)
        }
        showViewById(mNoNetworkView!!.id)
    }

    /**
     * 显示内容视图
     */
    fun showContent() {
        changeViewStatus(STATUS_CONTENT)
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater.inflate(mContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
        showContentView()
    }

    /**
     * 显示内容视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showContent(layoutId: Int, layoutParams: ViewGroup.LayoutParams) {
        showContent(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示内容视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    fun showContent(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Content view is null.")
        checkNull(layoutParams, "Layout params is null.")
        changeViewStatus(STATUS_CONTENT)
        clear(mContentView!!)
        mContentView = view
        addView(mContentView, 0, layoutParams)
        showViewById(mContentView!!.id)
    }

    private fun setStatusHintContent(view: View, resId: Int, vararg formatArgs: Any) {
        checkNull(view, "Target view is null.")
        setStatusHintContent(view, view.context.getString(resId, *formatArgs))
    }

    private fun setStatusHintContent(view: View, hint: String) {
        checkNull(view, "Target view is null.")
        val hintView = view.findViewById<TextView>(R.id.status_hint_content)
        if (null != hintView) {
            hintView.text = hint
        } else {
            throw NullPointerException("Not find the view ID `status_hint_content`")
        }
    }

    private fun inflateView(layoutId: Int): View {
        return mInflater.inflate(layoutId, null)
    }

    private fun showViewById(viewId: Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) VISIBLE else GONE
        }
    }

    private fun showContentView() {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) GONE else VISIBLE
        }
    }

    private fun checkNull(`object`: Any, hint: String) {
        if (null == `object`) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views: View) {
        if (null == views) {
            return
        }
        try {
            for (view in views) {
                if (null != view) {
                    removeView(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 视图状态改变接口
     */
    interface OnViewStatusChangeListener {
        /**
         * 视图状态改变时回调
         *
         * @param oldViewStatus 之前的视图状态
         * @param newViewStatus 新的视图状态
         */
        fun onChange(oldViewStatus: Int, newViewStatus: Int)
    }

    /**
     * 设置视图状态改变监听事件
     *
     * @param onViewStatusChangeListener 视图状态改变监听事件
     */
    fun setOnViewStatusChangeListener(onViewStatusChangeListener: OnViewStatusChangeListener?) {
        this.mViewStatusListener = onViewStatusChangeListener
    }

    /**
     * 改变视图状态
     *
     * @param newViewStatus 新的视图状态
     */
    private fun changeViewStatus(newViewStatus: Int) {
        if (viewStatus == newViewStatus) {
            return
        }
        if (null != mViewStatusListener) {
            mViewStatusListener!!.onChange(viewStatus, newViewStatus)
        }
        viewStatus = newViewStatus
    }


    private fun setContentViewResId(contentViewResId: Int) {
        this.mContentViewResId = contentViewResId
        this.mContentView = mInflater.inflate(mContentViewResId, null)
        addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
    }

    private fun setContentView(contentView: ViewGroup) {
        this.mContentView = contentView
        addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
    }

    companion object {
        private const val TAG = "MultipleStatusView"

        private val DEFAULT_LAYOUT_PARAMS = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )

        const val STATUS_CONTENT: Int = 0x00
        const val STATUS_LOADING: Int = 0x01
        const val STATUS_EMPTY: Int = 0x02
        const val STATUS_ERROR: Int = 0x03
        const val STATUS_NO_NETWORK: Int = 0x04

        private const val NULL_RESOURCE_ID = -1

        fun attach(fragment: Fragment, rootAnchor: Int): MultipleStatusView? {
            require(!(null == fragment || fragment.getView() == null)) { "fragment is null or fragment.getView is null" }
            if (-1 != rootAnchor) {
                val contentAnchor: ViewGroup = fragment.getView().findViewById(rootAnchor)
                if (null != contentAnchor) {
                    attach(contentAnchor)
                }
            }
            val contentParent = fragment.getView().getParent() as ViewGroup
            return attach(contentParent)
        }

        fun attach(activity: Activity, rootAnchor: Int): MultipleStatusView? {
            if (-1 != rootAnchor) {
                val contentAnchor = activity.findViewById<ViewGroup>(rootAnchor)
                if (null != contentAnchor) {
                    attach(contentAnchor)
                }
            }
            val defaultAnchor = activity.findViewById<ViewGroup>(R.id.content)
            return attach(defaultAnchor)
        }

        fun attach(rootAnchor: ViewGroup): MultipleStatusView? {
            requireNotNull(rootAnchor) { "root Anchor View can't be null" }
            val parent = rootAnchor.parent as ViewGroup
            val anchorIndex = parent.indexOfChild(rootAnchor)
            if (-1 != anchorIndex) {
                parent.removeView(rootAnchor)
                val statusView = MultipleStatusView(rootAnchor.context)
                statusView.setContentView(rootAnchor)
                val p = rootAnchor.layoutParams
                parent.addView(statusView, anchorIndex, p)
                return statusView
            }
            return null
        }
    }
}