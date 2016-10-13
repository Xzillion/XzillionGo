package go.xzillion.com.xzilliongo.common.view;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 */

public interface BaseView {
    public AppCompatActivity getAppcompatActivity();
    public void showLoadAnimation();
    public void hideLoadAnimation();
    public void onLoadDataError(Throwable throwable);
}
