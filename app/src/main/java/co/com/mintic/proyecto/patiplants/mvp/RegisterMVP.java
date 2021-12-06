package co.com.mintic.proyecto.patiplants.mvp;

public interface RegisterMVP {
    interface ModelRegister {

    }
    interface PresenterRegister {
        void onContinueClick();
    }
    interface ViewRegister  {
        RegisterInfo getRegisterInfo();
        void showEmailError(String error);
        void ShowPasswordError(String error);
        void ShowNameError(String error);
        void ShowAddressError(String error);

        void showVerArticulosActivity();
    }

    class RegisterInfo {
        private String email;
        private String password;
        private String name;
        private String address;

        public RegisterInfo(String email, String password, String name, String address) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }
    }
}
