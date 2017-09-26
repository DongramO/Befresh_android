package com.befresh.befreshapp.Navigationmain;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.befresh.befreshapp.R;

import java.util.ArrayList;


@SuppressLint({ "ViewHolder", "InflateParams" })
public class DrawerListAdapter extends BaseAdapter
{
    private String strColor = "#cbcbcb";
    private String strColor2 = "#166B36";
    private NavigationActivity context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private ArrayList<Integer> navDrawerItemImages;
    private CheckBox imageView;
    private int position2;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    public DrawerListAdapter(NavigationActivity context)
    {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return checkBoxes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.drawer_list_item, null, true);
        imageView = (CheckBox)convertView.findViewById(R.id.nav_image);

        if(position == 0)
        {
           imageView.setButtonDrawable(R.drawable.hamburger_recipe);imageView.setChecked(true);
        }
        else if(position == 1)
        {
            imageView.setButtonDrawable(R.drawable.hamburger_freshpeople);
        }
        else if(position == 2)
        {
            imageView.setButtonDrawable(R.drawable.hamburger_community);
        }
        else if(position == 3)
        {
            imageView.setButtonDrawable(R.drawable.hamburger_mypage);
        }

        checkBoxes.add(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            for(int i=0; i<checkBoxes.size(); i++)
            {
                checkBoxes.get(i).setChecked(false);
            }
            checkBoxes.get(position).setChecked(true);
            context.changeFragment(position+1);
            context.mDrawerLayout.closeDrawers();
        }
         });

        return convertView;
    }

}