package hu.undieb.nyilvantarto;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Button Btnlogin,BtnGoogle;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private EditText edtEmail,edtPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        Btnlogin=findViewById(R.id.loginBtn);
        BtnGoogle=findViewById(R.id.loginGoogleBtn);
        edtEmail=findViewById(R.id.edtTxtEmail);
        edtPassword=findViewById(R.id.edtTxtPassword);
        Btnlogin.setOnClickListener(v->{
            email=edtEmail.getText().toString().trim();
            password=edtPassword.getText().toString().trim();
            if(email.equals("") && password.equals(""))
            {
                Toast.makeText(Login.this, "Please fill out the Email and Password lines.",
                        Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(Login.this, "Please fill out the Email line.",
                        Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(Login.this, "Please fill out the Password line.",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent=new Intent(Login.this, KurzusokActivity.class);
                                    startActivity(intent);
                                } else {

                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });
        BtnGoogle.setOnClickListener(v->{

        });

    }

}