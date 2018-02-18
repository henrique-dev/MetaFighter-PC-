/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter;

import com.br.metafighter.cmp.Component;
import com.br.metafighter.cmp.GameEntity;
import com.br.metafighter.cmp.Sprite;
import com.br.metafighter.cmp.Texture;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author PH
 */
public class Personagem extends GameEntity implements Component{
    
    private Texture texture;
    private Sprite sprites[];
    
    private int counter = 0;

    public Personagem(String path, int x, int y, int width, int height) {
        super(x, y, width, height);
        
        this.texture = new Texture(path);
        sprites = Sprite.getSpriteFromTexture(texture, 9, 7, 63);
    }    
    

    @Override
    public void update(long time) {
        counter++;
        if (counter == 63*4)
            counter = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Graphics2D g = (Graphics2D) g2d.create();
                
        //g.drawImage(texture.getImage(), null, 0, 0);
        g.drawImage(sprites[counter/4].getTexture().getImage(), null, x, y);
        
        
        
        g.dispose();
    }

    @Override
    public boolean keyEvent(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
