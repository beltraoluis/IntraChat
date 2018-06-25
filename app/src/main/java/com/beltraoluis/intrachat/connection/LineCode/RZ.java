package com.beltraoluis.intrachat.connection.LineCode;

import com.beltraoluis.intrachat.model.BinaryData;
import com.beltraoluis.intrachat.model.Bit;

public class RZ implements  LineCode {

    @Override
    public String encode(String s){
        BinaryData data = new BinaryData();
        data.set(s);
        char[] signal = data.binaryString().toCharArray();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < signal.length; i++){
            if(signal[i] == '1'){
                str.append("1;0");
            }
            else{
                str.append("-1;0");
            }
            if(i+1 != signal.length) str.append(";");
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
                    data.set(new Bit(1));
                }
                else{
                    if (aVec.equals("-1")) {
                        data.set(new Bit(0));
                    }
                }
            }
            return data.toString();
        }catch (NullPointerException ignore){}
        return "";
    }

    @Override
    public String binaryString(String s){
        BinaryData data = new BinaryData();
        data.set(s);
        return data.binaryString();
    }
}