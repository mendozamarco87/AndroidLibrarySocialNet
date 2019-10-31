package com.mm87.android.lib.socialnet.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.mm87.android.lib.socialnet.R;

import org.json.JSONObject;

import androidx.fragment.app.FragmentManager;

/**
 * Created by marco.mendoza on 29/08/2016.
 */
public class FacebookNet {

    public static void init(Context context) {
        FacebookSdk.setApplicationId(context.getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(context);
    }

    public static void init(Context context, String appId) {
        FacebookSdk.setApplicationId(appId);
        FacebookSdk.sdkInitialize(context);
    }

    public static void login(FragmentManager fragmentManager, FacebookCallback<LoginResult> listener) {
        FbDialog login = new FbDialog();
        login.showLogin(fragmentManager, listener);
    }

    public static FBUser getUserProfile(AccessToken accessToken) {
        final FBUser fbUser = new FBUser();
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject user, GraphResponse graphResponse) {
                fbUser.setEmail(user.optString("email"));
                fbUser.setName(user.optString("name"));
                fbUser.setId(user.optString("id"));
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAndWait();
        return fbUser;
    }

    public static void shareLinkDialog(FragmentManager fragmentManager,
                                       FacebookCallback<Sharer.Result> listener, String titulo,
                                       String descripcion, String url) {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle(titulo)
                .setContentDescription(descripcion)
                .setContentUrl(Uri.parse(url))
                .build();
        FbDialog login = new FbDialog();
        login.showShareDialog(fragmentManager, listener, linkContent);
    }

    public static void sharePhotoDialog(FragmentManager fragmentManager,
                                        FacebookCallback<Sharer.Result> listener,
                                        Bitmap img) {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(img)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        FbDialog login = new FbDialog();
        login.showShareDialog(fragmentManager, listener, content);
    }

    public static void shareVideoDialog(FragmentManager fragmentManager,
                                        FacebookCallback<Sharer.Result> listener,
                                        String videoUrlLocal) {
        ShareVideo video = new ShareVideo.Builder()
                .setLocalUrl(Uri.parse(videoUrlLocal))
                .build();
        ShareVideoContent content = new ShareVideoContent.Builder()
                .setVideo(video)
                .build();
        FbDialog login = new FbDialog();
        login.showShareDialog(fragmentManager, listener, content);
    }
}
