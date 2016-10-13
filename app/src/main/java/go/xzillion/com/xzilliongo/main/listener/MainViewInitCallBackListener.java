package go.xzillion.com.xzilliongo.main.listener;

import go.xzillion.com.xzilliongo.common.listener.RetrofitCallBackListener;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/6.
 * E-mail : zouxiang13148@outlook.com
 */

public interface MainViewInitCallBackListener extends RetrofitCallBackListener {
    public void onNext(Soup soup);
}
