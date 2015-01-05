package com.guangzhou.gov.net.view;

import android.app.Dialog;
import android.content.Context;

/**
 * 方便添加dialog 不喜欢可以干掉
 * 
 * @ClassName: DialogManager
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class DialogManager {
    public static Dialog getProgressMsgDialog(Context context, String msg)
    {
        Dialog dialog = new PrjProgressMsgDialog.Builder(context).setTextContent(msg).create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
