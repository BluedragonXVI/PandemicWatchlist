package com.example.pandemicwatchlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StarView extends View {

    // get filled stars bitmap image
    Resources resources = getResources();
    Bitmap filled_stars = BitmapFactory.decodeResource(resources, R.drawable.stars_filled);

    public StarView(Context context) {
        super(context);
        init();
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint = new Paint();
        canvas.drawBitmap(filled_stars, 0,0, paint);
    }
}
