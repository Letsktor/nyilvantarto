package hu.undieb.nyilvantarto;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooserActivity extends AppCompatActivity {
    private Button Btnlogin,Btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
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
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(ChooserActivity.this, KurzusokActivity.class);
            startActivity(intent);
        }
    }

}
