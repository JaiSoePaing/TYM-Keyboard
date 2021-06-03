package com.soepaing.tymkeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;

class MyKeyboard extends Keyboard {
    private Key spaceKey;

    MyKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        // TODO Auto-generated constructor stub
    }

    MyKeyboard(Context context, int layoutTemplateResId,
               CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns,
                horizontalPadding);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
                                   XmlResourceParser parser) {

        Key key = new MyKey(res, parent, x, y, parser);
        if (key.codes[0] == 32) {
            spaceKey = key;
        }
        return key;
    }

    private static class MyKey extends Key {
        // LatinKey code...
        MyKey(Resources res, Row parent, int x, int y,
              XmlResourceParser parser) {
            super(res, parent, x, y, parser);
        }

        private final static int[] KEY_STATE_NORMAL_ON = {
                android.R.attr.state_checkable, android.R.attr.state_checked};

        private final static int[] KEY_STATE_PRESSED_ON = {
                android.R.attr.state_pressed, android.R.attr.state_checkable,
                android.R.attr.state_checked};

        private final static int[] KEY_STATE_NORMAL_OFF = {android.R.attr.state_checkable};

        private final static int[] KEY_STATE_PRESSED_OFF = {
                android.R.attr.state_pressed, android.R.attr.state_checkable};

        private final static int[] KEY_STATE_FUNCTION = {android.R.attr.state_single};

        private final static int[] KEY_STATE_FUNCTION_PRESSED = {
                android.R.attr.state_pressed, android.R.attr.state_single};

        private final static int[] KEY_STATE_NORMAL = {};

        private final static int[] KEY_STATE_PRESSED = {android.R.attr.state_pressed};

        @Override
        public int[] getCurrentDrawableState() {
            int[] states = KEY_STATE_NORMAL;

            if (on) {
                if (pressed) {
                    states = KEY_STATE_PRESSED_ON;
                } else {
                    states = KEY_STATE_NORMAL_ON;
                }
            } else {
                if (sticky) {
                    if (pressed) {
                        states = KEY_STATE_PRESSED_OFF;
                    } else {
                        states = KEY_STATE_NORMAL_OFF;
                    }
                } else if (modifier) {
                    if (pressed) {
                        states = KEY_STATE_FUNCTION_PRESSED;
                    } else {
                        states = KEY_STATE_FUNCTION;
                    }
                } else {
                    if (pressed) {
                        states = KEY_STATE_PRESSED;
                    }
                }
            }
            return states;
        }
    }
}