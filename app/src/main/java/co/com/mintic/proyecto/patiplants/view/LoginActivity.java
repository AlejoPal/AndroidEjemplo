package co.com.mintic.proyecto.patiplants.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.mintic.proyecto.patiplants.R;
import co.com.mintic.proyecto.patiplants.mvp.LoginMVP;
import co.com.mintic.proyecto.patiplants.presenter.loginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private LinearProgressIndicator pbWait;
    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etPassword;
    private AppCompatButton btnLogin;
    private AppCompatButton btnCreate;

    private LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new loginPresenter(this);  // pendiente de instsnciar el presentdor
        initUI();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {

        savedInstanceState.putString("Email", etEmail.getText().toString());
        savedInstanceState.putString("Password", etPassword.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        etEmail.setText(savedInstanceState.getString("Email"));
        etPassword.setText(savedInstanceState.getString("Password"));


    }

    private void initUI() {
        pbWait = findViewById(R.id.pb_wait);
        tilEmail = findViewById(R.id.til_email);
        etEmail = findViewById(R.id.et_email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> presenter.onLoginClick());

        btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(v -> presenter.onCreateClick());

    }


    private void onLoginClick() {
        // TODO Pendiente de implementar
        Intent intent = new Intent(LoginActivity.this, VerArticulosActivity.class);
        startActivity(intent);
    }

    private void onCreateClick() {
        // TODO Pendiente de implementar
        Intent intent = new Intent(LoginActivity.this, RegisterUsersActivity.class);
        startActivity(intent);
    }


    @Override
    public Activity getActivity() {
        return LoginActivity.this;
    }

    @Override
    public LoginMVP.LoginInfo getLoginInfo() {
        return new LoginMVP.LoginInfo(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim());
    }

    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void showVerArticulosActivity() {
        Intent intent = new Intent(LoginActivity.this, VerArticulosActivity.class);
        startActivity(intent);
    }

    @Override
    public void showGeneralError(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterUsersActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgresBar() {

        pbWait.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
        btnCreate.setEnabled(false);
    }

    @Override
    public void hideProgresBar() {

        pbWait.setVisibility(View.GONE);
        btnLogin.setEnabled(true);
        btnCreate.setEnabled(true);
    }

}