import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.SubScene;

public class Spider {
	
	
	public static String stockName = "", stockPrice = "", upsDowns = "", upsDownsp = "", opening = "", closedYesterday = "", highest = "", lowest = "", Volume = "", time = "";
	
	public Spider(String className) {
		
		String htmlCode = "", content = "";


		//1.新建url物件，表示要訪問的網頁
		try {
			URL url = new URL("https://tw.stock.yahoo.com/class");
			//2.建立http連線,返回連線物件urlconnection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			//3.獲取相應的http狀態碼，
			int responseCode= urlConnection.getResponseCode();
			//4.如果獲取成功，從URLconnection物件獲取輸入流來獲取請求網頁的原始碼
			if(responseCode == 200){
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				while((content = reader.readLine())!=null){
					htmlCode += content + "\n";
				}
				stockSelect(htmlCode, className);
			}
			else{
				System.out.println("獲取不到原始碼 ，伺服器響應程式碼為："+responseCode);
			}
			} 
		catch (Exception e) {
			System.out.println("獲取不到網頁原始碼："+e);
		}
		
	}
	
	public static void stockSelect(String htmlCode, String className) {	
		int length = className.length();
		//282支股票
		ArrayList<String> col = new ArrayList<String>();
		String temp = "", partitionAddress = "tw.stock.yahoo.com";
		int start = 0, end = 0, start2 = 0, end2 = 0;
		for(int i = 0; i < 282; i++) {			
			start = htmlCode.indexOf("<div class=\"Ta(s) W(20%)\"><a href=\"", end + 1);
			end = htmlCode.indexOf("</a></div>", start + 1);
			temp = htmlCode.substring(start, end);
			if(temp.contains(className)) {			//紡織
				temp = htmlCode.substring(start + 35, end - 77 - length);
				partitionAddress += temp;
				partitionAddress = partitionAddress.replace(';', '&');		

				stockData(partitionAddress);
			
				break;
			}
		
		}
	}

	public static void stockData(String stockAddress) {


		
		String htmlCode = "", content = "", temp = "";
		stockName = ""; stockPrice = ""; upsDowns = ""; upsDownsp = ""; opening = ""; closedYesterday = ""; highest = ""; lowest = ""; Volume = ""; time = "";

		stockAddress = "https://" + stockAddress;
		try {
			URL url = new URL(stockAddress);
			//2.建立http連線,返回連線物件urlconnection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			//3.獲取相應的http狀態碼，
			int responseCode= urlConnection.getResponseCode();
			//4.如果獲取成功，從URLconnection物件獲取輸入流來獲取請求網頁的原始碼
			if(responseCode == 200){
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				while((content = reader.readLine())!=null){
					htmlCode += content + "\n";
				}
			}
			else{
				System.out.println("獲取不到原始碼 ，伺服器響應程式碼為："+responseCode);
			}
			} 
		catch (Exception e) {
			System.out.println("獲取不到網頁原始碼："+e);
		}
		int start = 0, end = 0;
		
		start = htmlCode.indexOf("<p class=\"Fz(20px)--mobile Pb(0px) C(#6e7780) Fz(14px) Fw(n)\">", end + 1);
		end = htmlCode.indexOf("</p>", start + 1);
		temp = htmlCode.substring(start + 64, end - 4);
		int countStock = Integer.parseInt(temp);		
		
		
		//處理動態網頁//鉅亨網//ptt movie nba
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			/*start = htmlCode.indexOf("<div class=\"Lh(20px) Fw(600) Fz(16px) Ell\">", end + 1);
			start = htmlCode.indexOf("<div class=\"Lh(20px) Fw(600) Fz(", end + 1);*/
			start = htmlCode.indexOf("\",\"symbolName\"", end + 1);
			end = htmlCode.indexOf("\",\"systexId\"", start + 1);
			temp = htmlCode.substring(start + 16, end);
			stockName += temp + "\n";
			
			
		}
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"price\":", end + 1);
			end = htmlCode.indexOf(",\"regularMarketDayHigh\":", start + 1);
			temp = htmlCode.substring(start+10, end-1);

			stockPrice += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"change\":", end + 1);
			end = htmlCode.indexOf(",\"changePercent\":", start + 1);
			temp = htmlCode.substring(start+11, end-1);
			
			upsDowns += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"changePercent\":", end + 1);
			end = htmlCode.indexOf(",\"exchange\":", start + 1);
			temp = htmlCode.substring(start + 18, end-1);
			
			upsDownsp += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"regularMarketOpen\":", end + 1);
			end = htmlCode.indexOf(",\"regularMarketPreviousClose\":", start + 1);
			temp = htmlCode.substring(start + 22, end-1);
			
			opening += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"regularMarketPreviousClose\":", end + 1);
			end = htmlCode.indexOf(",\"regularMarketTime\":", start + 1);
			temp = htmlCode.substring(start + 31, end-1);
			
			closedYesterday += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"regularMarketDayHigh\":", end + 1);
			end = htmlCode.indexOf(",\"regularMarketDayLow\":", start + 1);
			temp = htmlCode.substring(start + 25, end-1);
			
			highest += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"regularMarketDayLow\":", end + 1);
			end = htmlCode.indexOf(",\"regularMarketOpen\":", start + 1);
			temp = htmlCode.substring(start + 24, end-1);
			
			lowest += temp + "\n";
			
			
		}
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf(",\"volume\":", end + 1);
			end = htmlCode.indexOf(",\"previousVolumeK\":", start + 1);
			temp = htmlCode.substring(start + 11, end-4);
			
			Volume += temp + "\n";
			
			
		}
		
		
		start = 0;
		end = 0;
		for(int i = 0; i < countStock; i++) {
			start = htmlCode.indexOf("</span></div><div class=\"Fxs(0) Fxg(1) Fxb(66px) Ta(end) Mend(12px)\"><span class=\"\">", end + 1);
			end = htmlCode.indexOf("</span></div></div></li>", start + 1);
			temp = htmlCode.substring(start + 84, end);
			
			time += temp + "\n";
			
			
		}
		
		
		
		
	}
	
}
	
	

	
	
	
	