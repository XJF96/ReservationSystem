package com.zhbit.client;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class GGView2 extends View{
    RootActivity activity;
    int COMPONENT_WIDTH;
    int COMPONENT_HEIGHT;
    boolean initflag=false;
    Bitmap[] bma;
    Paint paint;
    int[] drawablesId;
    int currIndex=0;
    boolean workFlag=true;

    public GGView2(Context father, AttributeSet as)
    {
        super(father,as);
        this.drawablesId=new int[]
                {
                        R.drawable.adv5,
                        R.drawable.adv4,
                        R.drawable.adv3,
                };
        bma=new Bitmap[drawablesId.length];
        initBitmaps();
        paint=new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);//消除锯齿

        new Thread()
        {
            public void run()
            {
                while(workFlag)
                {
                    currIndex=(currIndex+1)%drawablesId.length;
                    GGView2.this.postInvalidate();
                    try
                    {
                        Thread.sleep(3000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void initBitmaps()
    {
        Resources res=this.getResources();
        for(int i=0;i<drawablesId.length;i++)
        {
            bma[i]= BitmapFactory.decodeResource(res, drawablesId[i]);
        }
    }

    public void onDraw(Canvas canvas)
    {
        if(!initflag)
        {
            COMPONENT_WIDTH=this.getWidth();//获取view的宽度
            COMPONENT_HEIGHT=this.getHeight();//获取view的高度
            initflag=true;
        }

        int picWidth=bma[currIndex].getWidth();
        int picHeight=bma[currIndex].getHeight();

        int startX=(COMPONENT_WIDTH-picWidth)/2;
        int startY=(COMPONENT_HEIGHT-picHeight)/2;

        //绘制背景色
        canvas.drawARGB(255, 255,204, 128);
        canvas.drawBitmap(bma[currIndex], startX,startY, paint);

    }
}
