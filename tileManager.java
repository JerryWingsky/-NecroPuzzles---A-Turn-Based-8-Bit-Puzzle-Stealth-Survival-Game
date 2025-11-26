package tileCode;

import gamePanel.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public final class tileManager{
    GamePanel gp;
    tile[] tile;
    int mapTileNum[][];
    // Just importing everything above.
    //A constructor.
    public tileManager(GamePanel gp, int level){
        this.gp = gp;

        tile = new tile[100]; // Max amount of tile.
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/level" + level + ".txt"); // This is for loading the map data.
        setCollisionForTiles(new int[]{1,2,3,4,5,6,7,8,9,10,13,16,17,18,19,20,26,27,28,29,36,37,39,40,46,50,53,54,56,57,59,60,63,64,66,70,74,76,77,79,80,82,83,86,87,88,89,90,92,93,97,98,99});
        setDeathForTiles(new int[]{25,35,55,65});
        setVictoryForTiles(new int []{33,42});
        setZombiePathTiles(new int []{24,25});
        setPlayerPathTiles(new int[]{34});
}

    public void updateTile(int col, int row, int newValue) {
        mapTileNum[col][row] = newValue;
}

    public void getTileImage(){
        try {
            tile[0] = new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/void.png"));

            for (int i = 1; i <= 99; i++) {
                tile[i] = new tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile" + i + ".png"));
}
        } catch (IOException e){
            e.printStackTrace();
}
}
    
    public void loadMap(String filepath){
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while (col<gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while (col<gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
}
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
}
}
            br.close();
        } catch(Exception e){
            e.printStackTrace();
}
}

    // Used when instantiating the Tile Manager.
    public void setCollisionForTiles(int[] indices) {
        for (int i : indices) {
            if (i >= 0 && i < tile.length) {
                tile[i].collision = true;
                System.out.println("Tiles collision activated : "+i);
}
}
}

    // Used when instantiating the Tile Manager.
    public void setDeathForTiles(int[] indices) {
        for (int i : indices) {
            if (i >= 0 && i < tile.length) {
                tile[i].death = true;
                System.out.println("Tiles death activated : "+i);
}
}
}

    // Used when instantiating the Tile Manager.
    public void setVictoryForTiles(int[] indices) {
        for (int i : indices) {
            if (i >= 0 && i < tile.length) {
                tile[i].victory = true;
                System.out.println("Tiles victory activated : "+i);
}
}
}

    // Used when instantiating the Tile Manager.
    public void setZombiePathTiles(int[] indices) {
        for (int i : indices) {
            if (i >= 0 && i < tile.length) {
                tile[i].zombiePath = true;
                System.out.println("Tiles zombie path activated : "+i);
}
}
}

    // Used when instantiating the Tile Manager.
    public void setPlayerPathTiles(int[] indices) {
        for (int i : indices) {
        if (i >= 0 && i < tile.length) {
            tile[i].playerPath = true;
            System.out.println("Tiles player path activated : "+i);
}
}}

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize, null);
            col++;
            x+=gp.tileSize;
            
            if (col==gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y+=gp.tileSize;
}
}
}
    
    public boolean isTileCollision(int col, int row) {
        int tileNum = mapTileNum[col][row];
        return tile[tileNum].collision;
}

    public boolean isTileDeath(int col, int row) {
        int tileNum = mapTileNum[col][row];
        return tile[tileNum].death;
}

    public boolean isTileVictory(int col, int row) {
        int tileNum = mapTileNum[col][row];
        return tile[tileNum].victory;
}

    public boolean isZombiePath(int col, int row) {
        int tileNum = mapTileNum[col][row];
        return tile[tileNum].zombiePath;
}

    public boolean isPlayerPath(int col, int row) {
        int tileNum = mapTileNum[col][row];
        return tile[tileNum].playerPath;
}
}
