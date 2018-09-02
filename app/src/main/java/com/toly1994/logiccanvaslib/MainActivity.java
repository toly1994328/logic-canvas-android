package com.toly1994.logiccanvaslib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.toly1994.logiccanvaslib.MyView.LogicView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LogicView(this));
    }
}
