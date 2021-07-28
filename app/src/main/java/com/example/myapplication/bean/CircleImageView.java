package com.example.myapplication.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/28/17:21
 * @Description:
 */
public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Paint mPaint;
    public CircleImageView(@NonNull Context context) {
        super(context);
        mPaint=new Paint();
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getCircleBitmap(bitmap);
            mPaint.reset();
            canvas.drawBitmap(b, 0, 0, mPaint);
        }
    }

    /**
     * 获取图片
     *
     * @param bitmap
     * @return
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);//Canvas类可以绘制各种的图形,
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mPaint.setAntiAlias(true);//设置抗锯齿
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//取两层交集区域，显示上层
        canvas.drawBitmap(bitmap, rect, rect, mPaint);//将图片画出来
        return output;
 }
}
