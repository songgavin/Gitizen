package com.pangu.gitizen.adapter;

import com.pangu.gitizen.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodListAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater = null;
	private String[] name;
	private String[] price;
	private String[] score;
	private String[] location;
	
	public FoodListAdapter(Context context, String[] name, String[] price, String[] score, String[] location) {
		this.layoutInflater = LayoutInflater.from(context);
		this.name = name;
		this.price = price;
		this.score = score;
		this.location = location;
	}
	
	@Override
	public int getCount() {
		return name.length;
	}

	@Override
	public Object getItem(int position) {
		return name[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
        if (view == null) {
        	view = layoutInflater.inflate(R.layout.food_list_item, null);
        	holder = new ViewHolder();
        	holder.item_picture = (ImageView)view.findViewById(R.id.item_picture);
        	holder.item_name = (TextView)view.findViewById(R.id.item_name);
        	holder.item_price = (TextView)view.findViewById(R.id.item_price);
        	holder.item_comment = (TextView)view.findViewById(R.id.item_comment);
        	holder.item_location = (TextView)view.findViewById(R.id.item_location);
        	view.setTag(holder);
        }
        else {
        	holder = (ViewHolder)view.getTag();
        }
        holder.item_name.setText(name[position]);
        holder.item_price.setText(price[position]);
        holder.item_comment.setText(score[position]);
        holder.item_location.setText(location[position]);
        holder.item_picture.setImageResource(picture_id[position]);
        return view;
	}
	
    // references to our images
    private Integer[] picture_id = {R.drawable.test1, R.drawable.test2,R.drawable.test3};

    static final class ViewHolder{
		ImageView item_picture;
		TextView item_name;
		TextView item_price;
		TextView item_comment;
		TextView item_location;
	}
}
