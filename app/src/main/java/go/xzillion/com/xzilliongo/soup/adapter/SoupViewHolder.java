package go.xzillion.com.xzilliongo.soup.adapter;

import android.support.v7.widget.RecyclerView;
import butterknife.ButterKnife;
import go.xzillion.com.xzilliongo.common.listener.ViewBindListener;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤ViewHolder
 */

public class SoupViewHolder extends RecyclerView.ViewHolder {
    private ViewBindListener viewBindListener;

    public SoupViewHolder(ViewBindListener viewBindListener) {
        super(viewBindListener.getView());
        ButterKnife.bind(viewBindListener.getView());
        this.viewBindListener = viewBindListener;
    }

    public ViewBindListener getViewBindListener(){
        return this.viewBindListener;
    }
}
