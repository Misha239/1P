package com.example.a1g;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1g.dataClasses.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.logging.ErrorManager;


public class RegistrationActivity extends AppCompatActivity {
    EditText nameET, emailET, phoneET, passwordET;
    Button enterBtn;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);
        nameET = findViewById(R.id.name);
        emailET = findViewById(R.id.email);
        phoneET = findViewById(R.id.phone);
        passwordET = findViewById(R.id.password);
        enterBtn = findViewById(R.id.enterBtn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.RegisterNewUserInDatabase(
                        nameET.getText().toString(),
                        emailET.getText().toString(),
                        passwordET.getText().toString(),
                        phoneET.getText().toString(),
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent();
                                intent.putExtra("user", user);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        },
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.error(getApplicationContext(),
                                        "Нет соединения с сервером").show();
                            }
                        });
            }
        });
    }
}






