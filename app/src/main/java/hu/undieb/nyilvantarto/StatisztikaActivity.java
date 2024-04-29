package hu.undieb.nyilvantarto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisztikaActivity extends AppCompatActivity {
    RecyclerView statisztikaRecView;
    ArrayList<Ora> orak=new ArrayList<>();
    StatisztikaRecViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);
        statisztikaRecView=findViewById(R.id.statisztikaRecView);
        orak.add(0,new Ora("03.11", 9));
        adapter=new StatisztikaRecViewAdapter(orak,this);
        statisztikaRecView.setAdapter(adapter);
        statisztikaRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}
