package mao;

import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.time.Month;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
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
import java.text.SimpleDateFormat;

/**
 * Project name(项目名称)：数据可视化_JFreechart的使用
 * Package(包名): mao
 * Class(类名): Test9
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/10
 * Time(创建时间)： 15:29
 * Version(版本): 1.0
 * Description(描述)： 时间序列图
 */

public class Test9
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
        //A网站的访问量统计
        TimeSeries timeSeries1 = new TimeSeries("A-访问量");
        // 添加数据
        for (int i = 1; i <= 12; i++)
        {
            timeSeries1.add(new Month(i, 2022), getIntRandom(40000, 200000));
        }

        //A网站的UV统计
        TimeSeries timeSeries2 = new TimeSeries("A-UV");
        //添加数据
        for (int i = 1; i <= 12; i++)
        {
            timeSeries2.add(new Month(i, 2022), getIntRandom(5000, 80000));
        }

        //B网站的访问量统计
        TimeSeries timeSeries3 = new TimeSeries("B-访问量");
        // 添加数据
        for (int i = 1; i <= 12; i++)
        {
            timeSeries3.add(new Month(i, 2022), getIntRandom(40000, 200000));
        }

        //B网站的UV统计
        TimeSeries timeSeries4 = new TimeSeries("B-UV");
        //添加数据
        for (int i = 1; i <= 12; i++)
        {
            timeSeries4.add(new Month(i, 2022), getIntRandom(5000, 80000));
        }

        //定义时间序列的集合
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries1);
        dataset.addSeries(timeSeries2);
        dataset.addSeries(timeSeries3);
        dataset.addSeries(timeSeries4);


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
        JFreeChart chart = ChartFactory.createTimeSeriesChart("2022年度A,B网站访问统计",
                "时间", "访问量", dataset);

        XYPlot plot = (XYPlot) chart.getPlot();

        //设置曲线是否显示数据点
        XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        //xylinerenderer.setBaseShapesVisible(true);
        xylinerenderer.setDefaultShapesVisible(true);

        //设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setDefaultItemLabelsVisible(true);
        xyitem.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                TextAnchor.BASELINE_CENTER));
        xyitem.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setDefaultItemLabelFont(new Font("黑体", Font.BOLD, 15));
        plot.setRenderer(xyitem);

        //生成一张图表的图片文件
        String path = "./chart9.png";


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
