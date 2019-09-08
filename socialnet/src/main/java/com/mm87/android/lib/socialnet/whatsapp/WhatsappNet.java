package com.mm87.android.lib.socialnet.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by marco.mendoza on 30/08/2016.
 */
public class WhatsappNet {

    public static void shareImage(Context context, Bitmap bitmap) throws Exception {
        File file = new File(context.getCacheDir(), bitmap + ".png");
        FileOutputStream fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
        file.setReadable(true, false);
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setType("image/png");
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        boolean exist = false;
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.whatsapp")) {
                intent.setPackage(info.activityInfo.packageName);
                exist = true;
            }
        }
        if (!exist) {
            throw new PackageManager.NameNotFoundException("WhatsApp no esta instalado.");
        }
        context.startActivity(intent);
    }

    public static void shareText(Context context, String text) throws Exception {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        boolean exist = false;
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.whatsapp")) {
                intent.setPackage(info.activityInfo.packageName);
                exist = true;
            }
        }
        if (!exist) {
            throw new PackageManager.NameNotFoundException("WhatsApp no esta instalado.");
        }
        context.startActivity(intent);
    }
}
