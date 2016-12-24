package com.enrico.colorpicker;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

class paletteUtils {

    private static void createCircularBitmap(Context context, Resources resources, ImageView imageView, String tag) {

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(SpToPixels(context, 50), SpToPixels(context, 50), conf);
        int color = Color.parseColor(tag);
        bmp.eraseColor(color);
        RoundedBitmapDrawable RBD = RoundedBitmapDrawableFactory.create(resources, bmp);
        RBD.setCircular(true);
        imageView.setImageDrawable(RBD);
    }

    static int SpToPixels(Context context, int width) {

        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int density = Math.round(scaledDensity);
        return width * density;
    }

    static void initializeMaterialPalette(final Activity activity, Resources resources, final View colorView, final TextView hashtext, final TextView hashtag, final SeekBar alphatize, final EditText editAA, final EditText editAlpha, final TextView rgb, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB, ImageView... imageViews) {

        for (ImageView circles : imageViews) {

            final String tag = circles.getTag().toString();
            final String desc = circles.getContentDescription().toString();
            final int color = Color.parseColor(tag);

            createCircularBitmap(activity, resources, circles, tag);

            circles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alphatize.setProgress(255);
                    viewUtils.updateColorView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, color);
                    viewUtils.makeToast(activity, desc + ": " + tag.toUpperCase());

                }
            });
        }
    }
}