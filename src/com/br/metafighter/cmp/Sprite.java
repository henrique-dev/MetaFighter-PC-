/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.cmp;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author PH
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
