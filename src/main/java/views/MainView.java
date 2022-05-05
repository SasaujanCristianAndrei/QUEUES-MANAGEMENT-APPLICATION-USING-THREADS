package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JTextField numberClientsText;
    private JTextField numberOfQueuesText;
    private JTextField simulationInterval;
    private JTextField minClientTime;
    private JTextField maxClientTime;
    private JTextField minServiceTime;
    private JTextField maxServiceTime;
    private JTextField timerTextField;
    private JFrame frame;

    private JButton startButton;

    public MainView() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.getContentPane().setForeground(new Color(255, 255, 255));
        frame.setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 1150, 785);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("QUEUE MANAGEMENT");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        titleLabel.setBounds(380, 11, 303, 30);
        frame.getContentPane().add(titleLabel);

        JLabel numberOfClients = new JLabel("Number of clients: ");
        numberOfClients.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfClients.setFont(new Font("Tahoma", Font.BOLD, 14));
        numberOfClients.setBounds(10, 76, 144, 22);
        frame.getContentPane().add(numberOfClients);

        JLabel lblNumberOfQueues = new JLabel("Number of queues:");
        lblNumberOfQueues.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumberOfQueues.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNumberOfQueues.setBounds(10, 109, 144, 22);
        frame.getContentPane().add(lblNumberOfQueues);

        JLabel lblMaximumTime = new JLabel("Simulation interval:");
        lblMaximumTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaximumTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMaximumTime.setBounds(302, 76, 144, 22);
        frame.getContentPane().add(lblMaximumTime);

        JLabel lblClientParameters = new JLabel("Client parameters:");
        lblClientParameters.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientParameters.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblClientParameters.setBounds(637, 76, 144, 22);
        frame.getContentPane().add(lblClientParameters);

        JLabel serviceTimeLabel_1_1 = new JLabel("Service time:");
        serviceTimeLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        serviceTimeLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        serviceTimeLabel_1_1.setBounds(626, 109, 144, 22);
        frame.getContentPane().add(serviceTimeLabel_1_1);

        JLabel minimumSeconds = new JLabel("Minimum (seconds)");
        minimumSeconds.setHorizontalAlignment(SwingConstants.CENTER);
        minimumSeconds.setFont(new Font("Tahoma", Font.BOLD, 14));
        minimumSeconds.setBounds(773, 46, 159, 22);
        frame.getContentPane().add(minimumSeconds);

        JLabel lblMaximumseconds = new JLabel("Maximum (seconds)");
        lblMaximumseconds.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaximumseconds.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMaximumseconds.setBounds(942, 46, 159, 22);
        frame.getContentPane().add(lblMaximumseconds);

        numberClientsText = new JTextField();
        numberClientsText.setBounds(164, 79, 86, 20);
        frame.getContentPane().add(numberClientsText);
        numberClientsText.setColumns(10);

        numberOfQueuesText = new JTextField();
        numberOfQueuesText.setColumns(10);
        numberOfQueuesText.setBounds(164, 112, 86, 20);
        frame.getContentPane().add(numberOfQueuesText);

        simulationInterval = new JTextField();
        simulationInterval.setBounds(469, 79, 86, 20);
        frame.getContentPane().add(simulationInterval);
        simulationInterval.setColumns(10);

        minClientTime = new JTextField();
        minClientTime.setBounds(805, 79, 86, 20);
        frame.getContentPane().add(minClientTime);
        minClientTime.setColumns(10);

        maxClientTime = new JTextField();
        maxClientTime.setColumns(10);
        maxClientTime.setBounds(978, 79, 86, 20);
        frame.getContentPane().add(maxClientTime);

        minServiceTime = new JTextField();
        minServiceTime.setColumns(10);
        minServiceTime.setBounds(805, 112, 86, 20);
        frame.getContentPane().add(minServiceTime);

        maxServiceTime = new JTextField();
        maxServiceTime.setColumns(10);
        maxServiceTime.setBounds(978, 112, 86, 20);
        frame.getContentPane().add(maxServiceTime);

        startButton = new JButton("START SIMULATION");
        startButton.setForeground(Color.RED);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        startButton.setBounds(452, 705, 187, 30);
        frame.getContentPane().add(startButton);

        JLabel cronometerLabel = new JLabel("Timer:");
        cronometerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cronometerLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cronometerLabel.setBounds(373, 109, 86, 22);
        frame.getContentPane().add(cronometerLabel);

        timerTextField = new JTextField();
        timerTextField.setColumns(10);
        timerTextField.setBounds(469, 112, 86, 20);
        timerTextField.setEditable(false);
        frame.getContentPane().add(timerTextField);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void startButtonListener(ActionListener action) {
        startButton.addActionListener(action);
    }

    public void setTimerTextField(int timerTextField) {
        this.timerTextField.setText(String.valueOf(timerTextField));
    }

    public int getTimerTextField() {
        return Integer.parseInt(timerTextField.getText());
    }

    public int getNumberClientsText() {
        if(!numberClientsText.getText().equals(""))
            return Integer.parseInt(numberClientsText.getText());
        return -1;
    }

    public int getNumberOfQueuesText() {
        if(!numberOfQueuesText.getText().equals(""))
            return Integer.parseInt(numberOfQueuesText.getText());
        return -1;
    }

    public int getSimulationInterval() {
        if(!simulationInterval.getText().equals(""))
            return Integer.parseInt(simulationInterval.getText());
        return -1;
    }

    public int getMinClientTime() {
        if(!minClientTime.getText().equals(""))
            return Integer.parseInt(minClientTime.getText());
        return -1;
    }

    public int getMaxClientTime() {
        if(!maxClientTime.getText().equals(""))
            return Integer.parseInt(maxClientTime.getText());
        return -1;
    }

    public int getMinServiceTime() {
        if(!minServiceTime.getText().equals(""))
            return Integer.parseInt(minServiceTime.getText());
        return -1;
    }

    public int getMaxServiceTime() {
        if(!maxServiceTime.getText().equals(""))
            return Integer.parseInt(maxServiceTime.getText());
        return -1;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}

