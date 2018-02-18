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
package com.br.metafighter.cmp.graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Sprite {
    
    private Texture texture;
    
    public Sprite(Texture texture){
        this.texture = texture;
    }
    
    public Sprite(BufferedImage image){
        this.texture = new Texture(image);
    }
    
    public Texture getTexture(){
        return this.texture;
    }
    
    public void setTexture(Texture texture){
        this.texture = texture;
    }
    
    public static Sprite[] getSpriteFromTexture(Texture texture, int numberOfSpriteInLines, int numberOfSpriteInColumns, int maxSprites){
        int counter = 0;
        
        int spriteWidth = texture.getImage().getWidth() / numberOfSpriteInColumns;
        int spriteHeight = texture.getImage().getHeight() / numberOfSpriteInLines;                
        
        Sprite sprites[] = new Sprite[maxSprites];
        int cont = 0;
        
        for (int i=0; i<numberOfSpriteInLines; i++){
            for (int j=0; j<numberOfSpriteInColumns; j++){
                sprites[cont++] = new Sprite(Texture.cropImage(texture.getImage(), new Rectangle(j * spriteWidth, i * spriteHeight, spriteWidth, spriteHeight)));
                counter++;
                if (counter == maxSprites)
                    break;                
            }
        }
        
        return sprites;
    }
    
    public static Sprite[] getSpriteFromSprites(Sprite[] sprites, int indexBegin, int indexEnd, boolean reverse){
        
        Sprite cSprite[] = new Sprite[(indexEnd+1) - indexBegin];
        
        int counter = 0;
        for (int i=indexBegin; i<=indexEnd; i++){
            if (!reverse)
                cSprite[counter] = sprites[i];
            else
                cSprite[cSprite.length-1 - counter] = sprites[i];
            counter++;
        }
        
        return cSprite;
    }
    
}
