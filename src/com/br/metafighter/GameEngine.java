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
package com.br.metafighter;

import com.br.metafighter.cmp.LoopSteps;
import com.br.metafighter.cmp.graphics.Sprite;
import com.br.metafighter.screens.MatchScreen;
import com.br.metafighter.window.Screen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class GameEngine extends JFrame implements LoopSteps {
    
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 800;
    
    private Screen currentScreen;

    private GameLoop gameLoop = new GameLoop(this, 60);

    private long previous = System.currentTimeMillis();

    public GameEngine() {
        super("Meta Fighter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        // Define a janela para o modo tela cheia
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);                        
        
        setResizable(false);
        setIgnoreRepaint(true);
        setLocationRelativeTo(null);        
        addKeyListener(new TAdapter());
    }

    public void startMainLoop() {
        new Thread(gameLoop, "Main Loop").start();
    }    

    @Override
    public void start() {

        createBufferStrategy(2);                        
        currentScreen = new MatchScreen();                
        
    }

    @Override
    public void update() {
        //Calcula o tempo entre dois updates
        long time = System.currentTimeMillis() - previous;                        
        
        currentScreen.update(time);

        //Grava o tempo na saída do método
        previous = System.currentTimeMillis();
    }

    @Override
    public void renderize() {
        Graphics g = getBufferStrategy().getDrawGraphics();
        //Criamos um contexto gráfico que não leva em conta as bordas
        Graphics g2 = g.create(getInsets().left,
                getInsets().top,
                getWidth() - getInsets().right,
                getHeight() - getInsets().bottom);
        
        // Limpamos a tela
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        currentScreen.renderize((Graphics2D) g2);
        
        // Liberamos os contextos criados
        g.dispose();
        g2.dispose();
    }       

    @Override
    public void draw() {
        if (!getBufferStrategy().contentsLost())
            getBufferStrategy().show();
    }

    @Override
    public void stop() {
        
    }

    private class GameLoop implements Runnable {

        public static final int DEFAULT_UPS = 80;
        public static final int DEFAULT_NO_DELAYS_PER_YIELD = 16;
        public static final int DEFAULT_MAX_FRAME_SKIPS = 5;

        private LoopSteps game;
        private long desiredUpdateTime;
        private boolean running;

        private long afterTime;
        private long beforeTime = System.currentTimeMillis();

        private long overSleepTime = 0;
        private long excessTime = 0;

        private int noDelayPerYield = DEFAULT_NO_DELAYS_PER_YIELD;
        private int maxFrameSkips = DEFAULT_MAX_FRAME_SKIPS;

        int noDelays = 0;

        public GameLoop(LoopSteps loopSteps, int ups, int maxFrameSkips, int noDelayPerYield) {
            super();
            if (ups < 1) {
                throw new IllegalArgumentException("Voce deve fornecer ao menos 1 FPS");
            }

            if (ups > 1000) {
                ups = 1000;
            }

            this.game = loopSteps;
            this.desiredUpdateTime = 1000000000L / ups;
            this.running = true;
            this.maxFrameSkips = maxFrameSkips;
            this.noDelayPerYield = noDelayPerYield;
        }

        public GameLoop(LoopSteps loopSteps, int ups) {
            this(loopSteps, ups, DEFAULT_MAX_FRAME_SKIPS, DEFAULT_NO_DELAYS_PER_YIELD);
        }

        public GameLoop(LoopSteps loopSteps) {
            this(loopSteps, DEFAULT_UPS);
        }

        private void sleep(long nanos) {
            try {
                noDelays = 0;
                long beforeSleep = System.nanoTime();
                Thread.sleep(nanos / 1000000L, (int) (nanos % 1000000L));
                overSleepTime = System.nanoTime() - beforeSleep - nanos;
            } catch (InterruptedException e) {
            }
        }

        private void yieldIfNeed() {
            if (++noDelays == noDelayPerYield) {
                Thread.yield();
                noDelays = 0;
            }
        }

        private long calculateSleepTime() {
            return desiredUpdateTime - (afterTime - beforeTime) - overSleepTime;
        }

        @Override
        public void run() {
            running = true;

            try {
                game.start();
                while (running) {
                    beforeTime = System.nanoTime();
                    skipFramesInExcessTime();

                    game.update();
                    game.renderize();
                    game.draw();

                    afterTime = System.nanoTime();
                    long sleepTime = calculateSleepTime();
                    if (sleepTime >= 0) {
                        sleep(sleepTime);
                    } else {
                        excessTime -= sleepTime;
                        overSleepTime = 0L;
                        yieldIfNeed();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                running = false;
                game.stop();
                System.exit(0);
            }
        }

        private void skipFramesInExcessTime() {
            int skips = 0;
            while ((excessTime > desiredUpdateTime) && (skips < maxFrameSkips)) {
                excessTime -= desiredUpdateTime;
                game.update();
                skips++;
            }
        }

        public void stop() {
            running = false;
        }

    }
    
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            
            switch (key){
                case KeyEvent.VK_M:
                    System.out.println("PRESSIONOU");
                break;
                case KeyEvent.VK_LEFT:
                    System.out.println("PRESSINOU DE NOVO");
                break;
                    
            }
        }
        
    }        

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameEngine g = new GameEngine();
                g.setVisible(true);
                g.startMainLoop();
            }
        });
    }

}
