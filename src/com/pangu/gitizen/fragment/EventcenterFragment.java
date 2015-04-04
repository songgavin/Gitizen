package com.pangu.gitizen.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.pangu.gitizen.R;
import com.pangu.gitizen.adapter.HangoutListAdapter;
import com.pangu.gitizen.util.GetPostUtil;

public class EventcenterFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    String url = "http://106.185.44.27:8080/api/events";
    String response;
    ListView listView;
    String[] name = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] time = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] num = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] _id =  {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	HangoutListAdapter hlAdapter;
	//private SwipeRefreshLayout swipeLayout;
	TextView tmp_id;
	JSONObject tmp_ClientKey;
	String json2;
	String tmp_url;
	int tmp_number_of_people;
	int tmp_position;
	String tmp_name;
	String tmp_time;
	String tmp_num;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123)
			{
				JSONArray jsonArray;
				try 
				{
					jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++)
					{
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						name[i] = jsonObject.getString("g_loc_name");
						time[i] = jsonObject.getString("starttime");
						num[i] = "People: " + jsonObject.getString("number_joined");
						_id[i] = jsonObject.getString("_id");
					}
					hlAdapter.notifyDataSetChanged();
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
			}
			if(msg.what == 0x456)
			{
				JSONArray jsonArray;
				try 
				{
					jsonArray = new JSONArray(response);
					tmp_ClientKey = jsonArray.getJSONObject(tmp_position);
					tmp_number_of_people = Integer.valueOf(tmp_ClientKey.getString("number_joined")).intValue();
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
			}
		}
	};
	
    public EventcenterFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_fragment, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.planets_array)[i];
        preferences = getActivity().getSharedPreferences("ListData", Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
        editor.putInt("Count", 0);
        listView = (ListView)rootView.findViewById(R.id.list);
        getActivity().setTitle(planet);
        hlAdapter = new HangoutListAdapter(this, name, time, num, _id);
        listView.setAdapter(hlAdapter);
        //swipeLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        //swipeLayout.setOnRefreshListener(this);
        //swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		new Thread()
		{
			public void run()
			{
				response = GetPostUtil.sendGet(url);
				handler.sendEmptyMessage(0x123);
			}
		}.start();
   
        listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*
				tmp_desc = (TextView)view.findViewById(R.id.hangout_event_name);
				tmp_number_of_peo = (TextView)view.findViewById(R.id.hangout_number_joined);
				tmp_starttime = (TextView)view.findViewById(R.id.hangout_event_time);
				*/
				new Thread()
				{
					public void run()
					{
						response = GetPostUtil.sendGet(url);
						handler.sendEmptyMessage(0x456);
					}
				}.start();
				tmp_id = (TextView)view.findViewById(R.id._id);
				tmp_url = url + "/" + tmp_id.getText().toString();
				//tmp_number_of_peo.setText("People: " + tmp_number_of_people);
				TextView tmp_name_TextView = (TextView)view.findViewById(R.id.hangout_event_name);
				tmp_name = tmp_name_TextView.getText().toString();
				TextView tmp_time_TextView = (TextView)view.findViewById(R.id.hangout_event_time);
				tmp_time = tmp_time_TextView.getText().toString();
				TextView tmp_num_TextView = (TextView)view.findViewById(R.id.hangout_number_joined);
				tmp_num = tmp_num_TextView.getText().toString();
				new AlertDialog.Builder(EventcenterFragment.this.getActivity())
					.setTitle("Join this event?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							try {
								tmp_number_of_people++;
								tmp_ClientKey.put("number_joined", ""+tmp_number_of_people);
								json2 = String.valueOf(tmp_ClientKey);
							} catch (JSONException e) {
								e.printStackTrace();
							}
							new Thread()
							{
								public void run()
								{
									response = GetPostUtil.sendPut(tmp_url, json2);
									handler.sendEmptyMessage(0x123);
								}
							}.start();
							//hlAdapter.notifyDataSetChanged();
							int Count = preferences.getInt("Count", 0);
							
							editor.putString("name" + Count, tmp_name);
							editor.putString("time" + Count, tmp_time);
							editor.putString("num" + Count, tmp_num);
							Count++;
							editor.putInt("Count", Count);
							editor.commit();
							Toast.makeText(EventcenterFragment.this.getActivity(), "Successful join this event!", 3000).show();
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					})
					.create()
					.show();
			}
        });
        return rootView;
    }
}