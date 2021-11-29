package com.example.laba8;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Employees> adapter;
    private EditText nameText, workingpositionText, ageText;
    private List<Employees> employees;
    ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        workingpositionText = findViewById(R.id.workingpositionText);
        ageText = findViewById(R.id.ageText);
        listView = findViewById(R.id.list);
        employees = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        listView.setAdapter(adapter);
    }

    public void addWorkingposition(View view){
        String name = nameText.getText().toString();
        String workingposition = workingpositionText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        Employees employee = new Employees(name, workingposition, age);
        employees.add(employee);
        adapter.notifyDataSetChanged();
    }

    public void save(View view){

        boolean result = JSONHelper.exportToJSON(this, employees);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
    public void open(View view){
        employees = JSONHelper.importFromJSON(this);
        if(employees!=null){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }
}