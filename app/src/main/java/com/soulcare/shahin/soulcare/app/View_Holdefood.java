package com.soulcare.shahin.soulcare.app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shahin on 14-03-17.
 */

public class View_Holdefood extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    View_Holdefood(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }
}