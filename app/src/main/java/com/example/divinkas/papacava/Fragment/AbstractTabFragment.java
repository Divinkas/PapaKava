package com.example.divinkas.papacava.Fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

public class AbstractTabFragment extends Fragment {
    private String TITLE;

    protected Context context;
    protected View view;

    public void setTitle(String title){
        TITLE = title;
    }

    public String getTITLE() {
        return TITLE;
    }
}
