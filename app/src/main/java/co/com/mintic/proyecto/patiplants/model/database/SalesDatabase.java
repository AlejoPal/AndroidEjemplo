package co.com.mintic.proyecto.patiplants.model.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.mintic.proyecto.patiplants.model.database.dao.UserDao;
import co.com.mintic.proyecto.patiplants.model.database.entity.Role;
import co.com.mintic.proyecto.patiplants.model.database.dao.RoleDao;
import co.com.mintic.proyecto.patiplants.model.database.entity.User;

@Database(entities = {User.class, Role.class}, version = 1)
public abstract class SalesDatabase extends RoomDatabase {
    private volatile static SalesDatabase instance;

    public static SalesDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), SalesDatabase.class, "sales-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();

    public abstract RoleDao getRoleDao();
}
