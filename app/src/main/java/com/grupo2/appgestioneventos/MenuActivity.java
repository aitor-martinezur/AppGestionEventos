package com.grupo2.appgestioneventos;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.grupo2.appgestioneventos.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //establece el fondo animado con sus parametros
        //LinearLayout constraintLayout = findViewById(R.id.nav_header_menu_id);
        //AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        //animationDrawable.setEnterFadeDuration(2000);
        //animationDrawable.setExitFadeDuration(4000);
        //animationDrawable.start();

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

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

        // Converts 55 dip into its equivalent px
        float dip2 = 55f;
        float px2 = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip2,
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
}