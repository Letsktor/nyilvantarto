package hu.undieb.nyilvantarto;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JelenletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Hallgato> hallgatok=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelenlet);
        recyclerView=findViewById(R.id.recViewJelenlet);
        hallgatok.add(new Hallgato("Marika","12-121-21","0293019310"));
        hallgatok.get(0).setHianyzik(true);
        recyclerView.setAdapter(new JelenletRecViewAdapter(hallgatok));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
