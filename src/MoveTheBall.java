import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Move the ball around the screen.
 *
 * @author Caiden Merklin
 * @version July 18, 2022
 */
public class MoveTheBall extends Application {
    private final Color CircleColor = Color.rgb(118, 185, 0);
    private final Circle c;
    private final double CircleRadius;
    private final Pane p;
    private final BorderPane root;
    private double CircleX;
    private double CircleY;
    private Button left;
    private Button right;
    private Button up;
    private Button down;

    /**
     * Default Constructor
     */
    public MoveTheBall() {
        this.c = new Circle();
        this.p = new Pane();
        this.root = new BorderPane();
        this.root.setPrefWidth(800);
        this.root.setPrefHeight(500);

        this.CircleRadius = 40;
        this.CircleX = 400;
        this.CircleY = 250;

    }

    public static void Main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        CircleGen();
        root.getChildren().add(this.c);

        Scene sc = new Scene(this.root);

        HBox hb = hBoxGen();

        OnPressed();
        Pane pane = new Pane(hb);
        this.root.getChildren().add(pane);

        primaryStage.setTitle("Ball Mover");
        primaryStage.setScene(sc);
        primaryStage.show();

    }

    /**
     * Generates the HBox, with the buttons.
     *
     * @return the button hBox.
     */
    private HBox hBoxGen() {
        HBox hb = new HBox();
        left = new Button("Left");
        left.setMinWidth(60);
        right = new Button("Right");
        right.setMinWidth(60);
        up = new Button("Up");
        up.setMinWidth(60);
        down = new Button("Down");
        down.setMinWidth(60);
        hb.getChildren().addAll(left, right, up, down);
        hb.setSpacing(5);
        return hb;
    }

    /**
     * Determines the direction of the ball, on button pressed.
     */
    private void OnPressed() {
        EventListener moved = new EventListener();
        left.setOnMousePressed(moved);
        right.setOnMousePressed(moved);
        up.setOnMousePressed(moved);
        down.setOnMousePressed(moved);
    }

    /**
     * initial circle generation.
     */
    public void CircleGen() {
        this.c.setCenterX(CircleX);
        this.c.setCenterY(CircleY);
        this.c.setRadius(CircleRadius);
        this.c.setStrokeWidth(5);
        this.c.setStroke(CircleColor);
        this.c.setFill(Color.TRANSPARENT);
    }

    /**
     * EventListener class, determines the direction of the ball.
     */
    class EventListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (left.isPressed()) {
                if (CircleX > CircleRadius) {
                    CircleX -= 5;
                    c.setCenterX(CircleX);
                } else {
                    System.out.println("Circle can't be moved farther left " + c.getCenterX());
                }
            }
            if (right.isPressed()) {
                if (CircleX < root.getPrefWidth() - CircleRadius) {
                    CircleX += 5;
                    c.setCenterX(CircleX);
                } else {
                    System.out.println("Circle can't be moved farther right " + c.getCenterX());
                }
            }
            if (up.isPressed()) {
                if (CircleY > CircleRadius) {
                    CircleY -= 5;
                    c.setCenterY(CircleY);
                } else {
                    System.out.println("Circle can't be moved farther up " + c.getCenterY());
                }
            }
            if (down.isPressed()) {
                if (CircleY < root.getPrefHeight() - CircleRadius) {
                    CircleY += 5;
                    c.setCenterY(CircleY);
                } else {
                    System.out.println("Circle can't be moved farther down " + c.getCenterY());
                }
            }
        }
    }
}
