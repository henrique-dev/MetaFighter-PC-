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
package com.br.metafighter.game;

import com.br.metafighter.cmp.CharacterAction;
import com.br.metafighter.cmp.CharacterCommands;
import com.br.metafighter.cmp.CharacterM;
import com.br.metafighter.cmp.Event;
import com.br.metafighter.cmp.graphics.Sprite;
import com.br.metafighter.cmp.graphics.Texture;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class CharacterGuedes extends CharacterM {

    public CharacterGuedes(int x, int y, int width, int height) {
        super(x, y, width, height);
        super.name = "Carlos Guedes";

        super.sprites = Sprite.getSpriteFromTexture(
                new Texture("src/img/guedes/sprites01.png"), 9, 7, 62);

        super.actions.add(WALKING_LEFT_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 0, 5, true), 8));
        super.actions.add(WALKING_RIGHT_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 0, 5, false), 8));

        super.currentAction = super.actions.get(WALKING_LEFT_ACTION).execute();
    }

    @Override
    public void update(long time) {                        
        if (super.walking) {                                    
            Sprite tmpSprite = currentAction.getSprite();
            if (tmpSprite == null) {
                super.currentAction = super.actions.get(WALKING_LEFT_ACTION).execute();
                tmpSprite = super.actions.get(WALKING_LEFT_ACTION).getSprite();
            }
            super.currentSprite = tmpSprite.getTexture().getImage();
        }
    }

    @Override
    public void renderize(Graphics2D g2d) {
        Graphics2D g = (Graphics2D) g2d.create();

        g.drawImage(super.currentSprite, null, x, y);

        g.dispose();
    }

    @Override
    public boolean keyEvent(Event e) {
        int key = e.getKeyCode();
        boolean pressed = e.pressed();

        switch (key) {
            case KeyEvent.VK_LEFT:
                if (pressed && !super.walking) {                    
                    super.walking = true;
                    currentAction = super.actions.get(WALKING_LEFT_ACTION).execute();
                } else if (!pressed){
                    super.walking = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (pressed && !super.walking) {
                    super.walking = true;
                    currentAction = super.actions.get(WALKING_RIGHT_ACTION).execute();
                } else if (!pressed){
                    super.walking = false;
                }
                break;
        }                                

        return false;
    }

}
