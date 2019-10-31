package com.mm87.android.lib.socialnet.facebook;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog;
import com.mm87.android.lib.socialnet.R;

import java.util.Arrays;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by marco.mendoza on 29/08/2016.
 */
public class FbDialog extends DialogFragment {
    private ProgressDialog progress;

    LoginButton loginButton;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ShareContent shareContent;
    FacebookCallback facebookCallback;
    private String message = "Connecting with Facebook...";

    FbListener listener;
    int tipo = 1;

    public void setListener(FbListener listener) {
        this.listener = listener;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage(message);
        setCancelable(true);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        loginButton = new LoginButton(getActivity()); //(LoginButton) view.findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.setFragment(this);
//        LoginManager.getInstance().logInWithReadPermissions(
//                this,
//                Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, facebookCallback);
        shareDialog.registerCallback(callbackManager, facebookCallback);
        iniciar();

        return progress;
    }

    @TargetApi(15)
    private void iniciar() {
        switch (tipo) {
            case 1: // Login
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    loginButton.callOnClick();
                    }
                }, 300);
                break;
            case 2: // Share
                shareDialog.show(this.shareContent);
                break;
        }
    }

    public void showLogin(FragmentManager fragmentManager, FacebookCallback<LoginResult> listener) {
        this.facebookCallback = listener;
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog_No_Border);
        show(fragmentManager, "");
        this.tipo = 1;
    }

    public void showShareDialog(FragmentManager fragmentManager, FacebookCallback<Sharer.Result> listener, ShareContent shareContent) {
        this.facebookCallback = listener;
        this.shareContent = shareContent;
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog_No_Border);
        show(fragmentManager, "");
        this.tipo = 2;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dismiss();
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
