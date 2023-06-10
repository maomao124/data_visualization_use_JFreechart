package mao;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
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
 * Class(类名): Test11
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/10
 * Time(创建时间)： 16:06
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test11
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
        // 创建数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(getIntRandom(20, 100), "A", "Ⅰ");
        dataset.addValue(getIntRandom(20, 100), "A", "Ⅱ");
        dataset.addValue(getIntRandom(20, 100), "A", "Ⅲ");
        dataset.addValue(getIntRandom(20, 100), "A", "Ⅳ");
        dataset.addValue(getIntRandom(20, 100), "B", "Ⅰ");
        dataset.addValue(getIntRandom(20, 100), "B", "Ⅱ");
        dataset.addValue(getIntRandom(20, 100), "B", "Ⅲ");
        dataset.addValue(getIntRandom(20, 100), "B", "Ⅳ");

        // 创建CategoryPlot对象
        CategoryPlot plot = new CategoryPlot();

        // 添加第一个数据集并渲染为line
        CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setDataset(0, dataset);
        plot.setRenderer(0, lineRenderer);

        // 添加第二个数据集并渲染为线条bar
        CategoryItemRenderer baRenderer = new BarRenderer();
        plot.setDataset(1, dataset);
        plot.setRenderer(1, baRenderer);

        // 设置坐标轴
        plot.setDomainAxis(new CategoryAxis("Time"));
        plot.setRangeAxis(new NumberAxis("Value"));

        // 创建JFreeChart对象
        JFreeChart chart = new JFreeChart(plot);


        //生成一张图表的图片文件
        String path = "./chart11.png";


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
