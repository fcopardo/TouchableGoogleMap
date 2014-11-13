package com.grizzly.TouchableGoogleMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Fco on 11/12/14.
 */
public class TouchableWrapper extends FrameLayout {
    /**
     * Class members.
     */
    private boolean _mapIsTouched;

    public TouchableWrapper(Context context) {
        super(context);
    }

    public TouchableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isContentTouched(){
        return _mapIsTouched;
    }

    /**
     * Detects the touch events inside the layout.
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                _mapIsTouched = true;
                break;
            case MotionEvent.ACTION_UP:
                _mapIsTouched = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
