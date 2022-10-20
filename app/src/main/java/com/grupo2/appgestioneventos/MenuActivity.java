package com.grupo2.appgestioneventos;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.MenuItemKt;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.grupo2.appgestioneventos.databinding.ActivityMenuBinding;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //pone los botones del submenu en invisible, cambia los colores de los iconos y pone el fondo en invisible
        binding.appBarMenu.fab.setColorFilter(getResources().getColor(R.color.black));
        binding.appBarMenu.fab2.setVisibility(View.INVISIBLE);
        binding.appBarMenu.fab2.setColorFilter(getResources().getColor(R.color.black));
        binding.appBarMenu.fab3.setVisibility(View.INVISIBLE);
        binding.appBarMenu.fab3.setColorFilter(getResources().getColor(R.color.black));
        binding.appBarMenu.fab4.setVisibility(View.INVISIBLE);
        binding.appBarMenu.fab4.setColorFilter(getResources().getColor(R.color.black));
        binding.appBarMenu.imageViewSubMenu.setVisibility(View.INVISIBLE);

        setSupportActionBar(binding.appBarMenu.toolbar);
        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSubmenuVisibility(binding.appBarMenu.fab, binding.appBarMenu.fab2,binding.appBarMenu.fab3,binding.appBarMenu.fab4, binding.appBarMenu.imageViewSubMenu);
            }
        });
        DrawerLayout drawer = binding.menuLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_eventos, R.id.nav_contactos)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //recoge true si es admin, false si no lo es
        //si es admin le deja acceder al menu de crear usuario
        binding.appBarMenu.fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmin()){
                    Snackbar.make(view, "admin", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "No tienes permisos para acceder a esta función.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        /*//funcionalidad de cerrar sesion en los tres puntos de arriba a la derecha
        View botonCerrarSesion = findViewById(R.id.action_settings_cerrarSesion);
        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        //valores de la otra actividad
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int numUsuario = 0;
        String admin = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuarios = (ArrayList<Usuario>)getIntent().getExtras().getSerializable("keyUsuarios");
            numUsuario = extras.getInt("keyNumUsuario");
            //The key argument here must match that used in the other activity
        }
        if (numUsuario==0){
            admin = "true";
        }
        else{
            admin = "false";
        }
        //pone los valores en el menu desplegable
        TextView TVNombreApellido = findViewById(R.id.nombreUsuarioMenu);
        TextView TVEmail = findViewById(R.id.emailUsuarioMenu);
        TextView valorAdmin = findViewById(R.id.valorAdmin);
        valorAdmin.setText(admin);
        TVNombreApellido.setText(usuarios.get(numUsuario).getNombre()+" "+usuarios.get(numUsuario).getApellido());
        TVEmail.setText(usuarios.get(numUsuario).getEmail());

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //funcion para que cuando se haga click en el submenu de abajo a la derecha muestre y oculte las opciones
    /*
     * @param   fab,fab2,fab3,fab4  los botones de la actividad cuales se quieren cambiar la visibilidad
     * @param   imgV                la imagen que se usa de fondo para los botones cuando estan visibles
     */
    public void changeSubmenuVisibility(FloatingActionButton fab, FloatingActionButton fab2, FloatingActionButton fab3, FloatingActionButton fab4, ImageView imgV){
        imgV.requestLayout();
        // Converts 252 dip into its equivalent px
        float dip1 = 252f;
        Resources r = getResources();
        float px1 = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip1,
                r.getDisplayMetrics()
        );

        if ((fab2.getVisibility()==View.INVISIBLE)&&(fab3.getVisibility()==View.INVISIBLE)&&(fab4.getVisibility()==View.INVISIBLE)){
            fab2.setVisibility(View.VISIBLE);
            fab3.setVisibility(View.VISIBLE);
            fab4.setVisibility(View.VISIBLE);
            fab.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            fab.setColorFilter(getResources().getColor(R.color.black));
            imgV.getLayoutParams().height = Math.round(px1);
            imgV.requestLayout();
            imgV.setVisibility(View.VISIBLE);
        }
        else {
            fab2.setVisibility(View.INVISIBLE);
            fab3.setVisibility(View.INVISIBLE);
            fab4.setVisibility(View.INVISIBLE);
            fab.setImageResource(android.R.drawable.ic_input_add);
            fab.setColorFilter(getResources().getColor(R.color.black));
            imgV.setVisibility(View.INVISIBLE);
        }
    }

    //funcion que comprueba si el usuario con el que se ha hecho login tiene permisos de administrador para usar la funcionalidad del boton para crear usuarios
    /*
     * @return  true si el usuario es administrador, false en caso de que no lo sea
     */
    public boolean isAdmin(){
        TextView valorAdmin = findViewById(R.id.valorAdmin);
        CharSequence vAdmin = valorAdmin.getText();
        String vAdminString = vAdmin.toString();
        boolean isAdmin = Boolean.parseBoolean(vAdminString);

        return isAdmin;
    }

    //funcion que devuelve a la ventana de login para volver a iniciar sesion
    /*
     * @param   item    item del menu que se conecta con la funcion
     */
    public void pruebaCerrarSesion(MenuItem item){
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
        this.finish();
    }
}