package com.example.battleship;

import com.example.battleship.ship.ShipSprite;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the buttons used to place the ships on the board.
 */
public class ShipPlacer extends VBox {

    /** A list containing the default number of ship lengths to place. */
    private static final List<Integer> DEFAULT_SHIPS = new ArrayList<>(List.of(0, 0, 1, 2, 2, 1));
    private static final List<Integer> SINGLE_SHIP = new ArrayList<>(List.of(0, 0, 1));
    /** A list containing no ships to place. */
    private static final List<Integer> NONE = new ArrayList<>();

    /**
     * The mode the placer is in: either currently placing a ship, or waiting for a button to be pressed.
     */
    private enum Mode {
        PLACING_SHIP,
        IDLE
    }

    /**
     * The type of placement, which part of the boat the cursor is holding.
     */
    public enum PlacementMode {
        FRONT,
        BACK,
        MIDDLE
    }

    /** The current mode the placer is in, placing a ship or idling and waiting for a button to be pressed. */
    private Mode mode;
    /** The placement mode this placer uses. */
    private final PlacementMode placementMode;
    /** The player this ship placer is linked to. */
    private final BattleshipPlayer player;
    /** The board of the player we are linked to. */
    private final PlayerBoard board;
    /** A list of the ships still left to place, where the value at an index is the number of ships left to place of index length. */
    private final List<Integer> shipsToPlace;
    /** The current ship placement we are doing. */
    private ShipPlacement shipPlacement;

    /**
     * A handler that listens for when the mouse is pressed. If the primary button was pressed,
     * and we are placing a ship, place the ship if we are able to, according to {@link PlayerBoard#canPlaceShip(int, int, int, Direction)}.
     * If the secondary button was pressed, rotate the ship we are currently placing.
     */
    private EventHandler<MouseEvent> mousePressedHandler;
    /**
     * A handler that listens for when the mouse enters the player board, and sets the ship placement indicator to visible,
     * if we are currently placing a ship.
     */
    private EventHandler<MouseEvent> mouseEnterHandler;
    /**
     * A handler that listens for when the mouse exits the player board, and sets the ship placement indicator to invisible,
     * if we are currently placing a ship.
     */
    private EventHandler<MouseEvent> mouseExitHandler;
    /**
     * A handler that listens for when the mouse moves, and moves the ship placement indicator to the new position, if we are
     * currently placing a ship.
     */
    private EventHandler<MouseEvent> mouseMoveHandler;

    /**
     * Create a new ship placer linked to the provided player.
     *
     * @param player The player this placer is linked to
     */
    public ShipPlacer(BattleshipPlayer player) {
        super(5);

        mode = Mode.IDLE;
        this.player = player;
        placementMode = PlacementMode.BACK;
        this.board = player.getPlayerBoard();
        shipsToPlace = new ArrayList<>(SINGLE_SHIP);

        for (int i = 0; i < shipsToPlace.size(); i++) {
            if (shipsToPlace.get(i) == 0) continue;
            SelectorEntry entry = new SelectorEntry(shipsToPlace, i);
            entry.setOnAction(event -> {
                if (mode == Mode.IDLE && shipsToPlace.get(entry.getLength()) > 0) startPlacing(entry);
            });
            getChildren().add(entry);
        }

        createHandlers();
        addHandlers();
    }

    /**
     * Start placing a ship from the provided entry. Creates a new ship placement object and sets the mode to placing ship.
     *
     * @param entry The entry that is creating the ship
     */
    private void startPlacing(SelectorEntry entry) {
        mode = Mode.PLACING_SHIP;
        shipPlacement = new ShipPlacement(entry, board, placementMode);
    }

    /**
     * Place a ship of the provided length in the provided direction at the provided position on the board. This assumes we have already checked if all the squares were valid.
     *
     * @param col The column of the stern of the boat
     * @param row The row of the stern of the boat
     * @param length The length of the boat
     * @param direction The direction the boat is facing
     */
    private void placeShip(int col, int row, int length, Direction direction) {
        System.out.println(col + ", " + row + ", " + direction);
        board.placeShip(col, row, length, direction);

        mode = Mode.IDLE;
        shipPlacement.remove();
        shipPlacement = null;

        if (!hasShipsLeftToPlace()) finish();
    }

    /**
     * Removes the listeners added to the player board and tells the player we are linked to that we are done placing ships.
     */
    private void finish() {
        removeHandlers();
        player.donePlacingShips();
    }

    /**
     * Initializes the event handlers ({@link #mousePressedHandler}, {@link #mouseEnterHandler}, {@link #mouseExitHandler}, {@link #mouseMoveHandler}) that are added to the player board.
     */
    private void createHandlers() {
        mousePressedHandler = event -> {
            if (event.isPrimaryButtonDown()) {
                if (mode == Mode.PLACING_SHIP) {
                    int col = GridItem.getCol(event, board);
                    int row = GridItem.getRow(event, board);
                    if (board.canPlaceShip(col, row, shipPlacement.getLength(), shipPlacement.getDirection())) {
                        placeShip(col, row, shipPlacement.getLength(), shipPlacement.getDirection());
                    }
                }
            } else if (event.isSecondaryButtonDown()) {
                if (shipPlacement != null) {
                    shipPlacement.rotate();
                    movePlacementIndicator(event);
                }
            }
        };

        mouseEnterHandler = event -> {
            if (shipPlacement != null) {
                shipPlacement.setVisible(false);
            }
        };

        mouseExitHandler = event -> {
            if (shipPlacement != null) {
                shipPlacement.setVisible(true);
            }
        };

        mouseMoveHandler = this::movePlacementIndicator;
    }

