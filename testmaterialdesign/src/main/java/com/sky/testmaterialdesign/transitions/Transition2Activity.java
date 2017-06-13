package com.sky.testmaterialdesign.transitions;

import android.support.v4.view.ViewCompat;
import android.widget.ImageView;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

public class Transition2Activity extends BaseActivity {

    public static final String TAG_NAME = "tag_name_img";

    ImageView img;

    @Override
    protected void init() {
        setContentView(R.layout.activity_transition2);

        img = (ImageView)findViewById(R.id.transition2_img);

        ViewCompat.setTransitionName(img, TAG_NAME);
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getToolbar().setTitle("Transitions2");
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.transition2_toolbar_include;
    }

}
