package com.tinaio.tianapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.internal.NavigationMenu;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class second extends AppCompatActivity {
    FabSpeedDial fabSpeedDial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InitViewItems();
        InitFloatingactionbtn();
    }

    private void InitViewItems() {
        fabSpeedDial = findViewById(R.id.fab_main_menu);
    }

    private void InitFloatingactionbtn() {
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }
}
