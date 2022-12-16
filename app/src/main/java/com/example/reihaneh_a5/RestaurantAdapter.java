package com.example.reihaneh_a5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reihaneh_a5.databinding.ItemRestaurantBinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.HomeViewHolder> {
    private Context context;
    private final String TAG = this.getClass().getCanonicalName();
    private List<LocationDetails> restaurantList;

    static SharedPreferences myPref;
    static SharedPreferences.Editor prefEditor;
    private static Set<String> favhomeNames = new HashSet<>();

    public RestaurantAdapter(Context context, List<LocationDetails> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;

        this.myPref = context.getSharedPreferences(KEYS.RESTOURENT_LIST.name(), Context.MODE_PRIVATE);
        this.prefEditor = this.myPref.edit();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(ItemRestaurantBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(context, this.restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.restaurantList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        ItemRestaurantBinding binding;

        public HomeViewHolder(ItemRestaurantBinding r){
            super(r.getRoot());
            this.binding = r;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(Context context, LocationDetails locationDetails){

            binding.restaurantTitle.setText(locationDetails.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    this.openMap(locationDetails);
                    Toast.makeText(context, locationDetails.getName() + " Location", Toast.LENGTH_LONG).show();

                }

                private void openMap(LocationDetails locationDetails) {
                    Intent mapIntent = new Intent(context, MapsActivity.class);
                    mapIntent.putExtra("EXTRA_LAT", locationDetails.getLatitude());
                    mapIntent.putExtra("EXTRA_LNG", locationDetails.getLongitude());
                    mapIntent.putExtra("EXTRA_LABLE",locationDetails.getName());
                    context.startActivity(mapIntent);
                }
            });
        }
    }//homeViewHolder


}//homeAdapter