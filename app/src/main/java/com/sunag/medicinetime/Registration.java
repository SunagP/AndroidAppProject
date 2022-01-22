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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Registration extends AppCompatActivity {
    TextView alreadyhaveAccount;
    EditText inputEmail,inputPassword,confirmPassword;
    Button btnRegister;
    //regular exp
    String emailpattern = "[a-z0-9._%+-]"+"@[a-z0-9.-]"+"\\.[a-z]";
    String passwordpattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
    ProgressDialog progressdialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        alreadyhaveAccount = findViewById(R.id.alreadyaccount);

        inputEmail = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmpassword);
        btnRegister = findViewById(R.id.registerbtn);
        progressdialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



        alreadyhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }
        });


    }

    private void performAuth() {

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpass = confirmPassword.getText().toString();

        if (!email.matches(emailpattern)){
            inputEmail.setError("Enter correct Email");
        }

        if (!password.matches(passwordpattern) || password.isEmpty()){
            inputEmail.setError("Enter proper password");
        }
        if (!password.equals(confirmpass)){
            inputEmail.setError("password and confirm-password do not match");
        }
        else{
            progressdialog.setMessage("Please wait while registering......");
            progressdialog.setTitle("Registration");
            progressdialog.setCanceledOnTouchOutside(false);
            progressdialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressdialog.dismiss();
                        sendUsertoNextActivity();
                        Toast.makeText(Registration.this, "Registration Successful !!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressdialog.dismiss();
                        Toast.makeText(Registration.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUsertoNextActivity() {

        Intent intent = new Intent(Registration.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}