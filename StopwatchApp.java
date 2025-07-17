import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StopwatchApp extends JFrame implements ActionListener {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;

    private Timer timer;
    private int elapsedTime = 0; // in milliseconds
    private boolean running = false;

    public StopwatchApp() {
        setTitle("Stopwatch");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // light background color

        timeLabel = new JLabel(formatTime(0), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        timeLabel.setForeground(new Color(25, 25, 112)); // dark blue text
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255)); // match background

        startButton = createButton("Start", new Color(144, 238, 144)); // light green
        stopButton = createButton("Stop", new Color(255, 160, 122));  // light red
        resetButton = createButton("Reset", new Color(173, 216, 230)); // light blue

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(100, e -> updateTimer());

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(this);
        return button;
    }

    private void updateTimer() {
        elapsedTime += 100;
        timeLabel.setText(formatTime(elapsedTime));
    }

    private String formatTime(int millis) {
        int seconds = (millis / 1000) % 60;
        int minutes = (millis / (1000 * 60)) % 60;
        int hours = millis / (1000 * 60 * 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!running) {
                timer.start();
                running = true;
            }
        } else if (e.getSource() == stopButton) {
            if (running) {
                timer.stop();
                running = false;
            }
        } else if (e.getSource() == resetButton) {
            timer.stop();
            running = false;
            elapsedTime = 0;
            timeLabel.setText(formatTime(elapsedTime));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}
