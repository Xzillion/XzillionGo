package go.xzillion.com.xzilliongo.soup.model;

import android.content.Intent;
import android.graphics.Bitmap;

import com.google.gson.Gson;

import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.listener.SoupFullImageCallBackListener;

/**
 * Created by Xzillion on 2016/10/8.
 * E-mail : zouxiang13148@outlook.com
 */

public class SoupFullImageModelImpl implements SoupFullImageModel {

    private SoupFullImageCallBackListener soupFullImageCallBackListener;

    public SoupFullImageModelImpl(SoupFullImageCallBackListener soupFullImageCallBackListener){
        this.soupFullImageCallBackListener = soupFullImageCallBackListener;

    }

    @Override
    public void getFullImage(Intent intent) {

        this.soupFullImageCallBackListener.onNext(new Gson().fromJson(intent.getStringExtra("soup"),Soup.class));
    }
}
