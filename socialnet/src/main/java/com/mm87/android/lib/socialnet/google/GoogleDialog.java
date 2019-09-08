package com.mm87.android.lib.socialnet.google;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mm87.android.lib.socialnet.R;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by marco.mendoza on 29/08/2016.
 */
public class GoogleDialog extends DialogFragment {

    private static final int REQUEST_GOOGLE_SIGN_IN = 10101;

    private ProgressDialog progress;

    private GoogleSignInClient mGoogleSignInClient;

    private GoogleListener listener;
    private String message = "Connecting with Google...";

    public void setListener(GoogleListener listener) {
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

        initGoogleSignInClient();
        callGoogleSignInIntent();
        return progress;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
            dismiss();
        }
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    private void callGoogleSignInIntent() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_GOOGLE_SIGN_IN);
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            listener.onSuccess(account);

        } catch (ApiException e) {
            Log.w("GoogleDialog", "signInResult:failed code=" + e.getStatusCode());
            listener.onError(e);
        }
    }

    public void showLogin(FragmentManager fragmentManager) {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog_No_Border);
        show(fragmentManager, this.getClass().getSimpleName());
    }
}
