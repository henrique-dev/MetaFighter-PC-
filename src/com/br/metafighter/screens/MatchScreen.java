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
package com.br.metafighter.screens;

import com.br.metafighter.cmp.CharacterAction;
import com.br.metafighter.cmp.CharacterCommands;
import com.br.metafighter.cmp.CharacterM;
import com.br.metafighter.cmp.Event;
import com.br.metafighter.game.CharacterGuedes;
import com.br.metafighter.window.Scene;
import com.br.metafighter.window.Screen;
import java.awt.event.KeyEvent;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MatchScreen extends Screen {      
    
    private CharacterGuedes hero;    
    
    public MatchScreen(){
        super();
        
        addScene(new Scene(0, 0, 0, 0) {                        
            
            @Override
            public void init() {
                hero = new CharacterGuedes(500, 500, 100, 100);                                
                addGameComponent(hero);
                super.active = true;
                super.visible = true;
                super.clickable = true; // modificar
            }                        
        });
    }
    
}
