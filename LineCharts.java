import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
public class LineCharts extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public LineCharts(String s) {
        super(s);
        setContentPane(createDemoLine());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
//    public static void main(String[] args) {
//        LineCharts fjc = new LineCharts("折线图");
//        fjc.pack();
//        RefineryUtilities.centerFrameOnScreen(fjc);
//        fjc.setVisible(true);
//    }
    // 生成显示图表的面板
    public static JPanel createDemoLine() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }
    // 生成图表主对象JFreeChart
    public static JFreeChart createChart(DefaultCategoryDataset linedataset) {
        // 定义图表对象
        JFreeChart chart = ChartFactory.createLineChart(Main.PMNameToFind+"使用率", //折线图名称
                "时间", // 横坐标名称
                "使用率", // 纵坐标名称
                linedataset, // 数据
                PlotOrientation.VERTICAL, // 水平显示图像
                true, // include legend
                true, // tooltips
                false // urls
        );
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinesVisible(true); //是否显示格子线
        plot.setBackgroundAlpha(0.3f); //设置背景透明度
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setUpperBound(1);//纵轴最大值
        rangeAxis.setLabelAngle(Math.PI / 2.0);

        return chart;
    }
    // 生成数据
    public static DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
        // 各曲线名称
        String series1 = Main.G1MetaGame;
        String series2 = Main.G2MetaGame;
        String series3 = Main.G3MetaGame;
        String series4 = Main.G4MetaGame;
        String series5 = Main.G5MetaGame;
        String series6 = Main.G6MetaGame;
        String series7 = Main.G7MetaGame;
        // 横轴名称(列名称)
        for(int i = 0; i < Main.G1PM.size(); i++){
            linedataset.addValue(Main.G1PM.get(i).getUsage(), series1, Main.G1PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G2PM.size(); i++){
            linedataset.addValue(Main.G2PM.get(i).getUsage(), series2, Main.G2PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G3PM.size(); i++){
            linedataset.addValue(Main.G3PM.get(i).getUsage(), series3, Main.G3PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G4PM.size(); i++){
            linedataset.addValue(Main.G4PM.get(i).getUsage(), series4, Main.G4PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G5PM.size(); i++){
            linedataset.addValue(Main.G5PM.get(i).getUsage(), series5, Main.G5PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G6PM.size(); i++){
            linedataset.addValue(Main.G6PM.get(i).getUsage(), series6, Main.G6PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        for(int i = 0; i < Main.G7PM.size(); i++){
            linedataset.addValue(Main.G7PM.get(i).getUsage(), series7, Main.G7PM.get(i).getDate());//数值，曲线名称，横轴名称（列名称）
        }
        return linedataset;
    }
}