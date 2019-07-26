package com.demo.util;

import java.io.*;
import java.util.*;

public class ReadProperties {


    public static String getValue(String key) {

        String fileName = "application.properties";
        InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        Properties properties = new OrderedProperties();
        try {
            properties.load(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String value = properties.getProperty(key);
        return value != null ? value : null;
    }

    public static ArrayList getProductList() {
        ArrayList arrayList = new ArrayList();
        String fileName = "productList.properties";
        InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        Properties properties = new OrderedProperties();

        try {
            properties.load(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            arrayList.add(properties.getProperty(iterator.next().toString()));
        }

        return arrayList;
    }


    public static String getStateCNValue(String value) {
        String fileName = "state.properties";
        String key = null;

        InputStreamReader inputStreamReader = null;
        Properties properties = new OrderedProperties();
        try {
            inputStreamReader = new InputStreamReader(ReadProperties.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
            properties.load(inputStreamReader);
            Set<Map.Entry<Object,Object>> entrySet = properties.entrySet();
            for(Map.Entry<Object,Object> entry : entrySet){
                if(entry.getValue().equals(value)){
                    key = entry.getKey().toString();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key != null ? key : null;

    }

    public static LinkedHashMap<Object, Object> getResourceMap(String fileName) {
        InputStreamReader inputStreamReader = null;
        String value = null;
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList();
        Properties properties = new OrderedProperties();
        try {
            inputStreamReader = new InputStreamReader(ReadProperties.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
            properties.load(inputStreamReader);
            Iterator iterator = properties.keySet().iterator();
            while (iterator.hasNext()) {
                arrayList.add(iterator.next().toString());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i< arrayList.size(); i++){
            linkedHashMap.put(arrayList.get(i),properties.getProperty(arrayList.get(i).toString()));

        }

        return linkedHashMap;
    }

//    public static void main(String[] args) {
//        HashMap<Object,Object> hashMap = ReadProperties.getResourceMap("state.properties");
//        Set<Map.Entry<Object, Object>> set = hashMap.entrySet();
//        for(Map.Entry<Object, Object> entry : set){
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//    }

}
