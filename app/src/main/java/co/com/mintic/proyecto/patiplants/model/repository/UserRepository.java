package co.com.mintic.proyecto.patiplants.model.repository;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.com.mintic.proyecto.patiplants.model.database.SalesDatabase;
import co.com.mintic.proyecto.patiplants.model.database.dao.UserDao;
import co.com.mintic.proyecto.patiplants.model.database.entity.User;

public class UserRepository {

    private final UserDao userDao;
    private final DatabaseReference userRefer;
    private final boolean inDB = false;

    public UserRepository(Context context) {
        this.userDao = SalesDatabase.getInstance(context)
                .getUserDao();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userRefer = database.getReference("users");
        loadInitialUsers();

    }


    private void loadInitialUsers() {
        if (inDB) {
            //usar room Database
            userDao.insert(new User("Daniel Monrroy", "danielmonca77@gmail.com", "1234567", "calle 4 no 10-30"));
            userDao.insert(new User("Dayana Gomez", "aleja.gomez.332345@gmail.com", "123456", "calle 4 no 10-31"));
        } else {
            userRefer.child("id").setValue("3");
            userRefer.child("next").removeValue();
            //Usar Firebase
            userRefer.child("aleja_gomez_332345_gmail_com").child("name").setValue("Alejandra Gomez");
            userRefer.child("aleja_gomez_332345_gmail_com").child("email").setValue("aleja.gomez.332345@gmail.com");
            userRefer.child("aleja_gomez_332345_gmail_com").child("password").setValue("123456");
            userRefer.child("aleja_gomez_332345_gmail_com").child("direction").setValue("Calle3 #1d-20");

            userRefer.child("danielmonca77_gmail_com").child("name").setValue("Daniel Monrroy");
            userRefer.child("danielmonca77_gmail_com").child("email").setValue("danielmonca77@gmail.com");
            userRefer.child("danielmonca77_gmail_com").child("password").setValue("1234567");
            userRefer.child("danielmonca77_gmail_com").child("direction").setValue("Calle3 #1d-22");

        }
    }


    public void getUserByEmail(String email, GetUserByEmailCallback callback) {

        if (inDB) {
            callback.onSuccess(userDao.getUserByEmail(email));

        } else {
        // Read from the database
        userRefer.child(getEmailId(email))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);
                        callback.onSuccess(user);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        callback.onFailure();
                    }
                });
    }
}
    private String getEmailId(String email){
        return email.replace('.','_').replace('.','_').replace('@', '_').replace('.','_');
    }
    public static interface GetUserByEmailCallback{
        void onSuccess(User user);
        void onFailure();
    }
}
