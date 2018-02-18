/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter;

import com.br.metafighter.cmp.Component;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author PH
 */
public class BallExample implements Component {

    private static final float SPEED = 200; //Velocidade em 20 pixels / segundo
    private static final int SIZE = 20;

    private float x;
    private float y;

    private float vx = SPEED;
    private float vy = SPEED / 2;

    private int screenWidth;
    private int screenHeight;

    public BallExample(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    public void update(long time) {
        //O tempo é em milis. Para obter em segundos, precisamos dividi-lo por 1000.		
        x += (time * vx) / 1000;
        y += (time * vy) / 1000;
        checkCollision();
    }

    private void checkCollision() {
        //Testamos se a bola saiu da tela
        //Se sair, recolocamos na tela e invertemos a velocidade do eixo
        //Isso fará a bola "quicar".		
        if (x < 0) { //Lateral esquerda
            vx = -vx;
            x = 0;
        } else if ((x + SIZE) > screenWidth) { //Lateral direita
            vx = -vx;
            x = screenWidth - SIZE;
        }

        if (y < 0) { //topo
            vy = -vy;
            y = 0;
        } else if (((y + SIZE) > screenHeight)) { //baixo
            vy = -vy;
            y = screenHeight - SIZE;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Graphics2D g = (Graphics2D) g2d.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.RED);
        g.fill(new Ellipse2D.Float(x, y, SIZE, SIZE));
        g.dispose();
    }

    @Override
    public boolean keyEvent(KeyEvent e) {
        
        return false;
    }

}
