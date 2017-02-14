import java.awt.*;

/**
 * Created by jelaw_000 on 3/2/2016.
 */
public class Bullet {

    private int x, y, dx, dy=10;
    private int WIDTH = 10, HEIGHT = 10;
    Map map;
    Game game;

    public Bullet(Map map, Game game){
        this.game = game;
        this.map = map;
        x = game.player.x + game.player.WIDTH/2 - WIDTH/2;
        y = game.player.y + game.player.HEIGHT/2 - HEIGHT/2;

        if(game.fireDown){
            dy = 10;
            dx = 0;
        }
        if (game.fireLeft){
            dx = -10;
            dy = 0;
        }
        if (game.fireRight){
            dx = 10;
            dy = 0;
        }
        if(game.fireUp){
            dy = -10;
            dx = 0;
        }
    }

    public void move(){

        int nextTop = y - 1;
        int nextBottom = y + dy + HEIGHT;
        int nextRight = x + dx + WIDTH;
        int nextLeft = x - 1;

        if (!validLocation(x, nextTop) && !validLocation(x + WIDTH-1, nextTop)) {
           game.bullets.remove(this);

        }
        else if(!validLocation(x, nextBottom) && !validLocation(x + WIDTH-1, nextBottom)){
            game.bullets.remove(this);
        }
        else if(!validLocation(nextRight, y) && !validLocation(nextRight, y + HEIGHT - 1)){
            game.bullets.remove(this);
        }
        else if(!validLocation(nextLeft, y) && !validLocation(nextLeft, y + HEIGHT -1)){
            game.bullets.remove(this);
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

    public void paint(Graphics g, Color color){
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

}
