package go.xzillion.com.xzilliongo.soup.api;


import go.xzillion.com.xzilliongo.soup.entity.Soup;
import go.xzillion.com.xzilliongo.soup.entity.SoupList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Xzillion on 2016/9/20.
 * E-mail : zouxiang13148@outlook.com
 * 鸡汤的api
 */

public interface SoupApi {
    @GET("hp/idlist/{id}")
    public Observable <SoupList> getSoupList (@Path("id") int listId);//Retrofit获取鸡汤列表

    @GET("hp/detail/{id}")
    public Observable <Soup> getSoupDetail (@Path("id") String detailId);//Retrofit获取某期鸡汤详情，id为String 适配SoupList的属性
}
