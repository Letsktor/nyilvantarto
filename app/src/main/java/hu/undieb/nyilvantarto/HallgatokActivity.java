package hu.undieb.nyilvantarto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Tag;

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
        Log.d("TAG",kurzus_nev);
        Toast.makeText(this,kurzus_nev,Toast.LENGTH_LONG);
        addBtn.setOnClickListener(v->{
            KurzusokUtils.getInstance().addHallgato(kurzus_nev,"0",new Hallgato("Jankó","",""));
        });
        recyclerView.setAdapter(new HallgatokRecViewAdapter(KurzusokUtils.getInstance().getKurzusok().getValue().get(0).getOrak().get(0).getHallgatok()));
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }
}
