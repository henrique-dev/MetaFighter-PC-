/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.metafighter;

import com.br.metafighter.cmp.graphics.Sprite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author PH
 */
public class GameEngine extends JFrame implements LoopSteps {

    private GameLoop gameLoop = new GameLoop(this, 60);    
    
    private Personagem guedes;
    private Personagem quele;
    private Personagem romulo;
    private Personagem patricia;
    private Personagem luiz;
    
    private Sprite sprite;

    private long previous = System.currentTimeMillis();

    public GameEngine() {
        super("Meta Fighter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 900);
        setResizable(false);
        setIgnoreRepaint(true);

        setLocationRelativeTo(null);
        /*
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameLoop.stop();
            }
        });
        */
        addKeyListener(new TAdapter());

    }

    public void startMainLoop() {
        new Thread(gameLoop, "Main Loop").start();
    }    

    @Override
    public void start() {

        createBufferStrategy(2);        
        
        guedes = new Personagem("src/img/guedes/sprites01.png", 0, 0, 0, 0);
        quele = new Personagem("src/img/quele/sprites01.png", 300, 0, 0, 0);
        romulo = new Personagem("src/img/romulo/sprites01.png", 600, 0, 0, 0);
        patricia = new Personagem("src/img/patricia/sprites01.png", 0, 450, 0, 0);
        luiz = new Personagem("src/img/luiz/sprites01.png", 300, 450, 0, 0);
        
        
    }

    @Override
    public void update() {
        //Calcula o tempo entre dois updates
        long time = System.currentTimeMillis() - previous;        
        
        guedes.update(time);
        quele.update(time);
        romulo.update(time);
        patricia.update(time);
        luiz.update(time);
        

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
        
        //ball.draw((Graphics2D)g2); // desenhamos a bola
        guedes.renderize((Graphics2D)g2);
        quele.renderize((Graphics2D)g2);
        romulo.renderize((Graphics2D)g2);
        patricia.renderize((Graphics2D)g2);
        luiz.renderize((Graphics2D)g2);
        
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
    public void finalize() {

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
                game.finalize();
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
