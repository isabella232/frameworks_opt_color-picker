package com.enrico.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.enrico.colorpicker.colorDialog;

import static com.enrico.sample.PreferenceActivity.SettingsFragment.firstPreference;
import static com.enrico.sample.PreferenceActivity.SettingsFragment.secondPreference;

@SuppressLint("NewApi")
public class PreferenceActivity extends AppCompatActivity implements colorDialog.ColorSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preference_activity);

        //provide back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getFragmentManager().findFragmentById(R.id.content_frame) == null) {

            SettingsFragment settingsFragment = new SettingsFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_frame, settingsFragment).commit();
        }

    }

    //close app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onColorSelection(DialogFragment dialogFragment, int color) {

        int tag;

        tag = Integer.valueOf(dialogFragment.getTag());

        switch (tag) {

            //do something on color selection
            case 3:
                colorDialog.setColorPreferenceSummary(firstPreference, color, PreferenceActivity.this, getResources());
                colorDialog.setPickerColor(PreferenceActivity.this, 3, color);

                //do your shit here
                Toast.makeText(getBaseContext(), getResources().getString(R.string.selection) + tag + getResources().getString(R.string.is) + Integer.toHexString(color).toUpperCase(), Toast.LENGTH_SHORT)
                        .show();
                break;

            case 4:
                colorDialog.setColorPreferenceSummary(secondPreference, color, PreferenceActivity.this, getResources());
                colorDialog.setPickerColor(PreferenceActivity.this, 4, color);

                //do your shit here
                Toast.makeText(getBaseContext(), getResources().getString(R.string.selection) + tag + getResources().getString(R.string.is) + Integer.toHexString(color).toUpperCase(), Toast.LENGTH_SHORT)
                        .show();
                break;
        }

    }

    public static class SettingsFragment extends PreferenceFragment {

        //preferences
        static Preference firstPreference;
        static Preference secondPreference;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.info_pref);

            //get the preferences
            firstPreference = findPreference("preferenceColor");
            secondPreference = findPreference("preferenceColor2");

            //get preferences colors
            int color = colorDialog.getPickerColor(getActivity(), 3);
            int color2 = colorDialog.getPickerColor(getActivity(), 4);

            //set preferences colors
            colorDialog.setColorPreferenceSummary(firstPreference, color, getActivity(), getResources());
            colorDialog.setColorPreferenceSummary(secondPreference, color2, getActivity(), getResources());

            firstPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

                    //associate dialog with selected preference
                    colorDialog.showColorPicker(appCompatActivity, 3);
                    return false;
                }
            });

            secondPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

                    //associate dialog with selected preference
                    colorDialog.showColorPicker(appCompatActivity, 4);
                    return false;
                }
            });
        }
    }
}
