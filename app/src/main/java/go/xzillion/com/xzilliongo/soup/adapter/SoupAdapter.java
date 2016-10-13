package go.xzillion.com.xzilliongo.soup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.soup.entity.*;
import go.xzillion.com.xzilliongo.soup.listener.SoupViewBindListener;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 */

public class SoupAdapter extends RecyclerView.Adapter<SoupViewHolder> {

    private Context context;
    private List<Soup> soupList;

    public SoupAdapter(Context context){
        this.context = context;
        this.soupList = new ArrayList<Soup>();
    }
    @Override
    public SoupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SoupViewHolder(new SoupViewBindListener(this.context ,LayoutInflater.from(this.context).inflate(R.layout.list_item_soup,parent,false)));
    }

    @Override
    public void onBindViewHolder(SoupViewHolder holder, int position) {
        Soup soup = this.soupList.get(position);
        holder.getViewBindListener().onViewBind(soup);
    }


    @Override
    public int getItemCount() {
       if (this.soupList == null)
           return 0;
        return soupList.size();
    }

    public void setSoupList(List<Soup> soupList){
        int i = this.getItemCount();
        this.soupList.addAll(soupList);
        notifyItemRangeChanged(i,this.soupList.size());
    }

    public int getLastItemId(){
        return Integer.valueOf((this.soupList.get(this.soupList.size() - 1).getData()).getHpcontent_id()).intValue();
    }
}
