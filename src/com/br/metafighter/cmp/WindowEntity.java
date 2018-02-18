/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.cmp;

/**
 *
 * @author PH
 */
public abstract class WindowEntity extends Entity implements Component{
    
    protected boolean visible;
    protected boolean clickable;
    
    public WindowEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
}
