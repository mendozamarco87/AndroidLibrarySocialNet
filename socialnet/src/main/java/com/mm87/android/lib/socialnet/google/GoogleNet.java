package com.mm87.android.lib.socialnet.google;

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
}
