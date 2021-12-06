package co.com.mintic.proyecto.patiplants.mvp;

import android.app.Activity;

import java.lang.reflect.Constructor;

public interface LoginMVP {
    interface Model{

        void validateCredentials(String email, String password,
                                  ValidateCredentialsCallback callback);

        interface ValidateCredentialsCallback {
            void onSuccess();

            void onFailure();
        }
    }
    interface Presenter{
        void onLoginClick();
        void onCreateClick();
    }
    interface View{
        Activity getActivity();
        LoginInfo getLoginInfo();

        void showEmailError(String error);

        void showPasswordError(String error);

        void showVerArticulosActivity();

        void showRegisterUsersActivity();

        void showProgresBar();

        void hideProgresBar();

        void showGeneralError(String error);
    }
    class LoginInfo{
        private String email;
        private String password;

        public LoginInfo(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
