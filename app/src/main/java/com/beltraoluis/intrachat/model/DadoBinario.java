package com.beltraoluis.intrachat.model;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Stack;

public class DadoBinario {

    protected ArrayList<Bit> dado;
    protected int divisao;

    public DadoBinario(){
        dado = new ArrayList<>();
        this.divisao = 8;
    }

    public DadoBinario(int div){
        dado = new ArrayList<>();
        this.divisao = div;
    }

    public void set(Bit b){
        dado.add(b);
    }

    public void set(Integer v){
        Stack<Integer> pilha = new Stack<>();
        int i = v.intValue();
        while(i != 0 && i != 1){
            pilha.push(new Integer(i%2));
            i = (int) i/2;
        }
        try{
            pilha.push(i);
            if(divisao > 0){
                while(pilha.size()%divisao != 0){
                    pilha.push(0);
                }
            }
            while(!pilha.empty()){
                dado.add(new Bit(pilha.pop().intValue()));
            }
        }catch(NullPointerException e){}
    }

    public void set(String s){
        dado.clear();
        divisao = 8;
        for(int i = 0; i < s.length(); i++){
            set((int) s.charAt(i));
        }
    }

    public void print(){
        for(Bit b: dado){
            System.out.print(b.get());
        }
        System.out.print("b");
    }

    public void println(){
        for(Bit b: dado){
            System.out.print(b.get());
        }
        System.out.print("b\n");
    }

    public String tamanhoByte(){
        StringBuilder sb = new StringBuilder("");
        if(divisao%8 == 0){
            float tam = (float) dado.size()/8;
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
        return dado.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        int val;
        if(divisao%8 == 0){
            int tam = dado.size()/8;
            for(int i = 0; i < tam; i++){
                val = 0;
                for(int j = 0; j < 8; j++){
                    val += dado.get(8*i+j).get()*pow(2,7-j);
                }
                sb.append((char) val);
            }
        }
        return sb.toString();
    }

    public int get(int i){
        return dado.get(i).get();
    }
}
