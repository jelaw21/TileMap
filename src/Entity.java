import java.awt.*;

/**
 * Created by jlaw on 3/2/2016.
 */
public class Entity {


    public int x, y, dx, dy;
    public int WIDTH = 20, HEIGHT = 20;
    Map map;
    Game game;

    public Entity(Map map, int x, int y, Game game){
        this.map = map;
        this.game = game;
        this.x = x * map.TILE_SIZE + 5;
        this.y = y * map.TILE_SIZE + 5;
    }

    public void playerMove(){

        if(game.isUpPressed()) {
            int nextTop = y - 2;
            if (validLocation(x, nextTop) && validLocation(x + WIDTH-2, nextTop)) {
                y += -2;
            }
        }

        if(game.isDownPressed()){
            int nextBottom = y + dy + HEIGHT;
            if(validLocation(x, nextBottom) && validLocation(x + WIDTH-2, nextBottom)){
                y +=2;
            }
        }

        if(game.isRightPressed()){
            int nextRight = x + dx + WIDTH;
            if(validLocation(nextRight, y) && validLocation(nextRight, y+ HEIGHT - 2)){
                x += 2;
            }
        }

        if(game.isLeftPressed()){
            int nextLeft = x - 2;
            if(validLocation(nextLeft, y) && validLocation(nextLeft, y + HEIGHT -2)){
                x += -2;
            }
        }

    }

    public void setSpeed(int dx, int dy){

        this.dx = dx;
        this.dy = dy;
    }

    public void move(){

        int nextTop = y - 1;
        int nextBottom = y + dy + HEIGHT;
        int nextRight = x + dx + WIDTH;
        int nextLeft = x - 1;


            if (!validLocation(x, nextTop) && !validLocation(x + WIDTH-1, nextTop)) {
                dy *= -1;
            }
            if(!validLocation(x, nextBottom) && !validLocation(x + WIDTH-1, nextBottom)){
                dy *= -1;
            }
            if(!validLocation(nextRight, y) && !validLocation(nextRight, y + HEIGHT - 1)){
                dx *= -1;
            }
            if(!validLocation(nextLeft, y) && !validLocation(nextLeft, y + HEIGHT -1)){
                dx *= -1;

            }

        x+=dx;
        y+=dy;
    }

    public boolean validLocation(int nx, int ny){


        nx = (nx/map.TILE_SIZE);
        ny = (ny/map.TILE_SIZE);


            if (map.blocked(nx, ny)){
                return false;
            }
            else
                return true;
    }

    public void paint(Graphics g, Color color, int a){


        g.setColor(color);


        if(a == 0){
            g.fillRect(x, y, WIDTH, HEIGHT);
            g.setColor(g.getColor().darker());
            g.drawRect(x, y, WIDTH, HEIGHT);
            g.drawRect(x +1, y +1, WIDTH -2, HEIGHT -2);
            g.setColor(g.getColor().darker());
            g.fillRect(x + 3, y + 3, WIDTH - 5, HEIGHT - 5);
            g.setColor(Color.yellow);
            g.fillRect(x + 5, y + 5, WIDTH - 9, HEIGHT - 9);
        }
        else{
            g.fillOval(x, y, WIDTH, HEIGHT);
            g.setColor(g.getColor().darker());
            g.drawOval(x, y, WIDTH, HEIGHT);
            g.drawOval(x +1, y +1, WIDTH -2, HEIGHT -2);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
