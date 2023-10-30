import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class actionListener extends FramePuzzle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) throws IllegalArgumentException {
        JButton buttonPressed = (JButton) event.getSource();
        int index = indexOf(buttonPressed.getText());
        if (index == -1) {
            throw (new IllegalArgumentException("Index should be between 0-15"));
        }
        int row = index / DIM;
        int column = index % DIM;


        makeMove(row, column);


        if (isGameDone()) {
            JOptionPane.showMessageDialog(null, "You Win The Game.");
        }

        if (event.getSource() == newGame){
            //shuffle metod
        }
    }



}
