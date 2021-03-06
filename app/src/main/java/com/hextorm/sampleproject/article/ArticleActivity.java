package com.hextorm.sampleproject.article;

import android.app.ProgressDialog;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;

import android.graphics.PorterDuff;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.hextorm.sampleproject.Constants;
import com.hextorm.sampleproject.utils.NetworkStateReceiver;
import com.hextorm.sampleproject.ViewPagerAdapter;
import com.hextorm.sampleproject.R;

import com.hextorm.sampleproject.articlearchive.ArchiveFragment;
import com.hextorm.sampleproject.articlebase.BaseFragment;
import com.hextorm.sampleproject.articleprofile.ProfileFragment;
import com.hextorm.sampleproject.articlesearch.SearchFragment;
import com.hextorm.sampleproject.databinding.ActivityMainBinding;
import com.hextorm.sampleproject.utils.PopMessages;

import java.util.ArrayList;
import java.util.List;


public class ArticleActivity extends AppCompatActivity implements ArticleNavigator, BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int HOME_FRAGMENT = 0;
    public static final int SEARCH_FRAGMENT = 1;
    public static final int BASE_FRAGMENT = 2;
    public static final int ARCHIVE_FRAGMENT = 3;
    public static final int PROFILE_FRAGMENT = 4;

    // for IntentFilter of Broadcast Receiver
    public static final String RECEIVER_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static final int MAIN_ACTIVITY_FRAGMENT_COUNT = 5;

    public static MutableLiveData<Boolean> isConnectionAvailable = new MutableLiveData<>();

    //View binding
    ActivityMainBinding binding;

    ViewPagerAdapter viewPagerAdapter;

    NetworkStateReceiver broadcastReceiver;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isConnectionAvailable.setValue(true);

        Constants.getUrl();

        //View binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Observer<Boolean> snackBarObserver = (Boolean visibility) -> {
            PopMessages.makeConnectivityCheckSnack(binding.bottomNavigationView, this, visibility);
        };

        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        isConnectionAvailable.observe(this, snackBarObserver);

        setUpToolBar();
        setUpBottomNavigationBar();
        setUpViewPager();

        binding.collapsingToolBar.setTitleEnabled(false);
        binding.toolbar.setTitle("");

        // Broadcast Receiver -> it notifies us about condition of internet connectivity
        broadcastReceiver = new NetworkStateReceiver(binding.relativeLayout);
        registerReceiver(broadcastReceiver, new IntentFilter(RECEIVER_ACTION));

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            binding.toolbar.getMenu().findItem(R.id.toolbar_search_item).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    binding.bottomNavigationView.setClickable(false);
                    return true;
                }
            });
        } catch (NullPointerException e) {
            Log.e("Toolbar: NullPointerException", e.getLocalizedMessage());
        }
        //PopMessages.makeConnectivityCheckSnack(binding.relativeLayout,this,NetworkState.haveNetworkConnection(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_list, menu);
        return true;
    }

    /**
     * Bottom Navigation View
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        binding.toolbar.setVisibility(View.VISIBLE);

        switch (menuItem.getItemId()) {
            case R.id.bnm_home:
                binding.viewPager.setCurrentItem(HOME_FRAGMENT);
                Log.d("onNavigationItemSelected: ", " HOME_FRAGMENT");

                break;
            case R.id.bnm_search:
                binding.viewPager.setCurrentItem(SEARCH_FRAGMENT);
                Log.d("onNavigationItemSelected: ", " SEARCH_FRAGMENT");
                //   binding.toolbar.getMenu().clear();
                //   binding.toolbar.inflateMenu(R.menu.toolbar_search);

                break;
            case R.id.bnm_base:
                binding.viewPager.setCurrentItem(BASE_FRAGMENT);
                Log.d("onNavigationItemSelected: ", " BASE_FRAGMENT");

                break;
            case R.id.bnm_archive:
                binding.viewPager.setCurrentItem(ARCHIVE_FRAGMENT);
                Log.d("onNavigationItemSelected: ", " ARCHIVE_FRAGMENT");

                break;
            case R.id.bnm_profile:
                binding.viewPager.setCurrentItem(PROFILE_FRAGMENT);
                Log.d("onNavigationItemSelected: ", " PROFILE_FRAGMENT");
                break;
        }
        return true;
    }

    void onLoading() {
        ProgressDialog dialog = ProgressDialog.show(this, "", "", true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("home", "home");
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
                break;

            case R.id.menu_1:
                Log.d("menu1", "clicked");
                break;
            case R.id.menu_2:
                //feedback
                break;
            case R.id.menu_3:
                //onLoading();
                int pos = binding.viewPager.getCurrentItem();
                Fragment activeFragment = viewPagerAdapter.getItem(pos);
                if (pos == 0) {
                    ((ArticleFragment) activeFragment).changeListType();
                }
                break;
        }
        return true;

        //that was default return; -> //super.onOptionsItemSelected(item);
    }


    void setUpBottomNavigationBar() {
        binding.bottomNavigationView.setSelectedItemId(R.id.bnm_home);
    }

    void setUpToolBar() {
        binding.toolbar.getOverflowIcon().setColorFilter(getResources().getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_grey_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ;
    }

    List<Fragment> createFragmentList() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ArticleFragment());
        fragments.add(new SearchFragment());
        fragments.add(new BaseFragment());
        fragments.add(new ArchiveFragment());
        fragments.add(new ProfileFragment());

        return fragments;
    }

    void setUpViewPager() {
        setViewPagerAdapter();
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (v == 0) {
                    if (i == 0) {
                        binding.toolbar.getMenu().clear();
                        binding.toolbar.inflateMenu(R.menu.toolbar_list);
                    } else if (i == 1) {
                        binding.toolbar.getMenu().clear();
                        binding.toolbar.inflateMenu(R.menu.toolbar_search);
                        binding.toolbar.getOverflowIcon().setColorFilter(getResources().getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
                        binding.toolbar.getMenu().findItem(R.id.toolbar_search_item).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                binding.bottomNavigationView.setVisibility(View.GONE);
                                binding.bottomNavigationView.setClickable(false);
                                return true;
                            }
                        });
                        MenuItem searchItem = binding.toolbar.getMenu().findItem(R.id.toolbar_search_item);

                        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

                        

                        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String s) {
                                try {
                                    Fragment activeFragment = viewPagerAdapter.getItem(SEARCH_FRAGMENT);
                                    Log.i("MainActivity: ActiveSearchFragment", activeFragment.toString());
                                    ((SearchFragment) activeFragment).loadArticleList(s);
                                } catch (NullPointerException e) {
                                    Log.e("MainActivity: Search_view:onQueryTextSubmit -> ", e.getMessage());
                                    return false;
                                }
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String s) {
                                viewPagerAdapter.getItem(SEARCH_FRAGMENT);
                                return false;
                            }
                        });

                    } else {
                        binding.toolbar.getMenu().clear();
                        binding.toolbar.inflateMenu(R.menu.toolbar_default);
                    }
                } else {
                    binding.toolbar.getMenu().clear();
                }

                binding.bottomNavigationView.setVisibility(View.VISIBLE);

                Log.d("ViewPager:onPageScrolled  ", "i = " + i + " , v = " + v + " , il = " + i1);
            }

            @Override
            public void onPageSelected(int position) {
                //binding.bottomNavigationView.getMenu().getItem(0).setChecked(false);

                Log.d("ViewPager:onPageSelected ", " position= " + position);
                binding.bottomNavigationView.getMenu().getItem(position).setChecked(true);


                switch (position) {
                    case HOME_FRAGMENT:
                        binding.toolbar.getMenu().clear();
                        binding.bottomNavigationView.setSelectedItemId(R.id.bnm_home);
                        binding.toolbar.inflateMenu(R.menu.toolbar_list);

                        break;
                    case SEARCH_FRAGMENT:
                        binding.bottomNavigationView.setSelectedItemId(R.id.bnm_search);
                        binding.toolbar.inflateMenu(R.menu.toolbar_search);

                        binding.toolbar.getMenu().clear();
                        binding.toolbar.inflateMenu(R.menu.toolbar_search);
                        break;

                    default:
                        binding.toolbar.getMenu().clear();
                        binding.toolbar.inflateMenu(R.menu.toolbar_default);
                        break;
                }
                binding.toolbar.getMenu().clear();
                binding.toolbar.inflateMenu(R.menu.toolbar_list);

                //   prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d("ViewPager:onPageScrollStateChanged ", " i= " + i);
            }
        });


        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        binding.viewPager.setOffscreenPageLimit(MAIN_ACTIVITY_FRAGMENT_COUNT);
        binding.viewPager.setAdapter(viewPagerAdapter);

    }

    void setViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), createFragmentList());
    }

    @Override
    public void onArticleClicked() {
        // unused function
    }

}
