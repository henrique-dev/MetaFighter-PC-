/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter;

import java.awt.Graphics2D;

/**
 *
 * @author PH
 */
public interface Sprite {
    void update(long time);
    void draw(Graphics2D g2d);
}
