package com.befresh.befreshapp.Navigationmain;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

public class MasterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setTitle(String title) {
//        TextView titleTextView = (TextView) findViewById(R.id.title_text);
//        titleTextView.setText(title);

    }

    public void setTextSize(int title_text_size) {
//        TextView titleTextView = (TextView) findViewById(R.id.title_text);
//        titleTextView.setTextSize(title_text_size);
    }

    public void hideTitleBar() {
//        RelativeLayout titleLayout = (RelativeLayout) findViewById(R.id.titlebar_layout);
//        titleLayout.setVisibility(View.GONE);
    }

    public void showTitleBar() {
//        RelativeLayout titleLayout = (RelativeLayout) findViewById(R.id.titlebar_layout);
//        titleLayout.setVisibility(View.VISIBLE);
    }

    public void hideBackButton() {
//        ImageButton backbutton = (ImageButton) findViewById(R.id.back_btn);
//        backbutton.setVisibility(View.GONE);
    }

    public void showBackButton() {
//        ImageButton backbutton = (ImageButton) findViewById(R.id.navigation_btn);
//        backbutton.setVisibility(View.VISIBLE);
    }
}
