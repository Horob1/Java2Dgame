package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed,cPressed,pPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code =  e.getExtendedKeyCode();
        //game title
        if(gp.gameState== gp.titleState)
        {
            if(gp.ui.titleScreenOption==0){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum==-1){
                        gp.ui.commandNum=2;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum==3){
                        gp.ui.commandNum=0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum==0){
                        gp.ui.titleScreenOption =1;
                        gp.playSE(1);
                    }
                    if(gp.ui.commandNum==1){

                    }
                    if(gp.ui.commandNum==2){
                        System.exit(0);
                    }
                }
            } else if(gp.ui.titleScreenOption==1){
                if(gp.ui.commandNumF2==0){
                    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                        gp.ui.commandNum++;
                        if(gp.ui.commandNum==3){
                            gp.ui.commandNum=0;
                        }
                    }
                    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                        gp.ui.commandNum--;
                        if(gp.ui.commandNum==-1){
                            gp.ui.commandNum=2;
                        }
                    }
                    if (code == KeyEvent.VK_ENTER) {
                        if(gp.ui.commandNum==0){
                            gp.gameState = gp.playState;
                            gp.playSE(1);
                            gp.playMusic(0);
                        }
                        if(gp.ui.commandNum==1){
                            gp.gameState = gp.playState;
                            gp.playSE(1);
                            gp.playMusic(0);
                        }
                        if(gp.ui.commandNum==2){
                            gp.gameState = gp.playState;
                            gp.playSE(1);
                            gp.playMusic(0);
                        }
                    }
                }

                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNumF2--;
                    if(gp.ui.commandNumF2==-1){
                        gp.ui.commandNumF2=1;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNumF2++;
                    if(gp.ui.commandNumF2==2){
                        gp.ui.commandNumF2=0;
                    }
                }
                if(gp.ui.commandNumF2==1){
                    if (code == KeyEvent.VK_ENTER) {
                        gp.ui.titleScreenOption =0;
                        gp.playSE(1);
                    }
                }

            }
        }
        //play
        else if (gp.gameState==gp.playState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;

            }
        }
        //pause game
        else if(gp.gameState==gp.pauseState){
            if (code == KeyEvent.VK_P) {
                gp.gameState= gp.playState;
            }
        }

        // dialog
        else if(gp.gameState==gp.dialogueState){
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;

            }
            if(code == KeyEvent.VK_C) {
                cPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code  = e.getExtendedKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_P) {
            pPressed=false;
        }
    }
}
