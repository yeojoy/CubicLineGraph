package me.yeojoy.cubiclinegraph.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yeojoy on 2016. 6. 21..
 */
public class CubicLineView extends View {

    private static final String TAG = CubicLineView.class.getSimpleName();

    private final int MAX_WIDTH;
    private final int MAX_HEIGHT;

    private final int WIDTH_GAP;

    private final int RADIUS = 45;
    private final int HEIGHT = 140;

    private Path cubicPath = new Path();

    private Paint mRenderPaint;

    public CubicLineView(Context context) {
        this(context, null);
    }

    public CubicLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CubicLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        MAX_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
        MAX_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;

        WIDTH_GAP = MAX_WIDTH / 6;

        init();
    }

    private void init() {
        Log.i(TAG, "init()");
        mRenderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaint.setStyle(Paint.Style.FILL);
        mRenderPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw()");

        int halfHeight = MAX_HEIGHT / 2;
        Log.d(TAG, "===============================================================");
        Log.d(TAG, "halfheight : " + halfHeight);
        Log.d(TAG, "===============================================================");

        cubicPath.reset();

        int[] gradeArray = {0, 4, 1, 3, 2, 4, 0};

        cubicPath.moveTo(0, halfHeight - (gradeArray[0] * HEIGHT));

        Log.d(TAG, "X : " + (0) + ", Y : " + ((gradeArray[0] * HEIGHT)));
//        Log.d(TAG, "X : " + (0) + ", Y : " + (halfHeight - (gradeArray[0] * HEIGHT)));
//        for (int i = 1, j = gradeArray.length; i < j; i++) {
//            cubicPath.lineTo(i * WIDTH_GAP, halfHeight - (gradeArray[i] * HEIGHT));
//            Log.d(TAG, "X : " + (i * WIDTH_GAP) + ", Y : " + (halfHeight - (gradeArray[i] * HEIGHT)));
//        }

        float preX, preY;
        float nowX, nowY;
        float nextX, nextY;

        for (int i = 1, j = gradeArray.length; i < j; i++) {

            // 중간 값
            nowX = i * WIDTH_GAP;
            nowY = halfHeight - (gradeArray[i] * HEIGHT);

            // 이전 값 설정
            preX = nowX - RADIUS;
            if (gradeArray[i - 1] > gradeArray[i]) {
                // 전보다 지금 값이 작을 때 음의 방향
                preY = nowY - (((gradeArray[i - 1] - gradeArray[i]) * HEIGHT * RADIUS) / WIDTH_GAP);
            } else if (gradeArray[i - 1] < gradeArray[i]) {
                // 전보다 지금 값이 클 때 양의 방향
                preY = nowY - (((gradeArray[i - 1] - gradeArray[i]) * HEIGHT * RADIUS) / WIDTH_GAP);
            } else {
                // 같을 때
                preY = nowY;
            }

            if (i == 1) {
                cubicPath.lineTo(preX, preY);
            }

            // 다음 값
            nextX = nowX + RADIUS;
            if (i < (j - 1)) {
                if (gradeArray[i] > gradeArray[i + 1]) {
                    // 지금 값이 다음값보다 클 때 - 방향
                    nextY = nowY + (((gradeArray[i] - gradeArray[i + 1]) * HEIGHT * RADIUS) / WIDTH_GAP);
                } else if (gradeArray[i] < gradeArray[i + 1]) {
                    // 지금 값이 다음값보다 작을 때 + 방향
                    nextY = nowY + (((gradeArray[i] - gradeArray[i + 1]) * HEIGHT * RADIUS) / WIDTH_GAP);
                } else {
                    // 수평
                    nextY = nowY;
                }
            } else {
                cubicPath.lineTo(i * WIDTH_GAP, halfHeight - (gradeArray[i] * HEIGHT));
                break;
            }

            Log.d(TAG, "preX : " + preX + ", preY : " + (halfHeight - preY));
            Log.d(TAG, "nowX : " + nowX + ", nowY : " + (halfHeight - nowY));
            Log.d(TAG, "nextX : " + nextX + ", nextY : " + (halfHeight - nextY));
//            Log.d(TAG, "preX : " + preX + ", preY : " + preY);
//            Log.d(TAG, "nowX : " + nowX + ", nowY : " + nowY);
//            Log.d(TAG, "nextX : " + nextX + ", nextY : " + nextY);
            Log.d(TAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            cubicPath.cubicTo(preX, preY, nowX, nowY, nextX, nextY);
        }

        canvas.drawPath(cubicPath, mRenderPaint);
    }
}
