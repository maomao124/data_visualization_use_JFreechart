package mao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Project name(项目名称)：数据可视化_JFreechart的使用
 * Package(包名): mao
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/10
 * Time(创建时间)： 15:14
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        for (int i = 1; i < 10000; i++)
        {
            Class<?> aClass = Class.forName("mao.Test" + i);
            Method method = aClass.getMethod("main", String[].class);
            method.invoke(null, (Object) args);
        }
    }
}
