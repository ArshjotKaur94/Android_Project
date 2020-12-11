package com.example.project_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavLstViewAdapter extends BaseAdapter {

    Context context;
    RealmResults<GroceryModel> grocList;

    public FavLstViewAdapter(Context context, RealmResults<GroceryModel> grocItems) {
        this.context = context;
        this.grocList = grocItems;
    }

    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView textview1;
        public Button button;


    }

    @Override
    public int getCount() {
        return grocList.size();
    }

    @Override
    public Object getItem(int position) {
        return grocList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the list_item.xml file if convertView is null
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.img);
            viewHolder.textView = (TextView) rowView.findViewById(R.id.txt);
            viewHolder.textview1 = (TextView) rowView.findViewById(R.id.txt1);
            // viewHolder.button= (Button) rowView.findViewById(R.id.bt);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.imageView.setImageResource(grocList.get(position).image);
        holder.textView.setText(grocList.get(position).name);
        holder.textview1.setText(grocList.get(position).price);
        return rowView;
    }

    public void filter(String charText) {
        grocList = Realm.getDefaultInstance().where(GroceryModel.class).beginsWith("name",charText).findAll();
        notifyDataSetChanged();
    }

}
