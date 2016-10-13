package go.xzillion.com.xzilliongo.soup.entity;

import java.util.List;

/**
 * Created by Xzillion on 2016/9/20.
 * E-mail : zouxiang13148@outlook.com
 * 实体类 获取One鸡汤的序列id列表
 */

public class SoupList {
    private int res;

    private List<String> data ;//鸡汤序列id,一般为十期

    public void setRes(int res){
        this.res = res;
    }
    public int getRes(){
        return this.res;
    }
    public void setString(List<String> data){
        this.data = data;
    }
    public List<String> getString(){
        return this.data;
    }
}
