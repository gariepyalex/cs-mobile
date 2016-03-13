package com.mirego.rebelchat.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mirego.rebelchat.R;

import java.util.ArrayList;

public class MailboxAdapter extends BaseAdapter {

    Context context;
    ArrayList<Message> data;
    private static LayoutInflater inflater = null;

    public MailboxAdapter(Context context, ArrayList<Message> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.message_list_item, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(data.get(position).text);

        TextView from = (TextView) vi.findViewById(R.id.from);
        from.setText(data.get(position).from);
        return vi;
    }
}