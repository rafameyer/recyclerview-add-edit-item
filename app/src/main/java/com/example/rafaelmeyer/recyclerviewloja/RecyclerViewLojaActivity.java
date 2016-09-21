package com.example.rafaelmeyer.recyclerviewloja;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import model.Item;

public class RecyclerViewLojaActivity extends AppCompatActivity implements RVAdapter.RecyclerViewOnClickListener {

    private static final int REQUEST = 1001;
    private List<Item> items = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int location;
    private Item model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recylcerviewloja_activity);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.myFloatActionButton);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        floatingActionButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StartActivityForResult
                        Intent intent = new Intent(RecyclerViewLojaActivity.this, AddNewItem.class);
                        intent.putExtra("isEditing", true);
                        startActivityForResult(intent, REQUEST);

                    }
                }
        );

        mAdapter = new RVAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setMyRecyclerViewOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode) {
            if (data == null){
                return;
            }
            model = data.getParcelableExtra("message");
            if (resultCode == RESULT_FIRST_USER){
                items.add(model);
            }else if (resultCode == RESULT_OK){
                items.set(location, model);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        location = position;
        Item itemModel = new Item(items.get(position).getName(), items.get(position).getColor());
        Intent intent = new Intent(RecyclerViewLojaActivity.this, AddNewItem.class);
        intent.putExtra("isEditing", false);
        intent.putExtra("message", itemModel);
        startActivityForResult(intent, REQUEST);
    }
}
