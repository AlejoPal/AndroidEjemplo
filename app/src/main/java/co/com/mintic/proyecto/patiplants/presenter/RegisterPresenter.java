package co.com.mintic.proyecto.patiplants.presenter;

import co.com.mintic.proyecto.patiplants.model.RegisterInteractor;
import co.com.mintic.proyecto.patiplants.mvp.RegisterMVP;

public class RegisterPresenter implements RegisterMVP.PresenterRegister {
    private RegisterMVP.ViewRegister viewRegister;
    private RegisterMVP.ModelRegister modelRegister;

    public RegisterPresenter(RegisterMVP.ViewRegister viewRegister){
        this.viewRegister =viewRegister;
        this.modelRegister = (RegisterMVP.ModelRegister) new RegisterInteractor();
    }

    @Override
    public void onContinueClick() {
        boolean error = false;
        RegisterMVP.RegisterInfo registerInfo = viewRegister.getRegisterInfo();
        viewRegister.showEmailError("");
        viewRegister.ShowPasswordError("");
        viewRegister.ShowNameError("");
        viewRegister.ShowAddressError("");

        if(registerInfo.getEmail().isEmpty()){
            viewRegister.showEmailError("correo eléctronico requerido");
            error = true;
        }else if(!isEmailValid(registerInfo.getEmail())){
            viewRegister.showEmailError("correo eléctronico no valido");
            error = true;
        }

        if(registerInfo.getPassword().isEmpty()){
            viewRegister.ShowPasswordError("contraseña requerida");
            error = true;
        }else if(!isPasswordValid(registerInfo.getPassword())){
            viewRegister.ShowPasswordError("contraseña no segura");
            error = true;
        }

        if(registerInfo.getName().isEmpty()){
            viewRegister.ShowNameError("nombre requerido");
            error = true;
        }else if(!isNameValid(registerInfo.getName())){
            viewRegister.ShowNameError("nombre no completo");
            error = true;
        }

        if(registerInfo.getAddress().isEmpty()){
            viewRegister.ShowAddressError("dirección requerida");
            error = true;
        }else if(!isAddressValid(registerInfo.getAddress())){
            viewRegister.ShowAddressError("direccion no completa");
            error = true;
        }
        if(!error) {
            viewRegister.showVerArticulosActivity();
        }
    }

    private boolean isAddressValid(String address) {
        return address.length() >=5;
    }

    private boolean isNameValid(String name) {
        return name.length() >=5;
    }

    private boolean isPasswordValid(String password) {
        return password.length()>=5 && password.length()<=8;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@")
                && email.endsWith(".com");
    }

}
