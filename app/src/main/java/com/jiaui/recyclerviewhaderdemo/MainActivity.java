package com.jiaui.recyclerviewhaderdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiaui.recyclerviewhaderdemo.adapter.AdvertisePagerViewAdapter;
import com.jiaui.recyclerviewhaderdemo.adapter.NewsAdapter;
import com.jiaui.recyclerviewhaderdemo.bean.NewsBean;
import com.jiaui.recyclerviewhaderdemo.widget.WrapAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private LinearLayout headerLayout;//头布局

    // 广告轮播
    private ViewPager mAdverViewpager;
    // 广告集合
    private List<ImageView> adver_images = new ArrayList<ImageView>();
    private AdvertisePagerViewAdapter mAdvertiseAdapter; // 广告适配器
    private LinearLayout mDotLayout;

    RecyclerView mNewsRecyclerView;
    // 新闻适配器
    private NewsAdapter mNewsAdapter;
    // 数据适配器包装类
    private WrapAdapter<NewsAdapter> mWrapAdapter;
    // 新闻数据
    private LinkedList<NewsBean.News> newsData = new LinkedList<NewsBean.News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHeaderView();// 初始化头部
        initData();//加载数据
        initializeViews();
        setLinstener();
    }


    /**
     * 初始化头布局
     */
    private void initHeaderView() {

        // 布局解析器，解析取得头部北荣
        headerLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.recyclerview_header, null);

        //头部控件初始化
        mAdverViewpager = (ViewPager) headerLayout.findViewById(R.id.ad_viewpager);//初始化广告轮播ViewPager
        mDotLayout = (LinearLayout) headerLayout.findViewById(R.id.dot_layout);//广告对应的dot
    }

    /**
     * 加载数据
     */
    private void initData() {
        // 模拟数据——轮播，先用imageview代替广告实体类
        ImageView imageView;
        int[] ad_ids = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item8};
        for (int i = 0; i < 5; i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(ad_ids[i]);
            adver_images.add(imageView);
        }

        // 多少个轮播广告就多少个点dot
        for (int i = 0; i < adver_images.size(); i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {//第一个点不需要左边距
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.advertise_dot_selector);
            mDotLayout.addView(view);
        }

        // 模拟今日要闻数据
        newsData.add(new NewsBean.News("习近平对高级干部说了哪些\"不\"", R.mipmap.item1, new Date().toLocaleString(), 16));
        newsData.add(new NewsBean.News("江苏湖北教育部门回应高考减招:规模不减", R.mipmap.item2, new Date().toLocaleString(), 166));
        newsData.add(new NewsBean.News("湖北一名退休官员在巡视组巡视期间自杀", R.mipmap.item3, new Date().toLocaleString(), 116));
        newsData.add(new NewsBean.News("广州毒保姆:杀领退休金老人是替国家省钱", R.mipmap.item4, new Date().toLocaleString(), 1720));
        newsData.add(new NewsBean.News("陕西太白山降温五月现飞雪 积雪厚达5厘米(图)", R.mipmap.item5, new Date().toLocaleString(), 0));
        newsData.add(new NewsBean.News("中国女留学生德国夜跑遇害 遗体袒露(图)", R.mipmap.item6, new Date().toLocaleString(), 15));
        newsData.add(new NewsBean.News("食药监总局:从未批准能\"改善男性性功能\"保健食品", R.mipmap.item7, new Date().toLocaleString(), 356));
        newsData.add(new NewsBean.News("小区内蛇窝现两米长大蛇 消防员惊出冷汗(图)", R.mipmap.item8, new Date().toLocaleString(), 169));

    }

    /**
     * 初始化控件
     */
    private void initializeViews() {


        /** 广告轮播 **/
        mAdvertiseAdapter = new AdvertisePagerViewAdapter(adver_images);
        mAdverViewpager.setAdapter(mAdvertiseAdapter);

        mNewsRecyclerView = (RecyclerView) findViewById(R.id.news_rv);

        /** 新闻 **/
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        // 设置添加删除item的时候的动画效果
        mNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 新闻适配器
        mNewsAdapter = new NewsAdapter(this, newsData);
        mWrapAdapter = new WrapAdapter<>(mNewsAdapter);
        // 设置头部占据一行
        mWrapAdapter.adjustSpanSize(mNewsRecyclerView);
        // 设置RecyclerView的数据适配器(适配器包装)
        mNewsRecyclerView.setAdapter(mWrapAdapter);
        // 添加头布局
        mWrapAdapter.addHeaderView(headerLayout);

        //默认在1亿多
        mAdverViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % adver_images.size()));
        //3秒定时
        mAdvertiseHandler.sendEmptyMessageDelayed(0, 2000);
        updateDot();

    }

    /**
     * 事件监听
     */
    private void setLinstener() {
        mAdverViewpager.setOnPageChangeListener(this);
    }

    /**
     * 更新dot
     */
    private void updateDot() {
        int currentPage = mAdverViewpager.getCurrentItem() % adver_images.size();
        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            //设置setEnabled为true的话 在选择器里面就会对应的使用白色颜色
            mDotLayout.getChildAt(i).setEnabled(i == currentPage);
        }
    }


    /**
     * handler处理定时任务，广告轮播的定时
     */
    private Handler mAdvertiseHandler = new Handler() {
        public void handleMessage(Message msg) {
            mAdverViewpager.setCurrentItem(mAdverViewpager.getCurrentItem() + 1);
            // 每隔三秒发送一次
            mAdvertiseHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        updateDot();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
