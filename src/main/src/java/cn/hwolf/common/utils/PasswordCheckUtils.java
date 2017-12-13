package cn.hwolf.common.utils;

/**
 * 密码校验工具，参考
 * http://blog.csdn.net/dixialieren/article/details/48517009
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/3.
 */
public class PasswordCheckUtils {
    /**
     * 长度大于 6 位
     * @param password
     * @return
     */
    public static boolean checkPwdLength(String password){
        String regex = "^.{6,}$";
        return password.matches(regex);
    }

    /**
     * 不允许含有空格
     * @param password
     * @return
     */
    public static boolean checkPwdValue(String password){
        String regex = "^.*[\\s]+.*$";
        return !password.matches(regex);
    }
}
//1. 必须包含数字、字母、特殊字符 三种
//
//        2. 长度至少8位
//
//        3. 不能包含3位及以上相同字符的重复【eg：x111@q& xxxx@q&1】
//
//        4 不能包含3位及以上字符组合的重复【eg：ab!23ab!】
//
//        5. 不能包含3位及以上的正序及逆序连续字符【eg：123%@#aop %@#321ao efg3%@#47 3%@#47gfe】
//
//        6. 不能包含空格、制表符、换页符等空白字符
//
//        7. 键盘123456789数字对应的正序逆序特殊字符：eg：12#$%pwtcp(#$%(345对应的特殊字符#$%，仍视作连续))
//
//        8. 支持的特殊字符范围：^$./,;:’!@#%&*|?+()[]{}

