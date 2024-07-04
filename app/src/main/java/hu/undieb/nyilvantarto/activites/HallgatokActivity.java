package hu.undieb.nyilvantarto.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.activites.adapters.HallgatokRecViewAdapter;
import hu.undieb.nyilvantarto.model.Jelenlet;
import hu.undieb.nyilvantarto.model.Kurzus;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.model.Ora;

public class HallgatokActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private Button addBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallgatok);
        addBtn=findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recViewHallgato);
        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);

        addBtn.setOnClickListener(v->{
            AddHallgato(kurzus_nev);

        });

        KurzusokUtils.getInstance().getHallgatok(kurzus_nev).observe(this,Hallgatok->{
            if(Hallgatok!=null)
            {
                recyclerView.setAdapter(new HallgatokRecViewAdapter(Hallgatok,kurzus_nev));
                Log.d("ObservedData", "Hallgatok: " + Hallgatok.toString());
            }
            else{
                Log.d("ObservedData", "Hallgatok is null");
            }

            ArrayList<Jelenlet> temp=new ArrayList<>();
            for (Hallgato h:Hallgatok)
            {
                temp.add(new Jelenlet(h.getName()));
            }
            if(temp.size()<1)
            {
                //KurzusokUtils.getInstance().addOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),0));
            }
            else{
                //KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),0,temp),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()));
            }

        });
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

    private void AddHallgato(String kurzus_nev) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.add_hallgato_pop_up, null);
        EditText name = view.findViewById(R.id.edtTextName);
        Button btnHozzaAdd=view.findViewById(R.id.btnHozzaAdd);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnHozzaAdd.setOnClickListener(v2->{
            KurzusokUtils.getInstance().addHallgato(kurzus_nev,Integer.toString(KurzusokUtils.getInstance().getHallgatok(kurzus_nev).getValue().size()),new Hallgato(name.getText().toString(),"",""));
            dialog.cancel();
        });

    }

}
