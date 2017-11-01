/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package war;

/**
 *
 * @author пк
 */
public class Cell {
    public int x = 0, y = 0;
    int size = 30;
    int inf = 0;
    
    public void setInf(int inf){
        this.inf = inf;
    }

    public void setXY(int _x,int _y){
        x=_x;
        y=_y;
    }

    public int getInf(){
        return inf;
    }
}
