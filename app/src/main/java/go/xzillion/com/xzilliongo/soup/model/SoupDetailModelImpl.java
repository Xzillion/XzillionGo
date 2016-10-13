package go.xzillion.com.xzilliongo.soup.model;

import android.content.Intent;

import com.google.gson.Gson;

import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.listener.SoupDetailCallBackListener;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public class SoupDetailModelImpl implements SoupDetailModel {

    private SoupDetailCallBackListener soupDetailCallBackListener;
    private Soup soup;
    public SoupDetailModelImpl(SoupDetailCallBackListener soupDetailCallBackListener){
        this.soupDetailCallBackListener = soupDetailCallBackListener;
    }

    @Override
    public void getSoupDetail(Intent intent) {
        Gson gson = new Gson();
        this.soup = gson.fromJson(intent.getStringExtra("soup"), Soup.class);
        this.soupDetailCallBackListener.onNext(this.soup);
    }
}
