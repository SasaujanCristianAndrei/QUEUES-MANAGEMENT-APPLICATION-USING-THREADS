package models;
import views.MainView;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager implements Runnable {

    private AtomicInteger counter;
    private List<Client> client;
    private List<JTextField> textFieldList;
    private JTextArea textAreaWaitingList;
    private MainView mainView;
    private List<Coada> coada;
    private FileWriter log4Events;
    private double avgServiceTime=0;
    private int numarClienti=0;


    public Manager(MainView mainView) {
        this.client = new ArrayList<>();
        this.textFieldList = new ArrayList<>();
        this.mainView = mainView;
        counter = new AtomicInteger(0);
        this.coada = new ArrayList<>();
        populareCoada();
        File file=new File("log4Events.txt");
        try {
            log4Events= new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public void setCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    public void stopThreadIfMoreClicks()
    {
        for(int i=0;i<coada.size();i++)
        {
            coada.get(i).stop();
        }
        textAreaWaitingList.setText("");
    }

    public void populareCoada()
    {
        for(int i=0;i<mainView.getNumberOfQueuesText();i++)
        {
            Coada coadaCreata=new Coada(mainView);
            coada.add(coadaCreata);
        }
    }

    public List<Coada> getCoada() {
        return coada;
    }

    public void setCoada(List<Coada> coada) {
        this.coada = coada;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public List<Client> getClient() {
        return client;
    }

    public List<Client> clientGenerator(int numberofClients, int serviceTimeMin, int serviceTimeMax, int timeArrivalMin, int timeArrivalMax) {
        Random random = new Random();
        numarClienti=numberofClients;
        for (int i = 1; i <= numberofClients; i++) {
            client.add(new Client(i, random.nextInt(timeArrivalMin, timeArrivalMax), random.nextInt(serviceTimeMin, serviceTimeMax)));
        }
        for(Client clienti : client)
        {
            avgServiceTime=avgServiceTime+clienti.getServiceTime();
        }
        avgServiceTime=avgServiceTime/numberofClients;
        Collections.sort(client);
        return client;
    }

    public void showClients() {
        for (int i = 0; i < client.size(); i++) {
            System.out.println("ID: " + client.get(i).getId() + " timeArrival: " + client.get(i).getArrivalTime() + " serviceTime: " + client.get(i).getServiceTime());
        }
    }

    public boolean checkWaitingTimePerQueue()
    {
        for(Coada queue : coada)
        {
            if(queue.waitingTime>0)
            {
                return true;
            }
        }
        return false;
    }

    public void createTextFields() {
        int yLabel = 140, yText = 143, yLabel2 = 140, yText2 = 143;
        for (int i = 1; i <= mainView.getNumberOfQueuesText(); i++) {
            if (yLabel > 440) {
                JLabel quesCounter = new JLabel("Queue number " + i + ": ");
                quesCounter.setBounds(570, yLabel2, 144, 22);
                mainView.getFrame().getContentPane().add(quesCounter);
                yLabel2 = yLabel2 + 30;
                JTextField quesTextField = new JTextField();
                quesTextField.setColumns(10);
                quesTextField.setBounds(700, yText2, 369, 20);
                yText2 = yText2 + 30;
                mainView.getFrame().getContentPane().add(quesTextField);
                textFieldList.add(quesTextField);
            } else {
                JLabel quesCounter = new JLabel("Queue number " + i + ": ");
                quesCounter.setBounds(20, yLabel, 144, 22);
                mainView.getFrame().getContentPane().add(quesCounter);
                yLabel = yLabel + 30;
                JTextField quesTextField = new JTextField();
                quesTextField.setColumns(10);
                quesTextField.setBounds(124, yText, 369, 20);
                yText = yText + 30;
                mainView.getFrame().getContentPane().add(quesTextField);
                textFieldList.add(quesTextField);
            }
        }
        JLabel waitingList = new JLabel("Waiting List");
        waitingList.setHorizontalAlignment(SwingConstants.CENTER);
        waitingList.setFont(new Font("Tahoma", Font.BOLD, 14));
        waitingList.setBounds(483, 470, 117, 22);
        mainView.getFrame().getContentPane().add(waitingList);
        textAreaWaitingList = new JTextArea();
        textAreaWaitingList.setBounds(43, 495, 1058, 208);
        textAreaWaitingList.setColumns(10);
        textAreaWaitingList.setText(client.toString());
        mainView.getFrame().getContentPane().add(textAreaWaitingList);
        mainView.getFrame().repaint();
    }

    public void settingsQueue(AtomicInteger counter) {
        Iterator<Client> stergem = client.iterator();
        while (stergem.hasNext())
        {
            Client clientul = stergem.next();
            if (clientul.getArrivalTime() == counter.get() && clientul.getArrivalTime() <= mainView.getSimulationInterval()) {
                int minim = 0;
                for (int j = 1; j < coada.size(); j++) {
                    if (coada.get(j).waitingTime < coada.get(minim).waitingTime) {
                        minim = j;
                    }
                }
                coada.get(minim).setTextField(textFieldList.get(minim));
                coada.get(minim).addClient(clientul);
                stergem.remove();
                textAreaWaitingList.setText(client.toString());
            }
        }
    }

    public void writeInLog()
    {
        try {
            log4Events.write("TIMER = "+counter.get()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<coada.size();i++)
        {
            try {
                log4Events.write("COADA"+(i+1)+" = "+coada.get(i).getClients().toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            log4Events.write("WaitingClients"+textAreaWaitingList.getText()+"\n"+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeInLogFinal()
    {
        try {
            log4Events.write("TIMER = "+counter.get()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<coada.size();i++)
        {
            try {
                log4Events.write("COADA"+(i+1)+" = "+coada.get(i).getClients().toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            log4Events.write("WaitingClients"+textAreaWaitingList.getText()+"\n"+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void avg()
    {
        int maxim=coada.get(0).getPeakHour();
        double avgWaitingTime=0;
        int contor=0;
        for(Coada queue : coada)
        {
            if(maxim<queue.getPeakHour()) {
                maxim=queue.getPeakHour();
            }
            avgWaitingTime=avgWaitingTime+queue.getAvgWait();
            contor=contor+queue.contor;
        }
        try {
            log4Events.write("Average time = "+avgServiceTime+"\n"+"Peak hour = "+maxim+"\n"+"Average waiting time = "+((contor!=0) ? (avgWaitingTime/contor) : avgWaitingTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        textAreaWaitingList.setText("Average time = "+avgServiceTime+"\n"+"Peak hour = "+maxim+"\n"+"Average waiting time = "+((contor!=0) ? (avgWaitingTime/contor) : avgWaitingTime));
    }
    @Override
    public void run() {
        for(int i=0;i<coada.size();i++)
        {
            coada.get(i).start();
        }
        while ((counter.get() <= mainView.getSimulationInterval()) || checkWaitingTimePerQueue()) {
            mainView.setTimerTextField(counter.get());
            settingsQueue(counter);
            writeInLog();
            counter.getAndIncrement();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeInLogFinal();
        avg();
        try {
            log4Events.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<coada.size();i++)
        {
            coada.get(i).stop();
        }
        Thread.currentThread().stop();
    }
}
