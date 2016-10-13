package go.xzillion.com.xzilliongo.main.view;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import go.xzillion.com.xzilliongo.R;
import go.xzillion.com.xzilliongo.common.MyAppTheme;
import go.xzillion.com.xzilliongo.main.presenter.MainPresenter;
import go.xzillion.com.xzilliongo.main.presenter.MainPresenterImpl;
import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.view.SoupListFragment;



public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private DrawerLayout mainDrawer;
    private Toolbar mainToolBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView navHeadImage;
    private NavigationView mainNavView;
    private SystemBarTintManager tintManager;
    private SoupListFragment soupListFragment;
    private Fragment currentFragment;
    private MainPresenter mainPresenter;
    private Button netErrorBt;
    private Boolean isError = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);
        initView();
        initFragment();
    }

    private void initView(){//初始化主视图
        this.mainDrawer = (DrawerLayout) findViewById(R.id.drawer_main);
        this.mainToolBar = (Toolbar) findViewById(R.id.toolbar_main);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr_layout);
        swipeRefreshLayout.setEnabled(false);
        MainActivity.this.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        this.mainNavView = (NavigationView) findViewById(R.id.nav_view);
        View headView = mainNavView.getHeaderView(0);//奇怪的是不通过这种方式获取headImageView会报空指针
        this.navHeadImage = (ImageView) headView.findViewById(R.id.nav_head_image);
        this.netErrorBt = (Button) findViewById(R.id.tv_error);
        this.netErrorBt.setOnClickListener(this);
        this.mainToolBar.setTitle("GO");
        setSupportActionBar(mainToolBar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawer, mainToolBar, R.string.drawer_open, R.string.drawer_close);
        this.mainDrawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mainNavView.setNavigationItemSelectedListener(this);
        tintManager = new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏

//            TextView textView = new TextView(this);
//            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight());
//            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//            textView.setLayoutParams(lParams);
//            // 获得根视图并把TextView加进去。
//            ViewGroup view = (ViewGroup) getWindow().getDecorView();
//            view.addView(textView);
//

//            tintManager.setStatusBarTintEnabled(true);
//           tintManager.setStatusBarTintColor(Color.parseColor("#3F51B5"));//通知栏所需颜色
            mainToolBar.setPadding(0,tintManager.getConfig().getStatusBarHeight(),0,0);
            setStatusBarUpperAPI19();

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mainToolBar.setPadding(0,tintManager.getConfig().getStatusBarHeight(),0,0);
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initFragment(){//初始化fragment
        this.soupListFragment = new SoupListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,this.soupListFragment).hide(this.soupListFragment).commitAllowingStateLoss();//fragment的交互管理
        this.showSoupListFragment();//显示鸡汤fragment
        this.mainPresenter.loadData();
    }

    private void setStatusBarUpperAPI19() {
        Window window = getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = mContentView.getChildAt(0);
        //移除假的 View
        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                statusBarView.getLayoutParams().height == tintManager.getConfig().getStatusBarHeight()) {
            mContentView.removeView(statusBarView);
        }
        //不预留空间
        if (mContentView.getChildAt(0) != null) {
            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
        }
    }

    private void showSoupListFragment(){
        if ((this.currentFragment == null) || (!(this.currentFragment instanceof SoupListFragment))){
           getSupportFragmentManager().beginTransaction().show(this.soupListFragment).commitAllowingStateLoss();
        }
        this.currentFragment = this.soupListFragment;
    }

    @Override
    public void onSetTheme(final Soup soup) {
        Glide.with(this).load(soup.getData().getHp_img_url()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<Bitmap>(MainActivity.this.navHeadImage) {
            @Override
            protected void setResource(Bitmap resource) {
                MainActivity.this.navHeadImage.setImageBitmap(resource);//设置侧滑导航栏的顶部图片
                final MyAppTheme myAppTheme = MyAppTheme.getInstance();
                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch themeSwatch = palette.getMutedSwatch();
                        soup.getData().setColor_swatch(themeSwatch);
                        myAppTheme.setMainSwatch(themeSwatch);
                        MainActivity.this.mainToolBar.setBackgroundColor(themeSwatch.getRgb());
                        MainActivity.this.swipeRefreshLayout.setColorSchemeColors(themeSwatch.getRgb());
                        MainActivity.this.swipeRefreshLayout.setBackgroundColor(themeSwatch.getRgb());
                        Drawable menuIconDrawable = MainActivity.this.mainToolBar.getNavigationIcon();
                        menuIconDrawable.setColorFilter(themeSwatch.getBodyTextColor(), PorterDuff.Mode.SRC_IN);
                        MainActivity.this.mainToolBar.setNavigationIcon(menuIconDrawable);
                        MainActivity.this.mainToolBar.setTitleTextColor(themeSwatch.getBodyTextColor());
                        MainActivity.this.netErrorBt.setTextColor(themeSwatch.getTitleTextColor());
                    }
                });
            }
        });
    }

    @Override
    public void onSetInitData(List<Soup> soupList) {
        this.netErrorBt.setVisibility(View.GONE);
        this.soupListFragment.setSoupListViewData(soupList);
        this.soupListFragment.setMainView(this);
        isError = false;
    }

    @Override
    public AppCompatActivity getAppcompatActivity() {
        return this;
    }

    @Override
    public void showLoadAnimation() {
        this.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoadAnimation() {
        this.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onLoadDataError(Throwable throwable) {
        isError = true;
        this.netErrorBt.setVisibility(View.VISIBLE);
        Toast.makeText(this.getAppcompatActivity(),throwable.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_soup :
                this.showSoupListFragment();
                break;
            case R.id.menu_article :
                Toast.makeText(this,"点击了文章",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_movie :
                Toast.makeText(this, "点击了电影", Toast.LENGTH_SHORT).show();
                break;
            default :
                this.showSoupListFragment();
                break;
        }
        this.mainDrawer.closeDrawer(Gravity.LEFT);
        item.setChecked(true);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (isError){
            Toast.makeText(this,"ee",Toast.LENGTH_SHORT).show();
            this.mainPresenter.loadData();
        }
    }

}
