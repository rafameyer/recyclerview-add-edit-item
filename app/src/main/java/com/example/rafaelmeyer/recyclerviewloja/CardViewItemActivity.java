package com.example.rafaelmeyer.recyclerviewloja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import model.Item;

/**
 * Created by rafael.meyer on 8/24/16.
 */
public class CardViewItemActivity extends Activity{

    private TextView nomeItem;
    private View viewColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardviewitem);

        nomeItem = (TextView)findViewById(R.id.nomeItem);
        viewColor = (View)findViewById(R.id.viewColor);

    }
}
