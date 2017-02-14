import jdk.nashorn.internal.ir.Block;

import java.awt.*;

/**
 * Created by jlaw on 3/2/2016.
 */
public class Map {


    private final int CLEAR = 0;
    private final int BLOCKED = 1;
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    public final int TILE_SIZE = 30;

    private int[][] data = new int[WIDTH][HEIGHT];

    public Map(){


        for(int x = 0; x < WIDTH; x ++){
            for(int y = 0; y < HEIGHT; y++){

                data[x][y] = BLOCKED;

            }
        }

        for(int x = 1; x < 9; x++){
            data[1][x] = CLEAR;
        }

        for(int x = 1; x < 9; x++){
            data[3][x] = CLEAR;
        }

        for(int y = 1; y < 9; y++){
            for(int x = 5; x < 9; x++){
                data[x][y] = CLEAR;
            }
        }

        data[5][2] = BLOCKED;
        data[6][2] = BLOCKED;
        data[7][2] = BLOCKED;
        data[5][4] = BLOCKED;
        data[7][4] = BLOCKED;
        data[8][4] = BLOCKED;
        data[4][1] = CLEAR;
        data[2][8] = CLEAR;
    }


    public void paint(Graphics g){
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){

                g.setColor(Color.darkGray);
                if(data[x][y] == BLOCKED){
                    g.setColor(Color.gray);
                }

                g.fillRect(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(g.getColor().darker());
                g.drawRect(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public boolean blocked(int x, int y){

        return data[x][y] == BLOCKED;
    }

}
