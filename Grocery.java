package com.example.project_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Grocery#} factory method to
 * create an instance of this fragment.
 */
public class Grocery extends Fragment {
    Context context;
    SearchView searchView;
    ArrayList<GroceryModel> grocItems;
    Realm realm;
    String[] items = {"Apple", "Banana ", "Brinjal  ", "India Gate Basmati Rice ", "Kidney Beans", "Mango ", "Orange "};
    String[] items1 = {"$ 2.99 (1 lbs)", "$ 0.99 (1 lbs)", "$ 1.99 (1 lbs) ", "$ 16.99(10 lbs)", "$ 8.99 (4 lbs)", " $ 2 (1 pc)", "$ 5 (1 lbs) "};
    Integer[] imageid = {
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.brinjal,
            R.drawable.rice,
            R.drawable.kidney,
            R.drawable.mango,
            R.drawable.orange

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grocery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = Realm.getDefaultInstance();
        searchView = (SearchView) getView().findViewById(R.id.searchView);

        context = getContext();
        ListView lstview = getView().findViewById(R.id.list);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View view, final int i, long id) {
                AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(getContext());
                dlgbuilder.setMessage("Add this item to cart?");
                dlgbuilder.setCancelable(true);

                dlgbuilder.setPositiveButton(
                        "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final GroceryModel item = (GroceryModel) parent.getItemAtPosition(i);
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm1) {
                                        realm1.insertOrUpdate(item);
                                    }
                                });

                                dialog.dismiss();
                                dialog.cancel();
                            }
                        });
                dlgbuilder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = dlgbuilder.create();
                alert11.show();
            }
        });



        /*public void onItemClick(AdapterView < ? > adapterView, View view, int i, long l)
   {
    Toast.makeText(getBaseConext(), "You selected : " + countries[i], Toast.LENGTH_SHORT).show();
   }
  });

  Button button = findViewById(R.id.button);
      button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String value = editText.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("value",value);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
         }
      });


      protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_second);
      Bundle bundle = getIntent().getExtras();
      if (bundle ! = null) {
         String value = bundle.getString("value");
         TextView textView = findViewById(R.id.text_view);
         textView.setText(value);
      }
  */
        grocItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            GroceryModel it = new GroceryModel(items[i], items1[i], imageid[i]);
            grocItems.add(it);
        }

        final LstViewAdapter adapter = new LstViewAdapter(context, grocItems);
        // Bind data to the ListView
        lstview.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                adapter.filter(text);
                return false;
            }
        });


    }

    /*public void clickMe(View view){
        Button bt=(Button)view;
        Toast.makeText(this, "Button "+bt.getText().toString(),Toast.LENGTH_LONG).show();
    }*/

}
