package com.techkid.tqdu.tripadvisor.searchresult;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techkid.tqdu.tripadvisor.PlaceDetailActivity;
import com.techkid.tqdu.tripadvisor.R;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONGeometryModel;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONGeometryModelLocation;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONPhotosModel;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONResultsModel;
import com.techkid.tqdu.tripadvisor.utility.Utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tqdu on 8/28/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ItemHolder> {

    private String image_url;
    private String image_link;

    private List<JSONResultsModel> placeList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private Location currentLocation;

    public static final String PLACE_NAME = "place_name";
    public static final String PLACE_RATE = "place_rate";
    public static final String PLACE_ID = "place_id";
    public static final String PLACE_DIS = "place_dis";
    public static final String PLACE_LAT = "place_lat";
    public static final String PLACE_LNG = "place_lng";


    public SearchResultAdapter(List<JSONResultsModel> placeList, Context context, Location currentLocation) {
        this.placeList = placeList;
        this.context = context;
        this.currentLocation = currentLocation;
        layoutInflater = LayoutInflater.from(this.context);
        Log.d("Adapter.PlaceListSize:",placeList.size()+"");
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_result,parent,false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        JSONResultsModel place = placeList.get(position);
        if (place != null) {
            if (place.getJsonPhotosModelList() != null && place.getName() != null && place.getRating() != null
                    && place.getJsonGeometryModel() != null
                    && place.getJsonGeometryModel().getJsonGeometryModelLocation() != null) {
                image_url = place.getJsonPhotosModelList().get(0).getPhoto_reference();
                image_link = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + image_url + "&key=" + Utility.GOOGLE_API_KEY;
                Picasso.with(context).load(image_link)
                        .placeholder(R.drawable.icon_places_hotel_88)
                        .error(R.drawable.icon_places_hotel_88)
                        .into(holder.imageView);
                holder.placeName.setText(place.getName());
                //Distance calculation
                Location placeLoc = new Location("placeLoc");
                placeLoc.setLatitude(place.getJsonGeometryModel().getJsonGeometryModelLocation().getLat());
                placeLoc.setLongitude(place.getJsonGeometryModel().getJsonGeometryModelLocation().getLng());
                double distance = placeLoc.distanceTo(currentLocation)/1000;
                Double truncatedDouble = new BigDecimal(new Double(distance))
                        .setScale(1, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                holder.distance.setText(truncatedDouble + " km from here");
                place.setDistance(truncatedDouble);
                double rate = place.getRating();
                holder.rateNumber.setText("Rating: " +rate);
                if (rate< 1.5) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_1_small);
                } else if (rate > 1.5 && rate < 2) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_1h_small);
                } else if (rate >= 2 && rate < 2.5) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_2_small);
                } else if (rate >= 2.5 && rate < 3) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_2h_small);
                } else if (rate >= 3 && rate < 3.5) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_3_small);
                } else if (rate >= 3.5 && rate < 4) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_3h_small);
                } else if (rate >=4 && rate < 4.5) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_4_small);
                } else if (rate >= 4.5 && rate < 5) {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_4h_small);
                } else {
                    holder.placeRate.setImageResource(R.drawable.ta_rating_5_small);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView placeName;
        TextView rateNumber;
        TextView distance;
        ImageView placeRate;
        LinearLayout linearLayout;

        public ItemHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.result_image);
            placeName = (TextView) itemView.findViewById(R.id.place_name);
            rateNumber = (TextView) itemView.findViewById(R.id.rate_number);
            distance = (TextView) itemView.findViewById(R.id.distance);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.place_item);
            placeRate = (ImageView) itemView.findViewById(R.id.rating_icon);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaceDetailActivity.class);
                    intent.putExtra(PLACE_NAME,placeList.get(getAdapterPosition()).getName());
                    intent.putExtra(PLACE_DIS,placeList.get(getAdapterPosition()).getDistance());
                    intent.putExtra(PLACE_ID,placeList.get(getAdapterPosition()).getPlace_id());
                    intent.putExtra(PLACE_RATE,placeList.get(getAdapterPosition()).getRating());
                    intent.putExtra(PLACE_LAT,placeList.get(getAdapterPosition()).getJsonGeometryModel().getJsonGeometryModelLocation().getLat());
                    intent.putExtra(PLACE_LNG,placeList.get(getAdapterPosition()).getJsonGeometryModel().getJsonGeometryModelLocation().getLng());
                    context.startActivity(intent);
                    //Toast.makeText(itemView.getContext(),"vi tri"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
