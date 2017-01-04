package common.zeffect.cn.library.utils;

/**
 * 生成序号
 * <p>
 * 很菜的一个功能
 * Created by zeffect on 2017/1/4.
 *
 * @author zzx
 */

public class IndexUtils {
    /**
     * 生成带圈序号0-20，其它显示：(x)
     *
     * @param number 数字
     * @return 带圈字符
     */
    public static String toEncircleString(int number) {//
        String retuString = "";
        switch (number) {//⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩ ⑪ ⑫ ⑬ ⑭ ⑮ ⑯ ⑰ ⑱ ⑲ ⑳
            case 0:
                retuString = "⓪";
                break;
            case 1:
                retuString = "①";
                break;
            case 2:
                retuString = "②";
                break;
            case 3:
                retuString = "③";
                break;
            case 4:
                retuString = "④";
                break;
            case 5:
                retuString = "⑤";
                break;
            case 6:
                retuString = "⑥";
                break;
            case 7:
                retuString = "⑦";
                break;
            case 8:
                retuString = "⑧";
                break;
            case 9:
                retuString = "⑨";
                break;
            case 10:
                retuString = "⑩";
                break;
            case 11:
                retuString = "⑪";
                break;
            case 12:
                retuString = "⑫";
                break;
            case 13:
                retuString = "⑬";
                break;
            case 14:
                retuString = "⑭";
                break;
            case 15:
                retuString = "⑮";
                break;
            case 16:
                retuString = "⑯";
                break;
            case 17:
                retuString = "⑰";
                break;
            case 18:
                retuString = "⑱";
                break;
            case 19:
                retuString = "⑲";
                break;
            case 20:
                retuString = "⑳";
                break;
            default:
                retuString = "(" + number + ")";
        }
        return retuString;
    }
}
