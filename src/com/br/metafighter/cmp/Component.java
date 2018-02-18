/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter.cmp;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author PH
 */
public interface Component {
    void update(long time);
    void draw(Graphics2D g2d);
    boolean keyEvent(KeyEvent e);
}
