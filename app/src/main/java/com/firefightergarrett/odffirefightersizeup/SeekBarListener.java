package com.firefightergarrett.odffirefightersizeup;

import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by brandonsov on 8/29/16.
 * Sets the text view reflecting the seekBar progress value.
 */

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    TextView mProgressTextView;
    String[] mBrackets;

    public SeekBarListener(TextView progressTextView, String[] brackets) {
        mProgressTextView = progressTextView;
        mBrackets = brackets;
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // TODO mProgressTextView.setText(mBrackets[ ***TODO DESIGN MATH EQUATION FOR SELECTING VALUES***]);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {
        //TODO DELETE AFTER DESIGNING MATH EQUATION AS STATED ABOVE
        for (int i = 0; i < mBrackets.length; i++) {
            if (seekBar.getProgress() < (i+1)*(100.0/mBrackets.length)) {
                mProgressTextView.setText(mBrackets[i]);
                return;
            }
        }
    }

}
