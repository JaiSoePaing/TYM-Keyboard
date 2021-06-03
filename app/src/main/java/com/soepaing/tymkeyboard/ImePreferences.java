package com.soepaing.tymkeyboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import com.android.inputmethodcommon.InputMethodSettingsFragment;

public class ImePreferences extends PreferenceActivity {
    static InputMethodManager imeManager;

    @Override
    public Intent getIntent() {
        final Intent modIntent = new Intent(super.getIntent());
        modIntent.putExtra(EXTRA_SHOW_FRAGMENT,
                SettingsLanguage.class.getName());
        modIntent.putExtra(EXTRA_NO_HEADERS, true);
        return modIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We overwrite the title of the activity, as the default one is
        // "Voice Search".
        setTitle(R.string.settings_name);

        imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);

    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        // TODO Auto-generated method stub
        return SettingsLanguage.class.getName().equals(fragmentName);
    }

    public static class SettingsLanguage extends InputMethodSettingsFragment {

        @SuppressLint("NewApi")
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setInputMethodSettingsCategoryTitle(R.string.language_selection_title);
            setSubtypeEnablerTitle(R.string.select_language);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.ime_preferences);

            Preference myPref = (Preference) findPreference("go_to_ime");
            myPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    //open browser or intent here
                    startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                    return true;
                }
            });

            Preference myPref2 = (Preference) findPreference("choose_keyboard");
            myPref2.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    //open browser or intent here
                    imeManager.showInputMethodPicker();
                    return true;
                }
            });
        }
    }
}
