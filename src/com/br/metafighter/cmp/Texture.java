/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.cmp;

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
 * @author PH
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
