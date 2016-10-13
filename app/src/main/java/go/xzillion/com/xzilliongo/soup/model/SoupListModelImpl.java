package go.xzillion.com.xzilliongo.soup.model;

import java.util.List;

import go.xzillion.com.xzilliongo.common.oneapi.OneApi;
import go.xzillion.com.xzilliongo.soup.api.SoupApi;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.entity.SoupList;
import go.xzillion.com.xzilliongo.soup.listener.SoupListCallBackListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 获取鸡汤网络数据
 */

public class SoupListModelImpl implements SoupListModel {
    private SoupListCallBackListener soupListCallBackListener;

    public SoupListModelImpl (SoupListCallBackListener soupListCallBackListener){
        this.soupListCallBackListener = soupListCallBackListener;
    }

    @Override
    public void getSoupListData(int id) {
        ((SoupApi) OneApi
                .getRetrofit()
                .create(SoupApi.class))
                .getSoupList(id)//获取鸡汤List
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
                }).toList()//将获取到的所有鸡汤组装为一个List传入subscribe
                .observeOn(AndroidSchedulers.mainThread())//在主线程中设置界面的数据
                .subscribe(new Subscriber<List<Soup>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        SoupListModelImpl.this.soupListCallBackListener.onStart();
                    }

                    @Override
                    public void onCompleted() {//网络数据获取完成
                        SoupListModelImpl.this.soupListCallBackListener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {//网络数据获取错误
                        SoupListModelImpl.this.soupListCallBackListener.onError(e);
                    }

                    @Override
                    public void onNext(List<Soup> soups) {//忘了数据获取
                        SoupListModelImpl.this.soupListCallBackListener.onNext(soups);
                    }
                });
    }
}
