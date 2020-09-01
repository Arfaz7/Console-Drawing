package services;

import app.Application;
import model.Shape;

public class CommandHandler {

    public static Shape executeCommand(String input) {
        String[] splittedCommand = input.split(" ");
        Shape resultShape = null;

        try {
            char commandType = splittedCommand[0].toUpperCase().charAt(0);

            if (splittedCommand.length == 3 && commandType == 'C') {
                Application.canvas = ShapeFactory.getCanvas(Integer.parseInt(splittedCommand[1]), Integer.parseInt(splittedCommand[2]));
            }
            else if (splittedCommand.length == 4 && commandType == 'B') {
                Application.canvas.fillContent(Integer.parseInt(splittedCommand[1]), Integer.parseInt(splittedCommand[2]), splittedCommand[3]);
            }
            else if (splittedCommand.length == 5) {
                resultShape = ShapeFactory.getShape(commandType, Integer.parseInt(splittedCommand[1]), Integer.parseInt(splittedCommand[2]), Integer.parseInt(splittedCommand[3]), Integer.parseInt(splittedCommand[4]));
            }
            else {
                System.out.println("Command not found");
            }

        } catch (ClassNotFoundException classNotFoundEx) {
            System.out.println(classNotFoundEx.getMessage());

        } catch (IndexOutOfBoundsException indexOutOfBoundsEx) {
            System.out.println(indexOutOfBoundsEx.getMessage());

        } catch (NullPointerException ex) {
            System.out.println("Please create a Canvas before creating Shapes");
        } finally {
            return resultShape;
        }
    }
}
