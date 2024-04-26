package hu.undieb.nyilvantarto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button Btnlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Btnlogin=findViewById(R.id.loginBtn);
        Btnlogin.setOnClickListener(v->{
            Intent intent=new Intent(this, KurzusokActivity.class);
            startActivity(intent);
        });
    }
}
