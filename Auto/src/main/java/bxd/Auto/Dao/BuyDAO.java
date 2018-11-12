package bxd.Auto.Dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import bxd.Auto.Model.BuyHistory;
import bxd.Auto.Model.Plan;
import bxd.Auto.Util.GetJson;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class BuyDAO {
	private static String SESSION ="";
	private static String LOGIN_URL = "https://1111hj8.com/mobile/v3/dologin.do";
	private static String BALANCE_URL = "https://1111hj8.com/mobile/v3/personal_center.do";
	private static String BUYHISTORY_URL = "https://1111hj8.com/mobile/v3/get_lottery_order.do";
	private static String GAMEHISTORY_URL = "https://1111hj8.com/mobile/v3/draw_notice_details_data.do?lotCode=BJSC&page=1&rows=30";
	private static String TOKEN_URL = "https://1111hj8.com/mobile/v3/token.do";
	private static String DOBET_URL = "https://1111hj8.com/mobile/v3/betInfo.do";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PlanDAO pd=new PlanDAO();
	private int buyQishu=0;
	
	/**   
	 * @Title: doBet   
	 * @Description: TODO(三期不中换号模式)   
	 * @param: @param con
	 * @param: @param qiHao
	 * @param: @param bets
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String      
	 * @throws   
	 */  
	public String doBet1(Connection con) throws IOException {
		Date date = new Date(System.currentTimeMillis());
		String time = sdf.format(date).substring(0, 10);
		if (con != null) {
			String token=getToken(con);
			
			Plan p = pd.getPlan();
			String qiHao=p.getNewGameID();
			String newGameNum = p.getGameList()[0].substring(12, 26).replaceAll(" ", "");
			String beiShu = p.getNewGameMultiple();
			
			switch (Integer.parseInt(beiShu)) {
			case 24:
				beiShu = "20";
				break;
			case 72:
				beiShu = "40";
				break;
			case 216:
				beiShu = "90";
				break;
			case 648:
				beiShu = "200";
				break;
			case 1944:
				beiShu = "450";
				break;
			default:
				break;
			}
			String bets = "BJSC|dwd|100|" + beiShu + "|" + newGameNum + ",-,-,-,-,-,-,-,-,-";
			
			con.url(DOBET_URL);
			con.timeout(50000);
			con.header("Cookie","SESSION="+SESSION+ ";userName=9100z;password=123qwe");
			con.header("Referer", "https://1111hj8.com/mobile/v3/bet_lotterys.do?lotCode=BJSC");
			con.header("Content-Length", "234");
			
			String betsInfo = "{\"lotCode\":\"BJSC\",\"token\":\""+token+"\",\"qiHao\":\""+qiHao+"\",\"version\":1,\"bets\":[\""+bets+"\"]}";//betsString BJSC|dwd|100|1|06,-,-,-,-,-,-,-,-,-
			con.data("data",betsInfo);
			System.out.println(time +"投注:"+bets);
			return con.method(Method.POST).execute().body().toString();
		}else {
			System.err.println("————————————————" + time + "投注失败！CON 为 NULL"+ "——————————————————");
			return "time + \"投注失败！CON为NULL\"";
		}
		
	}
	
	//简单倍投模式
	public String doBet2(Connection con) throws IOException {
		Date date = new Date(System.currentTimeMillis());
		String time = sdf.format(date).substring(0, 10);
		if (con != null) {
			String token=getToken(con);
			
			Plan p = pd.getPlan();
			String qiHao=p.getNewGameID();
			String newGameNum = p.getNewGameNum().replaceAll(" ", "");
			String beiShu = p.getNewGameMultiple();
			
			switch (Integer.parseInt(beiShu)) {
			case 8:
				beiShu = "7";
				break;
			case 24:
				beiShu = "15";
				break;
			case 72:
				beiShu = "33";
				break;
			case 216:
				beiShu = "73";
				break;
			case 648:
				beiShu = "161";
				break;
			case 1944:
				beiShu = "355";
				break;
			case 5832:
				beiShu = "780";
				break;
			case 17496:
				beiShu = "1716";
				break;
			case 52488:
				beiShu = "3775";
				break;
			case 157464:
				beiShu = "8305";
				break;
			default:
				break;
			}
			String bets = "BJSC|dwd|100|" + beiShu + "|" + newGameNum + ",-,-,-,-,-,-,-,-,-";
			
			con.url(DOBET_URL);
			con.timeout(50000);
			con.header("Cookie","SESSION="+SESSION+ ";userName=9100z;password=123qwe");
			con.header("Referer", "https://1111hj8.com/mobile/v3/bet_lotterys.do?lotCode=BJSC");
			con.header("Content-Length", "234");
			
			String betsInfo = "{\"lotCode\":\"BJSC\",\"token\":\""+token+"\",\"qiHao\":\""+qiHao+"\",\"version\":1,\"bets\":[\""+bets+"\"]}";//betsString BJSC|dwd|100|1|06,-,-,-,-,-,-,-,-,-
			con.data("data",betsInfo);
			System.out.println(time +"投注:"+bets);
			return con.method(Method.POST).execute().body().toString();
		}else {
			System.err.println("————————————————" + time + "投注失败！CON 为 NULL"+ "——————————————————");
			return "投注失败fail";
		}
	}
	
	public String shDongBet(Connection con,String num,String muti) throws IOException {
		Date date = new Date(System.currentTimeMillis());
		String time = sdf.format(date).substring(0, 10);
		if (con != null) {
			String token=getToken(con);
			
			Plan p = pd.getPlan();
			String qiHao=p.getNewGameID();
			
			String bets = "BJSC|dwd|100|" + muti + "|" + num + ",-,-,-,-,-,-,-,-,-";
			
			con.url(DOBET_URL);
			con.timeout(50000);
			con.header("Cookie","SESSION="+SESSION+ ";userName=9100z;password=123qwe");
			con.header("Referer", "https://1111hj8.com/mobile/v3/bet_lotterys.do?lotCode=BJSC");
			con.header("Content-Length", "234");
			
			String betsInfo = "{\"lotCode\":\"BJSC\",\"token\":\""+token+"\",\"qiHao\":\""+qiHao+"\",\"version\":1,\"bets\":[\""+bets+"\"]}";//betsString BJSC|dwd|100|1|06,-,-,-,-,-,-,-,-,-
			con.data("data",betsInfo);
			System.out.println(time + "投注:"+bets);
			return con.method(Method.POST).execute().body().toString();
		}else {
			System.err.println("————————————————" + time + "投注失败！CON 为 NULL"+ "——————————————————");
			return "投注失败！CON为NULL\"";
		}
		
	}
	
	/**   
	 * @Title: getToken   
	 * @Description: TODO(获取下注所需的token)   
	 * @param: @param con
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String      
	 * @throws   
	 */  
	public String getToken(Connection con) throws IOException
	{
		con.url(TOKEN_URL);
		con.timeout(10000);
		con.header("Cookie","SESSION="+SESSION+ ";userName=9100z;password=123qwe");
		con.header("Referer", "https://1111hj8.com/mobile/v3/bet_lotterys.do?lotCode=BJSC");
		con.header("Content-Length", "239");
		con.method(Method.POST);
		String tokenJsonString=con.execute().body().toString();
		JSONObject tokenJson =  JSONObject.parseObject(tokenJsonString);
		
		return tokenJson.get("token").toString();
	}
	
	/**   
	 * @Title: getBuyHistory   
	 * @Description: TODO(获取投注记录)   
	 * @param: @param con
	 * @param: @param userName
	 * @param: @param password
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: BuyHistory      
	 * @throws   
	 */  
	public BuyHistory getBuyHistory(Connection con,String userName,String password,String time) throws IOException {
		con.url(BUYHISTORY_URL);
		System.out.println(SESSION);
		con.header("Cookie","SESSION="+SESSION+ ";userName="+userName+";password="+password);
		con.header("Host","1111hj8.com");
//		con.header("Upgrade-Insecure-Requests","1");
		con.header("Referer", "https://1111hj8.com/mobile/v3/betting_record_lottery.do");
//		con.header("Content-Length", "94");
		
		con.data("startTime", time+" 00:00:00");
		con.data("endTime", time+" 23:59:59");
		con.data("code", "");
		con.data("status", "");
		con.data("page", "1");
		con.data("rows", "40");
		
		String buyHistoryString = con.execute().body().toString();
		JSONObject allJson = JSONObject.parseObject(buyHistoryString);
		JSONObject pageJson = allJson.getJSONObject("page");
		JSONArray buyArrayJson = pageJson.getJSONArray("list");
		
		String winSum=allJson.getString("sumWinMoney");
		String buySum=allJson.getString("sumBuyMoney");
		
		String[] buyArray=buyArrayJson.size()>0?new String[buyArrayJson.size()]:new String[] {""};
		String firstQiHao="";
		if (buyArrayJson.size() > 0){
            for (int i = 0; i < buyArrayJson.size(); i++)
            {
                    JSONObject buyJson = buyArrayJson.getJSONObject(i);
                    if(i==0) {
                    	firstQiHao=buyJson.getString("qiHao");
                    }
                    buyJson.getString("qiHao");
                    buyJson.getString("haoMa");
                    buyJson.getString("multiple");
                    buyJson.getString("buyMoney");
                    buyJson.getString("lotteryHaoMa");
                    String buyM=buyJson.getString("winMoney")==null?"0":buyJson.getString("winMoney");
                    buyJson.getString("lotName");
                    buyArray[i]=buyJson.getString("lotName")+" "+buyJson.getString("qiHao")+" "+buyJson.getString("haoMa")+" "+buyJson.getString("multiple")+" "+buyJson.getString("buyMoney")+" "+buyM;
            }
        } 
		
		BuyHistory bh=new BuyHistory();
		bh.setBuyArray(buyArray);
		bh.setBuySum(buySum);
		bh.setWinSum(winSum);
		bh.setFirstQiHao(firstQiHao);
		
		return bh;
	}
	
	/**   
	 * @Title: getGameHistory   
	 * @Description: TODO(获取历史开奖记录)   
	 * @param: @param con
	 * @param: @param userName
	 * @param: @param password
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String[]      
	 * @throws   
	 */  
	public String[] getGameHistory(Connection con,String userName,String password) throws IOException
	{
		con.url(GAMEHISTORY_URL);
		con.timeout(10000);
		con.header("Cookie","SESSION="+SESSION+ ";userName="+userName+";password="+password);
		con.header("Host","1111hj8.com");
		con.header("Upgrade-Insecure-Requests","1");
		con.header("Referer", "https://1111hj8.com/mobile/v3/draw_notice_details.do?lotCode=BJSC");
		
		String gHistoryString=con.execute().body();
		JSONObject allJson = JSONObject.parseObject(gHistoryString);
		JSONObject dataJson = allJson.getJSONObject("data");
		JSONArray ghJsonArray = dataJson.getJSONArray("list");
		
		String[] ghArray=ghJsonArray.size()>0?new String[ghJsonArray.size()]:new String[] {""};
		
		if (ghJsonArray.size() > 0){
            for (int i = 0; i < ghJsonArray.size(); i++)
            {
                    JSONObject ghJson = ghJsonArray.getJSONObject(i);
                    ghArray[i]=ghJson.getString("qiHao")+" "+ghJson.getString("haoMaList");
            }
        } 
		System.out.println("开奖记录：");
		for(String s:ghArray) {
			System.out.println(s);
		}
		
		return ghArray;
	}
	
	
	/**   
	 * @Title: getBalance   
	 * @Description: TODO(获取账户余额)   
	 * @param: @param con
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String      
	 * @throws   
	 */  
	public String getBalance(Connection con) throws IOException {
		con.url(BALANCE_URL);
		con.timeout(10000);
		
		return con.get().getElementById("meminfoMoney").text();
	}
	
	/**   
	 * @Title: login   
	 * @Description: TODO(登陆)   
	 * @param: @param userName
	 * @param: @param password
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: Connection      
	 * @throws   
	 */  
	public Connection login(String userName,String password) throws IOException
	{
		Connection con = Jsoup.connect(LOGIN_URL);
		header(con);
		con.method(Method.POST);
		con.data("account", userName);
		con.data("password", password);
		con.data("verifyCode", "");
		con.timeout(5000);
		Response response = con.execute();
		SESSION = response.cookie("SESSION");
		Map<String, String> cookies = response.cookies();
		
		for (Entry<String, String> entry : cookies.entrySet())
		{
			System.out.println("response的cookie:" + entry.getKey() + "-" + entry.getValue());
			con.cookie(entry.getKey(), entry.getValue());
		}
		
		System.out.println(response.body());
		String success = GetJson.getJson(response.body(), "success");
		
		if(success.equals("true")) {
			return con;
		}else {
			return null;
		}
	}
	
	/**   
	 * @Title: header   
	 * @Description: TODO(包装请求头信息)   
	 * @param: @param con      
	 * @return: void      
	 * @throws   
	 */  
	public void header(Connection con)
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
