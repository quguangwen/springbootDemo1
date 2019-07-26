
package com.demo.service;


import com.demo.util.AesUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchEncrypt {

    public ArrayList getEncrpt(ArrayList arrayList){
        ArrayList encrptList = new ArrayList();
        for(int i=0; i< arrayList.size(); i++){
            String s = AesUtils.encrypt((String) arrayList.get(i));
            encrptList.add(s);
        }
        return encrptList;
    }

    public ArrayList getDecrpt(ArrayList arrayList){
        ArrayList decrypt = new ArrayList();
        for(int i=0; i<arrayList.size(); i++){
            String s = AesUtils.decrypt((String)arrayList.get(i));
            decrypt.add(s);
        }
        return decrypt;
    }

    public String getEncrpt(String test){

        String s = "" ;
        if(test.startsWith("xy")){
            s = AesUtils.decrypt(test);
        } else {
            s = AesUtils.encrypt(test);
        }
        return s;
    }


}
