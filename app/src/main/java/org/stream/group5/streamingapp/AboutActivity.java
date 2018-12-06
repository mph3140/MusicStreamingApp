package org.stream.group5.streamingapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static org.stream.group5.streamingapp.SettingActivity.PREFS_NAME;
import static org.stream.group5.streamingapp.SettingActivity.PREF_DARK_THEME;

public class AboutActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        if(useDarkTheme)
        {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        Toolbar setting_toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(setting_toolbar);
        setting_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        setting_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    protected void onRestart()
    {
        recreate();
        super.onRestart();

    }

}
