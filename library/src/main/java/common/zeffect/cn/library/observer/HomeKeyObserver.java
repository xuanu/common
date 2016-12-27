package common.zeffect.cn.library.observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Home键盘点击监听
 *
 * @author zeffect
 */
public class HomeKeyObserver {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 过滤器
     */
    private IntentFilter mIntentFilter;
    /**
     * 回调
     */
    private OnHomeKeyListener mOnHomeKeyListener;
    /**
     * 广播
     */
    private HomeKeyBroadcastReceiver mHomeKeyBroadcastReceiver;

    public HomeKeyObserver(Context context) {
        this.mContext = context;
    }

    /**
     * 注册广播接收者
     */
    public void startListen() {
        mIntentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mHomeKeyBroadcastReceiver = new HomeKeyBroadcastReceiver();
        mContext.registerReceiver(mHomeKeyBroadcastReceiver, mIntentFilter);
        System.out.println("----> 开始监听");
    }

    /**
     * 取消广播接收者
     **/
    public void stopListen() {
        if (mHomeKeyBroadcastReceiver != null) {
            mContext.unregisterReceiver(mHomeKeyBroadcastReceiver);
            System.out.println("----> 停止监听");
        }
    }

    /**
     * 对外暴露接口
     *
     * @param homeKeyListener 按键回调接口
     **/
    public void setHomeKeyListener(OnHomeKeyListener homeKeyListener) {
        mOnHomeKeyListener = homeKeyListener;
    }

    /**
     * 回调接口
     **/
    public interface OnHomeKeyListener {
        /**
         * 按下
         */
        public void onHomeKeyPressed();

        /**
         * 长按
         */
        public void onHomeKeyLongPressed();
    }

    /**
     * 广播接收者
     **/
    class HomeKeyBroadcastReceiver extends BroadcastReceiver {
        /**
         * 未知
         */
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        /**
         * 按下Home键
         **/
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
        /**
         * 长按Home键
         **/
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null && mOnHomeKeyListener != null) {
                    if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                        mOnHomeKeyListener.onHomeKeyPressed();
                    } else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                        mOnHomeKeyListener.onHomeKeyLongPressed();
                    }
                }
            }
        }
    }

}