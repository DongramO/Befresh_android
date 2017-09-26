package com.befresh.befreshapp.Navigationmain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.Account.AccountFragment;
import com.befresh.befreshapp.Account.ChangepwFragment;
import com.befresh.befreshapp.Community.CommunityFragment;
import com.befresh.befreshapp.Community.MyRecipe.MyRecipeContentsFragment;
import com.befresh.befreshapp.Community.MyRecipe.MyRecipeFragment;
import com.befresh.befreshapp.Community.MyRecipe.MyrecipeLeftFragment;
import com.befresh.befreshapp.Community.MyRecipe.MyrecipeRightFragment;
import com.befresh.befreshapp.Community.MyRecipe.WriteMyRecipeFragment;
import com.befresh.befreshapp.Community.RecommendStore.RecommendContentsFragment;
import com.befresh.befreshapp.Community.RecommendStore.RecommendFragment;
import com.befresh.befreshapp.Community.SaveList.SaveListFragment;
import com.befresh.befreshapp.Magazine.MagazineContentsFragment;
import com.befresh.befreshapp.Magazine.MagazineFragment;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeopleFragment;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeopleLeaveFragment;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeopleSettingsFragment;
import com.befresh.befreshapp.Membership.MembershipFragment;
import com.befresh.befreshapp.Membership.MembershipInputFragment;
import com.befresh.befreshapp.Membership.MembershipWelcomeFragment;
import com.befresh.befreshapp.Mypage.MypageFragment;
import com.befresh.befreshapp.R;
import com.befresh.befreshapp.Recipe.MainFragment;
import com.befresh.befreshapp.Recipe.RecipeDetail2Fragment;
import com.befresh.befreshapp.Recipe.RecipeDetailFragment;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeMainWellbeing;
import com.befresh.befreshapp.Recipe.ReviewDetailFragment;
import com.befresh.befreshapp.Recipe.ReviewWriteFragment;
import com.befresh.befreshapp.Recipe.SearchDialog;
import com.befresh.befreshapp.Search.SearchFragment;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

import static android.view.View.GONE;


@SuppressLint({"NewApi", "RtlHardcoded"})
public class NavigationActivity extends MasterActivity implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnDismissListener
{

    public static int flag = 1;
    private String[] navMenuTitles;
    private DrawerListAdapter adapter;
    private ListView mDrawerList;
    private View mHeader;
    private TextView setname, setemail,toolbar_complete, toolbar_write,center_text,titletext;
    public ImageView title_image, setting;
    private ImageButton recipe_tool_heart,bookmark;
    public SearchDialog customDialog;
    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private ImageButton menu;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private ArrayList<Integer> navDrawerItemImages;
    private ImageButton search_icon, back;
    private CheckBox filter;
    public DrawerLayout mDrawerLayout;
    private Dialog filterDialog;
    private RelativeLayout titlebar_layout , titlebar_layout2;
    private LinearLayout count_ll;
    private SharedPreferences auto;
    private SharedPreferences.Editor autoLogin;
    private String name, email,pwd;
    private TextView fronttxt, backtxt;
    private WriteMyRecipeFragment writeMyRecipeFragment;
    public int currentpage;
    public static int setid;
    public static int kind1= 0, over=0, under=0,kind2=0;
    public static getRecipeMainWellbeing.ResultData bundle;
    public static int filterFlag =0;
    public static String currentdata;
    public int pageFlag;
    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (ImageButton)findViewById(R.id.menu);
        back = (ImageButton)findViewById(R.id.back);
        fronttxt = (TextView)findViewById(R.id.fronttxt);
        backtxt = (TextView)findViewById(R.id.backtxt);
        toolbar_complete = (TextView)findViewById(R.id.toolbar_complete);
        toolbar_write = (TextView)findViewById(R.id.toolbar_write);
        center_text = (TextView)findViewById(R.id.center_text);
        titletext = (TextView)findViewById(R.id.title_text);
        filter = (CheckBox)findViewById(R.id.filter);
        search_icon = (ImageButton)findViewById(R.id.search);
        setting = (ImageView)findViewById(R.id.setting);
        recipe_tool_heart = (ImageButton)findViewById(R.id.recipe_tool_heart);
        bookmark = (ImageButton)findViewById(R.id.bookmark);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        titlebar_layout = (RelativeLayout)findViewById(R.id.titlebar_layout);
        titlebar_layout2 = (RelativeLayout)findViewById(R.id.titlebar_layout2);
        count_ll = (LinearLayout)findViewById(R.id.count_ll);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        writeMyRecipeFragment = new WriteMyRecipeFragment();

        auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        name = auto.getString("name", null);
        email = auto.getString("email",null);
        Log.i("mytag",email);
        pwd = auto.getString("pwd",null);

        mDrawerList.setItemChecked(0, true);
        mHeader = getLayoutInflater().inflate(R.layout.navigation_list_header,
                mDrawerList, false);

        setname = (TextView)mHeader.findViewById(R.id.name);
        setname.setText(name);
        setemail = (TextView)mHeader.findViewById(R.id.email);
        setemail.setText(email);

        mDrawerList.addHeaderView(mHeader);
        // Custom Header End...


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                Log.i("event","yes");
                changeFragment(position);
                mDrawerLayout.closeDrawers();
            }
        });

        adapter = new DrawerListAdapter(NavigationActivity.this);
        mDrawerList.setAdapter(adapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toggleMenu(v);
                if (mDrawerLayout.isDrawerVisible(Gravity.LEFT))
                {
                    title_image.setVisibility(View.VISIBLE);
                    return;
                } else
                {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new SearchDialog(NavigationActivity.this);
                customDialog.setOnDismissListener(NavigationActivity.this);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.show();

            }
        });

        toolbar_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeMyRecipeFragment.WriteRegist();
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(5);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(90);
            }
        });

        if (savedInstanceState == null)
        {
            MainFragment landingFragment = new MainFragment();
            ReplaceFragement(landingFragment);
        }
    }
    @Override
    public void onDismiss(DialogInterface $dialog)
    {
        // TODO Auto-generated method stub
        Log.i("디스미스 실행","디스미스실행");
        filter.setChecked(false);
        $dialog.dismiss();
        this.changeFragment(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void changeFragment(int index) {
        switch (index)
        {
            // MAIN ...
            case 1:
                MainFragment mainFragment = new MainFragment();
                this.ReplaceFragement(mainFragment);
                titlebar_layout2.setVisibility(GONE);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout.setVisibility(View.VISIBLE);
                search_icon.setVisibility(View.VISIBLE);
                filter.setVisibility(View.VISIBLE);
                setting.setVisibility(GONE);
                setting.setVisibility(View.GONE);
                titletext.setText("레시피");
                flag =1;
                break;
            case 2:
                MembershipFragment membershipFragment = new MembershipFragment();
                this.ReplaceFragement(membershipFragment);
                titlebar_layout.setVisibility(View.VISIBLE);
                titlebar_layout2.setVisibility(GONE);
                menu.setVisibility(View.VISIBLE);
                search_icon.setVisibility(GONE);
                filter.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                setting.setVisibility(View.GONE);
                titletext.setText("프레시피플");
                flag =2;
                break;
            case 3:
                CommunityFragment communityFragment = new CommunityFragment();
                this.ReplaceFragement(communityFragment);
                titlebar_layout.setVisibility(View.VISIBLE);
                titlebar_layout2.setVisibility(View.GONE);
                toolbar_complete.setVisibility(GONE);
                filter.setVisibility(GONE);
                search_icon.setVisibility(GONE);
                setting.setVisibility(View.GONE);
                titletext.setText("커뮤니티");
                flag =2;
                break;
            case 4:
                MypageFragment mypageFragment = new MypageFragment();
                this.ReplaceFragement(mypageFragment);
                titlebar_layout2.setVisibility(GONE);
                titlebar_layout.setVisibility(View.VISIBLE);
                titletext.setText("마이페이지");
                search_icon.setVisibility(GONE);
                filter.setVisibility(GONE);
                setting.setVisibility(View.VISIBLE);
                flag =2;
                break;
            case 5:
                SearchFragment searchfrag = new SearchFragment();
                this.ReplaceFragement(searchfrag);
                titlebar_layout.setVisibility(GONE);
                flag =2;
                break;
            case 6:
                RecipeDetailFragment recipeDetail = new RecipeDetailFragment();
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(GONE);
                bookmark.setVisibility(View.VISIBLE);
                recipe_tool_heart.setVisibility(View.VISIBLE);
                this.ReplaceFragement(recipeDetail);
                flag =2;
                break;
            case 7:
                RecipeDetail2Fragment recipedetail2 = new RecipeDetail2Fragment();
                titlebar_layout2.setVisibility(View.GONE);
                titlebar_layout.setVisibility(View.GONE);
                count_ll.setVisibility(View.VISIBLE);
                recipe_tool_heart.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                center_text.setVisibility(GONE);
                this.ReplaceFragement(recipedetail2);
                flag =50;
                break;
            case 8: //후기작성
                ReviewWriteFragment reviewWriteFragment = new ReviewWriteFragment();
                this.ReplaceFragement(reviewWriteFragment);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(GONE);
                count_ll.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                toolbar_write.setVisibility(View.VISIBLE);
                center_text.setVisibility(View.VISIBLE);
                break;
            case 9:
                ReviewDetailFragment reviewDetailFragment = new ReviewDetailFragment();
                this.ReplaceFragement(reviewDetailFragment);
                titlebar_layout2.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                center_text.setText("후기");
                center_text.setVisibility(View.VISIBLE);
                flag =50;
                break;
            case 20: //커뮤니티 전체보기 시작 1번
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                back.setVisibility(View.VISIBLE);
                center_text.setVisibility(View.VISIBLE);
                backtxt.setVisibility(View.GONE);
                fronttxt.setVisibility(GONE);
                center_text.setText("나의 레시피");
                toolbar_complete.setVisibility(GONE);
                toolbar_write.setVisibility(GONE);
                MyRecipeFragment myRecipeFragment = new MyRecipeFragment();
                this.ReplaceFragement(myRecipeFragment);
                flag =4;
                break;
            case 21://커뮤니티 전체보기 시작 2번
                RecommendFragment recommendFragment = new RecommendFragment();
                this.ReplaceFragement(recommendFragment);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                backtxt.setVisibility(View.GONE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                fronttxt.setVisibility(GONE);
                center_text.setVisibility(View.VISIBLE);
                center_text.setText("식당 추천");
                flag =4;
                break;
            case 22://커뮤니티 전체보기 시작 3번
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                fronttxt.setVisibility(GONE);
                center_text.setVisibility(View.VISIBLE);
                backtxt.setVisibility(View.GONE);
                center_text.setText("비프레시 메거진");
                MagazineFragment magazineFragment = new MagazineFragment();
                this.ReplaceFragement(magazineFragment);
                flag =4;
                break;
            case 23://커뮤니티 전체보기 시작 3번
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                backtxt.setVisibility(View.GONE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                fronttxt.setVisibility(GONE);
                center_text.setVisibility(View.VISIBLE);
                center_text.setText("나의 저장목록");
                SaveListFragment saveListFragment = new SaveListFragment();
                this.ReplaceFragement(saveListFragment);
                flag =4;
                break;
            case 24:////////////////////
                WriteMyRecipeFragment writeMyRecipeFragment1 = new WriteMyRecipeFragment();
                this.ReplaceFragement(writeMyRecipeFragment1);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                center_text.setText("나의 레시피 작성");
                toolbar_complete.setVisibility(View.VISIBLE);
                flag = 40;
                break;
            case 25:
                MagazineContentsFragment magazineContentsFragment = new MagazineContentsFragment();
                this.ReplaceFragement(magazineContentsFragment);
                flag = 98;
                break;
            case 26:
                RecommendContentsFragment recommendContentsFragment = new RecommendContentsFragment();
                this.ReplaceFragement(recommendContentsFragment);
                flag = 99;
                break;
            case 27:///////////////
                center_text.setText("나의 레시피");
                toolbar_complete.setVisibility(GONE);
                MyRecipeContentsFragment myRecipeContentsFragment = new MyRecipeContentsFragment();
                this.ReplaceFragement(myRecipeContentsFragment);
                flag = 40;
                break;
            case 28: //결제창
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                recipe_tool_heart.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                center_text.setVisibility(View.VISIBLE);
                center_text.setText("필수 정보 입력");
                MembershipInputFragment membershipInputFragment = new MembershipInputFragment();
                this.ReplaceFragement(membershipInputFragment);
                flag = 17;
                break;
            case 29:////////////가입되면 나오는거
                titlebar_layout.setVisibility(View.VISIBLE);
                filter.setVisibility(GONE);
                search_icon.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.GONE);
                recipe_tool_heart.setVisibility(GONE);
                bookmark.setVisibility(GONE);
                titletext.setText("필수 정보 입력");
                MembershipWelcomeFragment membershipWelcomeFragment = new MembershipWelcomeFragment();
                this.ReplaceFragement(membershipWelcomeFragment);
                flag = 7;
                break;
            case 30:
                titletext.setText("프레시피플");
                FreshPeopleFragment freshPeopleFragment = new FreshPeopleFragment();
                this.ReplaceFragement(freshPeopleFragment);
                flag = 2;
                break;
            case 31:
                FreshPeopleSettingsFragment freshPeopleSettingsFragment = new FreshPeopleSettingsFragment();
                this.ReplaceFragement(freshPeopleSettingsFragment);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                center_text.setText("계정 설정");
                center_text.setVisibility(View.VISIBLE);
                flag = 6;
                break;
            case 32:
                FreshPeopleLeaveFragment freshPeopleLeaveFragment = new FreshPeopleLeaveFragment();
                this.ReplaceFragement(freshPeopleLeaveFragment);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                center_text.setText("멤버십 탈퇴");
                center_text.setVisibility(View.VISIBLE);
                flag = 5;
                break;
            case 70: // 커뮤니티 마이레시피 탭 프래그먼트
                MyrecipeLeftFragment rank1 = new MyrecipeLeftFragment();
                this.ReplaceFragement(rank1);
                flag = 4;
                break;
            case 71: // 커뮤니티 마이레이시 탭 프래그먼트
                MyrecipeRightFragment rank2 = new MyrecipeRightFragment();
                this.ReplaceFragement(rank2);
                flag = 4;
                break;
            case 90:
                AccountFragment accountFragment = new AccountFragment();
                this.ReplaceFragement(accountFragment);
                titlebar_layout.setVisibility(GONE);
                titlebar_layout2.setVisibility(View.VISIBLE);
                bookmark.setVisibility(GONE);
                recipe_tool_heart.setVisibility(GONE);
                center_text.setText("나의 멤버십 정보");
                center_text.setVisibility(View.VISIBLE);
                flag = 80;
                break;
            case 91:
                ChangepwFragment changepwFragment = new ChangepwFragment();
                this.ReplaceFragement(changepwFragment);
                flag = 81;
                break;
        }
    }
    @Override
    public void onBackPressed()
    {
        long tempTime        = System.currentTimeMillis();
        long intervalTime    = tempTime - backPressedTime;
        Log.i("mytag","indicator flag"+flag);
        if (mDrawerLayout.isDrawerVisible(Gravity.RIGHT))
        {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        }
        else
        {
            if (flag == 1) {

                if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                else
                {
                    backPressedTime = tempTime;
                    Toast.makeText(getApplicationContext(), "뒤로 가기 키을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
                }
            }
            else if(flag ==2) {
                this.changeFragment(1); //main
            }
            else if(flag ==4) {
                this.changeFragment(3); //community
            }
            else if(flag ==50) {
                this.changeFragment(6); //recipe detail
            }
            else if(flag == 81) {
                this.changeFragment(90);
            }
            else if(flag == 80) {
                this.changeFragment(4);
            }
            else if(flag ==5) {
                this.changeFragment(31);
            }
            else if(flag ==6) {
                this.changeFragment(30);
            }
            else if(flag ==40) {
                this.changeFragment(20);
            }
            else if(flag ==99) {
                this.changeFragment(21);
            }
            else if(flag ==98) {
                this.changeFragment(22);
            }
            else if(flag ==7) {
                this.changeFragment(28);
            }
            else if(flag ==17) {
                this.changeFragment(2);
            }

        }
    }

    public void ReplaceFragement(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mDrawerLayout.isDrawerVisible(Gravity.RIGHT))
        {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}