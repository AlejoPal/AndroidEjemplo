package co.com.mintic.proyecto.patiplants.presenter;

import co.com.mintic.proyecto.patiplants.model.LoginInteractor;
import co.com.mintic.proyecto.patiplants.mvp.LoginMVP;

public class loginPresenter implements LoginMVP.Presenter {
    private LoginMVP.View view;
    private LoginMVP.Model model;

    public  loginPresenter(LoginMVP.View view){
        this.view=view;
        this.model = new LoginInteractor(view.getActivity());
    }
    @Override
    public void onLoginClick() {
        boolean error = false;
        LoginMVP.LoginInfo loginInfo = view.getLoginInfo();

        //validar datos
        view.showEmailError("");
        view.showPasswordError("");
        if(loginInfo.getEmail().isEmpty()){
            view.showEmailError("El correo electrónico es obligatorio");
            error = true;
        }else if(!isEmailValid(loginInfo.getEmail())){
            view.showEmailError("El correo electrónico es invalido");
            error = true;
        }


        if(loginInfo.getPassword().isEmpty()){
            view.showPasswordError("La contraseña es obligatoria");
            error = true;
        }else if(!isPasswordValid(loginInfo.getPassword())){
        view.showPasswordError("Contraseña es invalida");
            error = true;
        }
        if(!error) {
            view.showProgresBar();
            new Thread(()-> {
                model.validateCredentials(loginInfo.getEmail(), loginInfo.getPassword(),
                        new LoginMVP.Model.ValidateCredentialsCallback() {
                            @Override
                            public void onSuccess() {
                                view.getActivity().runOnUiThread(() -> {
                                    view.hideProgresBar();
                                    view.showVerArticulosActivity();
                                });

                            }

                            @Override
                            public void onFailure() {
                                view.getActivity().runOnUiThread(() -> {
                                    view.hideProgresBar();
                                    view.showGeneralError("Usuario no encontrado");
                                });
                            }

                });
            }).start();
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length()>=5 && password.length()<=8;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@")
                && email.endsWith(".com");
    }

    @Override
    public void onCreateClick() {
        view.showRegisterUsersActivity();
    }
}
