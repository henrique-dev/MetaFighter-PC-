/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.window;

import com.br.metafighter.cmp.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author PH
 */
public abstract class Screen implements Component{
    
    private Scene currentScene;
    
    @Override
    public void update(long time){
        currentScene.update(time);
    }
    
    @Override
    public void renderize(Graphics2D g){
        currentScene.renderize(g);
    }
    
    @Override
    public boolean keyEvent(KeyEvent e){
        currentScene.keyEvent(e);
        return false;
    }
    
}
