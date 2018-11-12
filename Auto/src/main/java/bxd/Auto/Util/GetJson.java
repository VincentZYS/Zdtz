package bxd.Auto.Util;

import com.alibaba.fastjson.JSONObject;

public class GetJson {
	
	public static JSONObject getJson(String txt){
		JSONObject tokenJson =  JSONObject.parseObject(txt);
		return tokenJson;
	}
	public static String getJson(String txt,String key){
		JSONObject tokenJson =  JSONObject.parseObject(txt);
		return tokenJson.get(key).toString();
	}
	
	public static String getBetResult(String jsonText) {
		JSONObject resAllJson=getJson(jsonText);
		System.out.println(resAllJson);
		System.out.println("——————————————————————华丽丽的分割线——————————————————————————————————————————————");
		JSONObject topGame=getJson(resAllJson.get("TopGame").toString());
		String R1 = topGame.get("R1").toString();
		String R2 = topGame.get("R2").toString();
		String R3 = topGame.get("R3").toString();
		String R4 = topGame.get("R4").toString();
		String R5 = topGame.get("R5").toString();
		String R6 = topGame.get("R6").toString();
		String R7 = topGame.get("R7").toString();
		String R8 = topGame.get("R8").toString();
		String R9 = topGame.get("R9").toString();
		String R10 = topGame.get("R10").toString();
		String gameid = topGame.get("gameid").toString();
		String betResult = "第"+gameid+"期 北京赛车开奖结果："+R1+" "+R2+" "+R3+" "+R4+" "+R5+" "+R6+" "+R7+" "+R8+" "+R9+" "+R10;
		System.out.println(betResult);
		
		return betResult;
	}
	
	
	public static void main(String[] args) {
		String txt="{\"TopGame\":{\"gameid\":712062,\"R1\":\"6\",\"R2\":\"4\",\"R3\":\"8\",\"R4\":\"3\",\"R5\":\"1\",\"R6\":\"5\",\"R7\":\"7\",\"R8\":\"2\",\"R9\":\"9\",\"R10\":\"10\"},\"NewGame\":{\"WaitGame\":\"062-064\\u671f \\u51a0\\u519b\\u301001 03 04 05 07\\u3011\\u7b2c2\\u671f 063\\u671f \\u7b49\\u5f00\"},\"EndList\":[{\"Ruestl\":\"061-063\\u671f \\u51a0\\u519b\\u301002 04 05 07 10\\u3011\\u7b2c1\\u671f 061\\u671f 10 \\u4e2d\"},{\"Ruestl\":\"060-062\\u671f \\u51a0\\u519b\\u301001 03 04 05 10\\u3011\\u7b2c1\\u671f 060\\u671f 04 \\u4e2d\"},{\"Ruestl\":\"059-061\\u671f \\u51a0\\u519b\\u301006 07 08 09 10\\u3011\\u7b2c1\\u671f 059\\u671f 09 \\u4e2d\"},{\"Ruestl\":\"058-060\\u671f \\u51a0\\u519b\\u301001 02 03 04 06\\u3011\\u7b2c1\\u671f 058\\u671f 02 \\u4e2d\"},{\"Ruestl\":\"057-059\\u671f \\u51a0\\u519b\\u301001 03 05 06 10\\u3011\\u7b2c1\\u671f 057\\u671f 01 \\u4e2d\"},{\"Ruestl\":\"055-057\\u671f \\u51a0\\u519b\\u301002 03 04 07 08\\u3011\\u7b2c2\\u671f 056\\u671f 04 \\u4e2d\"},{\"Ruestl\":\"054-056\\u671f \\u51a0\\u519b\\u301003 04 05 06 07\\u3011\\u7b2c1\\u671f 054\\u671f 05 \\u4e2d\"},{\"Ruestl\":\"052-054\\u671f \\u51a0\\u519b\\u301002 05 08 09 10\\u3011\\u7b2c2\\u671f 053\\u671f 08 \\u4e2d\"},{\"Ruestl\":\"051-053\\u671f \\u51a0\\u519b\\u301002 05 07 09 10\\u3011\\u7b2c1\\u671f 051\\u671f 05 \\u4e2d\"},{\"Ruestl\":\"050-052\\u671f \\u51a0\\u519b\\u301002 05 06 07 10\\u3011\\u7b2c1\\u671f 050\\u671f 05 \\u4e2d\"},{\"Ruestl\":\"049-051\\u671f \\u51a0\\u519b\\u301001 05 06 09 10\\u3011\\u7b2c1\\u671f 049\\u671f 10 \\u4e2d\"},{\"Ruestl\":\"048-050\\u671f \\u51a0\\u519b\\u301001 03 04 08 10\\u3011\\u7b2c1\\u671f 048\\u671f 10 \\u4e2d\"},{\"Ruestl\":\"046-048\\u671f \\u51a0\\u519b\\u301002 03 05 07 10\\u3011\\u7b2c2\\u671f 047\\u671f 05 \\u4e2d\"},{\"Ruestl\":\"045-047\\u671f \\u51a0\\u519b\\u301004 06 07 08 10\\u3011\\u7b2c1\\u671f 045\\u671f 06 \\u4e2d\"},{\"Ruestl\":\"044-046\\u671f \\u51a0\\u519b\\u301001 02 05 06 08\\u3011\\u7b2c1\\u671f 044\\u671f 02 \\u4e2d\"},{\"Ruestl\":\"043-045\\u671f \\u51a0\\u519b\\u301003 04 08 09 10\\u3011\\u7b2c1\\u671f 043\\u671f 04 \\u4e2d\"},{\"Ruestl\":\"040-042\\u671f \\u51a0\\u519b\\u301001 02 07 09 10\\u3011\\u7b2c3\\u671f 042\\u671f 06 \\u6302\"},{\"Ruestl\":\"038-040\\u671f \\u51a0\\u519b\\u301002 05 06 09 10\\u3011\\u7b2c2\\u671f 039\\u671f 05 \\u4e2d\"},{\"Ruestl\":\"037-039\\u671f \\u51a0\\u519b\\u301002 05 06 07 10\\u3011\\u7b2c1\\u671f 037\\u671f 10 \\u4e2d\"},{\"Ruestl\":\"034-036\\u671f \\u51a0\\u519b\\u301001 02 03 07 09\\u3011\\u7b2c3\\u671f 036\\u671f 02 \\u4e2d\"},{\"Ruestl\":\"032-034\\u671f \\u51a0\\u519b\\u301001 02 04 05 06\\u3011\\u7b2c2\\u671f 033\\u671f 06 \\u4e2d\"},{\"Ruestl\":\"031-033\\u671f \\u51a0\\u519b\\u301001 04 06 08 10\\u3011\\u7b2c1\\u671f 031\\u671f 10 \\u4e2d\"},{\"Ruestl\":\"029-031\\u671f \\u51a0\\u519b\\u301002 05 06 07 08\\u3011\\u7b2c2\\u671f 030\\u671f 02 \\u4e2d\"}],\"GameMultiple\":{\"NextGameID\":\"712063\",\"Gt\":\"\\u51a0\\u519b\",\"Multiple\":\"3\",\"num\":\"01 03 04 05 07\"}}\r\n";
		getBetResult(txt);
		
	}
}
