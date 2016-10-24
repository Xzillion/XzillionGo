package go.xzillion.com.xzilliongo.soup.listener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.google.gson.Gson;

import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.common.MyAppTheme;
import go.xzillion.com.xzilliongo.common.listener.ViewBindListener;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.view.SoupDetailActivity;

/**
 * Created by Xzillion on 2016/9/21.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤View绑定时设置数据
 */

public class SoupViewBindListener extends ViewBindListener implements View.OnClickListener {
    private Soup soup;
    private ImageView soupItemImage;
    private TextView soupItemTitle;
    private TextView soupItemAuthor;
    private MyAppTheme myAppTheme = MyAppTheme.getInstance();
    public SoupViewBindListener(Context context, View view) {
        super(context, view);
        soupItemImage = (ImageView) view.findViewById(R.id.soup_item_image);
        soupItemTitle = (TextView) view.findViewById(R.id.soup_item_title);
        soupItemAuthor = (TextView) view.findViewById(R.id.soup_item_author);
    }

    @Override
    public void onViewBind(Object object) {
        this.soup = (Soup)object;//获取鸡汤对象
        this.soupItemTitle.setText(soup.getData().getHp_title());//设置期数
        this.soupItemAuthor.setText(soup.getData().getHp_author());//设置作者信息
        Glide.with(getContext()).load(this.soup.getData().getHp_img_url()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<Bitmap>(this.soupItemImage) {//利用glide库加载图片
            @Override
            protected void setResource(Bitmap resource) {
                SoupViewBindListener.this.soupItemImage.setImageBitmap(resource);//设置图片
                if (SoupViewBindListener.this.soup.getData().getColor_swatch() == null){
                    Palette.from(resource)
                            .generate(new Palette.PaletteAsyncListener(){

                        @Override
                        public void onGenerated(Palette palette) {
                            SoupViewBindListener.this.soup.getData().setColor_swatch(myAppTheme.getMainSwatch(palette.getMutedSwatch()));//设置每个鸡汤的颜色样本
                            SoupViewBindListener.this.getView().setBackgroundColor(myAppTheme.getMainSwatch(palette.getMutedSwatch()).getRgb());
                        }
                    });
                }
            }
        });
        this.getView().setOnClickListener(this);
        Animation soupAnimation = AnimationUtils.loadAnimation(this.getContext(), android.R.anim.slide_in_left);
        this.getView().startAnimation(soupAnimation);//为item设置载入动画
    }

    @Override
    public void onViewUnBind() {//解除绑定
        this.soup = null;
        this.soupItemAuthor.setText("");
        this.soupItemTitle.setText("");
        this.soupItemImage.setImageBitmap(null);
    }

    @Override
    public void onClick(View view) {
        Intent soupDetailIntent = new Intent(this.getContext(), SoupDetailActivity.class);
        soupDetailIntent.putExtra("soup", new Gson().toJson(this.soup));
        if (Build.VERSION.SDK_INT >= 21){
            this.getContext().startActivity(soupDetailIntent, ActivityOptions.makeSceneTransitionAnimation((Activity)this.getContext(), view, "soupView").toBundle());
            return;
        }
        this.getContext().startActivity(soupDetailIntent);
    }


}
