package go.xzillion.com.xzilliongo.common.listener;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * Retrofit库subscriber订阅者的回调处理接口
 */

public interface RetrofitCallBackListener {

    public void onStart ();//开始阶段
    public void onCompleted ();//完成阶段
    public void onError (Throwable throwable);//错误发生阶段
    public void unsubscribe();//取消订阅阶段
}
