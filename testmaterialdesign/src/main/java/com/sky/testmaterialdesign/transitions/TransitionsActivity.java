package com.sky.testmaterialdesign.transitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

public class TransitionsActivity extends BaseActivity {

    ImageView ivImg;

    @Override
    protected void init() {
        setContentView(R.layout.activity_transitions);
        ivImg = (ImageView)findViewById(R.id.transition_img);

        // 测试使用Fresco 的SimpleDraweeView时无法显示动画
        //ivImg.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=3749430945,1756305810&fm=214&gp=0.jpg"));

        ivImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransitionsActivity.this, Transition2Activity.class);
                ActivityCompat.startActivity(TransitionsActivity.this, intent,
                    ActivityOptions
                        .makeSceneTransitionAnimation(TransitionsActivity.this,
                            new Pair<View, String>(ivImg, Transition2Activity.TAG_NAME))
                        .toBundle());
            }
        });
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getToolbar().setTitle("Transitions");
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.transition_toolbar_include;
    }
}
