package go.xzillion.com.xzilliongo.soup.presenter;

import android.content.Intent;
import android.graphics.Bitmap;

import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.listener.SoupFullImageCallBackListener;
import go.xzillion.com.xzilliongo.soup.model.SoupFullImageModel;
import go.xzillion.com.xzilliongo.soup.model.SoupFullImageModelImpl;
import go.xzillion.com.xzilliongo.soup.view.SoupFullImageView;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public class SoupFullImagePresenterImpl implements SoupFullImageCallBackListener, SoupFullImagePresenter {
    private SoupFullImageModel soupFullImageModel;
    private SoupFullImageView soupFullImageView;

    public SoupFullImagePresenterImpl(SoupFullImageView soupFullImageView){
        this.soupFullImageView = soupFullImageView;
        this.soupFullImageModel  = new SoupFullImageModelImpl(this);
    }

    @Override
    public void onNext(Soup soup) {
        this.soupFullImageView.getFullImage(soup);
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

    @Override
    public void getSoupFullImage(Intent intent) {
        this.soupFullImageModel.getFullImage(intent);
    }
}
