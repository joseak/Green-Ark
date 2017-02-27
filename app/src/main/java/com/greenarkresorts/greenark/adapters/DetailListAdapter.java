package com.greenarkresorts.greenark.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greenarkresorts.greenark.models.DetailModel;
import com.greenarkresorts.greenark.R;

import java.util.ArrayList;

public class DetailListAdapter extends BaseAdapter {
    ArrayList<DetailModel> detailModels;
    private LayoutInflater inflater;

    public DetailListAdapter(Context context, ArrayList<DetailModel> detailModels) {
        this.detailModels = detailModels;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return detailModels.size();
    }

    @Override
    public Object getItem(int position) {
        return detailModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.text_view_list, parent, false);
        }
        DetailModel detailModel = detailModels.get(position);
        TextView keyTextView = (TextView) convertView.findViewById(R.id.constantTextView);
        TextView valueTextView = (TextView) convertView.findViewById(R.id.variableTextView);
        keyTextView.setText(detailModel.getKey());
        keyTextView.setTextColor(Color.BLACK);
        valueTextView.setTextColor(Color.BLACK);
        valueTextView.setText(detailModel.getValue());
        return convertView;
    }
}
