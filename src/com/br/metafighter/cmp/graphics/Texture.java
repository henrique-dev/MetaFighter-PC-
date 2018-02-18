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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Texture{
        
    private BufferedImage image = null;
    private boolean imageLoaded;
    private ByteArrayOutputStream imageNoLoaded;
    
    public Texture(String path){        
                
        try{
            image = ImageIO.read(new File(path));            
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }                                        
        
    }
    
    public Texture(BufferedImage image){
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }        
    
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){
        BufferedImage tempBfImage = new BufferedImage(width, height, type);
        Graphics2D g = tempBfImage.createGraphics();        
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        
        return tempBfImage;
    }
    
    public static BufferedImage cropImage(BufferedImage src, Rectangle rect){
        return src.getSubimage(rect.x, rect.y, rect.width, rect.height);
    }
    
}
