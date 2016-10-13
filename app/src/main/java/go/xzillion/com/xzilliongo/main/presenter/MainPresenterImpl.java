package go.xzillion.com.xzilliongo.main.presenter;

import java.util.ArrayList;
import java.util.List;

import go.xzillion.com.xzilliongo.main.listener.MainViewInitCallBackListener;
import go.xzillion.com.xzilliongo.main.model.MainModel;
import go.xzillion.com.xzilliongo.main.model.MainModelImpl;
import go.xzillion.com.xzilliongo.main.view.MainView;
import go.xzillion.com.xzilliongo.soup.entity.Soup;

/**
 * Created by Xzillion on 2016/10/6.
 * E-mail : zouxiang13148@outlook.com
 */

public class MainPresenterImpl implements MainViewInitCallBackListener, MainPresenter{
    private MainView mainView;
    private MainModel mainModel;
    private Boolean isFirst = false;
    private List<Soup> soups;
    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        this.mainModel = new MainModelImpl(this);

    }

    @Override
    public void onNext(Soup soup) {
        if (isFirst){
            this.mainView.onSetTheme(soup);
            this.isFirst = false;
        }
        this.soups.add(soup);
    }

    @Override
    public void onStart() {
        this.soups = new ArrayList<Soup>();
        this.isFirst = true;
    }

    @Override
    public void onCompleted() {
        this.mainView.onSetInitData(this.soups);
        this.mainView.hideLoadAnimation();
    }

    @Override
    public void onError(Throwable throwable) {
        this.mainView.hideLoadAnimation();
        this.mainView.onLoadDataError(throwable);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadData() {
        this.mainView.showLoadAnimation();
        this.mainModel.getInitData();
    }
}
