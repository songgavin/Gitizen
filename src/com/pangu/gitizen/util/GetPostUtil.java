package com.pangu.gitizen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class GetPostUtil 
{
	public static String sendGet(String url)
	{
		String result = "";
		BufferedReader in = null;
		try
		{
			String urlName = url;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) 
			{
				result += '\n' + line;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendPost(String url, String json1)
	{
		BufferedReader in = null;
		String result = "";
		try 
		{
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(json1.getBytes());
			os.flush();
			os.close();
			
			if(conn.getResponseCode() == 200){
				InputStream is = conn.getInputStream();
				
				in = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = in.readLine()) != null) 
				{
					result += '\n' + line;
				}
				is.close();
				in.close();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String sendPut(String url, String json2)
	{
		BufferedReader in = null;
		String result = "";
		try 
		{
			
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(json2.getBytes());
			os.flush();
			os.close();
			
			if(conn.getResponseCode() == 200){
				InputStream is = conn.getInputStream();
				
				in = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = in.readLine()) != null) 
				{
					result += '\n' + line;
				}
				is.close();
				in.close();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String sendDelete(String url)
	{
		BufferedReader in = null;
		String result = "";
		try 
		{
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("DELETE");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			if(conn.getResponseCode() == 200){
				InputStream is = conn.getInputStream();
				
				in = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = in.readLine()) != null) 
				{
					result += '\n' + line;
				}
				is.close();
				in.close();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	public static String sendDelete(String url)
	{
		BufferedReader in = null;
		String result = "";
		HttpURLConnection conn = null;
		try 
		{	
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("DELETE");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{         
		    if (conn != null)
		    {
		    	conn.disconnect();
		    }
		}
		return result;
	}
	*/
}
