package hu.undieb.nyilvantarto;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HallgatokActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Hallgato> hallgatok=new ArrayList<>();
    private Button addBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallgatok);
        addBtn=findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recViewHallgato);
        Intent intent=getIntent();
        String kurzus_nev=intent.getStringExtra("kurzus_nev");

        addBtn.setOnClickListener(v->{
            KurzusokUtils.getInstance().addHallgato(kurzus_nev,"0",new Hallgato("Jankó","",""));
        });
        KurzusokUtils.getInstance().getHallgatok().observe(this,Hallgatok->{
            recyclerView.setAdapter(new HallgatokRecViewAdapter(Hallgatok,kurzus_nev));
        });
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

}
