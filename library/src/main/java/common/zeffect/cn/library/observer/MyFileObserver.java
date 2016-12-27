package common.zeffect.cn.library.observer;

import android.content.Context;
import android.os.FileObserver;
import android.text.TextUtils;

import java.io.File;

/**
 * 文件件变化监听
 * <p/>
 * Created by 郑治玄 on 2016/6/13.
 *
 * @author 郑治玄
 */
public class MyFileObserver {
    /**
     * 文件监听路径
     **/
    private String mPath = "";
    /**
     * 文件监听器
     **/
    private TempFileObserver tempFileObserver;
    /**
     * 文件变化回调
     **/
    private OnFileChangeListener mFileChangeListener;

    public MyFileObserver(Context context, String filePath) {
        this.mPath = filePath;
    }

    public MyFileObserver(Context context, File file) {
        if (file != null) {
            this.mPath = file.getAbsolutePath();
        }
    }

    /**
     * 开始监听文件变化
     */
    public void startListen() {
        if (TextUtils.isEmpty(mPath)) {
            return;
        }
        tempFileObserver = new TempFileObserver(mPath);
        tempFileObserver.startWatching();
    }

    /***
     * 结束监听文件事件
     */
    public void stopListen() {
        if (tempFileObserver != null) {
            tempFileObserver.stopWatching();
            tempFileObserver = null;
        }
    }

    /***
     * 设备文件变化 回调
     *
     * @param onFileChangeListener 文件变化回调
     */
    public void setFileChangeListenre(OnFileChangeListener onFileChangeListener) {
        this.mFileChangeListener = onFileChangeListener;
    }

    /**
     * 文件变化，监听。
     */
    class TempFileObserver extends android.os.FileObserver {

        TempFileObserver(String path) {
            super(path);
        }

        @Override
        public void onEvent(int event, String path) {
            final int action = event & android.os.FileObserver.ALL_EVENTS;
            switch (action) {
                case android.os.FileObserver.ACCESS:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileAccess(mPath);
                    }
                    break;
                case FileObserver.ATTRIB:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileChange(mPath);
                    }
                    break;
                case FileObserver.CLOSE_NOWRITE:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileAccess(mPath);
                    }
                    break;
                case FileObserver.CLOSE_WRITE:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileChange(mPath);
                    }
                    break;
                case FileObserver.DELETE_SELF:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileDel(mPath);
                    }
                    break;
                case FileObserver.MOVE_SELF:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileMove(mPath);
                    }
                    break;
                case FileObserver.MOVED_FROM:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileMove(mPath);
                    }
                    break;
                case FileObserver.MOVED_TO:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileMove(mPath);
                    }
                    break;
                case android.os.FileObserver.DELETE:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileDel(mPath);
                    }
                    break;
                case android.os.FileObserver.OPEN:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileOpen(mPath);
                    }
                    break;
                case android.os.FileObserver.MODIFY:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileChange(mPath);
                    }
                    break;
                case android.os.FileObserver.CREATE:
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileCreate(mPath);
                    }
                    break;
            }
        }
    }

    /**
     * 文件变化接口
     */
    public interface OnFileChangeListener {
        /**
         * 文件被修改
         *
         * @param path 文件监听路径
         **/
        void onFileChange(String path);

        void onFileCreate(String path);

        void onFileMove(String path);

        void onFileDel(String path);

        void onFileOpen(String path);

        void onFileAccess(String path);
    }

}
