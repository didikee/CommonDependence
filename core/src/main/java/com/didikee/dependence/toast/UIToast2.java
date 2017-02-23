package com.didikee.dependence.toast;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.dependence.R;
import com.didikee.uilibs.utils.DisplayUtil;

/**
 * @deprecated use UIToast instead {@link UIToast}
 * Created by didik 
 * Created time 2017/2/21
 * Description: 
 */

public class UIToast2 {

    public static void showToast(@NonNull Context context, String content) {
        showToast(context, content, false);
    }

    public static void showToast(@NonNull Context context, String content, boolean longTime) {
        showToast(context, content, longTime, 0,0);
    }

    /**
     * {@link layout/transient_notification.xml}
     * @param content content to show
     * @param longTime short or long
     * @param context context
     * @param textColor toast text color
     * @param toastBackgroundColor toast background color
     */
    public static void showToast(@NonNull Context context, String content, boolean longTime, @ColorInt
            int textColor,@ColorInt int toastBackgroundColor) {
        int type = longTime ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, content, type);
        ViewGroup toastView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout
                .layout_toast, null, false);
        if (toastBackgroundColor != 0) {
            toastView.setBackgroundDrawable(getToastBackground(context, toastBackgroundColor));
        }
        TextView textView = (TextView) toastView.findViewById(android.R.id.message);
        // 内部已经作非空判断了
        if (textColor!=0){
            textView.setTextColor(textColor);
        }
        Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        textView.setTypeface(typeface);
        toast.setView(toastView);
        toast.setText(content);
        toast.show();
    }

    private static Drawable getToastBackground(@NonNull Context context, @ColorInt int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(DisplayUtil.dp2px(context, 24));
        gradientDrawable.setColor(color);
        return gradientDrawable;

    }
}
