package com.gtappdevelopers.googlemapsroutes.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtappdevelopers.googlemapsroutes.R;

import java.util.ArrayList;

public class LanguageRVAdapter extends RecyclerView.Adapter<LanguageRVAdapter.ViewHolder> {
    private ArrayList<LanguageRVModal> languageRVModalArrayList;
    private Context context;

    public LanguageRVAdapter(ArrayList<LanguageRVModal> languageRVModalArrayList, Context context) {
        this.languageRVModalArrayList = languageRVModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LanguageRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.language_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageRVAdapter.ViewHolder holder, int position) {
        holder.lngNameTV.setText(languageRVModalArrayList.get(position).getLngName());
        holder.lngPlatformTV.setText("Platform : " + languageRVModalArrayList.get(position).getLngPlatForm());
        holder.lngLevelTV.setText("Level : " + languageRVModalArrayList.get(position).getLngLevel());
        holder.lngIV.setImageResource(languageRVModalArrayList.get(position).getLngImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(languageRVModalArrayList.get(position).getLngUrl()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languageRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView lngIV;
        private TextView lngNameTV, lngLevelTV, lngPlatformTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lngIV = itemView.findViewById(R.id.idIVLanguage);
            lngNameTV = itemView.findViewById(R.id.idTVLanguage);
            lngLevelTV = itemView.findViewById(R.id.idTVLanguageLevel);
            lngPlatformTV = itemView.findViewById(R.id.idTVLanguagePlatform);
        }
    }
}
