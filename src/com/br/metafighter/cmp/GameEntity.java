/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.cmp;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author PH
 */
public abstract class GameEntity extends Entity implements Component{
    
    protected boolean visible;

    public GameEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }   
    
}
