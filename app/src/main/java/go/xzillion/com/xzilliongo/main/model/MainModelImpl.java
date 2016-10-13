package go.xzillion.com.xzilliongo.main.model;

import java.util.List;

import go.xzillion.com.xzilliongo.common.listener.RetrofitCallBackListener;
import go.xzillion.com.xzilliongo.common.oneapi.OneApi;
import go.xzillion.com.xzilliongo.main.listener.MainViewInitCallBackListener;
import go.xzillion.com.xzilliongo.soup.api.SoupApi;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.entity.SoupList;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Xzillion on 2016/10/6.
 * E-mail : zouxiang13148@outlook.com
 */

public class MainModelImpl implements MainModel {
    private MainViewInitCallBackListener mainViewInitCallBackListener;

    public MainModelImpl(MainViewInitCallBackListener mainViewInitCallBackListener){
        this.mainViewInitCallBackListener = mainViewInitCallBackListener;
    }

    @Override
    public void getInitData() {
        ((SoupApi) OneApi
                .getRetrofit()
                .create(SoupApi.class))
                .getSoupList(0)//获取鸡汤List
                .subscribeOn(Schedulers.io())//在io线程中操作网络数据的获取
                .flatMap(new Func1<SoupList, Observable<String>>() {//从鸡汤id列表中逐个取出鸡汤的id
                    @Override
                    public Observable<String> call(SoupList soupList) {
                        return Observable.from(soupList.getString());//返回鸡汤id
                    }
                }).flatMap(new Func1<String, Observable<Soup>>() {
            @Override
            public Observable<Soup> call(String s) {
                return ((SoupApi) OneApi.getRetrofit()//通过鸡汤id获取每期鸡汤的详情
                        .create(SoupApi.class))
                        .getSoupDetail(s);
            }
        })//将获取到的所有鸡汤逐个传入subscribe
                .observeOn(AndroidSchedulers.mainThread())//在主线程中设置界面的数据
                .subscribe(new Subscriber<Soup>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        MainModelImpl.this.mainViewInitCallBackListener.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        MainModelImpl.this.mainViewInitCallBackListener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        MainModelImpl.this.mainViewInitCallBackListener.onError(e);
                    }

                    @Override
                    public void onNext(Soup soup) {
                        MainModelImpl.this.mainViewInitCallBackListener.onNext(soup);
                    }
                });
    }
}
