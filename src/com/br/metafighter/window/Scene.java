/*
 * Copyright (C) 2018 Paulo Henrique Gonçalves Bacelar 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * @author Paulo Henrique Gonçalves Bacelar
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
