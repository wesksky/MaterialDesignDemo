package com.sky.testmaterialdesign;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.sky.testmaterialdesign.collapsingtoolbar.CollapsingToolbarLayoutActivity;
import com.sky.testmaterialdesign.snackerbar.SnackerBarActivity;
import com.sky.testmaterialdesign.tablayout.TabLayoutActivity;
import com.sky.testmaterialdesign.textinputlayout.TextInputLayoutActivity;
import com.sky.testmaterialdesign.transitions.TransitionsActivity;

/**
 * 主页面
 * 提供了FloatingActionButton的简单用法
 * 提供了Menu的简单应用示例
 */
public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    // 悬浮按钮
    FloatingActionButton faButton;
    // RecyclerView
    MyAdapter adapter;
    RecyclerView recyclerView;
    // RecyclerView内容
    String titles[] = {
        "SnackerBar（替代Toast）",
        "TextInputLayout（EditText特效）",
        "TabLayout（实现Tab选项卡）",
        "CollapsingToolbarLayout（CoordinatorLayout特效）",
        "Transition（Activity跳转特效）"
    };
    Class classes[] = {
        SnackerBarActivity.class,
        TextInputLayoutActivity.class,
        TabLayoutActivity.class,
        CollapsingToolbarLayoutActivity.class,
        TransitionsActivity.class
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings1) {
            Snackbar.make(findViewById(R.id.main_toolbar_include), "click action_settings1", Snackbar.LENGTH_LONG)
                .show();
            return true;
        }
        if (id == R.id.action_settings2) {
            Snackbar.make(findViewById(R.id.main_toolbar_include), "click action_settings2", Snackbar.LENGTH_LONG)
                .show();
            return true;
        }
        if (id == R.id.action_settings3) {
            Snackbar.make(findViewById(R.id.main_toolbar_include), "click action_settings3", Snackbar.LENGTH_LONG)
                .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toActivity(Class activity) {
        startActivity(new Intent(MainActivity.this, activity));
    }

    @Override
    protected void init() {
        setContentView(R.layout.activity_main);

        faButton = (FloatingActionButton)findViewById(R.id.fab_search);
        faButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.main_toolbar_include), "hint FloatingActionButton",
                    Snackbar.LENGTH_LONG)
                    .show();
            }
        });

        adapter = new MyAdapter();

        recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // HomeButton 和 NavigationView 建立关联关系, 动画会随着拖动变化
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            MainActivity.this, drawer, getToolbar(), R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_main;
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.main_toolbar_include;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                MainActivity.this).inflate(R.layout.main_item, parent,
                false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.button.setText(titles[position]);
            holder.button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, classes[position]));
                }
            });
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        class MyViewHolder extends ViewHolder {
            Button button;

            public MyViewHolder(View itemView) {
                super(itemView);
                button = (Button)itemView.findViewById(R.id.item_button);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //if (id == R.id.nav_camera) {
        //    // Handle the camera action
        //} else if (id == R.id.nav_gallery) {
        //
        //} else if (id == R.id.nav_slideshow) {
        //
        //} else if (id == R.id.nav_manage) {
        //
        //} else if (id == R.id.nav_share) {
        //
        //} else if (id == R.id.nav_send) {
        //
        //}

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
