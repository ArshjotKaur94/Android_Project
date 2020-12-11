package com.example.project_android;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyList extends Fragment {

    Context context;
    SearchView searchView;
    RealmResults<GroceryModel> grocItems;
    Realm realm;
    FavLstViewAdapter adapter;
    ListView lstview;

    public MyList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false);
    }

    public void setGrocItems(RealmResults<GroceryModel> grocItems) {
        this.grocItems = grocItems;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchView = (SearchView) getView().findViewById(R.id.fvsearchView);
        context = getContext();
        lstview = getView().findViewById(R.id.fvlist);
    }

    public void setupList() {
        realm = Realm.getDefaultInstance();
        grocItems = realm.where(GroceryModel.class).findAll();
        adapter = new FavLstViewAdapter(context, grocItems);
        lstview.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                grocItems = realm.where(GroceryModel.class).contains("name", newText, Case.INSENSITIVE).findAll();
                adapter = new FavLstViewAdapter(getContext(), grocItems);
                lstview.setAdapter(adapter);
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setupList();
    }
}