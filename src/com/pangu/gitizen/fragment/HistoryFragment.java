package com.pangu.gitizen.fragment;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.pangu.gitizen.R;
import com.pangu.gitizen.adapter.HangoutListAdapter;
import com.pangu.gitizen.adapter.HistoryListAdapter;
import com.pangu.gitizen.util.GetPostUtil;

public class HistoryFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    String[] name = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] time = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] num = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	ListView listView;
	HistoryListAdapter hlAdapter;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	EditText desc;
	EditText number_of_peo;
	EditText starttime;
	
	TextView v1;
	TextView v2;
	TextView v3;
    public HistoryFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_fragment, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.planets_array)[i];
        preferences = getActivity().getSharedPreferences("ListData", Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
        int Count = preferences.getInt("Count", 10);
        int c = 0;
        while (c < Count){
        	name[c] = preferences.getString("name" + c, null);
        	time[c] = preferences.getString("time" + c, null);
        	num[c] = preferences.getString("num" + c, null);
        	c++;
        }
        listView = (ListView)rootView.findViewById(R.id.list);
        hlAdapter = new HistoryListAdapter(this, name, time, num);
        listView.setAdapter(hlAdapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TableLayout eventForm = (TableLayout)HistoryFragment.this.getActivity().getLayoutInflater().inflate(R.layout.event_form2, null);
				desc = (EditText)eventForm.findViewById(R.id.desc);
				number_of_peo = (EditText)eventForm.findViewById(R.id.number_of_peo);
				starttime = (EditText)eventForm.findViewById(R.id.starttime);
				v1 = (TextView)view.findViewById(R.id.history_event_name);
				v2 = (TextView)view.findViewById(R.id.history_event_time);
				v3 = (TextView)view.findViewById(R.id.history_number_joined);
				desc.setText(v1.getText().toString());
				number_of_peo.setText(v3.getText().toString());
				starttime.setText(v2.getText().toString());
				new AlertDialog.Builder(HistoryFragment.this.getActivity())
					.setTitle("Event")
					.setView(eventForm)
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Toast.makeText(HistoryFragment.this.getActivity(), "Successful join this event!", 3000).show();
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
        getActivity().setTitle(planet);
        return rootView;
    }
}