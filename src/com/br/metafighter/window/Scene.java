/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.window;

import com.br.metafighter.cmp.Component;
import com.br.metafighter.cmp.GameEntity;
import com.br.metafighter.cmp.WindowEntity;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PH
 */
public abstract class Scene extends WindowEntity{
    
    private List<GameEntity> gameComponents;
    private List<WindowEntity> windowComponents; 
    
    public Scene(int x, int y, int width, int height) {
        super(x, y, width, height);
        gameComponents = new ArrayList<>();
        windowComponents = new ArrayList<>();
        init();
    }
            
    public abstract void init();
    
    public Scene start(){
        active = true;
        return this;
    }
    
    public void addGameComponent(GameEntity cmp){
        this.gameComponents.add(cmp);
    }
    
    public void removeGameComponent(GameEntity cmp){
        this.gameComponents.remove(cmp);
    }
    
    public void addWindowComponent(WindowEntity cmp){
        this.windowComponents.add(cmp);
    }
    
    public void removeWindowComponent(WindowEntity cmp){
        this.windowComponents.remove(cmp);
    }

    @Override
    public void update(long time) {
        if (active){
            for (Component cmp : gameComponents)
                if (cmp != null)
                    cmp.update(time);    
            for (Component cmp : windowComponents)
                if (cmp != null)
                    cmp.update(time);    
        }
    }

    @Override
    public void renderize(Graphics2D g2d) {
        Graphics2D g = (Graphics2D) g2d.create();
        
        if (visible){
            for (Component cmp : gameComponents)
                if (cmp != null)
                    cmp.renderize(g2d);
            for (Component cmp : windowComponents)
                if (cmp != null)
                    cmp.renderize(g2d);   
        }
        g.dispose();
    }

    @Override
    public boolean keyEvent(KeyEvent e) {
        if (clickable){
            for (Component cmp : gameComponents)
                if (cmp != null)
                    cmp.keyEvent(e);
            for (Component cmp : windowComponents)
                if (cmp != null)
                    cmp.keyEvent(e);
        }
        return false;
    }
    
    
    
}
