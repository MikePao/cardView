package com.example.i329392.myapplication;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CardData>>{
    private RecyclerView mRecyclerView;
//    private MyAdapter mAdapter;
    private HeterogenousRecyclerAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int LOADER_TAG = 1;
    private static final String IMAGE_URL = "https://api.unsplash.com/photos/?client_id=6276e756b3176273ffb9b5ebaa3c26be6aee7f5188849d1212bab55e15e39f9d";
    private static final String NASA_URL = "https://api.nasa.gov/planetary/apod?start_date=2018-01-01&end_date=2018-01-26&api_key=OI4otegmiwDBf2aNYftRcqxVh17Ac5Osy6oyLxRg";

    List<CardData> displayList = new ArrayList<CardData>();
    List<TextData> textList = new ArrayList<TextData>();
    List<Object> objectList = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        List<CardData> myDataset = new ArrayList<CardData>();
//        mAdapter = new MyAdapter(myDataset, MainActivity.this);
        adapter = new HeterogenousRecyclerAdapter(new ArrayList<Object>(),new ArrayList<CardData>(), new ArrayList<TextData>(), MainActivity.this);
        mRecyclerView.setAdapter(adapter);

        getLoaderManager().initLoader(LOADER_TAG,null, this);
    }

    @Override
    public Loader<List<CardData>> onCreateLoader(int id, Bundle args) {
        return new NasaLoader(this,IMAGE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<CardData>> loader, List<CardData> data) {
        adapter.clear();
//        adapter.addData(new TextData("Omelette Recipe", null));
   //     adapter.addAll(data);

        displayList.clear();
        displayList.addAll(data);
        Singleton.photoList = data;
//        adapter.setDisplayList(displayList);


        List<TextData> title = new ArrayList<TextData>() {{
            add(new TextData("Omelette Recipe", null));
        }};
        Singleton.title = title;


        textList.clear();
        textList.addAll(new ArrayList<TextData>() {{
            add(new TextData("Ingredients", "Eggs, Salt, Edible oil, Onions."));
            add(new TextData("Steps", "1. Heat the frying pan for 1 minute."));
            add(new TextData(null, "2. Put 1 tea spoon of oil on the pan and heat the oil."));
            add(new TextData(null, "3. Put chopped onions in the pan and saute it for 30 seconds."));
            add(new TextData(null, "4. Put well stirred eggs in the pan."));
            add(new TextData(null, "5. Flip the omelette after 1 minute."));
            add(new TextData(null, "6. Wait for 1 minute and the omelette will be ready to eat."));
        }});
        Singleton.textList = textList;
//        adapter.setTextList(textList);

        objectList.clear();
//        objectList.addAll(displayList);
//        objectList.addAll(textList);
        objectList.add(title.get(0));
        objectList.add(displayList.get(0));
        objectList.add(textList.get(1));
        adapter.setTotalData(objectList);

//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<CardData>> loader) {
        adapter.clear();
    }
}
