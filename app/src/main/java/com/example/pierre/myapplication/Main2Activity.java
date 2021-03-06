package com.example.pierre.myapplication;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.example.pierre.myapplication.R.layout.*;

public class Main2Activity extends AppCompatActivity {
    public static Client yazid= new Client(33.472831, -112.066411);

    private Grid earth = new Grid();

    private int d = 0;

    private Context context;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Bundle b = getIntent().getExtras();
        String message = b.getString("distance");
        String mess=b.getString("category");


        d = Integer.parseInt(message);


        lecture(mess);
        ArrayList<Restaurant> res = earth.listof(yazid, d);
        if (mess!=null) {

        }

        MyAdapter myAdapter = new MyAdapter(d);

        myAdapter.setRestos(res);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myAdapter);


    }



    public void lecture(String keyword) {
        Database data = new Database();
        ArrayList<Restaurant> services = null;
        try {
            Resources r = getResources();
            int id = r.getIdentifier("test", "raw", getPackageName());
            services = data.createDB(getResources().openRawResource(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        earth.makeBoxes();

        Log.i("Nombre de service: ", String.valueOf(+services.size()));
        int i = 0;
        while (i < services.size()) {
            Restaurant r = services.get(i);
            if (keyword != null) {
                Log.i("macategorie: ", String.valueOf(r.category(r.categories)));
                if (r.category(r.categories).contains(keyword)){
                    earth.addRestaurant(r);}}
            else {
                earth.addRestaurant(r);}
            i++;
        }
    }
}