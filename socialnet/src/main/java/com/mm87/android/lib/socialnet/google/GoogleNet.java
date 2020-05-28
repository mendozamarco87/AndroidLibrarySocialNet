package com.mm87.android.lib.socialnet.google;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;

import androidx.fragment.app.FragmentManager;

/**
 * Created by mendoza on 07/09/2019.
 */
public class GoogleNet {


    public static void login(FragmentManager fragmentManager, GoogleListener googleListener) {
        GoogleDialog login = new GoogleDialog();
        login.setListener(googleListener);
        login.showLogin(fragmentManager);
    }

    public static void login(FragmentManager fragmentManager, GoogleListener googleListener, String message) {
        GoogleDialog login = new GoogleDialog();
        login.setMessage(message);
        login.setListener(googleListener);
        login.showLogin(fragmentManager);
    }

    public static void logout(Context context, OnCompleteListener<Void> listener) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        if (listener == null) {
            mGoogleSignInClient.signOut();
        } else {
            mGoogleSignInClient.signOut().addOnCompleteListener(listener);
        }
    }
}
