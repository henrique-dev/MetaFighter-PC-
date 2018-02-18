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
public class GameEntity extends Entity implements Component{

    public GameEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(long time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyEvent(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
