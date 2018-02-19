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
package com.br.metafighter.cmp;

import java.awt.Component;
import java.awt.event.KeyEvent;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public final class Event extends KeyEvent {
    
    private boolean keyPressed;        
    
    public Event(KeyEvent e, boolean pressed){
        super(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), e.getKeyCode(), e.getKeyChar(), e.getKeyLocation());
        this.keyPressed = pressed;
    }

    public Event(Component source, int id, long when, int modifiers, int keyCode, char keyChar, int keyLocation) {
        super(source, id, when, modifiers, keyCode, keyChar, keyLocation);
    }

    public boolean pressed() {
        return keyPressed;
    }            
    
}
