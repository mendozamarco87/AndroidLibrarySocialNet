package com.mm87.android.lib.socialnet.google;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;

/**
 * Created by marco.mendoza on 29/08/2016.
 */
public interface GoogleListener {

    void onSuccess(GoogleSignInAccount googleSignInAccount);

    /**
     * The ApiException status code indicates the detailed failure reason.
     * Please refer to the GoogleSignInStatusCodes class reference for more information.
     * @param e
     */
    void onError(ApiException e);
}
