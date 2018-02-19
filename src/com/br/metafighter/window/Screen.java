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
import com.br.metafighter.cmp.Event;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public abstract class Screen implements Component{
    
    private List<Scene> scenes;

    public Screen(){
        scenes = new ArrayList<>();
    }
    
    public void addScene(Scene scene){
        this.scenes.add(scene);
    }
    
    public void removerScene(Scene scene){
        this.scenes.remove(scene);
    }
    
    @Override
    public void update(long time){
        for (Component cmp : scenes)
            cmp.update(time);
    }
    
    @Override
    public void renderize(Graphics2D g){
        for (Component cmp : scenes)
            cmp.renderize(g);
    }
    
    @Override
    public boolean keyEvent(Event e){
        for (Component cmp : scenes)
            cmp.keyEvent(e);
        return false;
    }
    
}
