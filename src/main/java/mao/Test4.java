package mao;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Project name(项目名称)：数据可视化_JFreechart的使用
 * Package(包名): mao
 * Class(类名): Test4
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/10
 * Time(创建时间)： 0:24
 * Version(版本): 1.0
 * Description(描述)： 柱状图
 */

public class Test4
{
    /**
     * 得到int随机
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     */
    public static int getIntRandom(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }

    public static void main(String[] args) throws IOException
    {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 2012; i <= 2023; i++)
        {
            dataset.setValue(getIntRandom(200, 7000), "第一中学", String.valueOf(i));
        }

        for (int i = 2012; i <= 2023; i++)
        {
            dataset.setValue(getIntRandom(200, 7000), "第二中学", String.valueOf(i));
        }

        for (int i = 2012; i <= 2023; i++)
        {
            dataset.setValue(getIntRandom(200, 7000), "第四中学", String.valueOf(i));
        }

        for (int i = 2012; i <= 2023; i++)
        {
            dataset.setValue(getIntRandom(200, 7000), "第五中学", String.valueOf(i));
        }

        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 28));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD, 22));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.BOLD, 20));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        //参数1 title 标题
        //参数2　dataset 数据集
        //参数3  是否开启图例
        //参数4  是否开启工具栏
        //参数5  是否开启url跳转
        JFreeChart chart = ChartFactory.createBarChart("xx县各学校学生人数变化", "年", "人数", dataset);
        //生成一张图表的图片文件

        String path = "./chart4.png";

        ChartUtils.saveChartAsPNG(new File(path), chart, 1280, 720);

        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(path));
        JLabel jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon(bufferedImage));

        JFrame jFrame = new JFrame("图片");                                   //初始化顶层面板
        jFrame.setSize(1280, 900);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;       //获取屏幕宽度
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;     //获取屏幕高度
        jFrame.setLocation(screenWidth / 2 - jFrame.getWidth() / 2, screenHeight / 2 - jFrame.getHeight() / 2);  //位于屏幕中央

        jFrame.add(jLabel);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(1);
            }
        });
    }
}
