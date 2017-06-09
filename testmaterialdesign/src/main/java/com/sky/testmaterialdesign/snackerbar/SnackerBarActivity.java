package com.sky.testmaterialdesign.snackerbar;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

/**
 * SnackerBar简单示例
 */
public class SnackerBarActivity extends BaseActivity {

    @Override
    protected void init() {
        setContentView(R.layout.activity_snacker_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getToolbar().setTitle("SnackerBar");

    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    public void clickSimpleSnacker(View view) {
        Snackbar.make(findViewById(R.id.snacker1), "click simple snackerbar", Snackbar.LENGTH_LONG)
            .show();
    }

    public void clickActionSnacker(View view) {
        Snackbar.make(findViewById(R.id.snacker2), "click action snackerbar", Snackbar.LENGTH_LONG)
            .setAction("action", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SnackerBarActivity.this, "点了下action", Toast.LENGTH_LONG).show();
                }
            }).show();
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.snacker_toolbar_include;
    }
}
