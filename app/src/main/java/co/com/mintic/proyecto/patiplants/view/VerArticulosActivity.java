package co.com.mintic.proyecto.patiplants.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.net.URI;

import co.com.mintic.proyecto.patiplants.R;


public class VerArticulosActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,iv_logo;
    String _url = "https://wa.link/0ynvp3";
    private DrawerLayout drawerLayout;
    private MaterialToolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_articulos);

        initUI();
        iv_logo = findViewById(R.id.iv_logo);
        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link= Uri.parse(_url);
                //Log.d("Click","ok");
                Intent i = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(i);
            }
        });
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerArticulosActivity.this, EcheveriaBlackPrinceActivity.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerArticulosActivity.this, EcheveriaSiemprevivaActivity.class);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerArticulosActivity.this, CrassulaPerforataActivity.class);
                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerArticulosActivity.this, EcheveriaImbricataActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUI() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.app_toolbar);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        navigationView= findViewById(R.id.nv_ver_articulos);
        navigationView.setNavigationItemSelectedListener(menuItem ->onMenuItemClick(menuItem));
    }

    private boolean onMenuItemClick(MenuItem menuItem){
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
