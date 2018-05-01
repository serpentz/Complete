package com.carbon.complete.Utils;

import com.carbon.complete.R;

/**
 * Created by archlinux on 5/1/18.
 */

public enum WelcomePagerEnum {
    SCREEN1(R.string.Hello, R.layout.fragment_test),
    SCREEN2(R.string.Hello, R.layout.fragment_test),
    SCREEN3(R.string.Hello, R.layout.fragment_test);

    private int titleResourceId;
    private int layoutResourceId;

    WelcomePagerEnum(int titleResourceId, int layoutResourceId) {
        this.titleResourceId = titleResourceId;
        this.layoutResourceId = layoutResourceId;
    }

    public int getTitleResourceId() {
        return titleResourceId;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }
}