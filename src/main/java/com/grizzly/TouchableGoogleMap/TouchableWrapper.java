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
    private OnDragListener mOnDragListener;
    private boolean captureTouches = false;

    public TouchableWrapper(Context context) {
        super(context);
    }

    public TouchableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnDragListener {
        public void onDrag(MotionEvent motionEvent);
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
        if (mOnDragListener != null) {
            mOnDragListener.onDrag(event);
        }
        if(captureTouches) return true;
        return super.dispatchTouchEvent(event);
    }

    /**
     * Setter for custom listener
     * @param mOnDragListener
     */
    public void setOnDragListener(OnDragListener mOnDragListener) {
        this.mOnDragListener = mOnDragListener;
    }

    /**
     * Setter for the capture touch flag. Setting this to true will disable touch interaction with the wrapper's contents.
     * @param bol
     */
    public void setCaptureTouches(boolean bol){
        captureTouches = bol;
    }

    public boolean isCaptureTouches(){
        return captureTouches;
    }
}