    /**
     * Adds the handlers ({@link #mousePressedHandler}, {@link #mouseEnterHandler}, {@link #mouseExitHandler}, {@link #mouseMoveHandler})
     * to the player board. If one of the handlers isn't initialized, it initializes them first.
     */
    private void addHandlers() {
        if (mousePressedHandler == null || mouseEnterHandler == null || mouseExitHandler == null || mouseMoveHandler == null) createHandlers();
        board.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        board.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEnterHandler);
        board.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseExitHandler);
        board.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveHandler);
    }

    /**
     * Removes the handlers ({@link #mousePressedHandler}, {@link #mouseEnterHandler}, {@link #mouseExitHandler}, {@link #mouseMoveHandler})
     * from the player board.
     */
    private void removeHandlers() {
        board.removeEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        board.removeEventHandler(MouseEvent.MOUSE_EXITED, mouseEnterHandler);
        board.removeEventHandler(MouseEvent.MOUSE_ENTERED, mouseExitHandler);
        board.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveHandler);
    }

    /**
     * Move the ship placement indicator to the closest cell to the mouse.
     * @param event The current mouse position
     */
    private void movePlacementIndicator(MouseEvent event) {
        if (shipPlacement != null) {
            int col = GridItem.getCol(event, board);
            int row = GridItem.getRow(event, board);

            shipPlacement.getPlacementIndicator().moveTo(col, row, shipPlacement.getDirection());
        }
    }

    /**
     * Returns true if there is still ships left to place.
     *
     * @return true if there is a number above 0 in {@link #shipsToPlace}
     */
    private boolean hasShipsLeftToPlace() {
        for (Integer i : shipsToPlace) {
            if (i > 0) return true;
        }
        return false;
    }

    /**
     * Represents one button used to select which ship to place.
     */
    private static final class SelectorEntry extends Button {
        /** The list of ship lengths still left to place. */
        private final List<Integer> shipsToPlace;
        /** The length this entry corresponds to. */
        private final int length;
        /** The label showing how many ships there are left to place. */
        private final Label label;

        /**
         * Create a new entry that placed ships of the provided length.
         *
         * @param shipsToPlace The list of ship lengths still left to place
         * @param length The length of ship this entry places
         */
        public SelectorEntry(List<Integer> shipsToPlace, int length) {
            this.shipsToPlace = shipsToPlace;
            this.length = length;
            this.label = new Label("x" + shipsToPlace.get(length));
            HBox buttonNode = new HBox(5);
            buttonNode.getChildren().addAll(label, new ShipSprite(length));

            setGraphic(buttonNode);

            setBorder(new Border(
                    new BorderStroke(
                            Color.NAVY,
                            BorderStrokeStyle.SOLID,
                            new CornerRadii(BattleshipGame.CELL_WIDTH, false),
                            BorderWidths.DEFAULT
                    )
            ));

            setBackground(new Background(
                    new BackgroundFill(
                            null, null, null
                    )
            ));
        }

        /**
         * Decrements the number of ships left to place
         */
        public void decrement() {
            if (shipsToPlace.get(length) <= 0) return;

            shipsToPlace.set(length, shipsToPlace.get(length) - 1);
            label.setText("x" + shipsToPlace.get(length));
        }

        /**
         * @return The length of ship this entry places
         */
        public int getLength() {
            return length;
        }
    }

    /**
     * Represents all the information needed to place a ship.
     */
    private static final class ShipPlacement {

        /** The direction the ships start facing when you begin to place them. */
        private static final Direction DEFAULT_SHIP_DIRECTION = Direction.EAST;

        private final SelectorEntry entry;
        private final int length;
        private Direction direction;
        private final ShipPlacementIndicator placementIndicator;

        /**
         * Creates a new ship placement object from the provided entry, board and placement mode.
         * @param entry The entry the placement is using
         * @param board The board the ship placer is linked to
         * @param placementMode The placement mode this placement will use
         */
        public ShipPlacement(SelectorEntry entry, PlayerBoard board, PlacementMode placementMode) {
            this.entry = entry;
            direction = DEFAULT_SHIP_DIRECTION;
            length = entry.getLength();
            placementIndicator = new ShipPlacementIndicator(board, board.getShipSegments(), length, placementMode);
            placementIndicator.setVisible(false);
        }

        /**
         * Sets the visibility of the ship placement indicator.
         * @param visible if the ship placement indicator is visible or not
         */
        public void setVisible(boolean visible) {
            placementIndicator.setVisible(visible);
        }

        /**
         * Decrements the entry and removes the ship placement indicator from the board.
         */
        public void remove() {
            entry.decrement();
            placementIndicator.remove();
        }

        /**
         * Rotates the ship placement in a clockwise direction
         */
        public void rotate() {
            direction = direction.next();
            placementIndicator.setDirection(direction);
        }

        /**
         * @return The ship length of this placement.
         */
        public int getLength() {
            return length;
        }

        /**
         * @return The direction of this placement.
         */
        public Direction getDirection() {
            return direction;
        }

        /**
         * @return The ship placement indicator.
         */
        public ShipPlacementIndicator getPlacementIndicator() {
            return placementIndicator;
        }
    }
}
