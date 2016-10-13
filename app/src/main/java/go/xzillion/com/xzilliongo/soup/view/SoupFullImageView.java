package go.xzillion.com.xzilliongo.soup.view;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import go.xzillion.com.xzilliongo.common.view.BaseView;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public interface SoupFullImageView extends BaseView {
    public void getFullImage(Soup soup);
    public void onSetTheme(Palette.Swatch swatch);
}
