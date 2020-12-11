package com.example.project_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class LstViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<GroceryModel> grocList;
    ArrayList<GroceryModel> filterList;

    public LstViewAdapter(Context context, ArrayList<GroceryModel> grocItems) {
        this.context=context;
        this.grocList = grocItems;
        this.filterList = new ArrayList<>();
        this.filterList.addAll(grocList);
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
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(R.layout.list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.img);
            viewHolder.textView=(TextView)rowView.findViewById(R.id.txt);
            viewHolder.textview1= (TextView) rowView.findViewById(R.id.txt1);
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
        charText = charText.toLowerCase(Locale.getDefault());
        grocList.clear();
        if (charText.length() == 0) {
            grocList.addAll(filterList);
        } else {
            for (GroceryModel wp : filterList) {
                if (wp.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    grocList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
