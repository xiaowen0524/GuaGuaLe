package com.bawei.asus.guaguale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by asus on 2016/11/1.
 */
public class ViewDemo extends View {
    private Bitmap fgBitmap, frontBitmap;// 鍓嶆櫙姗＄毊鎿︾殑Bitmap鍜岃儗鏅垜浠簳鍥剧殑Bitmap

    private Canvas mCanvas;// 缁樺埗姗＄毊鎿﹁矾寰勭殑鐢诲竷

    private Paint mPaint;// 姗＄毊妾矾寰勭敾绗�

    private Path mPath;// 姗＄毊鎿︾粯鍒惰矾寰�

    private float x, y;

    public ViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewDemo(Context context) {
        super(context);
    }

    private void init() {
        // 瀹炰緥鍖栬矾寰勫璞�
        mPath = new Path();

        // 瀹炰緥鍖栫敾绗斿苟寮�鍚叾鎶楅敮榻垮拰鎶楁姈鍔�
        mPaint = new Paint();
        // 闃查敮榻�
        mPaint.setAntiAlias(true);
        // 闃叉姈鍔�
        mPaint.setDither(true);
        // 璁剧疆娣峰悎妯″紡涓篋ST_IN
        mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        // 璁剧疆鐢荤瑪椋庢牸涓烘弿杈�
        mPaint.setStyle(Paint.Style.STROKE);
        // 璁剧疆璺緞缁撳悎澶勬牱寮�
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 璁剧疆绗旇Е绫诲瀷
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 璁剧疆鎻忚竟瀹藉害
        mPaint.setStrokeWidth(50);

        // 鐢熸垚鍓嶆櫙鍥綛itmap
        fgBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_4444);

        // 灏嗗叾娉ㄥ叆鐢诲竷
        mCanvas = new Canvas(fgBitmap);
        // 鎷垮埌鐏拌壊鑳屾櫙鍥�
        frontBitmap = CreateBitmap(Color.GRAY, getWidth(), getHeight());
        // 缁樺埗鐏拌壊鑳屾櫙鍥�
        mCanvas.drawBitmap(frontBitmap, 0, 0, null);
    }

    /** 鑾峰彇浼犲叆棰滆壊锛岄珮绔紝瀹藉害鐨凚itmap */
    public Bitmap CreateBitmap(int color, int width, int height) {
        int[] rgb = new int[width * height];

        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = color;
        }

        return Bitmap.createBitmap(rgb, width, height, Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCanvas == null) {
            init();
        }
        // 缁樺埗鍓嶆櫙
        canvas.drawBitmap(fgBitmap, 0, 0, null);
		/*
		 * 杩欓噷瑕佹敞鎰廲anvas鍜宮Canvas鏄袱涓笉鍚岀殑鐢诲竷瀵硅薄
		 * 褰撴垜浠湪灞忓箷涓婄Щ鍔ㄦ墜鎸囩粯鍒惰矾寰勬椂浼氭妸璺緞閫氳繃mCanvas缁樺埗鍒癴gBitmap涓�
		 * 姣忓綋鎴戜滑鎵嬫寚绉诲姩涓�娆″潎浼氬皢璺緞mPath浣滀负鐩爣鍥惧儚缁樺埗鍒癿Canvas涓婏紝鑰屽湪涓婇潰鎴戜滑鍏堝湪mCanvas涓婄粯鍒朵簡涓�х伆鑹�
		 * 涓よ�呬細鍥犱负DST_IN妯″紡鐨勮绠楀彧鏄剧ず涓�х伆锛屼絾鏄洜涓簃Path鐨勯�忔槑锛岃绠楃敓鎴愮殑娣峰悎鍥惧儚涔熶細鏄�忔槑鐨�
		 * 鎵�浠ユ垜浠細寰楀埌鈥滄鐨摝鈥濈殑鏁堟灉
		 */
        mCanvas.drawPath(mPath, mPaint);

        super.onDraw(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
		/*
		 * 鑾峰彇褰撳墠浜嬩欢浣嶇疆鍧愭爣
		 */
        x = event.getX();
        y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {// 鎵嬫寚鎺ヨЕ灞忓箷閲嶇疆璺緞
            mPath.reset();
            mPath.moveTo(x, y);
            // 閲嶇粯瑙嗗浘
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {// 鎵嬫寚绉诲姩鏃惰繛鎺ヨ矾寰�
            mPath.lineTo(x, y);
            // 閲嶇粯瑙嗗浘
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
