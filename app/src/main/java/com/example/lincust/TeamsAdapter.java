package com.example.lincust;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kstanoev on 1/14/2015.
 */
public class TeamsAdapter extends ArrayAdapter<Team> {

    Context context;
    private ArrayList<Team> teams;

    public TeamsAdapter(Context context, int textViewResourceId, ArrayList<Team> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.teams = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team, null);
        }
        Team o = teams.get(position);
        if (o != null) {
            TextView pos = (TextView) v.findViewById(R.id.date);
            TextView name = (TextView) v.findViewById(R.id.item);
            TextView wins = (TextView) v.findViewById(R.id.money);

            TextView draws = (TextView) v.findViewById(R.id.size);
            TextView losses = (TextView) v.findViewById(R.id.save);
         /*   TextView points = (TextView) v.findViewById(R.id.points);
*/
            pos.setText(String.valueOf(o.getDate()));
            name.setText(String.valueOf(o.getItem()));
            wins.setText(String.valueOf(o.getMoney()));

            draws.setText(String.valueOf(o.getSize()));
            losses.setText(String.valueOf(o.getLosses()));
        /*    points.setText(String.valueOf(o.getPoints()));
            */
        }
        return v;
    }
}
