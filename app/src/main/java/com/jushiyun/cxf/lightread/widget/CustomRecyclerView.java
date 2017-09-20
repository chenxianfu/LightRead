package com.jushiyun.cxf.lightread.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jushiyun.cxf.lightread.R;

/**
 * @author mahuafeng
 * @title
 * @description 自定义recycleview，集成下拉刷新，上来加载, 侧滑删除
 * <p>
 * 1、new 一个adapter extends BaseQucikAdapter()
 * 2、setOnDataChangeListener实现下拉，上拉
 * 3、setOnItemClickListener实现item点击
 * 4、adapter.setItemSlideEnable(true)打开右滑菜单显示功能，要求item布局必须是LinearLayout横布局，分为左右2个部分
 * @date 16/8/9
 */

public class CustomRecyclerView extends LinearLayout
        implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnRefreshListener {
    private static final String TAG = CustomRecyclerView.class.getSimpleName();

    private Context context;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private BaseQuickAdapter adapter;

    private OnDataChangeListener onDataChangeListener;
    /**
     * 是否能够上拉加载更多
     */
    private boolean load_more_enable = true;
    private TextView footer;

    private int PAGE_SIZE = 10;

    public CustomRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView);
        // 设置srl下拉刷新组件是否可用
        srl.setEnabled(typedArray.getBoolean(R.styleable.CustomRecyclerView_enable_refresh, true));
        srl.setColorSchemeResources(R.color.blue, R.color.green, R.color.orange, R.color.red);

        typedArray.recycle();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(LinearLayout.VERTICAL);

        srl = new SwipeRefreshLayout(context);
        srl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        srl.setOnRefreshListener(new OnSwipeRefreshListener());

        rv = new RecyclerView(context);
        rv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rv.setVerticalScrollBarEnabled(true);

        srl.addView(rv);
        addView(srl);

    }

//    /**
//     * 给当前的RecyclerView设置适配器
//     *
//     * @param adapter     适配器
//     * @param emptyViewId 当没有内容时是显示的组件
//     */
//    public void setAdapter(final BaseQuickAdapter adapter, int emptyViewId, String noDataString) {
//        View view = LayoutInflater.from(context).inflate(emptyViewId, null);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        ((TextView) view.findViewById(R.id.tv_no_data)).setText(noDataString);
//        setAdapter(adapter, view);
//    }

    public void setAdapter(final BaseQuickAdapter adapter) {
        setAdapter(adapter, null);
    }

    public void setAdapter(final BaseQuickAdapter adapter, final View view) {
        this.adapter = adapter;
        adapter.setEmptyView(view);
        //动画
//        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        adapter.openLoadMore(PAGE_SIZE, true);
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRefreshListener(this);
        adapter.setOnRecyclerViewItemClickListener(null);

//        adapter.addFooterView(footer);

        rv.setAdapter(adapter);
    }

    /**
     * BaseQuickAdapter.RequestLoadMoreListener
     */
    @Override
    public void onLoadMoreRequested() {
        if (onDataChangeListener != null && load_more_enable) {
            onDataChangeListener.onLoadMore();
        }
    }

    /**
     * BaseQuickAdapter.OnRefreshListener
     *
     * @param refreshEnable
     */
    @Override
    public void onRefreshEnable(boolean refreshEnable) {
        srl.setEnabled(refreshEnable);
    }

    /**
     * 设置上拉刷新和下拉加载更多
     *
     * @param onDataChangeListener
     */
    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    /**
     * 设置item点击事件
     *
     * @param on_item_click
     */
    public void setOnItemClickListener(OnRecyclerItemClickListener on_item_click) {
        adapter.setOnRecyclerViewItemClickListener(on_item_click);
    }

    /**
     * 设置SwipeRefreshLayout刷新
     *
     * @param refresh
     */
    public void setRefreshing(boolean refresh) {
        if (srl != null) {
            srl.setRefreshing(refresh);
        }
//        adapter.removeFooterView(footer);
    }

    public void setRefreshEnable(boolean enable) {
        srl.setEnabled(enable);
    }

    public boolean isRefreshEnable(boolean enable) {
        return srl.isEnabled();
    }

    public void setLoadMoreEnable(boolean enable) {
        if (adapter != null) {
            adapter.openLoadMore(PAGE_SIZE, enable);
        }
        if (enable) {
            adapter.removeFooterView(footer);
            adapter.addFooterView(footer);
        } else {
            adapter.removeFooterView(footer);
        }
        this.load_more_enable = enable;
    }

    public boolean isLoadMoreEnable(boolean enable) {
        return this.load_more_enable;
    }

    public RecyclerView getRecyclerView() {
        return rv;
    }

    public SwipeRefreshLayout getRefreshView() {
        return srl;
    }

    public void setHasFixedSize(boolean isHasFixdSize) {
        rv.setHasFixedSize(isHasFixdSize);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        rv.setLayoutManager(layoutManager);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        rv.addOnScrollListener(listener);
    }

    public interface OnDataChangeListener {
        void onRefresh();

        void onLoadMore();
    }

    private class OnSwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (onDataChangeListener != null) {
                onDataChangeListener.onRefresh();
            }
        }
    }

    public interface OnRecyclerItemClickListener extends BaseQuickAdapter.OnRecyclerViewItemClickListener {
        //
    }
}
