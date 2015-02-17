/*
This file is part of Piller.

Piller is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Piller is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Piller. If not, see <http://www.gnu.org/licenses/>.

Copyright 2015, Giulio Fagioli, Lorenzo Salani
*/
package io.github.pillsoft.piller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private EasyLicenseChecker mLicenseChecker;
    protected TextView nameTheme;
    SharedPreferences sharedPreferences;
    private ProgressDialog pd;
    private AlertDialog.Builder md;
    public static List<Theme> ThemeList;
    String[] ThemeNames;
    String[] ThemePackages;
    String[] ThemeColors;
    String[] ThemeAccentColors;
    String[] ThemeDarkColors;
    String[] ThemeMotto;
    String[] ThemeHighlightedColors;
    //Theme currentTheme;
    private String copyDirTheme;
    private RecyclerView mRecyclerView;
    private ThemeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        copyDirTheme = Environment.getExternalStorageDirectory() + "/Themes/Data";
        final Toolbar toolbar = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(toolbar);
        //Check
        //mLicenseChecker = new EasyLicenseChecker(this, new Handler());
        //mLicenseChecker.start();

        ThemeList = new ArrayList<Theme>();
        ThemeNames = getResources().getStringArray(R.array.theme_names);
        ThemePackages = getResources().getStringArray(R.array.theme_packages);
        ThemeColors = getResources().getStringArray(R.array.theme_colors);
        ThemeDarkColors = getResources().getStringArray(R.array.theme_darkcolors);
        ThemeAccentColors = getResources().getStringArray(R.array.theme_accentcolors);
        ThemeHighlightedColors = getResources().getStringArray(R.array.theme_highlightedcolors);
        ThemeMotto = getResources().getStringArray(R.array.theme_motto);

        PopulateThemeList();

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ThemeAdapter(ThemeManager.getInstance().getListTheme(), R.layout.card_view, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void PopulateThemeList() {
        int i = 0;
        while (i < getResources().getStringArray(R.array.theme_names).length) {
            ThemeList.add(new Theme(ThemeNames[i], ThemePackages[i], ThemeColors[i], ThemeDarkColors[i], ThemeAccentColors[i], ThemeHighlightedColors[i], ThemeMotto[i]));
            Log.v("Groviee populate", "ADD");
            i++;
        }
    }

    protected void storeSharedPrefs() {
        sharedPreferences = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
        editor.putString("version", getResources().getString(R.string.current_version));
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_rate) {
            Rate("https://play.google.com/store/apps/details?id=" +getApplicationContext().getPackageName());
            Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.rate_thanks), Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menu_share) {
            Share("https://play.google.com/store/apps/details?id=" +getApplicationContext().getPackageName());
        }
        if (id == R.id.menu_developer) {
            Link(this.getResources().getString(R.string.developer_site));

        }
        if (id == R.id.menu_mail) {
            Mail(this.getResources().getString(R.string.app_name), this.getResources().getString(R.string.email_address));

        }
        if (id == R.id.community) {
            Link(this.getResources().getString(R.string.community_link));

        }

        return super.onOptionsItemSelected(item);
    }

    public void Share(String playStoreLink){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, playStoreLink);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void Rate(String playStoreLink){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink));
        startActivity(browserIntent);
    }
    public void Mail(String themeName, String email){
        Intent mailIntent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + email));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, themeName);
        startActivity(mailIntent);
    }
    public void Link(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLicenseChecker != null) mLicenseChecker.finish();
        try {
            this.delete(new File(copyDirTheme));
        } catch (IOException e) {
            Log.d("Error Deleting", "Error on call delete");
            e.printStackTrace();
        }
    }

    void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        } else if (f.getAbsolutePath().endsWith("FIR")) {
            if (!f.delete()) {
                new FileNotFoundException("Failed to delete file: " + f);
            }
        }
    }

    public void installTheme(View v) {
        Log.v("GROOVIE", "To Theme Installer");
        Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
        intent.putExtra("ThemeName", nameTheme.getText().toString());
        intent.putExtra("ThemePackage", "io.github.remeic.LinTheme");
        startActivity(intent);
    }

    public void cardOnClickListener(View v) {
        int index = -1;
        Theme auxTheme = null;
        String cardTagTheme = v.getTag().toString();
        for (Theme t : this.ThemeList) {
            if (t.theme_name.equals(cardTagTheme)) {
                index = ThemeList.indexOf(t);
            }
        }
        auxTheme = ThemeList.get(index);
        Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
        intent.putExtra("ThemeArray", auxTheme.getArrayListTheme());
        int res = 0;
        //Change it for image theme
        switch(auxTheme.theme_name){//Define the image to see in ThemeActivity
            case "Lin":
                res = R.drawable.imm;
                break;
            case "Daa":
                res = R.drawable.imm2;
        }
        intent.putExtra("ImageResurces", res);
        startActivity(intent);

    }

}



