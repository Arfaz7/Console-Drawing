import app.Application;
import model.Canvas;
import model.Line;
import model.Rectangle;
import model.Shape;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import services.CommandHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommandHandler.class)
public class CommandHandlerTest {

    @Test
    public void createLineWithoutCanvasAndGetError() {
        String input = "L 1 2 6 2";
        Application.canvas = null;
        Shape expectedLine = null;

        Shape resultLine = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultLine).isEqualTo(expectedLine);
    }

    @Test
    public void createRectangleWithoutCanvasAndGetError() {
        String input = "R 10 2 15 4";
        Application.canvas = null;
        Shape expectedRectangle = null;

        Shape resultRectangle = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultRectangle).isEqualTo(expectedRectangle);
    }

    @Test
    public void createNonExistingShapeAndGetError() {
        String input = "T 10 2 15 4";
        Application.canvas = new Canvas(20, 6);;
        Shape expectedShape = null;

        Shape resultShape = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultShape).isEqualTo(expectedShape);
    }

    @Test
    public void createLineWithSuccess() {
        Application.canvas = new Canvas(20, 6);
        String input = "L 1 2 6 2";
        Shape expectedLine = new Line(1,2,6,2);

        Shape resultLine = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultLine.getClass().getName()).isEqualTo(expectedLine.getClass().getName());
        Assertions.assertThat(resultLine.getX1()).isEqualTo(expectedLine.getX1());
        Assertions.assertThat(resultLine.getY1()).isEqualTo(expectedLine.getY1());
        Assertions.assertThat(resultLine.getX2()).isEqualTo(expectedLine.getX2());
        Assertions.assertThat(resultLine.getY2()).isEqualTo(expectedLine.getY2());
    }

    @Test
    public void createRectangeWithSuccess() {
        Application.canvas = new Canvas(20, 6);
        String input = "R 10 2 15 4";
        Shape expectedRectangle = new Rectangle(10,2,15,4);

        Shape resultRectangle = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultRectangle.getClass().getName()).isEqualTo(expectedRectangle.getClass().getName());
        Assertions.assertThat(resultRectangle.getX1()).isEqualTo(expectedRectangle.getX1());
        Assertions.assertThat(resultRectangle.getY1()).isEqualTo(expectedRectangle.getY1());
        Assertions.assertThat(resultRectangle.getX2()).isEqualTo(expectedRectangle.getX2());
        Assertions.assertThat(resultRectangle.getY2()).isEqualTo(expectedRectangle.getY2());
    }

    @Test
    public void createCanvasWithSuccess() {
        String input = "C 20 4";
        Canvas expectedCanvas = new Canvas(20, 4);

        CommandHandler.executeCommand(input);

        Assertions.assertThat(Application.canvas.getClass().getName()).isEqualTo(expectedCanvas.getClass().getName());
        Assertions.assertThat(Application.canvas.getWidth()).isEqualTo(expectedCanvas.getWidth());
        Assertions.assertThat(Application.canvas.getHeight()).isEqualTo(expectedCanvas.getHeight());
        Assertions.assertThat(Application.canvas.getShapeList()).isEqualTo(expectedCanvas.getShapeList());
        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedCanvas.getContent());
    }

    @Test
    public void createCanvasWithNegativeDimensionAndGetError() {
        String input = "C 20 -3";
        Application.canvas = null;
        CommandHandler.executeCommand(input);

        Assertions.assertThat(Application.canvas).isEqualTo(null);
    }

    @Test
    public void createLineWithNegativeDimensionAndGetError() {
        String input = "L 1 -3 6 -3";
        Application.canvas = new Canvas(20, 6);

        Shape resultLine =CommandHandler.executeCommand(input);

        Assertions.assertThat(resultLine).isEqualTo(null);
    }

    @Test
    public void createRectangleWithNegativeDimensionAndGetError() {
        String input = "R 10 -2 15 4";
        Application.canvas = new Canvas(20, 6);

        Shape resultRectangle = CommandHandler.executeCommand(input);

        Assertions.assertThat(resultRectangle).isEqualTo(null);
    }

    @Test
    public void createDiagonalLineAndGetError() {
        String input = "L 1 2 5 6";
        Application.canvas = null;
        CommandHandler.executeCommand(input);

        Assertions.assertThat(Application.canvas).isEqualTo(null);
    }
}
