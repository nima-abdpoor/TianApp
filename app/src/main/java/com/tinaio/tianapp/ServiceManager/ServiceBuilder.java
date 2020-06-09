package com.tinaio.tianapp.ServiceManager;

import android.content.Context;
import android.util.Log;

import com.tinaio.tianapp.DataBase.DataBaseHelper;

public class ServiceBuilder implements AddService{
    public static final String Attendance = "حضوروغیاب";
    public static final String FoodReservation = "رزرو غذا";
    public static final String Hotel = "هتل";
    public static final String Book = "کتاب";
    String ID;
    String name;
    Context context;

    @Override
    public AddService addService(Context context, String ID, String name) {
        this.ID = ID;
        this.name=name;
        this.context=context;
        PutInDataBase(ID,name);
        return new ServiceBuilder();
    }

    private void PutInDataBase(String ID,String name) {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
        Long result = dataBaseHelper.InsertService(ID,name);
        Log.i("insertingindatabase", String.valueOf(result));
    }

    public String getID(String text){
        return ID;
    }
    public String getName(String ID){
        return name;
    }


}
