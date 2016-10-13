package go.xzillion.com.xzilliongo.soup.presenter;

import android.content.Intent;

import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.listener.SoupDetailCallBackListener;
import go.xzillion.com.xzilliongo.soup.model.SoupDetailModel;
import go.xzillion.com.xzilliongo.soup.model.SoupDetailModelImpl;
import go.xzillion.com.xzilliongo.soup.view.SoupDetailView;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public class SoupDetailPresenterImpl implements SoupDetailPresenter , SoupDetailCallBackListener {

    private SoupDetailView soupDetailView;
    private SoupDetailModel soupDetailModel;
    public SoupDetailPresenterImpl(SoupDetailView soupDetailView){
        this.soupDetailModel = new SoupDetailModelImpl(this);
        this.soupDetailView = soupDetailView;
    }

    @Override
    public void getSoupDetail(Intent intent) {
        this.soupDetailModel.getSoupDetail(intent);
    }

    @Override
    public void onNext(Soup soup) {
        this.soupDetailView.getSoupDetail(soup);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void unsubscribe() {

    }
}
