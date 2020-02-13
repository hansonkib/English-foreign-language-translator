package com.example.translate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomListView extends ArrayAdapter<String> {
    private  String[] sourceLn;
    private String[] targetln;
    private Activity context;
    public CustomListView(Context context, ArrayList<String> sourceLn, ArrayList<String> targetln) {
        super(context, R.layout.listview_layout,sourceLn);
        this.context= (Activity) context;
        this.sourceLn=sourceLn.toArray(new String[0]);
        this.targetln= targetln.toArray(new String[0]);

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        View r=convertView;
        ViewHolder viewHolder=null;
        if (r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) r.getTag();
        }
        viewHolder.Sl.setText(sourceLn[position]);
        viewHolder.Tl.setText(targetln[position]);

        return r;
    }
    class ViewHolder{
        TextView Sl;
        TextView Tl;
        public ViewHolder(View v) {
            Sl=(TextView) v.findViewById(R.id.sourceLang);
            Tl=(TextView) v.findViewById(R.id.targetLang);

        }
    }
}
