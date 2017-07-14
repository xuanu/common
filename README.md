# Common
收集一点公共类
目前已有工具类：
- 存储：PrefercenesUtils
- 日志: LogUtils
- 权限申请：PermissionUtils
- 单位转换：DensityUtils，ScreenUtils
- MD5加密： MD5Crypto
- SD卡检测  StorageUtils
- 三个OBSERVER:HomeKeyObserver,MyFileObserver,NetChangeObserver
- 选择图片： ChoseImage
- BitmapUtils,TimeUtils, 常量类ConstUtils,单位转换ConvertUtils,关闭流CloseUtils,字符相关StringUtils copy自：https://github.com/Blankj/AndroidUtilCode
- 带圈字符：IndexUtils
- 防止内存泄露的AsyncTask，参考：[ Android实现弱引用AsyncTask，将内存泄漏置之度外](http://blog.csdn.net/u013718120/article/details/53032986)修改，使用方法:`new WeakAsyncTask<Void, Void, Void, MainActivity>(this) {……}.execute();`