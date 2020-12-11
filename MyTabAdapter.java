package com.example.project_android;

import android.content.Context;
import android.text.style.TabStopSpan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;

public class MyTabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    String userName = "";
    Home Tab1;
    Grocery Tab2;
    MyList Tab3;
    Contact Tab4;

    public MyTabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
        Tab1 = new Home();
        Tab1.setUserName(this.userName);
        Tab2 = new Grocery();
        Tab3 = new MyList();
        Tab4 = new Contact();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab1.setUserName(this.userName);
                return Tab1;
            case 1:
                return Tab2;
            case 2:
                return Tab3;
            case 3:
                return Tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
