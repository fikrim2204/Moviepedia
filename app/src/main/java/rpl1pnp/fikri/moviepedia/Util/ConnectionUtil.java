package rpl1pnp.fikri.moviepedia.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtil {
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return actNetworkInfo != null && actNetworkInfo.isConnectedOrConnecting();
    }
}
