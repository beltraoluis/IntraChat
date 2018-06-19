package com.beltraoluis.intrachat.model;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryData {

    private ArrayList<Bit> data;
    private int mbyte;

    public BinaryData(){
        data = new ArrayList<>();
        this.mbyte = 8;
    }

    public BinaryData(int div){
        data = new ArrayList<>();
        this.mbyte = div;
    }

    public void set(Bit b){
        data.add(b);
    }

    public void set(Integer v){
        Stack<Integer> stack = new Stack<>();
        int i = v;
        while(i != 0 && i != 1){
            stack.push(i % 2);
            i = (int) i/2;
        }
        try{
            stack.push(i);
            if(mbyte > 0){
                while(stack.size()% mbyte != 0){
                    stack.push(0);
                }
            }
            while(!stack.empty()){
                data.add(new Bit(stack.pop()));
            }
        }catch(NullPointerException ignored){}
    }

    public void set(String s){
        data.clear();
        mbyte = 8;
        for(int i = 0; i < s.length(); i++){
            set((int) s.charAt(i));
        }
    }

    public void print(){
        for(Bit b: data){
            System.out.print(b.get());
        }
        System.out.print("b");
    }

    public void println(){
        for(Bit b: data){
            System.out.print(b.get());
        }
        System.out.print("b\n");
    }

    public String byteSize(){
        StringBuilder sb = new StringBuilder("");
        if(mbyte %8 == 0){
            float tam = (float) data.size()/8;
            if(tam >= 1073741824){
                tam = tam/1073741824;
                sb.append(tam);
                sb.append(" GiB");
            }else{
                if(tam >= 1048576){
                    tam = tam/1048576;
                    sb.append(tam);
                    sb.append(" MiB");
                }else{
                    if(tam >= 1024){
                        tam = tam/1024;
                        sb.append(tam);
                        sb.append(" MiB");
                    }else{
                        sb.append(tam);
                        sb.append(" Bytes");

                    }
                }
            }
        }
        return sb.toString();
    }

    public int size(){
        return data.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        int val;
        if(mbyte %8 == 0){
            int tam = data.size()/8;
            for(int i = 0; i < tam; i++){
                val = 0;
                for(int j = 0; j < 8; j++){
                    val += data.get(8*i+j).get()*pow(2,7-j);
                }
                sb.append((char) val);
            }
        }
        return sb.toString();
    }

    public String toBinary(){
        StringBuilder sb = new StringBuilder();
        for(Bit b : data){
            if(b.estado){
                sb.append('1');
            }
            else{
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public int get(int i){
        return data.get(i).get();
    }
}
