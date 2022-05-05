import controllers.Controller;
import views.MainView;

public class Main {

    public static void main(String[] args) {

        MainView mainView=new MainView();
        Controller controller=new Controller(mainView);
    }
}
