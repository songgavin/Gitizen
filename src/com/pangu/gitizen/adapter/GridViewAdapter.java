package com.pangu.gitizen.adapter;


import com.pangu.gitizen.R;
import com.pangu.gitizen.fragment.CategoryFragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater = null;
	private Fragment mContext;
	private String[] activity;
	
    public GridViewAdapter(CategoryFragment planetFragment2, String[] activity) {
		this.layoutInflater = LayoutInflater.from(planetFragment2.getActivity());
		this.mContext = planetFragment2;
		this.activity = activity;
	}


	@Override
	public int getCount() {
		return picture_id.length;
	}

	@Override
	public Object getItem(int position) {
		return activity[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
        if (view == null) {
        	view = layoutInflater.inflate(R.layout.gitizen_grid_item, null);
        	holder = new ViewHolder();
        	holder.activity_picture = (ImageView)view.findViewById(R.id.activity_picture);
        	holder.activity_name = (TextView)view.findViewById(R.id.activity_name);
        	view.setTag(holder);
        }
        else {
        	holder = (ViewHolder)view.getTag();
        }
        holder.activity_name.setText(activity[position]);
        holder.activity_picture.setImageResource(picture_id[position]);
        return view;
	}
	
    // references to our images
    private Integer[] picture_id = {R.drawable.sport, R.drawable.food,R.drawable.concert,
    		R.drawable.hangout,R.drawable.drama,R.drawable.bar,R.drawable.hiking,
    		R.drawable.read};
    //private String[] activity = {"SPORT", "FOOD", "CONCERT", "HANGOUT", "DRAMA", "BAR", "HIKING", "READ"};
	
    static final class ViewHolder{
		ImageView activity_picture;
		TextView activity_name;
	}
	
}

