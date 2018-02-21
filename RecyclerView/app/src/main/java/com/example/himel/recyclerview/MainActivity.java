package com.example.himel.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.himel.recyclerview.CustomAdapterClass.ItemAdapter;
import com.example.himel.recyclerview.ModelClass.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvitem;
    //CardView cvItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);

        rvitem = findViewById(R.id.recyclerView);

        rvitem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvitem.setLayoutManager(manager);

        ArrayList<Item>itemList = genaratedDummy();

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), itemList);

        rvitem.setAdapter(adapter);
    }

   private ArrayList<Item>genaratedDummy(){
        ArrayList<Item>list = new ArrayList<>();
        for (int i =0;i<20;i++){

            if (i%3 ==0){
                Item item = new Item();
                item.id = i;
                item.nameImage = "Sujana Zafar "+i;
                item.image = "https://i2.wp.com/dhakaonair.com/wp-content/uploads/2015/04/Suzana-Zafar-9.jpg";
                list.add(item);

            }
            else if(i%7==0){
                Item item = new Item();
                item.id = i;
                item.nameImage = "Sujana Zafar"+i;
                item.image = "https://pbs.twimg.com/profile_images/581404910836580353/dPqiYz1O.jpg";
                list.add(item);
            }

            else {
                Item item = new Item();
                item.id = i;
                item.nameImage = "Sujana Zafar"+i;
                item.image = "http://1.bp.blogspot.com/-plFn9KdajvE/U-JlnI3HjOI/AAAAAAAABfM/ZwosNCat25A/s1600/hridoy4.jpg";
                list.add(item);
            }

        }

        return list;
    }
}
