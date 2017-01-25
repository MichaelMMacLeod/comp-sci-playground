
import javax.swing.*;
import java.awt.*;
public class DrMario
{
    public static void main(String[] args)
    {
        JFrame start;
        Container contentPane;
        Graphics g;
        
        start = new JFrame("Dr. Mario");
        start.setSize(250,250);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        
        contentPane = start.getContentPane();
        g = contentPane.getGraphics();
        contentPane.setBackground(Color.BLUE);
        
        try{Thread.sleep(400);} catch (Exception e) {}
        
        Color[] colors = new Color[5];
        colors[0] = new Color(255,255,255);//white
        colors[1] = new Color(0,0,0);//black
        colors[2] = new Color(255, 218, 185);//peach
        colors[3] = new Color(116, 7, 7);//brown
        colors[4] = new Color(0, 255, 0);//green
        
        // I wasted my life doing this instead of loops until row 14.....
        g.setColor(colors[1]);//black
        g.fillRect(85, 10, 70, 10);//row one
        g.fillRect(65, 20, 20, 10);//row two
        g.fillRect(155, 20, 20, 10);//row two
        g.fillRect(55, 30, 10, 10);//row three
        g.fillRect(175, 30, 10, 10);//row three
        g.fillRect(55, 40, 10, 10);//row four
        g.fillRect(175, 40, 10, 10);//row four
        g.fillRect(45, 50, 10, 10);//row five
        g.fillRect(95, 50, 10, 10);//row five
        g.fillRect(135, 50, 10, 10);//row five
        g.fillRect(185, 50, 10, 10);//row five
        g.fillRect(45, 60, 10, 10);//row six
        g.fillRect(105, 60, 30, 10);//row six
        g.fillRect(185, 60, 10, 10);//row six
        g.fillRect(35, 70, 10, 10);//row seven
        g.fillRect(85, 70, 20, 10);//row seven
        g.fillRect(135, 70, 20, 10);//row seven
        g.fillRect(195, 70, 10, 10);//row seven
        g.fillRect(25, 80, 10, 10);//row eight
        g.fillRect(75, 80, 10, 10);//row eight
        g.fillRect(155, 80, 10, 10);//row eight
        g.fillRect(205, 80, 10, 10);//row eight
        g.fillRect(15, 90, 10, 10);
        g.fillRect(105, 90, 10, 10);
        g.fillRect(125, 90, 10, 10);
        g.fillRect(215, 90, 10, 10);
        g.fillRect(15, 100, 10, 10);
        g.fillRect(105, 100, 10, 10);
        g.fillRect(125, 100, 10, 10);
        g.fillRect(215, 100, 10, 10);
        g.fillRect(15, 110, 10, 10);
        g.fillRect(215, 110, 10, 10);
        g.fillRect(25, 120, 10, 10);
        g.fillRect(205, 120, 10, 10);
        g.fillRect(35, 130, 10, 10);
        g.fillRect(195, 130, 10, 10);
        
        g.setColor(colors[3]);//brown
        g.fillRect(85, 20, 20, 10);//row two
        g.fillRect(135, 20, 20, 10);//row two
        g.fillRect(65, 30, 30, 10);// row three
        g.fillRect(145, 30, 30, 10);//row three
        g.fillRect(65, 40, 30, 10);// row four
        g.fillRect(145, 40, 30, 10);//row four
        g.fillRect(55, 60, 50, 10);//row six
        g.fillRect(135, 60, 50, 10);//row six
        g.fillRect(45, 70, 20, 10);//row seven
        g.fillRect(75, 70, 10, 10);//row seven
        g.fillRect(155, 70, 10, 10);//row seven
        g.fillRect(175, 70, 20, 10);//row seven
        g.fillRect(55, 80, 10, 10);//row eight
        g.fillRect(175, 80, 10, 10);//row eight
        g.fillRect(55, 90, 10, 10);
        g.fillRect(175, 90, 10, 10);
        g.fillRect(35, 100, 10, 10);
        g.fillRect(55, 100, 20, 10);
        g.fillRect(165, 100, 20, 10);
        g.fillRect(195, 100, 10, 10);
        g.fillRect(45, 110, 20, 10);
        g.fillRect(185, 110, 10, 10);
        g.fillRect(185, 120, 10, 10);
        g.fillRect(75, 130, 20, 10);
        g.fillRect(145, 130, 20, 10);
             
        g.setColor(colors[0]);//white
        g.fillRect(105, 20, 30, 10);//row two
        g.fillRect(95, 30, 50, 10);//row three
        g.fillRect(95, 40, 50, 10);//row four
        g.fillRect(55, 50, 40, 10);//row five
        g.fillRect(105, 50, 30, 10);//row five
        g.fillRect(145, 50, 40, 10);//row five
        g.fillRect(85, 80, 30, 10);//row eight
        g.fillRect(125, 80, 30, 10);//row eight
        g.fillRect(85, 90, 20, 10);
        g.fillRect(135, 90, 20, 10);
        g.fillRect(85, 100, 20, 10);
        g.fillRect(135, 100, 20, 10);
        g.fillRect(85, 110, 30, 10);
        g.fillRect(125, 110, 30, 10);
        
        g.setColor(colors[2]);//peach
        g.fillRect(65, 70, 10, 10);//row seven
        g.fillRect(105, 70, 30, 10);//row seven
        g.fillRect(165, 70, 10, 10);//row seven
        g.fillRect(35, 80, 20, 10);//row eight
        g.fillRect(65, 80, 10, 10);//row eight
        g.fillRect(115, 80, 10, 10);//row eight
        g.fillRect(165, 80, 10, 10);//row eight
        g.fillRect(185, 80, 20, 10);//row eight
        g.fillRect(25, 90, 30, 10);
        g.fillRect(65, 90, 20, 10);
        g.fillRect(115, 90, 10, 10);
        g.fillRect(155, 90, 20, 10);
        g.fillRect(185, 90, 30, 10);
        g.fillRect(25, 100, 10, 10);
        g.fillRect(45, 100, 10, 10);
        g.fillRect(75, 100, 10, 10);
        g.fillRect(115, 100, 10, 10);
        g.fillRect(155, 100, 10, 10);
        g.fillRect(185, 100, 10, 10);
        g.fillRect(205, 100, 10, 10);
        g.fillRect(25, 110, 20, 10);
        g.fillRect(65, 110, 20, 10);
        g.fillRect(115, 110, 10, 10);
        g.fillRect(155, 110, 30, 10);
        g.fillRect(195, 110, 20, 10);
        g.fillRect(35, 120, 150, 10);
        g.fillRect(195, 120, 10, 10);
        g.fillRect(45, 130, 30, 10);
        g.fillRect(95, 130, 50, 10);
        g.fillRect(165, 130, 30, 10);
                
        int [] row_14 = {1, 2, 3, 3, 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 2, 1};
        int [] row_15 = {1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 1};
        int [] row_16 = {1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1};
        int [] row_17 = {1, 0, 0, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 0, 0, 1};
        int [] row_18 = {1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 1};
        int [] row_19 = {1, 0, 0, 0, 1, 0, 0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1};
        int [] row_20 = {1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 1, 2, 1, 2, 1, 3, 1, 0, 0, 0, 1, 0, 0, 1};
        int [] row_21 = {1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 1, 0, 1, 0, 1, 3, 1, 0, 0, 0, 1, 0, 0, 1};
        int y = 35;
        for(int x = 0; x < row_14.length; x++)//row 14
        {
            g. setColor(colors[row_14[x]]);
            g.fillRect(y, 140, 10, 10);
            y = y + 10;
        }
        y = 35;
        for(int a = 0; a < row_15.length; a++)//row 15
        {
            g.setColor(colors[row_15[a]]);
            g.fillRect(y, 150, 10, 10);
            y = y + 10;
        }
        y = 35;
        for(int b = 0; b < row_16.length; b++)//row 16
        {
            g.setColor(colors[row_16[b]]);
            g.fillRect(y, 160, 10, 10);
            y = y + 10;
        }
        y = 25;
        for(int c = 0; c < row_17.length; c++)//row 17
        {
            g.setColor(colors[row_17[c]]);
            g.fillRect(y, 170, 10, 10);
            y = y + 10;
        }
        y = 15;
        for(int d = 0; d < row_18.length; d++)//row 18
        {
            g.setColor(colors[row_18[d]]);
            g.fillRect(y, 180, 10, 10);
            y = y + 10;
        }
        y = 5;
        for(int e = 0; e < row_19.length; e++)//row 19
        {
            g.setColor(colors[row_19[e]]);
            g.fillRect(y, 190, 10, 10);
            y = y + 10;
        }
        y = 0;
        for(int f = 0; f < row_20.length; f++)//row 20
        {
            g.setColor(colors[row_20[f]]);
            g.fillRect(y, 200, 10, 10);
            y = y + 10;
        } 
        y = 0;
        for(int h = 0; h < row_21.length ; h++)//row 21
        {
            g.setColor(colors[row_21[h]]);
            g.fillRect(y, 210, 10, 10);
            y = y + 10;
        }
    }
}