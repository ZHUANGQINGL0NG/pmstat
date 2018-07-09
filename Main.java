import org.jfree.ui.RefineryUtilities;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    static String PMNameToFind = "";//要查询的pm名字

    //G1常量，G1-6使用同样的Month，Date
    final static int G1Months = 44;//smogon一共出了几个月的统计数据 2014-11|2018-06:44
    final static String G1Date = "2014-11";//起始日期
    final static String G1MetaGame = "gen1ou";
    public static ArrayList<ArrayList<PmBean>> G1 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G1PM = new ArrayList<PmBean>();

    //G2常量
    final static String G2MetaGame = "gen2ou";
    public static ArrayList<ArrayList<PmBean>> G2 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G2PM = new ArrayList<PmBean>();

    //G3常量
    final static String G3MetaGame = "gen3ou";
    public static ArrayList<ArrayList<PmBean>> G3 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G3PM = new ArrayList<PmBean>();

    //G4常量
    final static String G4MetaGame = "gen4ou";
    public static ArrayList<ArrayList<PmBean>> G4 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G4PM = new ArrayList<PmBean>();

    //G5常量
    final static String G5MetaGame = "gen5ou";
    public static ArrayList<ArrayList<PmBean>> G5 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G5PM = new ArrayList<PmBean>();

    //G6常量
    final static String G6MetaGame = "gen6ou";
    public static ArrayList<ArrayList<PmBean>> G6 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G6PM = new ArrayList<PmBean>();

    //G7常量
    final static int G7Months = 20;//smogon一共出了几个月的统计数据,2016-11|2018-06:20
    final static String G7Date = "2016-11";//起始日期
    final static String G7MetaGame = "gen7ou";
    public static ArrayList<ArrayList<PmBean>> G7 = new ArrayList<ArrayList<PmBean>>();
    public static ArrayList<PmBean> G7PM = new ArrayList<PmBean>();

//    //控制读写线程关闭的变量
//    public static boolean flag = true;

    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        long startTime = System.currentTimeMillis();

