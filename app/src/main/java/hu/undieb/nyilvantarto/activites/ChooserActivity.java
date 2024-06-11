package hu.undieb.nyilvantarto.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import hu.undieb.nyilvantarto.R;

public class ChooserActivity extends AppCompatActivity {
    private Button Btnlogin,Btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        Btnlogin=findViewById(R.id.loginBtn);
        Btnregister=findViewById(R.id.registerBtn);
        Btnlogin.setOnClickListener(v->{
            Intent intent=new Intent(ChooserActivity.this, Login.class);
            startActivity(intent);

        });
        Btnregister.setOnClickListener(v->{
            Intent intent=new Intent(ChooserActivity.this, Register.class);
            startActivity(intent);

        });

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(ChooserActivity.this, KurzusokActivity.class);
            startActivity(intent);
        }
    }
}
