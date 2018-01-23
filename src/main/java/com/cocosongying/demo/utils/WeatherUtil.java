package com.cocosongying.demo.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author cocosongying
 * 
 */
public class WeatherUtil {

	private static String SERVICES_HOST = "www.webxml.com.cn";
	private static String WEATHER_SERVICES_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/";
	private static String WEATHER_SERVICES_URL2 = "http://www.webxml.com.cn/WebServices/WeatherWS.asmx/";
	private static String SUPPORT_CITY_URL = WEATHER_SERVICES_URL + "getSupportCity?byProvinceName=ALL";
	private static String WEATHER_QUERY_3_URL = WEATHER_SERVICES_URL + "getWeatherbyCityName?theCityName=";
	private static String WEATHER_QUERY_5_URL = WEATHER_SERVICES_URL2 + "getWeather?theUserID=&theCityCode=";
	
	public static InputStream getSoapInputStream(String url) {
		InputStream is = null;
		try{
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("Host", SERVICES_HOST);
			conn.connect();
			is = conn.getInputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
		return is;
	}
	
	public static ArrayList<String> getSupportCity() {
		ArrayList cityList = null;
		Document doc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getSoapInputStream(SUPPORT_CITY_URL);
			doc = db.parse(is);
			NodeList nl = doc.getElementsByTagName("string");
			int len = nl.getLength();
			cityList = new ArrayList<String>(len);
			for (int i = 0; i < len; i++) {
				Node n = nl.item(i);
				String city = n.getFirstChild().getNodeValue();
				cityList.add(city);
			}
			is.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return cityList;
	}
	
	public static ArrayList<String> getWeather3Days(String city) {
	    ArrayList weatherList = null;
	    Document doc;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    dbf.setNamespaceAware(true);
	    try{
	    	DocumentBuilder db = dbf.newDocumentBuilder();
		    InputStream is = getSoapInputStream(WEATHER_QUERY_3_URL + city);
		    doc = db.parse(is);
		    NodeList nl = doc.getElementsByTagName("string");
		    int len = nl.getLength();
		    weatherList = new ArrayList<String>(len);
		    for (int i = 0; i < len; i++) {
		    	Node n = nl.item(i);
		    	String weather = n.getFirstChild().getNodeValue();
		    	weatherList.add(weather);
		    }
		    is.close();	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    return weatherList;
	}
	
	public static ArrayList<String> getWeather5Days(String city) {
	    ArrayList weatherList = null;
	    Document doc;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    dbf.setNamespaceAware(true);
	    try{
	    	DocumentBuilder db = dbf.newDocumentBuilder();
		    InputStream is = getSoapInputStream(WEATHER_QUERY_5_URL + city);
		    doc = db.parse(is);
		    NodeList nl = doc.getElementsByTagName("string");
		    int len = nl.getLength();
		    weatherList = new ArrayList<String>(len);
		    for (int i = 0; i < len; i++) {
		    	Node n = nl.item(i);
		    	String weather = n.getFirstChild().getNodeValue();
		    	weatherList.add(weather);
		    }
		    is.close();	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    return weatherList;
	}
	
	public static void main(String[] args) {
		ArrayList<String> cityList = getSupportCity();
		for(String city: cityList){
			System.out.println(city);
		}
		
		ArrayList<String> weatherList = getWeather5Days("南京");
		int i=0;
		for(String weather: weatherList){
			i++;
			System.out.println(i + "===" + weather);
		}
	}
}
