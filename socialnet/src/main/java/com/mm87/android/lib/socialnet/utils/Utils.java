package com.mm87.android.lib.socialnet.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * Created by marco.mendoza on 29/08/2016.
 */
public class Utils {

    public static String generateHashCode(Context context, String packagee) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    packagee,PackageManager.GET_SIGNATURES);
            String code = "";
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                code = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", code);
            }
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Convert to UTF-8 text to put it on url format
     *
     * @param s text to be converted
     * @return text on UTF-8 format
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf("wtf", "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }
}
