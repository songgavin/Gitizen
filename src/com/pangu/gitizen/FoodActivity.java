package com.pangu.gitizen;

import com.pangu.gitizen.adapter.FoodListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FoodActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.food_event);
		
		final String[] name = {"Amelie's French Bakery", "Cypress Street Pint & Plate", "Vinny's New York Pizza"};
		final String[] price = {"$123", "$234", "$345"};
		final String[] score = {"Score:4", "Score:3", "Score:5"};
		final String[] location = {"Atlanta", "Macon", "Augusta"};
        ListView listView = (ListView) findViewById(R.id.food_list);
        listView.setAdapter(new FoodListAdapter(this, name, price, score, location));
        listView.setOnItemClickListener(new OnItemClickListener(){
  			@Override
  			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  				new AlertDialog.Builder(FoodActivity.this)
  					.setTitle("Join this event?")
  					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
  						@Override
  						public void onClick(DialogInterface dialog, int which) {
  							Toast.makeText(FoodActivity.this, "Successful join this event!", 3000).show();
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
	}
}
