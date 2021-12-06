package co.com.mintic.proyecto.patiplants.model;

import android.content.Context;

import co.com.mintic.proyecto.patiplants.model.database.entity.User;
import co.com.mintic.proyecto.patiplants.model.repository.UserRepository;
import co.com.mintic.proyecto.patiplants.mvp.LoginMVP;

public class LoginInteractor implements LoginMVP.Model {

    private final UserRepository userRepository;

    public LoginInteractor(Context context){ userRepository = new UserRepository(context);
    }
    @Override
    public void validateCredentials(String email, String password,
                                    ValidateCredentialsCallback callback) {
        userRepository.getUserByEmail(email, new UserRepository.GetUserByEmailCallback() {
            @Override
            public void onSuccess(User user) {
                if( user != null && user.getPassword().equals(password)){
                    callback.onSuccess();
                }else{
                    callback.onFailure();
                }

            }

            @Override
            public void onFailure() {
                 callback.onFailure();
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
