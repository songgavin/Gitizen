package com.pangu.gitizen.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.pangu.gitizen.FoodActivity;
import com.pangu.gitizen.HangoutActivity;
import com.pangu.gitizen.R;
import com.pangu.gitizen.adapter.GridViewAdapter;


public class CategoryFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    FragmentManager fm = null;
    public CategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    fm = getFragmentManager();

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_fragment, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.planets_array)[i];

        final String[] activity = {"SPORT", "FOOD", "CONCERT", "HANGOUT", "DRAMA", "BAR", "HIKING", "READ"};

        ((GridView) rootView.findViewById(R.id.gridview)).setAdapter(new GridViewAdapter(this, activity));
        ((GridView) rootView.findViewById(R.id.gridview)).setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(CategoryFragment.this.getActivity(), activity[position], 3000).show();
				if (position == 1) {
					Intent intent = new Intent(getActivity(), FoodActivity.class);
					startActivity(intent);
				}
				else {
					Intent intent = new Intent(getActivity(), HangoutActivity.class);
					startActivity(intent);
				}
			}
        });

        getActivity().setTitle(planet);
        return rootView;
    }
}