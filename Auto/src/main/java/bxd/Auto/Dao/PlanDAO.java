package bxd.Auto.Dao;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import bxd.Auto.Model.Plan;

public class PlanDAO {
	private static String URL="http://557799hj.cc/json/pk10.json?r="+Math.random();
	
	public static void main(String[] args) throws IOException {
		PlanDAO pd = new PlanDAO();
		Plan p = pd.getPlan();
		System.out.println(p.getTopGame());
		System.out.println("——————————————————————————————————————————————华丽丽的分割线——————————————————————————————————————————————");
		System.out.println(p.getNewGameID());
		System.out.println(p.getNewGameMultiple());
		System.out.println(p.getNewGameNum());
		System.out.println("——————————————————————————————————————————————华丽丽的分割线——————————————————————————————————————————————");
		for(String s:p.getGameList()) {
			System.out.println(s);
		}
		System.out.println("——————————————————————————————————————————————华丽丽的分割线——————————————————————————————————————————————");
		System.out.println(p.getGameList()[0]);
	}
	
	public Plan getPlan() throws IOException {
		Connection con = Jsoup.connect(URL);
		header(con);
		String planString=con.execute().body();
		
		JSONObject allJson = JSONObject.parseObject(planString);
		JSONArray endListJsonArray = allJson.getJSONArray("EndList");
		JSONObject topGameJson = allJson.getJSONObject("TopGame");
		JSONObject gameMultipleJson = allJson.getJSONObject("GameMultiple");
		
		String R1 = topGameJson.getString("R1");
		String R2 = topGameJson.getString("R2");
		String R3 = topGameJson.getString("R3");
		String R4 = topGameJson.getString("R4");
		String R5 = topGameJson.getString("R5");
		String R6 = topGameJson.getString("R6");
		String R7 = topGameJson.getString("R7");
		String R8 = topGameJson.getString("R8");
		String R9 = topGameJson.getString("R9");
		String R10 = topGameJson.getString("R10");
		String gameid = topGameJson.getString("gameid");
		String topGameString = "第"+gameid+"期 开奖结果："+R1+" "+R2+" "+R3+" "+R4+" "+R5+" "+R6+" "+R7+" "+R8+" "+R9+" "+R10;
		String [] endArray=new String[endListJsonArray==null?1:endListJsonArray.size()+1];
		endArray[0]=allJson.getJSONObject("NewGame").get("WaitGame").toString();
		
		if (endListJsonArray.size() > 0){
            for (int i = 0; i < endListJsonArray.size(); i++)
            {
                    JSONObject valueJsonObject = endListJsonArray.getJSONObject(i);
                    endArray[i+1]=valueJsonObject.get("Ruestl").toString();
            }
        } 
		
		Plan p=new Plan();
		p.setTopGame(topGameString);
		p.setNewGameID(gameMultipleJson.getString("NextGameID"));
		p.setNewGameMultiple(gameMultipleJson.getString("Multiple"));
		p.setNewGameNum(gameMultipleJson.getString("num"));//
		p.setGameList(endArray);
		return p;
	}
	
	
	public static String executor(String url,Connection con) throws IOException
	{
		header(con);
		return con.execute().body();
	}
	
	public static void header(Connection con)
	{
		con.header("Pragma", "");
		con.header("Pragma", "no-cache");
		con.header("Origin", "https://1111hj8.com");
		con.header("Accept-Encoding", "gzip, deflate, br");
		con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6");
		con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");
		con.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.header("Accept", "*/*");
		con.header("Cache-Control", "no-cache");
		con.header("X-Requested-With", "XMLHttpRequest");
		con.header("Connection", "keep-alive");
		con.ignoreContentType(true);
		con.ignoreHttpErrors(true);
	}
}
