package go.xzillion.com.xzilliongo.common.listener;

import android.content.Context;
import android.view.View;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * view的绑定抽象类
 */

public abstract class ViewBindListener {
    private Context context;
    private View view;

    public ViewBindListener(Context context, View view ){
        this.view = view;
        this.context = context;
    }

    public Context getContext(){
        return this.context;
    }

    public View getView(){
        return this.view;
    }

    public abstract void onViewBind(Object object);

    public abstract void onViewUnBind();
}
