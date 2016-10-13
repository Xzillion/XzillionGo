package go.xzillion.com.xzilliongo.soup.listener;

import android.graphics.Bitmap;

import go.xzillion.com.xzilliongo.common.listener.RetrofitCallBackListener;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public interface SoupFullImageCallBackListener extends RetrofitCallBackListener {
    public void onNext(Soup soup);
}
