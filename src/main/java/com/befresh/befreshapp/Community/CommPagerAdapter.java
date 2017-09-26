package com.befresh.befreshapp.Community;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.befresh.befreshapp.R;

import java.util.ArrayList;


/**
 * Created by JongBong on 2017-06-25.
 */

public class CommPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> items = new ArrayList<>();

    public CommPagerAdapter(Context context, ArrayList<Integer> items) {
        this.context = context;
        this.items.addAll(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_img_item_comm, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_item_rcy_comm);
        imageView.setImageResource(items.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
