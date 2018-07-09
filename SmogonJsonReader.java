import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class SmogonJsonReader {

    public ArrayList<ArrayList<PmBean>> ReadJson(int Months,String Date, String MetaGame) throws IOException, JSONException {
            ArrayList<ArrayList<PmBean>> Gens = new ArrayList<ArrayList<PmBean>>();

            final String BeginMetaGame = MetaGame;
            String GMetaGame = BeginMetaGame;

            for (int d = 0; d < Months; d++) {
                //把json文件转换为JSONObject
                GMetaGame = BeginMetaGame;

                //异常文件名处理
                if (GMetaGame.equals("gen1ou") && Date.equals("2014-11")) {
                    GMetaGame = "gen1oubeta";
                } else if (GMetaGame.equals("gen2ou") && Date.equals("2014-11")) {
                    GMetaGame = "gen2oubeta";
                } else if (GMetaGame.equals("gen3ou") && Date.equals("2014-11")) {
                    GMetaGame = "gen3oubeta";
                } else if (GMetaGame.equals("gen3ou") && Date.equals("2014-12")) {
                    GMetaGame = "gen3oubeta";
                } else if (GMetaGame.equals("gen3ou") && Date.equals("2015-01")) {
                    GMetaGame = "gen3oubeta";
                } else if (GMetaGame.equals("gen3ou") && Date.equals("2015-02")) {
                    GMetaGame = "gen3oubeta";
                } else if (GMetaGame.equals("gen7ou") && Date.equals("2017-01")) {
                    GMetaGame = "gen7pokebankou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2014-11")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2014-12")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-01")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-02")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-03")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-04")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-05")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-06")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-07")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-08")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-09")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-10")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-11")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2015-12")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-01")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-02")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-03")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-04")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-05")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-06")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-07")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-08")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-09")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-10")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-11")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2016-12")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-01")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-02")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-03")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-04")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-05")) {
                    GMetaGame = "ou";
                } else if (GMetaGame.equals("gen6ou") && Date.equals("2017-06")) {
                    GMetaGame = "ou";
                }

                String path = new String("stats-json\\" + Date + "\\chaos\\" + GMetaGame + "-0.json");//E:/Developer/idea/workspace/pmstat/target/classes/stats/2018-04/chaos/gen7ou-0.json
                path = path.replace("\\", "/");
                if (path.contains(":")) {
                    path = path.replace("file:/", "");
                }

//                BufferedInputStream in = new BufferedInputStream(SmogonJsonReader.class.getClassLoader().getResourceAsStream(path));
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 1 * 1024 * 1024);//1M缓存
//                StringBuffer buffer = new StringBuffer();
//                String line; // 用来保存每行读取的内容
//                line = reader.readLine(); // 读取第一行
//                while (line != null) { // 如果 line 为空说明读完了
//                    buffer.append(line); // 将读到的内容添加到 buffer 中
//                    line = reader.readLine(); // 读取下一行
//                }
//                reader.close();
//                String content = buffer.toString();
//                JSONObject jsonObject = new JSONObject(content);

                InputStream in = SmogonJsonReader.class.getClassLoader().getResourceAsStream(path);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 1 * 1024 * 1024);
                StringBuffer buffer = new StringBuffer();
                String line; // 用来保存每行读取的内容
                line = bufferedReader.readLine(); // 读取第一行
                while (line != null) { // 如果 line 为空说明读完了
                    buffer.append(line); // 将读到的内容添加到 buffer 中
                    line = bufferedReader.readLine(); // 读取下一行
                }
                bufferedReader.close();
                String content = buffer.toString();
                JSONObject jsonObject = new JSONObject(content);

                //从info中读取战斗场次
                JSONObject info = jsonObject.getJSONObject("info");
                int numberOfBattle = info.getInt("number of battles");

                //读取data部分
                JSONObject data = jsonObject.getJSONObject("data");
                //keys作为迭代器，转换成字符串是pm的名字
                Iterator iterator = data.keys();
                while (iterator.hasNext()) {
                    String pmName = (String) iterator.next();
                    //通过pm名字产生pm对象
                    JSONObject pm = data.getJSONObject(pmName);
                    double usage;
                    if (pm.has("usage")) {
                        usage = pm.getDouble("usage");
                    } else {
                        //使用率计算，手动算一个使用率，2015-08以前没有usage
                        int rawCount = pm.getInt("Raw count");
                        usage = rawCount * 1.0 / (numberOfBattle * 2);
                    }
                    PmBean pmBean = new PmBean();
                    pmBean.setPmName(pmName);
                    pmBean.setUsage(usage);
                    pmBean.setDate(Date);

                    //第一次，判断数组是否为空，为空的时候增加一列，增加一个
                    if (Gens == null) {
                        Gens.add(new ArrayList<PmBean>());
                        Gens.get(0).add(pmBean);
                    } else {
                        //非第一次，遍历数组，找到相同名字就增加在下面，否则新增一列
                        boolean flag = false;
                        for (int i = 0; i < Gens.size(); i++) {
                            if (pmName.equals(Gens.get(i).get(0).getPmName())) {
                                Gens.get(i).add(pmBean);
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            Gens.add(new ArrayList<PmBean>());
                            Gens.get(Gens.size() - 1).add(pmBean);
                        }
                    }
                }
                Date = subMonth(Date);
            }
            return  Gens;
    }

    public ArrayList<PmBean> findPM(String PMNameToFind, ArrayList<ArrayList<PmBean>> Gens) {

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

    private static String subMonth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = null;
        try {
            dt = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }
}