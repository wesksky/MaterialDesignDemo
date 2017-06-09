package com.sky.testmaterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 简易封装，考虑每个页面都会使用Toolbar，Toolbar就在baseActivity中统一初始化
 */
public abstract class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    View includeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (getToolbarIncludeId() != 0) {
            includeView = findViewById(getToolbarIncludeId());
            if (includeView != null) {
                toolbar = (Toolbar)includeView.findViewById(R.id.main_toolbar);

                toolbar.setTitle("MaterialDesign Demo");
                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });

                setSupportActionBar(toolbar);

                //显示返回按钮
                getSupportActionBar().setHomeButtonEnabled(true);

                //设置返回按钮可点击
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() != 0) {
            getMenuInflater().inflate(getMenuId(), menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 默认点击返回 finish Activity
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 通用初始化,需要在这里设置布局文件
    protected abstract void init();

    // 设置menuId则有menu菜单
    protected abstract int getMenuId();

    // 返回Toolbar include控件的id
    protected abstract int getToolbarIncludeId();

    protected Toolbar getToolbar() {
        return toolbar;
    }

}
