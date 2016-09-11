package com.techkid.tqdu.tripadvisor.searchresult;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.techkid.tqdu.tripadvisor.R;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONGeometryModel;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONGeometryModelLocation;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONPhotosModel;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONResultsModel;
import com.techkid.tqdu.tripadvisor.utility.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tqdu on 8/28/2016.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<ItemHolder> {

    private String image_url;
    private String image_link;

    private List<JSONResultsModel> placeList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public SearchResultAdapter(List<JSONResultsModel> placeList, Context context) {
        //this.placeList = placeList;
        List<JSONPhotosModel> list = new ArrayList<>();
        JSONGeometryModelLocation jsonGeometryModelLocation = new JSONGeometryModelLocation(21.0439295,105.8341636);
        list.add(new JSONPhotosModel(543,960,"CoQBcwAAAFxxZEsecm5moAR3Yarh9qZWIqsrqOsPE7cPU_VE7i725cUiE8gHY9woYztASTRyQ4cvNathDJ6f58C_ByPvBi1vj3xgSXAsVU6XviRiZW"));
        JSONGeometryModel jsonGeometryModel = new JSONGeometryModel(jsonGeometryModelLocation);
        JSONResultsModel jsonResultsModel = new JSONResultsModel(jsonGeometryModel,"","bfe6fa39735b242b8f03461bb8cf7a9725d7149f",
                "Nhà hàng Du thuyền Hồ tây Potomac",
                list,"ChIJY_ydqqirNTER_-JPGPWWupk",
                "CoQBfAAAADMr5PHgPtj0xL1TPlXcuT58i5qlJhM_sQKNOL9wtTAKUn_E4Z3sBM6YidMAkb220c7n27HtFzOc6FN6NP6z9EMTQBU_yPcjNgOjtJETcqlvyNtOMKfB3AcE0EQ4m2CxUrGMsUdTfTHkR6Vh",
                3.8,"4 Thụy Khuê");
        placeList.add(jsonResultsModel);
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
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
            //Chèn ảnh và text từ place vào imageView
            image_url = place.getJsonPhotosModelList().get(0).getPhoto_reference();
            image_link = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+image_url+"&key="+ Utility.GOOGLE_API_KEY;
            Picasso.with(context).load(image_link)
                    .placeholder(R.drawable.icon_places_hotel_88)
                    .error(R.drawable.icon_places_hotel_88)
                    .into(holder.imageView);
            holder.placeName.setText(place.getName());
            holder.distance.setText("1.5 km from here");
            holder.rateNumber.setText(place.getRating().toString());
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
