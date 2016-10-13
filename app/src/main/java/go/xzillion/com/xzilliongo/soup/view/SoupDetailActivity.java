package go.xzillion.com.xzilliongo.soup.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.google.gson.Gson;

import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.common.utils.Tools;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.presenter.SoupDetailPresenter;
import go.xzillion.com.xzilliongo.soup.presenter.SoupDetailPresenterImpl;

public class SoupDetailActivity extends AppCompatActivity implements SoupDetailView, View.OnClickListener {

    private Toolbar soupDetailToolbar;
    private ImageView soupDetailImage;
    private TextView soupDetailTv;
    private View bgView;
    private View wordView;
    private SoupDetailPresenter soupDetailPresenter;
    private View soupDetailShadow;
    private Palette.Swatch detailTheme;
    private Soup soup;
    private MenuItem itemShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_soup_detail);
        initView();
        soupDetailPresenter = new SoupDetailPresenterImpl(this);
        soupDetailPresenter.getSoupDetail(getIntent());
        setSupportActionBar(soupDetailToolbar);
    }

    private void initView(){
        soupDetailToolbar = (Toolbar) findViewById(R.id.soup_detail_toolbar);
        soupDetailImage = (ImageView) findViewById(R.id.soup_detail_image);
        soupDetailTv = (TextView) findViewById(R.id.soup_detail_word);
        wordView = findViewById(R.id.soup_detail_content);
        bgView = findViewById(R.id.content_soup_detail);
        soupDetailShadow = findViewById(R.id.soup_detail_shadow);
        soupDetailImage.setOnClickListener(this);
        if(Tools.checkDeviceHasNavigationBar(this)){
            this.soupDetailTv.setPadding(this.soupDetailTv.getPaddingLeft(), this.soupDetailTv.getPaddingTop(),this.soupDetailTv.getPaddingRight(),  this.soupDetailTv.getPaddingBottom()+Tools.getNavigationBarHeight(this));
        }
    }

    @Override
    public void getSoupDetail(Soup soup) {
        this.soup = soup;
        this.onSetTheme(soup);
        Glide.with(this).load(soup.getData().getHp_img_url()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<Bitmap>(this.soupDetailImage) {
            @Override
            protected void setResource(Bitmap resource) {
                SoupDetailActivity.this.soupDetailImage.setImageBitmap(resource);
            }
        });
        this.soupDetailToolbar.setTitle(soup.getData().getHp_title());
        this.soupDetailTv.setText(soup.getData().getHp_content());
    }

    @Override
    public void onSetTheme(Soup soup) {
        detailTheme = soup.getData().getColor_swatch();
        this.bgView.setBackgroundColor(detailTheme.getRgb());
        this.soupDetailTv.setBackgroundColor(detailTheme.getRgb());
        this.soupDetailTv.setTextColor(detailTheme.getBodyTextColor());
        this.soupDetailToolbar.setTitleTextColor(detailTheme.getTitleTextColor());
        this.soupDetailToolbar.setBackgroundColor(detailTheme.getRgb());

        if(Build.VERSION.SDK_INT >= 21 && Tools.checkDeviceHasNavigationBar(this)){

            this.getWindow().setNavigationBarColor(detailTheme.getRgb());//当navigationBar不透明时计算padding是以navigationBar顶部为基准，透明时以底部为基准
            this.soupDetailTv.setPadding(this.soupDetailTv.getPaddingLeft(), this.soupDetailTv.getPaddingTop(),this.soupDetailTv.getPaddingRight(),  24);
        }
//        this.showContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.soup_detail_menu,menu);
        itemShow = menu.getItem(0);
        for(int i = 0; i< menu.size(); i++){
            MenuItem toolMenuItem = menu.getItem(i);
            Drawable menuItemDrawable = toolMenuItem.getIcon();
            menuItemDrawable.setColorFilter(detailTheme.getTitleTextColor(), PorterDuff.Mode.SRC_IN);
            toolMenuItem.setIcon(menuItemDrawable);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public AppCompatActivity getAppcompatActivity() {
        return this;
    }

    @Override
    public void showLoadAnimation() {

    }

    @Override
    public void hideLoadAnimation() {

    }

    @Override
    public void onLoadDataError(Throwable throwable) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.detail_menu_show:
                Intent intent = new Intent(this,SoupDetailFullImageActivity.class);
                  intent.putExtra("soup",new Gson().toJson(this.soup));
                if (Build.VERSION.SDK_INT >= 21){
                    this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, soupDetailImage, "soupImage").toBundle());
                    break;
                }
                this.startActivity(intent);
                break;
            case R.id.detail_menu_download:
                Toast.makeText(this, "点击了下载图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_menu_share:
                Toast.makeText(this, "点击了分享鸡汤", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showContent()
    {
        AnimatorSet localAnimatorSet = new AnimatorSet();
        localAnimatorSet.setDuration(300L);
        Animator[] arrayOfAnimator = new Animator[4];
        arrayOfAnimator[0] = ObjectAnimator.ofFloat(this.soupDetailImage, "translationY", new float[] { 200.0F, 0.0F });
        arrayOfAnimator[1] = ObjectAnimator.ofFloat(this.soupDetailImage, "alpha", new float[] { 0.0F, 1.0F });
        arrayOfAnimator[2] = ObjectAnimator.ofFloat(this.wordView, "translationY", new float[] { -200.0F, 0.0F });
        arrayOfAnimator[3] = ObjectAnimator.ofFloat(this.wordView, "alpha", new float[] { 0.0F, 1.0F });
        localAnimatorSet.playTogether(arrayOfAnimator);
        localAnimatorSet.setStartDelay(300L);
        localAnimatorSet.start();
    }

    @Override
    public void onBackPressed() {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.soupDetailShadow, "alpha", new float[] { 0.0F, 1.0F });
        localObjectAnimator.addListener(new AnimatorListenerAdapter()
        {
            public void onAnimationEnd(Animator paramAnonymousAnimator)
            {
            }

            public void onAnimationStart(Animator paramAnonymousAnimator)
            {
                SoupDetailActivity.this.soupDetailShadow.setVisibility(View.VISIBLE);
            }
        });
        localObjectAnimator.start();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
         this.onOptionsItemSelected(itemShow);
    }
}
