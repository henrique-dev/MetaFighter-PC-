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

import com.br.metafighter.GameEngine;
import com.br.metafighter.cmp.character.CharacterAction;
import com.br.metafighter.cmp.character.CharacterM;
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

        super.actions.add(WALKING_LEFT_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 0, 5, true), 8, WALKING_LEFT_ACTION));
        super.actions.add(WALKING_RIGHT_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 0, 5, false), 8, WALKING_RIGHT_ACTION));
        
        super.actions.add(MOVE1_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 6, 13, false), 10, MOVE1_ACTION));
        super.actions.add(MOVE2_ACTION, new CharacterAction(Sprite.getSpriteFromSprites(sprites, 6, 13, true), 10, MOVE2_ACTION));
        
        super.velocityX = GameEngine.SCREEN_WIDTH / 150;
        super.velocityY = GameEngine.SCREEN_HEIGHT / 40;
        super.acceleration = velocityY / 0.5f;

        super.currentAction = super.actions.get(MOVE1_ACTION).execute();
    }
    
    private void changeCurrentAction(int action){
        currentAction = super.actions.get(action);
    }        

    @Override
    public void update(long time) {                                                    
        
        takeSprite();
        
        if (moving){
            x += (velocityX * directionX);
        }
        
    }
    
    private void takeSprite(){
        Sprite tmpSprite = currentAction.getSprite();
        if (tmpSprite != null) {
            super.currentSprite = tmpSprite.getTexture().getImage();            
        } else {
            switch (currentAction.getAction()){
                case MOVE1_ACTION:
                    changeCurrentAction(MOVE2_ACTION);
                break;
                case MOVE2_ACTION:
                    changeCurrentAction(MOVE1_ACTION);
                break;
            }
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
                if (pressed && !super.moving) {                    
                    super.moving = true;
                    currentAction = super.actions.get(WALKING_LEFT_ACTION).execute();
                    super.currentKeyAction = WALKING_LEFT_ACTION;
                    super.moveState = super.moving = true;
                    directionX = invertState ? 1 : -1;  
                } else if (!pressed){
                    super.moving = false;
                    currentAction = super.actions.get(MOVE1_ACTION).execute();
                    super.moveState = super.moving = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (pressed && !super.moving) {
                    super.moving = true;
                    currentAction = super.actions.get(WALKING_RIGHT_ACTION).execute();
                    super.currentKeyAction = WALKING_RIGHT_ACTION;
                    super.moveState = super.moving = true;
                    directionX = invertState ? -1 : 1;                    
                } else if (!pressed){
                    super.moving = false;
                    currentAction = super.actions.get(MOVE1_ACTION).execute();
                    super.moveState = super.moving = false;
                }
                break;
        }                                

        return false;
    }

}
