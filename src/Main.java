import animal_registry.view.ConsoleUI;
import animal_registry.view.View;
import java.io.Serializable;


public class Main implements Serializable{
    public static void main(String[] args) {
        View view = new ConsoleUI();
        view.start();
    }
}