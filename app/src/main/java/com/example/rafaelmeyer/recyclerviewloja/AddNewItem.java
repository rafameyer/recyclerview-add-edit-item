package com.example.rafaelmeyer.recyclerviewloja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import model.Item;

public class AddNewItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Item itemModel;
    private EditText itemName;
    private String itemColorSelected;
    private Spinner mySpinner;
    private Button button;
    private Boolean isAdding = true;
    private int location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        itemName = (EditText) findViewById(R.id.editname);
        button = (Button) findViewById(R.id.button_add);

        mySpinner = (Spinner) findViewById(R.id.colors_spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.colorsspinners,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);

        isAdding = getIntent().getExtras().getBoolean("isEditing");

        if (!isAdding) {
            itemModel = getIntent().getParcelableExtra("message");
            String itemNameEdit = itemModel.getName();
            itemColorSelected = itemModel.getColor();
            itemName.setText(itemNameEdit);

            location = adapter.getPosition(itemColorSelected);
            mySpinner.setSelection(location);
        }

        int selectionPosition = adapter.getPosition(itemColorSelected);
        mySpinner.setSelection(selectionPosition);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onActionSaveButton();
                    }
                }
        );

    }

    private void onActionSaveButton() {
        if (!itemColorSelected.equals("Seleciona a Cor") && !itemName.getText().toString().trim().equals("")) {
            itemModel = new Item(itemName.getText().toString(), itemColorSelected);
            Intent intentMessage = new Intent();
            intentMessage.putExtra("message", itemModel);
            if (isAdding) {
                setResult(RESULT_FIRST_USER, intentMessage);
            } else {
                setResult(RESULT_OK, intentMessage);
            }
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemColorSelected = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
