package com.beltraoluis.intrachat.model;

public class Bit {

    protected boolean estado;

    public Bit(int i) throws NullPointerException{
        switch(i){
            case 0: estado = false; break;
            case 1: estado = true; break;
            default: throw new NullPointerException("valor invalido");
        }
    }

    public int get(){
        if(estado){
            return 1;
        }
        return 0;
    }

    public boolean isEstado() {
        return estado;
    }

    public void set(int i) throws NullPointerException{
        switch(i){
            case 0: estado = false; break;
            case 1: estado = true; break;
            default: throw new NullPointerException("valor invalido");
        }
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
