package io.github.mortenjenne.util;

import java.io.*;
import java.lang.reflect.Type;

public class FileDataStore<T> implements DataStore<T> {
    @Override
    public String save(T obj) {
        Type typeOf = obj.getClass();
        String fileName = typeOf.toString();
        String fileSuffix = (java.time.LocalDateTime.now()).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        fileName = fileName + fileSuffix + ".ser";
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public T load(String fileName) {
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            T obj = (T) in.readObject();
            in.close();
            fis.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }


