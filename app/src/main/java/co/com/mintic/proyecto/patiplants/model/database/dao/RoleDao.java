package co.com.mintic.proyecto.patiplants.model.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import co.com.mintic.proyecto.patiplants.model.database.entity.Role;

@Dao
public interface RoleDao {

    @Query("SELECT * FROM role")
    List<Role> getAll();
}
