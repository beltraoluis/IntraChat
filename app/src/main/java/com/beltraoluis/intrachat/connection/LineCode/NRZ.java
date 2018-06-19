package com.beltraoluis.intrachat.connection.LineCode;

import com.beltraoluis.intrachat.model.BinaryData;
import com.beltraoluis.intrachat.model.Bit;

public class NRZ implements  LineCode{

    @Override
    public String encode(String s){
        BinaryData data = new BinaryData();
        data.set(s);
        char[] signal = data.toBinary().toCharArray();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < signal.length; i++){
            if(signal[i] == '1'){
                str.append("-1");
                if(i == 0) str.append(";");
            }
            else{
                str.append("1");
                if(i+1 != signal.length) str.append(";");
            }
        }
        return str.toString();
    }

    @Override
    public String decode(String s){
        try{
            BinaryData data = new BinaryData();
            String[] vec = s.split(";");
            for (String aVec : vec) {
                if (aVec.equals("1")) {
                    data.set(new Bit(0));
                }
                else{
                    if (aVec.equals("-1")) {
                        data.set(new Bit(1));
                    }
                }
            }
            return data.toString();
        }catch (NullPointerException ignore){}
        return "";
    }
}
