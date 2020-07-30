import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;

    GameClient() {
        this(1024, 768);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        init();
        new Thread(()->{
            repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    public void init(){
            playerTank=new Tank(getCenterPosX(47), getCenterPosY(47),Direction.DOWN);
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(playerTank.getImage(),
                playerTank.getX(), playerTank.getY(), null);

    }

    private int getCenterPosX(int width){
        return (screenWidth-width)/2;
    }

    private int getCenterPosY(int height){
        return (screenHeight-height)/2;
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                playerTank.setY(playerTank.getY()-playerTank.getSpeed());
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                playerTank.setY(playerTank.getY()+playerTank.getSpeed());
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                playerTank.setY(playerTank.getX()-playerTank.getSpeed());
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                playerTank.setY(playerTank.getY()+playerTank.getSpeed());
            default:
        }
        repaint();
    }

}
