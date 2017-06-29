package com.pingan.u17.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;
import com.pingan.u17.ui.fragment.BookRackFragment;
import com.pingan.u17.ui.fragment.HomeFragment;
import com.pingan.u17.ui.fragment.MineFragment;
import com.pingan.u17.ui.fragment.SortFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.main_framelayout)
    FrameLayout  mainFramelayout;
    @BindView(R.id.tab_home)
    RadioButton  tabHome;
    @BindView(R.id.tab_search)
    RadioButton  tabSearch;
    @BindView(R.id.tab_bookrack)
    RadioButton  tabBookrack;
    @BindView(R.id.tab_mine)
    RadioButton  tabMine;
    @BindView(R.id.main_radiogroup)
    RadioGroup   mainRadiogroup;
    private Unbinder         mUnbinder;
    private HomeFragment     mHomeFragment;
    private FragmentManager  mFragmentManager;
    private SortFragment     mSearchFragment;
    private BookRackFragment mBookRackFragment;
    private MineFragment     mMineFragment;
    private int mCurrentPage = 0;
    private double exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取管理器和事务
        mFragmentManager = getSupportFragmentManager();

        setSelectFragment(mCurrentPage);
        mainRadiogroup.check(R.id.tab_home);
        //setSelectTab(mCurrentPage);
        //设置对应fragment
        setRadioGroupLisenter();
    }

    /**
     * 更换fragment
     */
    private void setRadioGroupLisenter() {
        mainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_home:
                        mCurrentPage = 0;
                        break;
                    case R.id.tab_search:
                        mCurrentPage = 1;
                        break;
                    case R.id.tab_bookrack:
                        mCurrentPage = 2;
                        break;
                    case R.id.tab_mine:
                        mCurrentPage = 3;
                        break;
                    default:
                }
                setSelectFragment(mCurrentPage);
                //setSelectTab(mCurrentPage);
            }
        });
    }

    /**
     * @param currentPage  设置对应的fragment
     */
    private void setSelectFragment(int currentPage) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //现隐藏所有的
        hideAllFragments(transaction);
        switch (currentPage) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.main_framelayout, mHomeFragment, "HomeFragment");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (mSearchFragment == null) {
                    mSearchFragment = new SortFragment();
                    transaction.add(R.id.main_framelayout, mSearchFragment, "SearchFragment");
                } else {
                    transaction.show(mSearchFragment);
                }
                break;
            case 2:
                if (mBookRackFragment == null) {
                    mBookRackFragment = new BookRackFragment();
                    transaction.add(R.id.main_framelayout, mBookRackFragment, "BookRackFragment");
                } else {
                    transaction.show(mBookRackFragment);
                }
                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.main_framelayout, mMineFragment, "MineFragment");
                } else {
                    transaction.show(mMineFragment);
                }
                break;
            default:
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mSearchFragment != null) {
            transaction.hide(mSearchFragment);
        }
        if (mBookRackFragment != null) {
            transaction.hide(mBookRackFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    private void setSelectTab(int currentPage){
        switch(currentPage){
//            case 0:
//
//                tabHome.setButtonDrawable(R.drawable.main_radiobutton_bookshelf_light);
//                tabHome.setTextColor(getResources().getColor(R.color.tab_text_selected));
//                break;
//            case 1:
//                tabSearch.setButtonDrawable(R.drawable.main_radiobutton_search_light);
//                tabSearch.setTextColor(getResources().getColor(R.color.tab_text_selected));
//
//                break;
//            case 2:
//                tabSearch.setButtonDrawable(R.drawable.main_radiobutton_bookshelf_light);
//                break;
//            case 3:
//                tabSearch.setButtonDrawable(R.drawable.main_radiobutton_mine_light);
                //break;
            default:
        }
    }

    //按两次推出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

    }
}
