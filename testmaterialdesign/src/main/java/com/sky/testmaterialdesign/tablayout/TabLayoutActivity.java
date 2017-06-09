package com.sky.testmaterialdesign.tablayout;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

/**
 * TabLayout的应用示例
 * 配合ViewPager + Fragement实现
 */
public class TabLayoutActivity extends BaseActivity {

    SimpleFragmentPagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    private String tabTitles[] = new String[] {"tab1", "tab2", "tab3", "tab4", "tab5", "tab6", "tab7",
        "tab8", "tab9", "tab10"};

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void init() {
        setContentView(R.layout.activity_tab_layout);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        // TabLayout.MODE_FIXED 平均分
        // TabLayout.MODE_SCROLLABLE 可
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // 初始化
        for (int i = 0; i < 10; i++) {
            Bundle args = new Bundle();
            args.putInt(PageFragment.ARG_PAGE, i + 1);
            PageFragment pageFragment = new PageFragment();
            pageFragment.setArguments(args);
            fragmentList.add(pageFragment);
        }

        pagerAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getToolbar().setTitle("TabLayout");
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.tablayout_toolbar_include;
    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < tabTitles.length) {
                return tabTitles[position];
            } else {
                return "";
            }
        }
    }

}
