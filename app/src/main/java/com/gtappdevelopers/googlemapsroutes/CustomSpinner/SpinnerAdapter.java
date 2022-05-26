package com.gtappdevelopers.googlemapsroutes.CustomSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gtappdevelopers.googlemapsroutes.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<SpinnerModal> {

    public SpinnerAdapter(@NonNull Context context, ArrayList<SpinnerModal> modalArrayList) {
        super(context, 0, modalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.idIVLogo);
        TextView txt = convertView.findViewById(R.id.idTVText);

        SpinnerModal modal = getItem(position);

        if (convertView != null) {
            txt.setText(modal.getName());
            img.setImageResource(modal.getImgID());
        }
        return convertView;
    }
}
