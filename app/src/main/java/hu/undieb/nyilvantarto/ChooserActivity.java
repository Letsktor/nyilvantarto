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
    private String email;
    private String password;
    private EditText edtEmail,edtPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        mAuth = FirebaseAuth.getInstance();
        Btnlogin=findViewById(R.id.loginBtn);
        Btnregister=findViewById(R.id.registerBtn);
        edtEmail=findViewById(R.id.edtTxtEmail);
        edtPassword=findViewById(R.id.edtTxtPassword);


        Btnlogin.setOnClickListener(v->{
            email=edtEmail.getText().toString().trim();
            password=edtPassword.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent=new Intent(ChooserActivity.this, KurzusokActivity.class);
                                startActivity(intent);
                            } else {

                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(ChooserActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        Btnregister.setOnClickListener(v->{
            Intent intent=new Intent(ChooserActivity.this, Register.class);
            startActivity(intent);
            email=edtEmail.getText().toString().trim();
            password=edtPassword.getText().toString().trim();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {

                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(ChooserActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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
