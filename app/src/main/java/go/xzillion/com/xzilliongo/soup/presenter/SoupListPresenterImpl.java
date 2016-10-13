package go.xzillion.com.xzilliongo.soup.presenter;

import java.util.List;

import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.listener.SoupListCallBackListener;
import go.xzillion.com.xzilliongo.soup.model.SoupListModel;
import go.xzillion.com.xzilliongo.soup.model.SoupListModelImpl;
import go.xzillion.com.xzilliongo.soup.view.SoupListView;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤列表Presenter的实现类，实现了presenter接口，回调接口，以view为构造形参，供调用view的 数据设置
 * 实例化了ViewModelImpl，通过依赖倒置原则调用数据的获取方法
 */

public class SoupListPresenterImpl implements SoupListPresenter,SoupListCallBackListener {

    private SoupListModel soupListModel;
    private SoupListView soupListView;

    public SoupListPresenterImpl (SoupListView soupListView){
        this.soupListView = soupListView;
        this.soupListModel = new SoupListModelImpl(this);
    }
    @Override
    public void onNext(List<Soup> soups) {
        this.soupListView.setSoupListViewData(soups);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCompleted() {
        this.soupListView.hideLoadAnimation();
    }

    @Override
    public void onError(Throwable throwable) {
        this.soupListView.hideLoadAnimation();
        this.soupListView.onLoadDataError(throwable);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getSoupList(int id) {
        this.soupListView.showLoadAnimation();
        this.soupListModel.getSoupListData(id);
    }
}
