package controllers;

import models.Coada;
import models.Manager;
import views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class Controller {
    private MainView mainView;
    private Manager manager;
    private Coada coada;
    private Thread thread1;


    public Controller(MainView mainView)
    {
        this.mainView=mainView;
        this.mainView.startButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(mainView.getNumberClientsText()<0 || mainView.getSimulationInterval()<0 || mainView.getMinClientTime()<0 || mainView.getMaxClientTime()<0 || mainView.getMinServiceTime()<0 || mainView.getMaxServiceTime()<0 || mainView.getNumberOfQueuesText()<0 || mainView.getMinClientTime()>mainView.getMaxClientTime() || mainView.getMinServiceTime()>mainView.getMaxServiceTime()) {
                        throw new Exception("error");
                    }

                    if(manager!=null)
                    {
                        manager.stopThreadIfMoreClicks();
                        thread1.stop();
                    }
                    manager=new Manager(mainView);
                    manager.clientGenerator(mainView.getNumberClientsText(),mainView.getMinServiceTime(),mainView.getMaxServiceTime(),mainView.getMinClientTime(),mainView.getMaxClientTime());
                    manager.createTextFields();
                    thread1=new Thread(manager);
                    thread1.start();

                }catch (Exception exception)
                {
                    if(exception.getMessage().equals("error")) mainView.showMessage("Va rugam introduceti valori >0 si completati toate campurile, aveti grija la introducerea valorii minime si maxime, valoarea minima trebuie sa fie mai mica sau egala decat valoarea maxima");
                }
            }
        });
    }
}
