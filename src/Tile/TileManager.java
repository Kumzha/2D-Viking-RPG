package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNumber[][]; 


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        mapTileNumber = new int[gp.getMaxColumns()][gp.getMaxRows()];

        getTimeImage();
        
        loadMap();
    }

    public void getTimeImage(){
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/Sprites/Tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/Sprites/Tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/Sprites/Tiles/water01.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/Sprites/Tiles/floor01.png"));
            
        } catch (IOException e) {
            System.out.println("Tile image not found");
        }

    }

    public void loadMap(){

        try {

            //Works
            InputStream is = getClass().getResourceAsStream("map01.txt");

            //PROBLEM
            // InputStream is = getClass().getResourceAsStream("/res/Maps/map01.txt");

            if(is == null){
                System.out.println("is == null");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));



            int coll = 0;
            int row = 0;

            while(coll<gp.getMaxColumns() && row<gp.getMaxRows()){

                String line = br.readLine();

                while(coll<gp.getMaxColumns()){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[coll]);

                    // System.out.println(num);

                    mapTileNumber[coll][row] = num;

                    coll++;
                    
                }

                if(coll == gp.getMaxColumns()){
                    coll = 0;
                    row++;
                }

            }
            br.close();
            
        } catch (Exception e) {
            System.out.println("Map not found");
        }

    }
    public void draw(Graphics2D g2){
       int coll = 0;
       int row = 0;
       int x = 0;
       int y = 0;

       while(coll<gp.getMaxColumns() && row<gp.getMaxRows()){

            int tileNum = mapTileNumber[coll][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            coll++;
            x += gp.getTileSize();

            if(coll == gp.getMaxColumns()){
                coll = 0;
                row++;
                x = 0;
                y += gp.getTileSize();
            }

       }

    }
}
