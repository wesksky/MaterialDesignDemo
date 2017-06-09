package com.sky.testmaterialdesign.textinputlayout;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.sky.testmaterialdesign.BaseActivity;
import com.sky.testmaterialdesign.R;

/**
 * TextInputLayout简单示例
 */
public class TextInputLayoutActivity extends BaseActivity {

    TextInputLayout usernameInputLayout;
    EditText etUsername;

    TextInputLayout passwordInputLayout;
    EditText etPassword;

    @Override
    protected void init() {
        setContentView(R.layout.activity_text_input_layout);

        usernameInputLayout = (TextInputLayout)findViewById(R.id.text_input_layout_username);
        passwordInputLayout = (TextInputLayout)findViewById(R.id.text_input_layout_password);
        usernameInputLayout.setHint("请输入用户名");
        passwordInputLayout.setHint("请输入密码");
        etUsername = usernameInputLayout.getEditText();
        etPassword = passwordInputLayout.getEditText();

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 6) {
                    usernameInputLayout.setErrorEnabled(true);
                    usernameInputLayout.setError("输入用户名过长");
                } else {
                    usernameInputLayout.setErrorEnabled(false);
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 4) {
                    passwordInputLayout.setErrorEnabled(true);
                    passwordInputLayout.setError("密码有问题");
                } else {
                    passwordInputLayout.setErrorEnabled(false);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getToolbar().setTitle("TextInputLayout");
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected int getToolbarIncludeId() {
        return R.id.snacker_toolbar_include;
    }
}
