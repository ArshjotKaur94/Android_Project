package com.example.project_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;

public class TabActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private TabLayout tabs;
    String userName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("username")){
            this.userName = extras.getString("username");
        }
        tabLayout=(TabLayout)findViewById(R.id.tabLayoutID);
        viewPager=(ViewPager)findViewById(R.id.viewPagerID);
        TabLayout.Tab home = tabLayout.newTab();
        home.setIcon(R.drawable.home);
        home.setText("Home");
        tabLayout.addTab(home);

        TabLayout.Tab list = tabLayout.newTab();
        list.setIcon(R.drawable.list);
        list.setText("Grocery");
        tabLayout.addTab(list);

        TabLayout.Tab cart = tabLayout.newTab();
        cart.setIcon(R.drawable.cart);
        cart.setText("Cart");
        tabLayout.addTab(cart);

        TabLayout.Tab con = tabLayout.newTab();
        con.setIcon(R.drawable.phone);
        con.setText("Contact");
        tabLayout.addTab(con);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyTabAdapter adapter = new MyTabAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        adapter.setUserName(this.userName);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 2) {
                    MyList Tab3 = (MyList) adapter.getItem(tab.getPosition());
                    Tab3.setupList();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearDB();
    }

    public void clearDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                realm1.delete(GroceryModel.class);
            }
        });
    }
}