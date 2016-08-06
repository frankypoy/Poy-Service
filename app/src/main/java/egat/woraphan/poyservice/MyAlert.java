package egat.woraphan.poyservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by surasaklaocharoen on 8/6/16.
 */
public class MyAlert {

    public void myDialog(Context context,
                         int intAvata,
                         String strTitle,
                         String strMessage) {
        //เรียก class alert มาใช้งาน
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //set ปุ่ม undo ให้ใช้ไม่ได้ เวลาที่popup มันโชว์แล้ว
        builder.setCancelable(false);
        //เรียก method findAvata
        builder.setIcon(findAvata(intAvata));
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //แสดง popup
        builder.show();
    }

    public int findAvata(int intAvata) {

        int[] resultInts = new int[]{R.drawable.bird48,R.drawable.doremon48,
                R.drawable.kon48,R.drawable.nobita48,R.drawable.rat48};

        return resultInts[intAvata];
    }


}   //Main Class
