package com.example.tow.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";
    public static void writeData(ArrayList<String> items, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemlist = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemlist = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

            itemlist = new ArrayList<>();
            e.printStackTrace();

        } catch (StreamCorruptedException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

        }
        return itemlist;

    }
}