package com.jiaui.recyclerviewhaderdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiaui.recyclerviewhaderdemo.R;
import com.jiaui.recyclerviewhaderdemo.bean.NewsBean;
import com.jiaui.recyclerviewhaderdemo.widget.BaseRecyclerAdapter;

import java.util.LinkedList;

public class NewsAdapter extends BaseRecyclerAdapter<NewsBean.News, NewsAdapter.MyViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    public NewsAdapter(Context context, LinkedList mItemList) {
        super(context, mItemList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        NewsBean.News News = mItemLists.get(position);
        // 新闻图片
        Glide.with(mContext).load(News.getResId()).into(holder.image);
        holder.title.setText(News.getTitle());
        holder.count.setText(News.getCount() + "");
        holder.time.setText(News.getTime());
        holder.position = position;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView time;
        TextView count;
        LinearLayout adContent;
        int position;

        public MyViewHolder(View NewsView) {
            super(NewsView);
            title = (TextView) NewsView.findViewById(R.id.news_title);
            image = (ImageView) NewsView.findViewById(R.id.news_image);
            time = (TextView) NewsView.findViewById(R.id.news_time);
            count = (TextView) NewsView.findViewById(R.id.news_count);
            adContent = (LinearLayout) NewsView.findViewById(R.id.news_content);
        }
    }
}