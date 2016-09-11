package com.techkid.tqdu.tripadvisor.searchresult;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.techkid.tqdu.tripadvisor.R;

import org.w3c.dom.Text;

/**
 * Created by tqdu on 8/28/2016.
 */
public class ItemHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
     TextView placeName;
     TextView rateNumber;
     TextView distance;

    public ItemHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.result_image);
        placeName = (TextView) itemView.findViewById(R.id.place_name);
        rateNumber = (TextView) itemView.findViewById(R.id.rate_number);
        distance = (TextView) itemView.findViewById(R.id.distance);
    }
}
