// Lawrence's program:

import javax.swing.*;
import java.awt.*;
public class DrMariox2
{
    public static void main(String[] args)
    {
        JFrame start;
        Container contentPane;
        Graphics g;
        
        start = new JFrame("Dr. Mario");
        start.setSize(500,500);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        
        contentPane = start.getContentPane();
        g = contentPane.getGraphics();
        contentPane.setBackground(Color.BLUE);
        
        try{Thread.sleep(800);} catch (Exception e) {}
        
        Color[] colors = new Color[5];
        colors[0] = new Color(255,255,255);//white
        colors[1] = new Color(0,0,0);//black
        colors[2] = new Color(255, 218, 185);//peach
        colors[3] = new Color(116, 7, 7);//brown
        colors[4] = new Color(0, 255, 0);//green
        
        // I wasted my life doing this instead of loops until row 14.....
        g.setColor(colors[1]);//black
        g.fillRect(170, 20, 140, 20);//
        g.fillRect(130, 40, 40, 20);//
        g.fillRect(310, 40, 40, 20);//
        g.fillRect(110, 60, 20, 20);//
        g.fillRect(350, 60, 20, 20);//
        g.fillRect(110, 80, 20, 20);//
        g.fillRect(350, 80, 20, 20);//
        g.fillRect(90, 100, 20, 20);//
        g.fillRect(190, 100, 20, 20);//
        g.fillRect(270, 100, 20, 20);//
        g.fillRect(370, 100, 20, 20);//
        g.fillRect(90, 120, 20, 20);//
        g.fillRect(210, 120, 60, 20);//
        g.fillRect(370, 120, 20, 20);//
        g.fillRect(70, 140, 20, 20);//
        g.fillRect(170, 140, 40, 20);//
        g.fillRect(270, 140, 40, 20);//
        g.fillRect(390, 140, 20, 20);//
        g.fillRect(50, 160, 20, 20);//
        g.fillRect(150, 160, 20, 20);//
        g.fillRect(310, 160, 20, 20);//
        g.fillRect(410, 160, 20, 20);//
        g.fillRect(30, 180, 20, 20);
        g.fillRect(210, 180, 20, 20);
        g.fillRect(250, 180, 20, 20);
        g.fillRect(430, 180, 20, 20);
        g.fillRect(30, 200, 20, 20);
        g.fillRect(210, 200, 20, 20);
        g.fillRect(250, 200, 20, 20);
        g.fillRect(430, 200, 20, 20);
        g.fillRect(30, 220, 20, 20);
        g.fillRect(430, 220, 20, 20);
        g.fillRect(50, 240, 20, 20);
        g.fillRect(410, 240, 20, 20);
        g.fillRect(70, 260, 20, 20);
        g.fillRect(390, 260, 20, 20);
        
        g.setColor(colors[3]);//brown
        g.fillRect(170, 40, 40, 20);//
        g.fillRect(270, 40, 40, 20);//
        g.fillRect(130, 60, 60, 20);// 
        g.fillRect(290, 60, 60, 20);//
        g.fillRect(130, 80, 60, 20);// 
        g.fillRect(290, 80, 60, 20);//
        g.fillRect(110, 120, 100, 20);//
        g.fillRect(270, 120, 100, 20);//
        g.fillRect(90, 140, 40, 20);//
        g.fillRect(150, 140, 20, 20);//
        g.fillRect(310, 140, 20, 20);//
        g.fillRect(350, 140, 40, 20);//
        g.fillRect(110, 160, 20, 20);//
        g.fillRect(350, 160, 20, 20);//
        g.fillRect(110, 180, 20, 20);
        g.fillRect(350, 180, 20, 20);
        g.fillRect(70, 200, 20, 20);
        g.fillRect(110, 200, 40, 20);
        g.fillRect(330, 200, 40, 20);
        g.fillRect(390, 200, 20, 20);
        g.fillRect(90, 220, 40, 20);
        g.fillRect(370, 220, 20, 20);
        g.fillRect(370, 240, 20, 20);
        g.fillRect(150, 260, 40, 20);
        g.fillRect(290, 260, 40, 20);
             
        g.setColor(colors[0]);//white
        g.fillRect(210, 40, 60, 20);//
        g.fillRect(190, 60, 100, 20);//
        g.fillRect(190, 80, 100, 20);//
        g.fillRect(110, 100, 80, 20);//
        g.fillRect(210, 100, 60, 20);//
        g.fillRect(290, 100, 80, 20);//
        g.fillRect(170, 160, 60, 20);//
        g.fillRect(250, 160, 60, 20);//
        g.fillRect(170, 180, 40, 20);
        g.fillRect(270, 180, 40, 20);
        g.fillRect(170, 200, 40, 20);
        g.fillRect(270, 200, 40, 20);
        g.fillRect(170, 220, 60, 20);
        g.fillRect(250, 220, 60, 20);
        
        g.setColor(colors[2]);//peach
        g.fillRect(130, 140, 20, 20);//
        g.fillRect(210, 140, 60, 20);//
        g.fillRect(330, 140, 20, 20);//
        g.fillRect(70, 160, 40, 20);//
        g.fillRect(130, 160, 20, 20);//
        g.fillRect(230, 160, 20, 20);//
        g.fillRect(330, 160, 20, 20);//
        g.fillRect(370, 160, 40, 20);//
        g.fillRect(50, 180, 60, 20);
        g.fillRect(130, 180, 40, 20);
        g.fillRect(230, 180, 20, 20);
        g.fillRect(310, 180, 40, 20);
        g.fillRect(370, 180, 60, 20);
        g.fillRect(50, 200, 20, 20);
        g.fillRect(90, 200, 20, 20);
        g.fillRect(150, 200, 20, 20);
        g.fillRect(230, 200, 20, 20);
        g.fillRect(310, 200, 20, 20);
        g.fillRect(370, 200, 20, 20);
        g.fillRect(410, 200, 20, 20);
        g.fillRect(50, 220, 40, 20);
        g.fillRect(130, 220, 40, 20);
        g.fillRect(230, 220, 20, 20);
        g.fillRect(310, 220, 60, 20);
        g.fillRect(390, 220, 40, 20);
        g.fillRect(70, 240, 300, 20);
        g.fillRect(390, 240, 20, 20);
        g.fillRect(90, 260, 60, 20);
        g.fillRect(190, 260, 100, 20);
        g.fillRect(330, 260, 60, 20);
                
        int [] row_14 = {1, 2, 3, 3, 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 2, 1};
        int [] row_15 = {1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 1};
        int [] row_16 = {1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1};
        int [] row_17 = {1, 0, 0, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 0, 0, 1};
        int [] row_18 = {1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 1};
        int [] row_19 = {1, 0, 0, 0, 1, 0, 0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1};
        int [] row_20 = {1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 1, 2, 1, 2, 1, 3, 1, 0, 0, 0, 1, 0, 0, 1};
        int [] row_21 = {1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 1, 0, 1, 0, 1, 3, 1, 0, 0, 0, 1, 0, 0, 1};
        int y = 70;
        for(int x = 0; x < row_14.length; x++)//
        {
            g. setColor(colors[row_14[x]]);
            g.fillRect(y, 280, 20, 20);
            y = y + 20;
        }
        y = 70;
        for(int a = 0; a < row_15.length; a++)//
        {
            g.setColor(colors[row_15[a]]);
            g.fillRect(y, 300, 20, 20);
            y = y + 20;
        }
        y = 70;
        for(int b = 0; b < row_16.length; b++)//
        {
            g.setColor(colors[row_16[b]]);
            g.fillRect(y, 320, 20, 20);
            y = y + 20;
        }
        y = 50;
        for(int c = 0; c < row_17.length; c++)//
        {
            g.setColor(colors[row_17[c]]);
            g.fillRect(y, 340, 20, 20);
            y = y + 20;
        }
        y = 30;
        for(int d = 0; d < row_18.length; d++)//
        {
            g.setColor(colors[row_18[d]]);
            g.fillRect(y, 360, 20, 20);
            y = y + 20;
        }
        y = 10;
        for(int e = 0; e < row_19.length; e++)//
        {
            g.setColor(colors[row_19[e]]);
            g.fillRect(y, 380, 20, 20);
            y = y + 20;
        }
        y = 0;
        for(int f = 0; f < row_20.length; f++)//
        {
            g.setColor(colors[row_20[f]]);
            g.fillRect(y, 400, 20, 20);
            y = y + 20;
        } 
        y = 0;
        for(int h = 0; h < row_21.length ; h++)//
        {
            g.setColor(colors[row_21[h]]);
            g.fillRect(y, 420, 20, 20);
            y = y + 20;
        }
    }
}
