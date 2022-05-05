package models;

import views.MainView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


public class Coada extends Thread {

    private List<Client> clients;
    int waitingTime = 0;
    private AtomicInteger timpulCozii;
    private List<Integer>outTime;
    private JTextField textField;
    private int peakHour=0;
    private int minim=0;
    private int avgWait=0;

    public Coada(MainView mainView) {
        this.clients = Collections.synchronizedList(new ArrayList<>());
        this.outTime = new ArrayList<>();
        this.textField = new JTextField();
        timpulCozii=new AtomicInteger(0);
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public List<Integer> getOutTime() {
        return outTime;
    }
    int contor=0;
    public void setOutTime(List<Integer> outTime) {
        this.outTime = outTime;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getAvgWait() {
        return avgWait;
    }

    public void setAvgWait(int avgWait) {
        this.avgWait = avgWait;
    }

    public void addClient(Client clientul)
    {
        if(this.clients.isEmpty()) {
            this.outTime.add(clientul.getServiceTime()+timpulCozii.get());
        }else {
            this.outTime.add(this.outTime.get(outTime.size()-1)+clientul.getServiceTime());
        }
        this.clients.add(clientul);
        textField.setText(clients.toString());
        waitingTime=waitingTime+ clientul.getServiceTime();
    }

    public int getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public void getNumberOfClients(AtomicInteger timpulCozii)
    {
        for(int i=0;i<clients.size();i++)
        {

            if(minim<clients.size())
            {
                minim=clients.size();
                peakHour=timpulCozii.get();
            }
        }
    }


    @Override
    public void run() {
        while (true)
        {
            for(int i=0;i<clients.size();i++)
            {
                if(clients.size()>1){
                    avgWait=avgWait+clients.get(i).getServiceTime();
                    contor++;
                }
                getNumberOfClients(timpulCozii);
                if(outTime.get(i)==timpulCozii.get()) {
                    clients.remove(i);
                    outTime.remove(i);
                    i--;
                }
            }
            if(!clients.isEmpty())
                textField.setText(clients.toString());
            else textField.setText("closed");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(waitingTime>0) waitingTime--;
            timpulCozii.getAndIncrement();
        }
    }
}
