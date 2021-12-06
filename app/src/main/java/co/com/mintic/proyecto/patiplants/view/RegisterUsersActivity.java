package co.com.mintic.proyecto.patiplants.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import co.com.mintic.proyecto.patiplants.R;
import co.com.mintic.proyecto.patiplants.mvp.RegisterMVP;
import co.com.mintic.proyecto.patiplants.presenter.RegisterPresenter;

public class RegisterUsersActivity extends AppCompatActivity implements  RegisterMVP.ViewRegister {

    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etPassword;
    private TextInputLayout tilName;
    private TextInputEditText etName;
    private TextInputLayout tilAddress;
    private TextInputEditText etAddress;
    private AppCompatButton btnContinue;
    private AppCompatButton btnInto;

    private EditText mEditTextName;
    private EditText mEditTextAddress;
    private EditText mEditTextMail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    private String name = "";
    private String address = "";
    private String mail = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    private RegisterMVP.PresenterRegister presenterRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_users);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = (EditText) findViewById(R.id.et_name);
        mEditTextAddress = (EditText) findViewById(R.id.et_address);
        mEditTextMail = (EditText) findViewById(R.id.et_email);
        mEditTextPassword = (EditText) findViewById(R.id.et_password);
        mButtonRegister = (Button) findViewById(R.id.btn_continue);

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
                    Toast.makeText(RegisterUsersActivity.this, "la contraseña debe tener mas de 6 caracteres",Toast.LENGTH_SHORT).show();
                }

            }
        });

        presenterRegister = new RegisterPresenter(this);
        initUI();
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



                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(RegisterUsersActivity.this, ProfileActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterUsersActivity.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(RegisterUsersActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void initUI() {
        tilEmail = findViewById(R.id.til_email);
        etEmail = findViewById(R.id.et_email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        tilName = findViewById(R.id.til_name);
        etName = findViewById(R.id.et_name);

        tilAddress = findViewById(R.id.til_address);
        etAddress = findViewById(R.id.et_address);

        btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(v -> presenterRegister.onContinueClick());

        btnInto = findViewById(R.id.btn_into);
        btnInto.setOnClickListener(v -> onIntoClick());

    }
    private void onIntoClick(){
        Intent intent =new Intent(RegisterUsersActivity.this, LoginActivity.class );
        startActivity(intent);
    }


    @Override
    public RegisterMVP.RegisterInfo getRegisterInfo() {
        return new RegisterMVP.RegisterInfo(
                etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                etName.getText().toString().trim(),
                etAddress.getText().toString().trim());
    }

    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void ShowPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void ShowNameError(String error) {
        tilName.setError(error);
    }

    @Override
    public void ShowAddressError(String error) {
        tilAddress.setError(error);
    }

    @Override
    public void showVerArticulosActivity() {
        Intent intent=new Intent(RegisterUsersActivity.this, LoginActivity.class)  ;
        startActivity(intent);
    }
}