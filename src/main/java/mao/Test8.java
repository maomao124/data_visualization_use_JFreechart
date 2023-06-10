package mao;

import org.jfree.chart.*;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;

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
 * Class(类名): Test8
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/10
 * Time(创建时间)： 15:05
 * Version(版本): 1.0
 * Description(描述)： 时序图
 */

public class Test8
{
    public static void main(String[] args) throws IOException
    {

        // 创建数据
        TimeSeries series = new TimeSeries("数据");
        Second current = new Second();
        double value = 100.0;
        for (int i = 0; i < 4000; i++)
        {
            value = value + Math.random() - 0.5;
            series.add(current, value);
            current = (Second) current.next();
        }
        XYDataset dataset = new TimeSeriesCollection(series);


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
        JFreeChart chart = ChartFactory.createTimeSeriesChart("时序图",
                "x", "y", dataset);
        //生成一张图表的图片文件

        String path = "./chart8.png";


        ChartUtils.saveChartAsPNG(new File(path), chart, 1280, 720);

        // 利用awt进行显示
        ChartFrame chartFrame = new ChartFrame("程序", chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
        chartFrame.setSize(1280, 900);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;       //获取屏幕宽度
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;     //获取屏幕高度
        chartFrame.setLocation(screenWidth / 2 - chartFrame.getWidth() / 2, screenHeight / 2 - chartFrame.getHeight() / 2);  //位于屏幕中央

        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(path));
        JLabel jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon(bufferedImage));

        JFrame jFrame = new JFrame("图片");                                   //初始化顶层面板
        jFrame.setSize(1280, 900);
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;       //获取屏幕宽度
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;     //获取屏幕高度
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
