package go.xzillion.com.xzilliongo.soup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.common.animator.SlideInOutLeftItemAnimator;
import go.xzillion.com.xzilliongo.common.utils.Tools;
import go.xzillion.com.xzilliongo.main.view.MainView;
import go.xzillion.com.xzilliongo.soup.adapter.SoupAdapter;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.presenter.SoupListPresenter;
import go.xzillion.com.xzilliongo.soup.presenter.SoupListPresenterImpl;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤列表的fragment界面
 */

public class SoupListFragment extends Fragment implements SoupListView {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SoupAdapter soupAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SoupListPresenter soupListPresenter;
    private MainView mainView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.scroll_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View layout = LayoutInflater.from(this.getActivity()).inflate(R.layout.activity_main, null);
        swipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.sr_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_layout);
        this.soupListPresenter = new SoupListPresenterImpl(this);
        this.soupAdapter = new SoupAdapter(this.getActivity());
        this.linearLayoutManager = new LinearLayoutManager(getActivity(),1,false);
        this.recyclerView.setAdapter(soupAdapter);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int x, int y) {
                super.onScrolled(recyclerView, x,y);
                Boolean isTop = SoupListFragment.this.linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
                Boolean isBottom = (SoupListFragment.this.linearLayoutManager.findLastCompletelyVisibleItemPosition() == SoupListFragment.this.soupAdapter.getItemCount() - 1);//判断是否滑倒底部
                if( !SoupListFragment.this.swipeRefreshLayout.isRefreshing() && isBottom){

                    SoupListFragment.this.soupListPresenter.getSoupList(SoupListFragment.this.soupAdapter.getLastItemId());
                }
                if(isTop){
                    SoupListFragment.this.swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setSoupListViewData(List<Soup> soupList) {
        this.soupAdapter.setSoupList(soupList);
    }

    @Override
    public AppCompatActivity getAppcompatActivity() {
        return (AppCompatActivity) this.getActivity();
    }

    @Override
    public void showLoadAnimation() {
                mainView.showLoadAnimation();
    }

    @Override
    public void hideLoadAnimation() {
                mainView.hideLoadAnimation();

    }

    @Override
    public void onLoadDataError(Throwable throwable) {
        Toast.makeText(this.getAppcompatActivity(),throwable.toString(),Toast.LENGTH_SHORT).show();
    }

    public void setMainView(MainView mainView){
        this.mainView = mainView;
    }
}
