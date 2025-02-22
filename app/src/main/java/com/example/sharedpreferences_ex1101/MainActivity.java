package com.example.sharedpreferences_ex1101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The type Main activity.
 *
 * @author      Tal Weintraub <tv0823@bs.amalnet.k12.il>
 * @version	    1
 * @since		22/2/2025
 * short description:
 *      Gets user input and stores it in a SharedPreferences file, when opens again reads from it.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The CountTv.
     */
    TextView countTv;

    /**
     * The NameEt.
     */
    EditText nameEt;

    /**
     * The Count.
     */
    int count = 0;

    /**
     * The Settings.
     */
    SharedPreferences settings;

    /**
     * The Editor.
     */
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTv = findViewById(R.id.countTv);
        nameEt = findViewById(R.id.nameEt);

        settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        editor = settings.edit();

        String name = settings.getString("Name", "");
        nameEt.setText(name);
        count = settings.getInt("Counter", 0);
        countTv.setText(count + "");
    }

    /**
     * Increase counter and update it in textView.
     *
     * @param view the view
     */
    public void incCount(View view) {
        count ++;
        countTv.setText(count + "");
    }

    /**
     * Resets counter and update it in textView.
     *
     * @param view the view
     */
    public void resetCount(View view) {
        count = 0;
        countTv.setText(count + "");
    }

    /**
     * Stores the user input in a SharedPreferences and exits the app.
     *
     * @param view the view
     */
    public void exitApp(View view) {
        editor.putString("Name", nameEt.getText().toString());
        editor.putInt("Counter", count);
        editor.commit();
        finish();
    }

    /**
     * Creates the options menu on screen
     *
     * @param menu the menu
     * @return ture
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Checks if the clicked menuItem is R.id.menuCred
     *
     * @param item a menuItem
     * @return ture
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuCred) {
            Intent si = new Intent(this, CreditsActivity.class);
            startActivity(si);
        }
        return true;
    }
}