//        //固定容器大小为10
//        ArrayBlockingQueue<DateInfo> share = new ArrayBlockingQueue<DateInfo>(10);
//        Thread reader = new Thread(new Reader(share));
//        reader.start();
//        Thread writer1 = new Thread(new Writer(share));
//        Thread writer2 = new Thread(new Writer(share));
//        Thread writer3 = new Thread(new Writer(share));
//        Thread writer4 = new Thread(new Writer(share));
//        writer1.start();
//        writer2.start();
//        writer3.start();
//        writer4.start();
//        Thread.sleep(170000);

        SmogonJsonReader smogonJsonReader = new SmogonJsonReader();
        //读取json数据，放到对应世代的二维数组中
        G1 = smogonJsonReader.ReadJson(G1Months, G1Date, G1MetaGame);
        System.out.println("G1");
        G2 = smogonJsonReader.ReadJson(G1Months, G1Date, G2MetaGame);
        System.out.println("G2");
        G3 = smogonJsonReader.ReadJson(G1Months, G1Date, G3MetaGame);
        System.out.println("G3");
        G4 = smogonJsonReader.ReadJson(G1Months, G1Date, G4MetaGame);
        System.out.println("G4");
        G5 = smogonJsonReader.ReadJson(G1Months, G1Date, G5MetaGame);
        System.out.println("G5");
        G6 = smogonJsonReader.ReadJson(G1Months, G1Date, G6MetaGame);
        System.out.println("G6");
        G7 = smogonJsonReader.ReadJson(G7Months, G7Date, G7MetaGame);
        System.out.println("G7");

        JFrame frame=new JFrame();
        //设置窗口名称
        frame.setTitle("Smogon使用率曲线");
        //设置窗口大小
        frame.setSize(540,427);
        //设置窗口位于屏幕中央
        frame.setLocationRelativeTo(null);
        //参数为3时，表示关闭窗口则程序退出
        frame.setDefaultCloseOperation(3);

        //1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
        //用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
        FlowLayout f1=new FlowLayout(FlowLayout.CENTER);
        //frame窗口设置为f1的流式左对齐
        frame.setLayout(f1);
        String[] pmNames = new String[G7.size()];
        for(int i = 0; i < G7.size(); i++){
            pmNames[i] = G7.get(i).get(0).getPmName();
        }
        final JComboBox cb = new JComboBox(pmNames);
        //cb.setBounds(50, 50, 80, 30);
        frame.add(cb);
        final JTextField nameText=new JTextField(PMNameToFind);
        nameText.setPreferredSize(new Dimension(220, 30));
        frame.add(nameText);
        cb.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                PMNameToFind = cb.getSelectedItem().toString();
                nameText.setText(PMNameToFind);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JButton创建一个可点击的按钮，按钮上可显示文本图片
        JButton bu=new JButton("显示曲线");
        //bu.setPreferredSize(new Dimension(80,30));
        frame.add(bu);
        bu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PMNameToFind = nameText.getText();
                SmogonJsonReader smogonJsonReader = new SmogonJsonReader();
                //按照pm名字找到对应世代的数据
                G1PM = findPM(PMNameToFind, G1);
                G2PM = findPM(PMNameToFind, G2);
                G3PM = findPM(PMNameToFind, G3);
                G4PM = findPM(PMNameToFind, G4);
                G5PM = findPM(PMNameToFind, G5);
                G6PM = findPM(PMNameToFind, G6);
                G7PM = findPM(PMNameToFind, G7);
                LineCharts fjc = new LineCharts("折线图");
                fjc.pack();
                RefineryUtilities.centerFrameOnScreen(fjc);
                fjc.setVisible(true);
            }
        });

        //设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
        frame.setVisible(true);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    public static ArrayList<PmBean> findPM(String PMNameToFind, ArrayList<ArrayList<PmBean>> Gens) {

        ArrayList<PmBean> PM = new ArrayList<PmBean>();
        //把要查的pm数组写入PM
        for(int i = 0; i < Gens.size(); i++){
            if(Gens.get(i).get(0).getPmName().equals(PMNameToFind)){
                for(int j = 0; j < Gens.get(i).size(); j++){
                    PM.add(Gens.get(i).get(j));
                }
            }
        }
        return PM;
    }
}
///////////////////////////多线程，总是内存溢出
//class DateInfo{
//    String Date;
//    String Info;
//    DateInfo(){}
//    DateInfo(String Date, String Info){
//        this.Date = Date;
//        this.Info = Info;
//    }
//
//    public String getDate() {
//        return Date;
//    }
//
//    public void setDate(String date) {
//        Date = date;
//    }
//
//    public String getInfo() {
//        return Info;
//    }
//
//    public void setInfo(String info) {
//        Info = info;
//    }
//}
//
//class Reader implements Runnable{
//    private ArrayBlockingQueue<DateInfo> share;
//    Reader(ArrayBlockingQueue share) {
//        this.share = share;
//    }
//    public void run(){
//        try {//try/catch捕获异常
////            ReadJson(Main.G1Months, Main.G1Date, Main.G1MetaGame);
////            ReadJson(Main.G1Months, Main.G1Date, Main.G2MetaGame);
////            ReadJson(Main.G1Months, Main.G1Date, Main.G3MetaGame);
////            ReadJson(Main.G1Months, Main.G1Date, Main.G4MetaGame);
////            ReadJson(Main.G1Months, Main.G1Date, Main.G5MetaGame);
////            ReadJson(Main.G1Months, Main.G1Date, Main.G6MetaGame);
//            ReadJson(Main.G7Months, Main.G7Date, Main.G7MetaGame);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void ReadJson(int Months,String Date, String MetaGame) throws IOException {
//        final String BeginMetaGame = MetaGame;
//        String GMetaGame = BeginMetaGame;
//
//        for (int d = 0; d < Months; d++) {
//            //把json文件转换为JSONObject
//            GMetaGame = BeginMetaGame;
//
//            //异常文件名处理
//            if(GMetaGame.equals("gen1ou") && Date.equals("2014-11")){
//                GMetaGame = "gen1oubeta";
//            } else if (GMetaGame.equals("gen2ou") && Date.equals("2014-11")){
//                GMetaGame = "gen2oubeta";
//            } else if (GMetaGame.equals("gen3ou") && Date.equals("2014-11")){
//                GMetaGame = "gen3oubeta";
//            } else if (GMetaGame.equals("gen3ou") && Date.equals("2014-12")){
//                GMetaGame = "gen3oubeta";
//            } else if (GMetaGame.equals("gen3ou") && Date.equals("2015-01")){
//                GMetaGame = "gen3oubeta";
//            } else if (GMetaGame.equals("gen3ou") && Date.equals("2015-02")){
//                GMetaGame = "gen3oubeta";
//            } else if (GMetaGame.equals("gen7ou") && Date.equals("2017-01")){
//                GMetaGame = "gen7pokebankou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2014-11")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2014-12")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-01")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-02")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-03")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-04")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-05")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-06")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-07")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-08")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-09")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-10")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-11")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-12")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-01")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-02")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-03")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-04")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-05")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-06")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-07")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-08")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-09")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-10")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-11")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-12")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-01")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-02")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-03")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-04")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-05")){
//                GMetaGame = "ou";
//            } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-06")){
//                GMetaGame = "ou";
//            }
//
//            String path = new String("stats-json\\" + Date + "\\chaos\\"+GMetaGame+"-0.json");//E:/Developer/idea/workspace/pmstat/target/classes/stats/2018-04/chaos/gen7ou-0.json
//            path = path.replace("\\", "/");
//            if (path.contains(":")) {
//                path = path.replace("file:/","");
//            }
//            System.out.println(path);
//            InputStream in = SmogonJsonReader.class.getClassLoader().getResourceAsStream(path);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"), 1 * 1024 * 1024);
//            StringBuffer buffer = new StringBuffer();
//            String line; // 用来保存每行读取的内容
//            line = bufferedReader.readLine(); // 读取第一行
//            while (line != null) { // 如果 line 为空说明读完了
//                buffer.append(line); // 将读到的内容添加到 buffer 中
//                line = bufferedReader.readLine(); // 读取下一行
//            }
//            System.gc();
//            bufferedReader.close();
//            String content = buffer.toString();
//            //把json文件的字符串content存入共享空间
//            try {
//                //当生产的字符串数量装满了容器，那么在while里面该食品容器(阻塞队列)会自动阻塞  wait状态 等待消费
//                share.put(new DateInfo(Date, content));
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//            //月份增加一个月
//            Date = subMonth(Date);
//        }
//        //添加结束标志
//        try {
//            share.put(new DateInfo("","end"));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String subMonth(String date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        Date dt = null;
//        try {
//            dt = sdf.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(dt);
//        rightNow.add(Calendar.MONTH, 1);
//        Date dt1 = rightNow.getTime();
//        String reStr = sdf.format(dt1);
//        return reStr;
//    }
//}
//
//class Writer implements Runnable{
//    private ArrayBlockingQueue<DateInfo> share;
//    Writer(ArrayBlockingQueue share) {
//        this.share = share;
//    }
//    public void run() {
//        try {
//            while(Main.flag){
//                //当容器里面的食品数量为空时，那么在while里面该食品容器(阻塞队列)会自动阻塞  wait状态 等待生产
//                if(share.peek() == null){Thread.sleep(1);}
//                else if(share.peek().getInfo().equals("end")){
//                    Main.flag = false;
//                    System.out.println("end");
//                } else {
//                    writeJson();
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private void writeJson() throws InterruptedException, JSONException {
//        System.out.println("writer start");
//        ArrayList<ArrayList<PmBean>> Gens = new ArrayList<ArrayList<PmBean>>();
//
//        DateInfo dateInfo = share.take();
//        String content = dateInfo.getInfo();
//        JSONObject jsonObject = new JSONObject(content);
//
//        //从info中读取战斗场次
//        JSONObject info = jsonObject.getJSONObject("info");
//        int numberOfBattle = info.getInt("number of battles");
//        System.out.println(numberOfBattle);
//        String metagame = info.getString("metagame");
//
//        if(metagame.equals("gen1ou")){
//            Gens = Main.G1;
//        } else if(metagame.equals("gen2ou")){
//            Gens = Main.G2;
//        } else if(metagame.equals("gen3ou")){
//            Gens = Main.G3;
//        } else if(metagame.equals("gen4ou")){
//            Gens = Main.G4;
//        } else if(metagame.equals("gen5ou")){
//            Gens = Main.G5;
//        } else if(metagame.equals("gen6ou")){
//            Gens = Main.G6;
//        } else if(metagame.equals("gen7ou")){
//            Gens = Main.G7;
//        } else if(metagame.equals("gen1oubeta")){
//            Gens = Main.G1;
//        } else if(metagame.equals("gen2oubeta")){
//            Gens = Main.G2;
//        } else if(metagame.equals("gen3oubeta")){
//            Gens = Main.G3;
//        } else if(metagame.equals("gen7pokebankou")){
//            Gens = Main.G7;
//        } else if(metagame.equals("ou")){
//            Gens = Main.G6;
//        }
//
//        //读取data部分
//        JSONObject data = jsonObject.getJSONObject("data");
//        //keys作为迭代器，转换成字符串是pm的名字
//        Iterator iterator = data.keys();
//        while (iterator.hasNext()){
//            String pmName = (String) iterator.next();
//            //通过pm名字产生pm对象
//            JSONObject pm = data.getJSONObject(pmName);
//            double usage;
//            if (pm.has("usage")) {
//                usage = pm.getDouble("usage");
//            } else {
//                //使用率计算，手动算一个使用率，2015-08以前没有usage
//                int rawCount = pm.getInt("Raw count");
//                usage = rawCount * 1.0 / (numberOfBattle * 2);
//            }
//            PmBean pmBean = new PmBean();
//            pmBean.setPmName(pmName);
//            pmBean.setUsage(usage);
//            pmBean.setDate(dateInfo.getDate());
//
//            //第一次，判断数组是否为空，为空的时候增加一列，增加一个
//            if (Gens == null) {
//                Gens.add(new ArrayList<PmBean>());
//                Gens.get(0).add(pmBean);
//            } else {
//                //非第一次，遍历数组，找到相同名字就增加在下面，否则新增一列
//                boolean flag = false;
//                for (int i = 0; i < Gens.size(); i++) {
//                    if (pmName.equals(Gens.get(i).get(0).getPmName())) {
//                        Gens.get(i).add(pmBean);
//                        flag = true;
//                    }
//                }
//                if (flag == false) {
//                    Gens.add(new ArrayList<PmBean>());
//                    Gens.get(Gens.size() - 1).add(pmBean);
//                }
//            }
//        }
//        if(metagame.equals("gen1ou")){
//            Main.G1 = Gens;
//        } else if(metagame.equals("gen2ou")){
//            Main.G2 = Gens;
//        } else if(metagame.equals("gen3ou")){
//            Main.G3 = Gens;
//        } else if(metagame.equals("gen4ou")){
//            Main.G4 = Gens;
//        } else if(metagame.equals("gen5ou")){
//            Main.G5 = Gens;
//        } else if(metagame.equals("gen6ou")){
//            Main.G6 = Gens;
//        } else if(metagame.equals("gen7ou")){
//            Main.G7 = Gens;
//        } else if(metagame.equals("gen1oubeta")){
//            Main.G1 = Gens;
//        } else if(metagame.equals("gen2oubeta")){
//            Main.G2 = Gens;
//        } else if(metagame.equals("gen3oubeta")){
//            Main.G3 = Gens;
//        } else if(metagame.equals("gen7pokebankou")){
//            Main.G7 = Gens;
//        } else if(metagame.equals("ou")){
//            Main.G6 = Gens;
//        }
//    }
//}
//
