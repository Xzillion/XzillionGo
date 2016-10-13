package go.xzillion.com.xzilliongo.soup.listener;

import java.util.List;

import go.xzillion.com.xzilliongo.common.listener.RetrofitCallBackListener;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤列表的网络数据获取的回调接口，处理数据获取各阶段的回调事件
 */

public interface SoupListCallBackListener extends RetrofitCallBackListener {
    public void onNext(List<Soup> soups);//接受数据阶段
}
