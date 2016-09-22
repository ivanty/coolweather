package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CoolWeatherDB {
	public static final String  DB_NAME="cool_weather";
	 public static final int VERSION=1;
	 
	 private static CoolWeatherDB coolWeatherDB;
	 private SQLiteDatabase db;
	 private CoolWeatherDB(Context context){
		 CoolWeatherOpenHelper dbhelper=new CoolWeatherOpenHelper(context,DB_NAME, null, VERSION);
		 db=dbhelper.getWritableDatabase();
	 }
	 public synchronized CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null)
		{
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	 }
	 /*
	  * ��Provinceʵ���洢�����ݿ�
	  * */
	 public void saveProvince(Province province){
		 if(province!=null){
			 ContentValues values=new  ContentValues();
			 values.put("province_name",province.getProvinceName());
			 values.put("province_code",province.getProvinceCode());
			 db.insert("Province", null, values);
			 
		 }
	 }
	 /*
	  * �����ݿ��ȡȫ�����е�ʡ����Ϣ
	  * */
	 public List<Province> loadProvinces(){
		 List<Province> list=new ArrayList<Province>();
		 Cursor cursor=db.query("Province",null, null, 
				 null, null, null, null);
		 if(cursor.moveToFirst()){
			 do{
				 Province province=new Province();
				 province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				 province.setProvinceName(cursor.getString(cursor.getColumnIndex("provice_name")));
				 province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				 list.add(province);
				 
			 }while(cursor.moveToNext());
		 }
		 
		return list;
		 
	 }
	 /*
	  * ��Cityʵ���洢�����ݿ�
	  * */
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new  ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
			
		}
		
	}
	/*
	 * �����ݿ��ȡȫ�����еĳ�����Ϣ
	 * */
			

}
