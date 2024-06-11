package hu.undieb.nyilvantarto.activites;

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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import hu.undieb.nyilvantarto.R;

public class Login extends AppCompatActivity {
    private Button Btnlogin,BtnGoogle;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private EditText edtEmail,edtPassword;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

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
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(Login.this, KurzusokActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

}