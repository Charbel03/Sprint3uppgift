import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class FramePuzzle extends JFrame {

    public static final int dim = 4;

    public static final int size = dim * dim;

    public static final int hightFrame = 400;

    public static final int wifhtFrame = 400;

    public int emptyCell = dim * dim;

    public JButton[][] board = new JButton[dim][dim];
    public JFrame frame;
    public JPanel panel = new JPanel();
    public JPanel panel1 = new JPanel();

    public JButton newGameButton;




    public int getIndex(int i, int j) {
        return ((i * dim) + j);

    }


    public void puzzleFrame () {
        ArrayList<Integer> intialList = new ArrayList<Integer>(size);


        for (boolean isSolvable = false; isSolvable == false;) {


            intialList = new ArrayList<Integer>(size);
            for (int i = 0; i < size; i++) {
                intialList.add(i, i);
            }


            Collections.shuffle(intialList);


            isSolvable = isSolvable(intialList);
        }
        System.out.println("Initial Board state:" + intialList);




        for (int index = 0; index < size; index++) {
            final int ROW = index / dim;
            final int COL = index % dim;
            board[ROW][COL] = new JButton(String.valueOf(intialList.get(index)));

            if (intialList.get(index) == 0) {
                emptyCell = index;
                board[ROW][COL].setVisible(false);
            }
            board[ROW][COL].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            board[ROW][COL].setBackground(Color.WHITE);
            board[ROW][COL].setForeground(Color.BLACK);
            board[ROW][COL].addActionListener(new actionListener(this));
            panel.add(board[ROW][COL]);
        }
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shufflePuzzle();
            }
        });
        panel1.add(newGameButton);

        frame = new JFrame("15 Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(hightFrame, wifhtFrame);
        frame.setLocationRelativeTo(null);


        panel.setLayout(new GridLayout(dim, dim));
        panel.setBackground(Color.GRAY);


        java.awt.Container content = frame.getContentPane();
        content.add(panel, BorderLayout.CENTER);
        content.add(panel1, BorderLayout.SOUTH);

        content.setBackground(Color.GRAY);
        frame.setVisible(true);

    }


    public boolean isSolvable(ArrayList<Integer> list) {

        if(list.size() != 16)
        {
            System.err.println("isSolvable function works only" +
                    "with a list having 0-16 as values");
        }

        int inversionSum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                inversionSum += ((i / dim) + 1);
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

    public int indexOf(String cellNum) {

        for (int ROW = 0; ROW < board.length; ROW++) {
            for (int COL = 0; COL < board[ROW].length; COL++) {
                if (board[ROW][COL].getText().equals(cellNum)) {
                    return (getIndex(ROW, COL));
                }
            }
        }
        return -1;

    }


    public boolean makeMove(int row, int col) {
        final int emptyRow = emptyCell / dim;
        final int emptyCol = emptyCell % dim;
        int rowDiff = emptyRow - row;
        int colDiff = emptyCol - col;
        boolean isInRow = (row == emptyRow);
        boolean isInCol = (col == emptyCol);
        boolean isNotDiagonal = (isInRow || isInCol);

        if (isNotDiagonal) {
            int diff = Math.abs(colDiff);


            if (colDiff < 0 & isInRow) {
                for (int i = 0; i < diff; i++) {
                    board[emptyRow][emptyCol + i].setText(
                            board[emptyRow][emptyCol + (i + 1)].getText());
                }

            }
            else if (colDiff > 0 & isInRow) {
                for (int i = 0; i < diff; i++) {
                    board[emptyRow][emptyCol - i].setText(
                            board[emptyRow][emptyCol - (i + 1)].getText());
                }
            }

            diff = Math.abs(rowDiff);


            if (rowDiff < 0 & isInCol) {
                for (int i = 0; i < diff; i++) {
                    board[emptyRow + i][emptyCol].setText(
                            board[emptyRow + (i + 1)][emptyCol].getText());
                }

            }
            else if (rowDiff > 0 & isInCol) {
                for (int i = 0; i < diff; i++) {
                    board[emptyRow - i][emptyCol].setText(
                            board[emptyRow - (i + 1)][emptyCol].getText());
                }
            }


            board[emptyRow][emptyCol].setVisible(true);
            board[row][col].setText(Integer.toString(0));
            board[row][col].setVisible(false);
            emptyCell = getIndex(row, col);
        }

        return true;
    }
    public void shufflePuzzle() {
        ArrayList<Integer> shuffledList = new ArrayList<>(size);

        for (boolean isSolvable = false; isSolvable == false;) {
            shuffledList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                shuffledList.add(i, i);
            }

            Collections.shuffle(shuffledList);

            isSolvable = isSolvable(shuffledList);
        }


        for (int index = 0; index < size; index++) {
            final int ROW = index / dim;
            final int COL = index % dim;

            if (shuffledList.get(index) == 0) {
                emptyCell = index;
                board[ROW][COL].setVisible(false);
            } else {
                board[ROW][COL].setText(String.valueOf(shuffledList.get(index)));
                board[ROW][COL].setVisible(true);
            }
        }
    }
   public boolean isGameDone() {
        ArrayList<Integer> solvedState = new ArrayList<>(size);
        for (int i = 1; i < size; i++) {
            solvedState.add(i);
        }
        solvedState.add(0);

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                int cellValue = Integer.parseInt(board[row][col].getText());
                if (cellValue != solvedState.get(getIndex(row, col))) {
                    return false;
                }
            }
        }

        return true;
    }


}
