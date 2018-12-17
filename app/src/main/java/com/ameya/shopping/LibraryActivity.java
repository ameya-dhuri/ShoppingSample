package com.ameya.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    private String TAG = LibraryActivity.class.getName();
    private LibraryFragment mLibraryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mLibraryFragment = LibraryFragment.newInstance();
        fragmentTransaction.add(R.id.library_bundle_holder, mLibraryFragment);
        fragmentTransaction.commit();
    }
}
