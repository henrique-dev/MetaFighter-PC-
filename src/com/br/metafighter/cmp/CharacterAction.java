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

import com.br.metafighter.cmp.graphics.Sprite;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class CharacterAction {
    
    private Sprite[] sprites;
    private int divFrame;
    private int counter;
    private int currentSprite;
    private int totalSprites;
    
    public CharacterAction(Sprite[] sprites, int divFrame){
        this.sprites = sprites;
        this.divFrame = divFrame;                        
        this.totalSprites = sprites.length - 1;
        this.counter = 0;
        this.currentSprite = 0;
    }
    
    public CharacterAction execute(){
        counter = 0;
        currentSprite = 0;
        return this;
    }
    
    public CharacterAction execute(int counter){
        this.counter = counter;
        currentSprite = 0;
        return this;
    }
    
    public Sprite getSprite(){
        currentSprite = counter / divFrame;
        if (currentSprite == totalSprites){
            counter = 0;
        } 
        else{
            counter++;
            return sprites[currentSprite];
        }
        return null;        
    }
    
    public Sprite getSprite(int spriteIndex){
        if (spriteIndex > totalSprites)
            throw new IllegalArgumentException("Esse sprite não existe");
        return sprites[spriteIndex];
    }
    
    
    
    
}
