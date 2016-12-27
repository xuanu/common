package common.zeffect.cn.library.observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络变化 监听
 * Created by zeffect on 2016/10/12.
 *
 * @author zeffect
 */
public class NetChangeObserver {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 网络变化回调
     */
    private OnNetChangeListener mOnNetChangeListener;
    /**
     * 广播
     */
    private NetBroadCastReceiver mBroadCastReceiver;

    public NetChangeObserver(Context pContext) {
        this.mContext = pContext;
    }

    /**
     * 开启监听
     */
    public void startListen() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mBroadCastReceiver = new NetBroadCastReceiver();
        mContext.registerReceiver(mBroadCastReceiver, filter);
    }

    /**
     * 结束监听
     */
    public void stopListen() {
        if (mBroadCastReceiver != null) {
            mContext.unregisterReceiver(mBroadCastReceiver);
        }
    }

    /**
     * 设置回调
     *
     * @param pListener 变化 回调
     */
    public void setChangeListener(OnNetChangeListener pListener) {
        this.mOnNetChangeListener = pListener;
    }

    /**
     * 回调
     */
    public interface OnNetChangeListener {
        void connect();

        void disConnect();
    }

    /**
     * 广播接收者
     */
    class NetBroadCastReceiver extends BroadcastReceiver {
        /**
         * 网络管理
         */
        private ConnectivityManager mConnectivityManager;
        /**
         * 网络信息
         */
        private NetworkInfo netInfo;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                netInfo = mConnectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isAvailable()) {  /////////////网络连接//
                    if (mOnNetChangeListener != null) {
                        mOnNetChangeListener.connect();
                    }//           String name = netInfo.getTypeName();//                    if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {//                        /////WiFi网络//                    } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {//                        /////有线网络//                    } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {//                        /////////3g网络//                    }
                } else {  ////////网络断开
                    if (mOnNetChangeListener != null) {
                        mOnNetChangeListener.disConnect();
                    }
                }
            }
        }
    }

}
