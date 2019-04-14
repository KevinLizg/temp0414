package com.example.focusassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<record> {
    private int resourceId;
    public RecordAdapter(Context context, int resource, List<record> objects) {
        super(context, resource,objects);
        this.resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        record tempRecord=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView type=view.findViewById(R.id.type);
        TextView time=view.findViewById(R.id.time);
        TextView finished=view.findViewById(R.id.finished);

        type.setText(tempRecord.getType());
        time.setText(tempRecord.getTime());
        finished.setText(tempRecord.getFinished());

        return view;
    }
}
