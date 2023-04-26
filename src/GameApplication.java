import javax.swing.*;

public class GameApplication{
    public static void main(String[] args) throws Exception {
        
        JFrame game = new JFrame();
        game.setTitle("Kirby's Adventure");
        game.setContentPane(new GamePanel());
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.pack();
        game.setVisible(true);

    }
}
