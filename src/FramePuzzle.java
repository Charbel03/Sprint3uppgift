import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class FramePuzzle extends JFrame {

    public static final int DIM = 4;

    public static final int SIZE = DIM * DIM;

    public static final int HEIGHT = 400;

    public static final int WIDTH = 400;

    public int emptyCell = DIM * DIM;

    public JButton[][] board = new JButton[DIM][DIM];
    public JFrame frame;
    public JPanel panel = new JPanel();
    public JPanel panel1 = new JPanel();
    public JButton newGame = new JButton("New Game");
    public boolean isSolvable(ArrayList<Integer> list) {

        int inversionSum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                inversionSum += ((i / DIM) + 1);
                continue;
            }

            int count = 0;
            for (int j = i + 1; j < list.size(); j++) {

                if (list.get(j) == 0) {
                    continue;
                } else if (list.get(i) > list.get(j)) {
                    count++;
                }
            }
            inversionSum += count;
        }
        return ((inversionSum & 1) == 0) ? true : false;
    }

    public void puzzleFrame () {
        ArrayList<Integer> intialList = new ArrayList<Integer>(SIZE);


        for (boolean isSolvable = false; isSolvable == false;) {


            intialList = new ArrayList<Integer>(SIZE);
            for (int i = 0; i < SIZE; i++) {
                intialList.add(i, i);
            }


            Collections.shuffle(intialList);

            isSolvable = isSolvable(intialList);
        }
        System.out.println("Initial Board state:" + intialList);


        for (int index = 0; index < SIZE; index++) {
            final int ROW = index / DIM;
            final int COL = index % DIM;
            board[ROW][COL] = new JButton(String.valueOf(intialList.get(index)));

            if (intialList.get(index) == 0) {
                emptyCell = index;
                board[ROW][COL].setVisible(false);
            }
            board[ROW][COL].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            board[ROW][COL].setBackground(Color.WHITE);
            board[ROW][COL].setForeground(Color.BLACK);
            board[ROW][COL].addActionListener(new actionListener());
            panel.add(board[ROW][COL]);
        }

        frame = new JFrame("15 Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(HEIGHT, WIDTH);
        frame.setLocationRelativeTo(null);


        panel.setLayout(new GridLayout(DIM, DIM));
        panel.setBackground(Color.GRAY);


        java.awt.Container content = frame.getContentPane();
        content.add(panel, BorderLayout.CENTER);
        content.add(panel1, BorderLayout.SOUTH);
        panel1.add(newGame);
        content.setBackground(Color.GRAY);
        frame.setVisible(true);
    }


}
