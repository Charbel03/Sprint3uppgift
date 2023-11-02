import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class actionListener extends FramePuzzle implements ActionListener {
    public FramePuzzle fifteenPuzzle;

    public actionListener(FramePuzzle fifteenPuzzle) {
        this.fifteenPuzzle = fifteenPuzzle;
    }

    @Override
    public void actionPerformed(ActionEvent event) {


        JButton buttonPressed = (JButton) event.getSource();
        int index = fifteenPuzzle.indexOf(buttonPressed.getText());
        int row = index / FramePuzzle.dim;
        int column = index % FramePuzzle.dim;

        fifteenPuzzle.makeMove(row, column);

        if (fifteenPuzzle.isGameDone()) {
            JOptionPane.showMessageDialog(null, "You Win The Game.");
        }
    }
}




