package com.mm87.android.sample;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.mm87.android.lib.base.view.BaseActivity;
import com.mm87.android.lib.socialnet.facebook.FBUser;
import com.mm87.android.lib.socialnet.facebook.FacebookNet;

public class MainActivity extends BaseActivity {

    @Override
    protected int getIdResLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.app_name);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        GoogleNet.login(getSupportFragmentManager(), new GoogleListener() {
//            @Override
//            public void onSuccess(GoogleSignInAccount googleSignInAccount) {
//                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
//            }
//        });
        FacebookNet.init(this);
        FacebookNet.login(getSupportFragmentManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                FBUser fbUser = FacebookNet.getUserProfile(loginResult.getAccessToken());
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }

}
