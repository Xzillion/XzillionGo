package go.xzillion.com.xzilliongo.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 常用的工具类
 */

public class Tools {
    public static int Dp2Px(Context paramContext, float paramFloat) {//将dp转化为px
        return (int) (0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
    }

    public static int getNavigationBarHeight(Context paramContext) {//获取导航栏的高度
        int navigationBarHeight = 0;
        Resources rs = paramContext.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(paramContext) ) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    public static int getStatusBarHeight(Context paramContext) {//获取状态栏的高度
        int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int j = 0;
        if (i > 0)
            j = paramContext.getResources().getDimensionPixelSize(i);
        return j;
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {

        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            Log.w("Toos", e);
        }
        return hasNavigationBar;
    }

    public static void hideInputPanel(final EditText paramEditText) {//隐藏输入键盘
        paramEditText.postDelayed(new Runnable() {
                                      public void run() {
                                          paramEditText.setFocusable(true);
                                          paramEditText.requestFocus();
                                          ((InputMethodManager) paramEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
                                      }
                                  }
                , 0L);
    }

    public static void showInputPanel(final EditText paramEditText) {//弹出输入键盘
        paramEditText.postDelayed(new Runnable() {
                                      public void run() {
                                          paramEditText.setFocusable(true);
                                          paramEditText.requestFocus();
                                          ((InputMethodManager) paramEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(paramEditText, 0);
                                      }
                                  }
                , 500L);
    }
    public static boolean isBright(int paramInt)//将color转化为H（色度）S（饱和度）V（明度）模型，判断颜色是否明亮（V大于等于0.5视为明亮）
    {
        float[] arrayOfFloat = new float[3];
        Color.colorToHSV(paramInt, arrayOfFloat);
        return arrayOfFloat[2] >= 0.5f;
    }
}
