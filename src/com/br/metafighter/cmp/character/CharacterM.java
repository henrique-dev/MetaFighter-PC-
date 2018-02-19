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
package com.br.metafighter.cmp.character;

import com.br.metafighter.cmp.GameEntity;
import com.br.metafighter.cmp.character.CharacterAction;
import com.br.metafighter.cmp.graphics.Sprite;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public abstract class CharacterM extends GameEntity {     
        
    public static final int WALKING_LEFT_ACTION = 0;
    public static final int WALKING_RIGHT_ACTION = 1;
    public static final int WALKING_FRONT = 19;
    public static final int WALKING_BACK = 20;
    public static final int MOVE1_ACTION = 2;
    public static final int MOVE2_ACTION = 3;
    public static final int JUMP2_ACTION = 4;
    public static final int JUMP3_ACTION = 5;
    public static final int JUMP1_ACTION = 6;
    public static final int CROUCH_ACTION = 7;
    public static final int CROUCHED_ACTION = 8;
    public static final int PUNCH1_ACTION = 9;
    public static final int PUNCH2_ACTION = 10;
    public static final int KICK1_ACTION = 11;
    public static final int KICK2_ACTION = 12;
    public static final int GUARD_ACTION = 13;
    public static final int DEFEAT1_ACTION = 14;
    public static final int DEFEAT2_ACTION = 15;
    public static final int VICTORY1_ACTION = 16;
    public static final int VICTORY2_ACTION = 17;
    public static final int DAMAGED_ACTION = 18;        
    
    protected String name;    
    protected List<CharacterAction> actions;
    protected Sprite currentSprite;
    protected CharacterAction currentAction;
    protected int currentKeyAction;
    
    protected boolean moveState;
    protected boolean moving;
    protected boolean jumpState;
    protected boolean jumping;
    protected boolean crouchState;
    protected boolean crouching;
    protected boolean guardState;
    protected boolean guarding;
    
    protected int directionX;
    protected float velocityX;
    protected float velocityY;
    protected float acceleration;
    
    protected boolean invertState;
     

    public CharacterM(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.actions = new ArrayList<>(21);
    }   
    
}
