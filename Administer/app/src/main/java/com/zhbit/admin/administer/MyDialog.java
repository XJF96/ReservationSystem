package com.zhbit.admin.administer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class MyDialog extends Dialog
{
    int id;
    public MyDialog(Context context, int id)
    {
        super(context,R.style.FullHeightDialog);
        this.id=id;
    }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        this.setContentView(id);

    }

    @Override
    public String toString()
    {
        return "MyDialog";
    }
}
