package com.wildsmith.material.views;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageButton;

import com.wildsmith.material.R;
import com.wildsmith.material.utils.ResourceHelper;

public class FloatingActionButton extends ImageButton {

    private Interpolator mInterpolator;

    private PathInterpolator mPathInterpolator;

    private Bitmap mBitmap;

    private Paint mDrawablePaint;

    private float currentY;

    private boolean mHidden;

    private boolean isAttached;

    private int mScreenHeight;

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mInterpolator = new AccelerateDecelerateInterpolator();
        mPathInterpolator = new PathInterpolator(0.4f, 0f, 0.2f, 1f);

        mScreenHeight = getScreenHeight(context);

        final float elevationPx = ViewHelper.getPxFromDP(getResources(), 1);
        setElevation(elevationPx);

        ViewOutlineProvider outlineProvider = new FloatingActionButtonOutline();
        setOutlineProvider(outlineProvider);
        setClipToOutline(true);

        setScaleType(ScaleType.FIT_CENTER);
        setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, R.drawable.fab_button_states));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            mBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        }

        if (mBitmap == null) {
            return;
        }

        canvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2, (getHeight() - mBitmap.getHeight()) / 2, mDrawablePaint);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        mBitmap = ((BitmapDrawable) drawable).getBitmap();
    }

    public void setImageDrawableColor(int color) {
        if (mDrawablePaint == null) {
            mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        mDrawablePaint.setColor(color);
    }

    private int getScreenHeight(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public void hide() {
        if (mHidden) {
            return;
        }

        currentY = getY();

        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "Y", mScreenHeight);
        animator.setInterpolator(mInterpolator);
        animator.start();

        mHidden = true;
    }

    public void show() {
        if (mHidden == false) {
            return;
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "Y", currentY);
        animator.setInterpolator(mPathInterpolator);
        animator.start();

        mHidden = false;
    }

    public boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean isAttached) {
        this.isAttached = isAttached;
    }

    private class FloatingActionButtonOutline extends ViewOutlineProvider {

        @Override
        public void getOutline(View view, Outline outline) {
            final float elevationPx = ViewHelper.getPxFromDP(getResources(), 1);
            final int size = (int) ResourceHelper.getDimensionPixelSize(R.dimen.fab_size);
            outline.setOval(0, 0, size - (int) elevationPx, size - (int) elevationPx);
        }
    }
}