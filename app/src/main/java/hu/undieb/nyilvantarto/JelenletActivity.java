package hu.undieb.nyilvantarto;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JelenletActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelenlet);
        recyclerView=findViewById(R.id.recViewJelenlet);
        recyclerView.setAdapter(new JelenletRecViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
