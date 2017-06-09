package com.sky.testmaterialdesign.collapsingtoolbar;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

/**
 * CollapsingToolbarLayout 配合 RecyclerView的简单使用
 * 加入SwipeRefreshLayout.OnRefreshListener实现了RecyclerView的下拉刷新
 * 加入Fresco实现了Gif的图片加载
 * 加入AppBarLayout.OnOffsetChangedListener 解决了在Toolbar收起到最上部时，下拉优先出现了下拉刷新而不是Toolbar的问题
 */
public class CollapsingToolbarLayoutActivity extends BaseActivity
    implements SwipeRefreshLayout.OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    List<String> datas;
    Toolbar toolbar;
    AppBarLayout appBarLayout;

    SwipeRefreshLayout swipeRefreshLayout;

    SimpleDraweeView draweeView;

    String picUrl = "http://img1.comic.zongheng.com/comic/image/2009/8/tyysh3000/ori/20090910073342105382.gif";

    @Override
    protected void init() {
        setContentView(R.layout.activity_collapsing_toolbar_layout);

        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        toolbar = (Toolbar)findViewById(R.id.activity_collapsing_toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.collapsing_swiperefreshlayout);
        draweeView = (SimpleDraweeView)findViewById(R.id.collapsing_img);
        appBarLayout = (AppBarLayout)findViewById(R.id.appBarLayout);

        //设置还没收缩时状态下字体颜色
        collapsingToolbar.setExpandedTitleColor(0x00000000);
        //设置收缩后Toolbar上字体的颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

        initToolbar();
        initRecyclerView();
        loadPic();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("CollapsingToolbarLayout");
    }

    // 加载头部的图片
    private void loadPic() {
        Uri uri = Uri.parse(picUrl);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
            .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setAutoPlayAnimations(true)
            .build();
        draweeView.setController(controller);
    }

    // 初始化RecyclerView
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

        adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Snackbar.make(recyclerView, "Click Item " + (position + 1),
                    Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // 造点RecyclerView的数据
    private void initData() {
        datas = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            datas.add("item " + i);
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        //The Refresh must be only active when the offset is zero :
        swipeRefreshLayout.setEnabled(i == 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onRefresh() {
        Snackbar.make(recyclerView, "下拉刷新咯～", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        private OnRecyclerViewItemClickListener listener;

        public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CollapsingToolbarLayoutActivity.this)
                .inflate(R.layout.item_collapsing_view, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.button.setText(datas.get(position));

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends ViewHolder {

            Button button;

            public MyViewHolder(View itemView) {
                super(itemView);
                button = (Button)itemView.findViewById(R.id.item_collapsing_button);
            }
        }

    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected int getToolbarIncludeId() {
        return 0;
    }
}
