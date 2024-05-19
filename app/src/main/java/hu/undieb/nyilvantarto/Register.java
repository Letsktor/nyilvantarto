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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private Button Btnregister,BtnGoogle;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private EditText edtEmail,edtPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmail=findViewById(R.id.edtTxtEmail);
        edtPassword=findViewById(R.id.edtTxtPassword);
        mAuth = FirebaseAuth.getInstance();
        Btnregister=findViewById(R.id.registerBtn);
        BtnGoogle=findViewById(R.id.registerGoogleBtn);
        Btnregister.setOnClickListener(v->{
            email=edtEmail.getText().toString();
            password=edtPassword.getText().toString();
            if(email.equals("") && password.equals(""))
            {
                Toast.makeText(Register.this, "Please fill out the Email and Password lines.",
                        Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(Register.this, "Please fill out the Email line.",
                        Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(Register.this, "Please fill out the Password line.",
                        Toast.LENGTH_SHORT).show();
            } else {

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
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }

}