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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
    private HallgatokRecViewAdapter adapter;
    private  ArrayList<Jelenlet> temp=new ArrayList<>();
    private int jelszam=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallgatok);
        addBtn=findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recViewHallgato);

        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        addBtn.setOnClickListener(v->{
            AddHallgato(kurzus_nev);
        });
        if(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()<1)
        {

            KurzusokUtils.getInstance().addOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),0));
        }
            else{
                temp=KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size() - 1).getJelenlevok();
                for(Jelenlet jel:temp) {
                    if(jel.getStatus()== Jelenlet.Status.PRESENT || jel.getStatus()== Jelenlet.Status.RECORDEDBYTEACHER){
                        jelszam+=1;
                    }
                }
            }
        KurzusokUtils.getInstance().getHallgatok(kurzus_nev).observe(this,h->{
            if(h!=null)
            {
                    adapter=new HallgatokRecViewAdapter(h,kurzus_nev);
                    recyclerView.setAdapter(adapter);
                    Log.d("ObservedData", "Hallgatok: " + h.toString());

            }
            else{
                Log.d("ObservedData", "Hallgatok is null");
            }



        });

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
            temp.add(new Jelenlet(name.getText().toString()));
            Log.d("LOGD",temp.toString());
            KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),jelszam,temp),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1));
            dialog.cancel();
        });

    }

}
