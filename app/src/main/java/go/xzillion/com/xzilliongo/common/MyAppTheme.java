package go.xzillion.com.xzilliongo.common;

import android.support.v7.graphics.Palette;

/**
 * Created by Xzillion on 2016/10/6.
 * E-mail : zouxiang13148@outlook.com
 */

public class MyAppTheme {
    private Palette.Swatch mainSwatch;
    private final static MyAppTheme myAppTheme = new MyAppTheme();

    public static MyAppTheme getInstance(){
        return myAppTheme;
    }

    public void setMainSwatch(Palette.Swatch mainSwatch){
        this.mainSwatch = mainSwatch;
    }

    public Palette.Swatch getMainSwatch(Palette.Swatch swatch){
        if (swatch == null)
            return this.mainSwatch;
        else
            return swatch;
    }
}
