package go.xzillion.com.xzilliongo.soup.view;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.common.utils.Tools;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.presenter.SoupFullImagePresenter;
import go.xzillion.com.xzilliongo.soup.presenter.SoupFullImagePresenterImpl;

public class SoupDetailFullImageActivity extends AppCompatActivity implements SoupFullImageView{

    private TextView authorTv;
    private ImageView fullIv;
    private RelativeLayout fullRelativeLayout;
    private LinearLayout fullTvContainer;
    private SoupFullImagePresenter soupFullImagePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_soup_detail_full_image);
        initView();
        soupFullImagePresenter = new SoupFullImagePresenterImpl(this);
        soupFullImagePresenter.getSoupFullImage(getIntent());
    }

    private void initView(){
        authorTv = (TextView) findViewById(R.id.soup_full_author);
        fullIv = (ImageView) findViewById(R.id.soup_full_imageView);
        fullRelativeLayout = (RelativeLayout) findViewById(R.id.content_soup_detail_full_image);
        fullTvContainer = (LinearLayout) findViewById(R.id.soup_full_tvcontainer);
        if(Build.VERSION.SDK_INT >= 21 && Tools.checkDeviceHasNavigationBar(this)){
           getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

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
    public void getFullImage(Soup soup) {
        this.onSetTheme(soup.getData().getColor_swatch());
        Glide.with(this).load(soup.getData().getHp_img_url()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<Bitmap>(fullIv) {
            @Override
            protected void setResource(Bitmap resource) {
                SoupDetailFullImageActivity.this.fullIv.setImageBitmap(resource);
            }
        });
        this.authorTv.setText(soup.getData().getHp_author());
        if(Tools.checkDeviceHasNavigationBar(this)){
            this.fullTvContainer.setPadding(this.authorTv.getPaddingLeft(), this.authorTv.getPaddingTop(),this.authorTv.getPaddingRight(), this.authorTv.getPaddingBottom() + Tools.getNavigationBarHeight(this));
        }
    }

    @Override
    public void onSetTheme(Palette.Swatch swatch) {
                SoupDetailFullImageActivity.this.fullRelativeLayout.setBackgroundColor(swatch.getRgb());
    }
}
