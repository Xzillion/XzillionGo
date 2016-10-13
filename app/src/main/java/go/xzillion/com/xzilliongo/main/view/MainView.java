package go.xzillion.com.xzilliongo.main.view;

import android.support.v7.graphics.Palette;

import java.util.List;

import go.xzillion.com.xzilliongo.common.view.BaseView;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/5.
 * E-mail : zouxiang13148@outlook.com
 */

public interface MainView extends BaseView {
    public void onSetTheme(Soup soup);
    public void onSetInitData(List<Soup> soupList);
}
