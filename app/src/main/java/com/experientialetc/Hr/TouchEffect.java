package com.experientialetc.Hr;

import android.view.MotionEvent;
import android.view.View;

public class TouchEffect implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.setAlpha(0.6f);
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            v.setAlpha(1f);
        }

        return false;
    }
}
