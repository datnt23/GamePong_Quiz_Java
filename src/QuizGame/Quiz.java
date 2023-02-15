package QuizGame;

import java.awt.event.*;
import java.awt.*;
import java.sql.Time;
import javax.swing.*;

public class Quiz implements ActionListener{

    String[] questions = {
            "Which company created Java?",
            "Which year was Java created?",
            "What was Java originally called?",
            "Who's credited with creating Java?"
    };
    String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerburg"}
    };
    char[] answers = {
            'A',
            'B',
            'C',
            'C'
    };
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    JFrame jFrame = new JFrame();
    JTextField jTextField = new JTextField();
    JTextArea jTextArea = new JTextArea();
    JButton jButtonA = new JButton();
    JButton jButtonB = new JButton();
    JButton jButtonC = new JButton();
    JButton jButtonD = new JButton();
    JLabel jLabel_answerA = new JLabel();
    JLabel jLabel_answerB = new JLabel();
    JLabel jLabel_answerC = new JLabel();
    JLabel jLabel_answerD = new JLabel();
    JLabel jLabel_time = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jLabel_time.setText("Go(^_^)!");
            seconds_left.setText(String.valueOf(seconds--));
            if (seconds <= 0) {
                displayAnswer();
                jLabel_time.setText("Time Out!");
            }
        }
    });
    public Quiz() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600, 680);
        jFrame.getContentPane().setBackground(new Color(50, 50, 50));
        jFrame.setLocationRelativeTo(null); //screen center
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        jTextField.setBounds(0, 0, 600, 50);
        jTextField.setBackground(new Color(25, 25, 25));
        jTextField.setForeground(new Color(25, 255, 0));
        jTextField.setFont(new Font("Ink Free", Font.BOLD, 30));
        jTextField.setBorder(BorderFactory.createBevelBorder(1));
        jTextField.setHorizontalAlignment(JTextField.CENTER);
        jTextField.setEditable(false);

        jTextArea.setBounds(0, 50, 600, 50);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setBackground(new Color(25, 25, 25));
        jTextArea.setForeground(new Color(25, 255, 0));
        jTextArea.setFont(new Font("MV Boli", Font.BOLD, 25));
        jTextArea.setBorder(BorderFactory.createBevelBorder(1));
        jTextArea.setEditable(false);

        jButtonA.setBounds(0, 100, 100, 100);
        jButtonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        jButtonA.setFocusable(false);
        jButtonA.addActionListener(this);
        jButtonA.setText("A");

        jButtonB.setBounds(0, 200, 100, 100);
        jButtonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        jButtonB.setFocusable(false);
        jButtonB.addActionListener(this);
        jButtonB.setText("B");

        jButtonC.setBounds(0, 300, 100, 100);
        jButtonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        jButtonC.setFocusable(false);
        jButtonC.addActionListener(this);
        jButtonC.setText("C");

        jButtonD.setBounds(0, 400, 100, 100);
        jButtonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        jButtonD.setFocusable(false);
        jButtonD.addActionListener(this);
        jButtonD.setText("D");

        jLabel_answerA.setBounds(125, 100, 500, 100);
        jLabel_answerA.setBackground(new Color(50, 50, 50));
        jLabel_answerA.setForeground(new Color(25, 255, 0));
        jLabel_answerA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        jLabel_answerB.setBounds(125, 200, 500, 100);
        jLabel_answerB.setBackground(new Color(50, 50, 50));
        jLabel_answerB.setForeground(new Color(25, 255, 0));
        jLabel_answerB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        jLabel_answerC.setBounds(125, 300, 500, 100);
        jLabel_answerC.setBackground(new Color(50, 50, 50));
        jLabel_answerC.setForeground(new Color(25, 255, 0));
        jLabel_answerC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        jLabel_answerD.setBounds(125, 400, 500, 100);
        jLabel_answerD.setBackground(new Color(50, 50, 50));
        jLabel_answerD.setForeground(new Color(25, 255, 0));
        jLabel_answerD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        seconds_left.setBounds(460, 523, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        jLabel_time.setBounds(460, 500, 100, 25);
        jLabel_time.setBackground(new Color(25, 25, 25));
        jLabel_time.setForeground(new Color(255, 0, 0));
        jLabel_time.setFont(new Font("MV Boli", Font.PLAIN, 16));
        jLabel_time.setHorizontalAlignment(JTextField.CENTER);

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        jFrame.add(jLabel_time);
        jFrame.add(seconds_left);
        jFrame.add(jLabel_answerA);
        jFrame.add(jLabel_answerB);
        jFrame.add(jLabel_answerC);
        jFrame.add(jLabel_answerD);
        jFrame.add(jButtonA);
        jFrame.add(jButtonB);
        jFrame.add(jButtonC);
        jFrame.add(jButtonD);
        jFrame.add(jTextArea);
        jFrame.add(jTextField);
        jFrame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {
        if (index >= total_questions) {
            results();
        }
        else{
            jTextField.setText("Question " + (index + 1));
            jTextArea.setText(questions[index]);
            jLabel_answerA.setText(options[index][0]);
            jLabel_answerB.setText(options[index][1]);
            jLabel_answerC.setText(options[index][2]);
            jLabel_answerD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        jButtonA.setEnabled(false);
        jButtonB.setEnabled(false);
        jButtonC.setEnabled(false);
        jButtonD.setEnabled(false);

        if (e.getSource() == jButtonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == jButtonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == jButtonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == jButtonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {
        timer.stop();
        jButtonA.setEnabled(false);
        jButtonB.setEnabled(false);
        jButtonC.setEnabled(false);
        jButtonD.setEnabled(false);

        if (answers[index] != 'A') {
            jLabel_answerA.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'B') {
            jLabel_answerB.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'C') {
            jLabel_answerC.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'D') {
            jLabel_answerD.setForeground(new Color(255, 0, 0));
        }

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel_answerA.setForeground(new Color(25, 255, 0));
                jLabel_answerB.setForeground(new Color(25, 255, 0));
                jLabel_answerC.setForeground(new Color(25, 255, 0));
                jLabel_answerD.setForeground(new Color(25, 255, 0));

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                jButtonA.setEnabled(true);
                jButtonB.setEnabled(true);
                jButtonC.setEnabled(true);
                jButtonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results() {

        jButtonA.setEnabled(false);
        jButtonB.setEnabled(false);
        jButtonC.setEnabled(false);
        jButtonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        jTextField.setText("Results!");
        jTextArea.setText("");
        jLabel_answerA.setText("");
        jLabel_answerB.setText("");
        jLabel_answerC.setText("");
        jLabel_answerD.setText("");

         number_right.setText("("+correct_guesses+"/"+total_questions+")");
         percentage.setText(result+"%");

         jFrame.add(number_right);
         jFrame.add(percentage);
    }
}
