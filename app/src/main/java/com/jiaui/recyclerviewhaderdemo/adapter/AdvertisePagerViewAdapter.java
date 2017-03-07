package com.jiaui.recyclerviewhaderdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Coffee on 2016/5/28 0028.
 */
public class AdvertisePagerViewAdapter extends PagerAdapter {

    private List<ImageView> adver_images;

    public AdvertisePagerViewAdapter(List<ImageView> adver_images) {
        this.adver_images = adver_images;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 类似于BaseAdapger的getView方法 用了将数据设置给view 由于它最多就3个界面，不需要viewHolder
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = adver_images.get(position % adver_images.size());
        container.addView(view);// 一定不能少，将view加入到viewPager中
        return view;
    }

    /**
     * 销毁page position： 当前需要消耗第几个page object:当前需要消耗的page
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
