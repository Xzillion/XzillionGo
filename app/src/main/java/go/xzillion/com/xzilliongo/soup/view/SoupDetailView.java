package go.xzillion.com.xzilliongo.soup.view;

import go.xzillion.com.xzilliongo.common.view.BaseView;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public interface SoupDetailView extends BaseView {
    public void getSoupDetail(Soup soup);
    public void onSetTheme(Soup soup);
}
