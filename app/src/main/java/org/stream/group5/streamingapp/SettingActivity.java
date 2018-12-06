package org.stream.group5.streamingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "prefs";
    public static final String PREF_DARK_THEME = "dark_theme";
    public Switch themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        if(useDarkTheme)
        {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar setting_toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(setting_toolbar);
        setting_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        setting_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        themeSwitch = (Switch) findViewById(R.id.themeSwitch);
        themeSwitch.setChecked(useDarkTheme);
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                changeTheme(isChecked);

            }
        });
    }
    public void changeTheme(Boolean darkTheme)
    {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);

        editor.apply();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
