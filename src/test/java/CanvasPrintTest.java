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
public class CanvasPrintTest {

    private String[][] initializeContent(int width, int height) {
        String[][] content = new String[width][height];

        for(int y = 0; y < content[0].length; y++) {
            if(y == 0 || y == content[0].length -1) {
                for(int x = 0; x < content.length; x++) {
                    content[x][y] = "-";
                }
            }
            else {
                for(int x = 1; x < content.length-1; x++) {
                    content[x][y] = " ";
                }
                content[0][y] = "|";
                content[content.length - 1][y] = "|";
            }
        }

        return content;
    }

    private void addLineToContent(String[][] content, Shape line) {
        for(int x = line.getX1(); x < line.getX2() +1; x++) {
            for(int y = line.getY1(); y < line.getY2() +1; y++) {
                content[x][y] = "x";
            }
        }
    }

    private void addReactangleToContent(String[][] content, Shape rectangle) {
        for(int x = rectangle.getX1(); x < rectangle.getX2() +1; x++) {
            content[x][rectangle.getY1()] = "x";
            content[x][rectangle.getY2()] = "x";
        }

        for(int y = rectangle.getY1(); y < rectangle.getY2() +1; y++) {
            content[rectangle.getX1()][y] = "x";
            content[rectangle.getX2()][y] = "x";
        }
    }

    @Test
    public void printCanvasInitialStateWithSuccess() {
        Application.canvas = new Canvas(4, 2);
        String[][] expectedContent = initializeContent(6, 4);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void printCanvasInitialStateWithError() {
        Application.canvas = new Canvas(4, 2);
        String[][] expectedContent = initializeContent(5, 4);

        Assertions.assertThat(Application.canvas.getContent()).isNotEqualTo(expectedContent);
    }

    @Test
    public void printCanvasWithOneHorizontalLineAndGetSuccess() {
        Application.canvas = new Canvas(20, 6);
        Shape line = new Line(1,2,6,2);
        line.draw(Application.canvas);

        String[][] expectedContent = initializeContent(22, 8);
        addLineToContent(expectedContent, line);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void printCanvasWithOneRectangleAndGetSuccess() {
        Application.canvas = new Canvas(20, 6);
        Shape rectangle = new Rectangle(14,1,18,3);
        rectangle.draw(Application.canvas);

        String[][] expectedContent = initializeContent(22, 8);
        addReactangleToContent(expectedContent, rectangle);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void printCanvasWithOneHorizontalAndOneVerticalLineAndGetSuccess() {
        Application.canvas = new Canvas(20, 6);
        Shape horizontalLine = new Line(1,2,6,2);
        horizontalLine.draw(Application.canvas);

        Shape verticalLine = new Line(6,3,6,4);
        verticalLine.draw(Application.canvas);

        String[][] expectedContent = initializeContent(22, 8);
        addLineToContent(expectedContent, horizontalLine);
        addLineToContent(expectedContent, verticalLine);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void printCanvasWithOneHorizontalAndOneRectangleAndGetSuccess() {
        Application.canvas = new Canvas(20, 6);
        Shape horizontalLine = new Line(1,2,6,2);
        horizontalLine.draw(Application.canvas);

        Shape rectangle = new Rectangle(14,1,18,3);
        rectangle.draw(Application.canvas);

        String[][] expectedContent = initializeContent(22, 8);
        addLineToContent(expectedContent, horizontalLine);
        addReactangleToContent(expectedContent, rectangle);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void fillEmptyCanvas() {
        Application.canvas = new Canvas(2, 2);
        Application.canvas.fillContent(1,1, "o");

        String[][] expectedContent = initializeContent(4, 4);
        expectedContent[1][1] = "o";
        expectedContent[1][2] = "o";
        expectedContent[2][1] = "o";
        expectedContent[2][2] = "o";

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void printLineOfLengthOneAndGetSuccess() {
        String input = "L 1 1 1 1";
        Application.canvas = new Canvas(2, 2);

        String[][] expectedContent = initializeContent(4, 4);
        expectedContent[1][1] = "x";

        Shape line =CommandHandler.executeCommand(input);
        line.draw(Application.canvas);

        Assertions.assertThat(Application.canvas.getContent()).isEqualTo(expectedContent);
    }
}
