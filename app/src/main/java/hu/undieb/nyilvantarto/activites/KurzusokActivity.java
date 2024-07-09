package hu.undieb.nyilvantarto.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.model.Kurzus;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.model.Ora;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.adapters.KurzusokRecViewAdapter;

public class KurzusokActivity extends AppCompatActivity {
    private RecyclerView kurzusokRecView;
    private KurzusokRecViewAdapter adapter;
    private ArrayList<Kurzus> kurzusok=new ArrayList<>();
    private Button btnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurzusok);
        kurzusokRecView=findViewById(R.id.kurzusRecView);
        btnAdd=findViewById(R.id.btnAdd);
        adapter=new KurzusokRecViewAdapter(kurzusok,this);
        kurzusokRecView.setAdapter(adapter);
        kurzusokRecView.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(v->{
            AddKurzus();
        });
        KurzusokUtils.getInstance().getKurzusok().observe(this, Kurzusok->{
            kurzusok=Kurzusok;
            adapter=new KurzusokRecViewAdapter(Kurzusok,this);
            kurzusokRecView.setAdapter(adapter);

        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position=viewHolder.getAdapterPosition();
                    KurzusokUtils.getInstance().removeKurzus(adapter.getKurzusName(position));
            }
        });

        itemTouchHelper.attachToRecyclerView(kurzusokRecView);


    }
    private void AddKurzus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.add_hallgato_pop_up, null);
        EditText name = view.findViewById(R.id.edtTextName);
        Button btnHozzaAdd=view.findViewById(R.id.btnHozzaAdd);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnHozzaAdd.setOnClickListener(v2->{
            KurzusokUtils.getInstance().addKurzus(new Kurzus(name.getText().toString()));
            dialog.cancel();
        });
    }

}
