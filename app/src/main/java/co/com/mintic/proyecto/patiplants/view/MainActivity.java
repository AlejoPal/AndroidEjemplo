package co.com.mintic.proyecto.patiplants.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import co.com.mintic.proyecto.patiplants.R;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextAddress;
    private EditText mEditTextMail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    //private TextView mTextViewData;

    //variables de datos a registrar
    private String name = "";
    private String address = "";
    private String mail = "";
    private String password = "";

    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = (EditText) findViewById(R.id.ed_name);
        mEditTextAddress = (EditText) findViewById(R.id.ed_address);
        mEditTextMail = (EditText) findViewById(R.id.ed_email);
        mEditTextPassword = (EditText) findViewById(R.id.ed_password);
        mButtonRegister = (Button) findViewById(R.id.btna_continue);


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              name = mEditTextName.getText().toString();
              address = mEditTextAddress.getText().toString();
              mail = mEditTextMail.getText().toString();
              password = mEditTextPassword.getText().toString();

              if (password.length() >=6){
                  registerUser();
              }
              else{
                  Toast.makeText(MainActivity.this, "la contraseña debe tener mas de 6 caracteres",Toast.LENGTH_SHORT).show();
              }

            }
        });

        Log.i("MainActivity", "Aplicacion iniciada");
    }


    private  void registerUser() {
        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("address", address);
                    map.put("mail", mail);
                    map.put("password", password);

                    String id =mAuth.getCurrentUser().getUid();

                    mDatabase.child("users").child(mail.replace('.','_').replace('.','_').replace('@', '_').replace('.','_')).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}