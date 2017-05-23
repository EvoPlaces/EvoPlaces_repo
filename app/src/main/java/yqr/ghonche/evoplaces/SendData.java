package yqr.ghonche.evoplaces;

/**
 * Created by root on 5/21/17.
 */

import android.content.Context;
import android.util.Log;

public class SendData {

    private HttpConnectionManager mHttpConnectionManager;
    private Runnable sendDataRunnable;
    private Context appContext;

    public SendData(Context context) {
        mHttpConnectionManager = new HttpConnectionManager("http://198.143.181.24:3000/api");
        appContext = context;
    }


    protected void send() {


                if (mHttpConnectionManager.isOnline(appContext)) {

                    String singleRowOfDb;

                    singleRowOfDb = MainActivity1.dataBaseManager.getDBrowinJson();

                    if (singleRowOfDb!= null )
                        Log.i("SingleRowDB",singleRowOfDb);


                    if (singleRowOfDb!= null )
                        mHttpConnectionManager.
                                postDataHttpUrlConnection("http://198.143.181.24:3000/api"
                                        ,singleRowOfDb);

                }

            }

}
