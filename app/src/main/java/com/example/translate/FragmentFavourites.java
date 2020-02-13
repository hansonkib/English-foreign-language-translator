package com.example.translate;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentFavourites extends Fragment {
    SQLiteHelper db;
    ArrayList<String> sourceln;
    ArrayList<String>  targetln;
//   String [] sourcelang={"lala","soma"};
//   String [] targetlang={"sommeil","sommein"};
    ListView lst;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favourites,container,false);
lst=view.findViewById(R.id.listView);
db=new SQLiteHelper(getContext(),"LanguagesDB.sqlite",null,1);
sourceln=new ArrayList<>();
targetln=new ArrayList<>();
viewFavourites();
        return view;
    }

    private void viewFavourites() {
//        CustomListView customListView=new CustomListView(getContext(),sourcelang,targetlang);
//        lst.setAdapter(customListView);
        Cursor cursor=db.allFavourites();
        if(cursor.getCount()==0){
            Toast.makeText(getContext(),"no data to display",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                sourceln.add(cursor.getString(0)); //for the first column
                targetln.add(cursor.getString(1));
            }
            CustomListView customListview=new CustomListView(getContext(),sourceln,targetln);
            lst.setAdapter(customListview);
        }

    }
}
