package com.example.reihaneh_a5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.reihaneh_a5.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, List<LocationDetails>> homeMap;
    private ActivityMainBinding binding;
    private SharedPreferences restListpref;
    private SharedPreferences.Editor prefEditor;
    private RestaurantAdapter restaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.restListpref = getSharedPreferences(KEYS.RESTOURENT_LIST.name(), MODE_PRIVATE);
        this.prefEditor = restListpref.edit();

        this.restaurantAdapter = new RestaurantAdapter(this, prepareLocationDetails());
        this.binding.restaurantList.setAdapter(this.restaurantAdapter);
        this.binding.restaurantList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.restaurantList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.restaurant_list);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(this.restaurantAdapter);
        myRecycler.setClickable(true);
    }

    public List<LocationDetails> prepareLocationDetails() {
        ArrayList<LocationDetails> list = new ArrayList<LocationDetails>();

        LocationDetails locationDetails1 = new LocationDetails("Green Basil",43.67194509399123,-79.29481045076973);
        LocationDetails locationDetails2 = new LocationDetails("Garden State Restaurant ",43.67328970467347, -79.28743641138487 );
        LocationDetails locationDetails3 = new LocationDetails("ViVetha Bistro",43.67445500956285,-79.28192137352565);
        LocationDetails locationDetails4 = new LocationDetails("La Sala Restaurant",43.67055563130399,-79.30038745534648);
        LocationDetails locationDetails5 = new LocationDetails("Moti Mahal",43.672931144770516,-79.3225715402185);
        LocationDetails locationDetails6 = new LocationDetails("Uncle Betty's Diner",43.71525069234152,-79.40050585666548);

        list.add(locationDetails1);
        list.add(locationDetails2);
        list.add(locationDetails3);
        list.add(locationDetails4);
        list.add(locationDetails5);
        list.add(locationDetails6);

        return list;
    }

}