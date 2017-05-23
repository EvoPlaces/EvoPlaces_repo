package yqr.ghonche.evoplaces;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

/**
 * Created by root on 5/22/17.
 */

public class ShowProgress {


    public static int pValue=0;
    public static void showProgress(Context context , String progressTxt , final int sleepDuration ){

        final ProgressDialog pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(false);
        pd.setMessage(progressTxt);

        pValue = 0;


        Drawable drawable = new ProgressBar(context).getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent),
                PorterDuff.Mode.SRC_IN);
        pd.setIndeterminateDrawable(drawable);
        pd.show();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(pValue<=100){
                    pd.setProgress(pValue);
                    try {
                        Thread.sleep(sleepDuration);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    pValue++;
                }
                pd.cancel();
            }
        }).start();


    }//method
}//class
