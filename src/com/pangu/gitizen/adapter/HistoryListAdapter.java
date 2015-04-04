package com.pangu.gitizen.adapter;

import com.pangu.gitizen.R;
import com.pangu.gitizen.fragment.EventcenterFragment;
import com.pangu.gitizen.fragment.HistoryFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryListAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater = null;
	private String[] name;
	private String[] time;
	private String[] join_num;
	
	public HistoryListAdapter(Context context, String[] name, String[] time, String[] join_num) {
		this.layoutInflater = LayoutInflater.from(context);
		this.name = name;
		this.time = time;
		this.join_num = join_num;
	}
	
	public HistoryListAdapter(HistoryFragment eventcenterFragment, String[] name2, String[] time2, String[] num) {
		this.layoutInflater = LayoutInflater.from(eventcenterFragment.getActivity());
		this.name = name2;
		this.time = time2;
		this.join_num = num;
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
        	view = layoutInflater.inflate(R.layout.history_fragment_item, null);
        	holder = new ViewHolder();
        	holder.item_name = (TextView)view.findViewById(R.id.history_event_name);
        	holder.item_time = (TextView)view.findViewById(R.id.history_event_time);
        	holder.item_num = (TextView)view.findViewById(R.id.history_number_joined);
        	view.setTag(holder);
        }
        else {
        	holder = (ViewHolder)view.getTag();
        }
        holder.item_name.setText(name[position]);
        holder.item_time.setText(time[position]);
        holder.item_num.setText(join_num[position]);
        return view;
	}
	
    static final class ViewHolder{
		TextView item_name;
		TextView item_time;
		TextView item_num;
	}
	
}
