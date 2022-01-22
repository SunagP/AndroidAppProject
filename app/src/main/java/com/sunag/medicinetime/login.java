package com.sunag.medicinetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sunag.medicinetime.medicine.MainActivity;

public class login extends AppCompatActivity {
    TextView createnewAccount;

    EditText inputEmail, inputPassword;
    Button btnlogin;
    String emailpattern = "[a-z0-9._%+-]" + "@[a-z0-9.-]" + "\\.[a-z]";
    String passwordpattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

    ProgressDialog progressdialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createnewAccount = findViewById(R.id.newaccount);
        inputEmail = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        btnlogin = findViewById(R.id.loginbtn);
        progressdialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        createnewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Registration.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performlogin();
            }
        });


    }

    private void performlogin() {

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();


//        if (!email.matches(emailpattern)){
//            inputEmail.setError("Enter correct Email");
//        }

//        if (!password.matches(passwordpattern) || password.isEmpty()){
//            inputEmail.setError("Enter proper password");
//        }

        {
            progressdialog.setMessage("Please wait while Logging in......");
            progressdialog.setTitle("Login");
            progressdialog.setCanceledOnTouchOutside(false);
            progressdialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressdialog.dismiss();
                        sendUsertoNextActivity();
                        Toast.makeText(login.this, "Login Successful !!", Toast.LENGTH_SHORT).show();
                    } else {
                        progressdialog.dismiss();
                        Toast.makeText(login.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUsertoNextActivity() {

        Intent intent = new Intent(login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}