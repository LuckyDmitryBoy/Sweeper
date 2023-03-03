import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import swepeer.Box;
import swepeer.Coord;
import swepeer.Game;
import swepeer.Ranges;

public class JavaSweeper extends JFrame {
    private Game game;
    private final int ROWS=9;
    private final int COLS=9;
    private final int BOMBS=81;
    private JLabel label;
    private final int IMAGE_SIZE=50;
    public static void main(String[] args) {
        new JavaSweeper();
    }
  private JPanel panel;
    private JavaSweeper() {
        game=new Game(COLS,ROWS,BOMBS);
        game.start();
        setImages();
        initLabele();
        initPanel();
    initFrame();
    }
    private void initLabele(){
        label=new JLabel("Welcom!!!");
        add(label,BorderLayout.SOUTH);
    }
    private void initPanel(){
        panel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord:Ranges.getAllCoords()){

                g.drawImage((Image) game.getBox(coord).image,coord.x*IMAGE_SIZE,coord.y*IMAGE_SIZE,this);}
            }};
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x=e.getX()/IMAGE_SIZE;
                int y=e.getY()/IMAGE_SIZE;
                Coord coord=new Coord(x,y);
                if(e.getButton()==MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if(e.getButton()==MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if(e.getButton()==MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                    panel.repaint();

            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x*IMAGE_SIZE,Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()){
            case PLAYED:return "Lucky";
            case BOMBED:return "You lose";
            case WINNER:return "You WINNER!!!";
            default:return "Good day)";
        }
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Sweeper");
        setResizable(false);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }
    private void setImages(){  //картинки будут подгружены, но не будут отображаться
        for (Box box:Box.values())
            box.image=getImage(box.name());
    }
    private Image getImage(String name){
        String filename=name.toLowerCase()+".jpeg";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
    return icon.getImage();
    }
}
