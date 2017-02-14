import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by jlaw on 3/2/2016.
 */
public class Game extends JPanel implements ActionListener, KeyListener {

    private Map map;
    public Entity player, enemy, treasure;
    public Bullet bullet;
    Timer timer;
    boolean upPressed = false, downPressed = false, rightPressed = false, leftPressed = false, spacePressed = false;
    boolean fireDown, fireRight,fireUp,fireLeft;
    ArrayList<Bullet> bullets;
    ArrayList<Entity> enemies;
    long lastTime;

    public Game(){

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300,300));
        setBackground(Color.BLACK);
        frame.add(this);
        frame.addKeyListener(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        map = new Map();
        setup();

        timer = new Timer(1000/60, this);
    }

    public void setup(){
        player = new Entity(map, 1, 1, this);
        treasure = new Entity(map, 7, 7, this);
        enemies = new ArrayList<Entity>();
        enemies.add(new Entity(map, 5, 3, this));
        bullets = new ArrayList<Bullet>();

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).setSpeed(1, 0);
        }
        upPressed = false;
        downPressed  = false;
        rightPressed = false;
        leftPressed = false;
    }

    public void run(){
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        map.paint(g);
        player.paint(g, Color.RED, -1);
        treasure.paint(g, Color.YELLOW, 0);
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).paint(g, Color.BLUE, -1);
        }
        for(int i  = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g, Color.WHITE);
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            upPressed = true;
            fireUp = true;
            fireDown = false;
            fireLeft = false;
            fireRight = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            downPressed = true;
            fireUp = false;
            fireDown = true;
            fireLeft = false;
            fireRight = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightPressed = true;
            fireUp = false;
            fireDown = false;
            fireLeft = false;
            fireRight = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            leftPressed = true;
            fireUp = false;
            fireDown = false;
            fireLeft = true;
            fireRight = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.playerMove();
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).move();
        }
        if(spacePressed) {
            if(System.currentTimeMillis() - lastTime > 50)
            bullets.add(new Bullet(map, this));
            lastTime = System.currentTimeMillis();
        }
        for(int i  = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }
        collision();
        repaint();
    }

    public void collision(){

        for(int i = 0; i < bullets.size(); i++){
            for(int k = 0; k < enemies.size(); k++){
                if(bullets.get(i).getBounds().intersects(enemies.get(k).getBounds())){
                    bullets.remove(i);
                    enemies.remove(i);
                }
            }
        }
        for(int i = 0; i < enemies.size(); i++){
            if(player.getBounds().intersects(enemies.get(i).getBounds())) {
                JOptionPane.showMessageDialog(this, "YOU FAILED TO SAVE THE PRINCESS");
                setup();
            }
        }

        if(player.getBounds().intersects(treasure.getBounds())){
            JOptionPane.showMessageDialog(this, "YOU SAVED THE PRINCESS");
            setup();
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
}
