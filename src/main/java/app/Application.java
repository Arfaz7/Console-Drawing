package app;

import model.Canvas;
import model.Shape;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.CommandHandler;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static Canvas canvas;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("enter command : ");
        do{
            if (scanner.hasNext()) {
                input = scanner.nextLine();
            }

            Shape shape = CommandHandler.executeCommand(input);
            if(shape != null)
                canvas.getShapeList().add(shape);

            if(canvas != null)
                canvas.print();

            System.out.println("enter command : ");

        } while (!input.equalsIgnoreCase("Q") && scanner.hasNextLine());

        scanner.close();
        System.out.println("Exit application");
        System.exit(0);
    }
}
