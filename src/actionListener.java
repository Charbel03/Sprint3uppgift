import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class actionListener implements ActionListener {
    JButton b1 = new JButton();
    public actionListener(JButton buttonFromFramePuzzle) {
        this.b1 = buttonFromFramePuzzle;


    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1){
            System.out.println("hej");
        }


    }


}
