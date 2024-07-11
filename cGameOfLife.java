import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;  
import java.util.Random;
public class cGameOfLife {  
    
static boolean flip;
static boolean nextF;
cGameOfLife(){
        flip = true;
        nextF = false;
        JFrame f=new JFrame("Conway's Game of Life - by Suryansh aka paneerpulao");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JPanel pan = new JPanel();
        //pan.setBounds(10,60, 380, 380);
        //pan.setBackground(Color.gray);
        JButton autoB=new JButton("Auto");
        JButton nextB=new JButton("Next");
        //JButton pauseB=new JButton("Pause");
        autoB.setBounds(0,0,200, 40);
                autoB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(flip) autoB.setText("Pause");
                        else autoB.setText("Auto");
                        cGameOfLife.this.boolFlip();
                    }
                });
                nextB.setBounds(200, 0, 200, 40);
                nextB.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(!flip) {
                            cGameOfLife.this.boolFlip();
                            autoB.setText("Auto");
                        }
                        cGameOfLife.this.boolNextF();

                        
                        //cGameOfLife.this.nextFrame();
                    }
                });
        f.add(autoB);
        f.add(nextB);
        //f.add(pan);


        //f.add(pauseB);
                
        f.setSize(414,490);
        f.setLayout(null);
        f.setVisible(true);
        int[][] x = new int[38][38];
        Random r = new Random();
        for(int i = 0; i < 38; i++){
            for(int j = 0; j < 38; j++){
                x[i][j] = r.nextInt(2);
            }
        }

        JPanel[][] liveCells = new JPanel[38][38];
        for(int i = 0; i < 38; i++){
            for(int j = 0; j < 38; j++){
                liveCells[i][j] = new JPanel();
                liveCells[i][j].setBounds(10+i*10, 60+j*10, 10, 10);
                f.add(liveCells[i][j]);
            }
        }

        for(int i = 0; i < 38; i++){
            for(int j = 0; j < 38; j++){
                if(x[i][j] == 1) liveCells[i][j].setBackground(Color.black);
                else 
                liveCells[i][j].setBackground(Color.gray);
            }
        }
        //System.out.println("reaching place 1");
        while(true){
            if(!flip){
                this.game(x);
                for(int i = 0; i < 38; i++){
                    for(int j = 0; j < 38; j++){
                        if(x[i][j] == 1) liveCells[i][j].setBackground(Color.black);
                        else liveCells[i][j].setBackground(Color.gray);
                    }
                }
                System.out.println("Next Generation");
                try{
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else if(nextF){
                this.game(x);
                for(int i = 0; i < 38; i++){
                    for(int j = 0; j < 38; j++){
                        if(x[i][j] == 1) liveCells[i][j].setBackground(Color.black);
                        else liveCells[i][j].setBackground(Color.gray);
                    }
                }
                System.out.println("Next Generation");
                nextF = false;
            }
            else {
                System.out.println("Paused");
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            }
        }
    }
    public static void main(String[] args) {  
    cGameOfLife x = new cGameOfLife();
    } 
    void boolFlip() {
        flip = !flip;
    }
    void boolNextF(){
        nextF = !nextF;
    }
    void game(int[][] x){
        int l = x.length, r = x[0].length;
        int t = 0;
        int[][] a = new int[l][r];
        int u, d, o, s;

        for(int i = 0; i < l; i++){
            for(int j = 0; j < r; j++){
                if(i == 0) u = l - 1; else u = i - 1;
                if(i == l-1) d = 0; else d = i + 1;
                if(j == 0) o = r - 1; else o = j - 1;
                if(j == l - 1) s = 0; else s = j + 1;
                t = x[u][j] + x[i][o] + x[d][j] + x[i][s] + x[u][o] + x[d][o] + x[u][s] + x[d][s];
                if(t<2) a[i][j] = 0;
                else if(t==2) a[i][j] = x[i][j];
                else if(t==3) a[i][j] = 1;
                else if(t > 3) a[i][j]= 0;
                t = 0;
            }
        }
        for(int i = 0; i < l; i++){
            for(int j = 0; j < r; j++){
                x[i][j] = a[i][j];
            }
        }
    }
}  