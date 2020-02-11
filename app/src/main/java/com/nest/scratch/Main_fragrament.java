package com.nest.scratch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Coded with love.
 * Created by black on 1/21/17.
 */

public class Main_fragrament extends Fragment {

    private DBLocal dbHandler;
    private ArrayList<Item> items;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container,false);
        dbHandler = new DBLocal(this.getContext());
        bundle = savedInstanceState;
        loadItemsToScreen(root);

        return root;

    }

    public void loadItemsToScreen(View v){
        items=dbHandler.getAllItems();
        GridView gv=(GridView) v.findViewById(R.id.main_grid);
        gv.setAdapter(new GridAdapter());
    }

    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Item getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =getLayoutInflater(bundle).inflate(R.layout.item,parent,false);
            TextView price =(TextView) convertView.findViewById(R.id.item_price);
            TextView title =(TextView) convertView.findViewById(R.id.item_title);
            TextView description =(TextView) convertView.findViewById(R.id.item_description);
            title.setText(getItem(position).getItem_title());
            description.setText(getItem(position).getItem_description());
            price.setText(getItem(position).getItem_price());
            ImageView imageView =(ImageView) convertView.findViewById(R.id.displayed_image);
            imageView.setImageBitmap(getItem(position).getImage(0));
            return convertView;

        }
    }


}
