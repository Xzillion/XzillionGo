package go.xzillion.com.xzilliongo.soup.view;

import java.util.List;

import go.xzillion.com.xzilliongo.common.view.BaseView;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 提供鸡汤列表数据设置的入口,供listFragment实现
 */

public interface SoupListView extends BaseView{
    public void setSoupListViewData(List<Soup> soupList);
}
