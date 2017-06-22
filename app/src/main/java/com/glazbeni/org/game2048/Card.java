package com.glazbeni.org.game2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Glazbeni on 17.6.15.
 */

public class Card extends FrameLayout {

    private int num = 0;
    private TextView label;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (getNum() <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
    }

    public Card(@NonNull Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    public boolean equals(Card obj) {
        return getNum() == obj.getNum();
    }
}
