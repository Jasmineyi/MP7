/**
 * A class that implements a Connect4-like game.
 */
public class ConnectN {
    /**
     * Public game title.
     */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public String title = "ConnectN";

    /**
     * Maximum board height.
     */
    public static final int MAX_HEIGHT = 16;

    /**
     * Maximum board width.
     */
    public static final int MAX_WIDTH = 16;

    /**
     * Minimum board height.
     */
    public static final int MIN_HEIGHT = 6;

    /**
     * Minimum board width.
     */
    public static final int MIN_WIDTH = 6;

    /**
     * Minimum board N value.
     */
    public static final int MIN_N = 4;

    /**
     * Board width.
     */
    private int width;

    /**
     * Board height.
     */
    private int height;

    /**
     * N value.
     */
    private int n;

    /**
     * Total game count.
     */
    private static int total = 0;

    /**
     * Id.
     */
    private int id;

    /**
     * The game board.
     */
    private Player[][] board;

    /**
     * Test whether the game started.
     */
    private boolean started;

    /**
     * Creates a new ConnectN board with a given width, height, and N value.
     * @param setWidth the width for the new ConnectN board
     * @param setHeight the height for the new ConnectN board
     * @param setN the N value for the new ConnectN board
     */
    public ConnectN(final int setWidth, final int setHeight, final int setN) {
        total++;
        this.id = total - 1;
        if (setN < MIN_N) {
            this.n = 0;
        } else {
            this.n = setN;
        }
        if (setWidth < MIN_WIDTH || setWidth > MAX_WIDTH) {
            this.width = 0;
            this.n = 0;
        } else {
            this.width = setWidth;
        }
        if (setHeight < MIN_HEIGHT || setHeight > MAX_HEIGHT) {
            this.height = 0;
            this.n = 0;
        } else {
            this.height = setHeight;
        }
        int max = 0;
        if (setWidth > setHeight) {
            max = setWidth - 1;
        } else {
            max = setHeight - 1;
        }
        if (setN > max) {
            this.n = 0;
        }
        this.board = new Player[this.width][this.height];
    }

    /**
     * Create a new ConnectN board with uninitialized width, height, and N value.
     */
    public ConnectN() {
        total++;
        this.id = total - 1;
        this.width = 0;
        this.height = 0;
        this.n = 0;
    }

    /**
     * Create a new ConnectN board with given width and height and uninitialized N value.
     * @param setWidth the width for the new ConnectN board
     * @param setHeight the height for the new ConnectN board
     */
    public ConnectN(final int setWidth, final int setHeight) {
        total++;
        this.id = total - 1;
        if (setWidth < MIN_WIDTH || setWidth > MAX_WIDTH) {
            this.width = 0;
            this.n = 0;
        } else {
            this.width = setWidth;
        }
        if (setHeight < MIN_HEIGHT || setHeight > MAX_HEIGHT) {
            this.height = 0;
            this.n = 0;
        } else {
            this.height = setHeight;
        }
        this.board = new Player[this.width][this.height];
    }

    /**
     * Create a new ConnectN board with dimensions and N value copied from another board.
     * @param otherBoard the ConnectN board to copy width, height, and N from
     */
    public ConnectN(final ConnectN otherBoard) {
        total++;
        this.id = total - 1;
        this.width = otherBoard.width;
        this.height = otherBoard.height;
        this.n = otherBoard.n;
        this.board = new Player[otherBoard.width][otherBoard.height];
    }

    /**
     * Return the total number of games that have been created.
     * @return the total number of games that have been created
     */
    public static int getTotalGames() {
        return total;
    }

    /**
     * Get the current board width.
     * @return the current board width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Attempt to set the board width.
     * @param setWidth the new width to set
     * @return true if the width was set successfully, false on error
     */
    public boolean setWidth(final int setWidth) {
        if (started) {
            return false;
        } else if (setWidth < MIN_WIDTH || setWidth > MAX_WIDTH) {
            return false;
        } else {
            this.width = setWidth;
            int max = 0;
            if (setWidth > getHeight()) {
                max = setWidth - 1;
            } else {
                max = getHeight() - 1;
            }
            if (getN() > max) {
                this.n = 0;
            }
            if (this.height > MIN_HEIGHT) {
                board = new Player[setWidth][this.height];
            }
            return true;
        }
    }

    /**
     * Get the current board height.
     * @return the current board height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Attempt to set the board height.
     * @param setHeight the new height to set
     * @return true if the height was set successfully, false on error
     */
    public boolean setHeight(final int setHeight) {
        if (started) {
            return false;
        } else if (setHeight < MIN_HEIGHT || setHeight > MAX_HEIGHT) {
            return false;
        } else {
            this.height = setHeight;
            int max = 0;
            if (getWidth() > setHeight) {
                max = getWidth() - 1;
            } else {
                max = setHeight - 1;
            }
            if (getN() > max) {
                this.n = 0;
            }
            if (this.width > MIN_WIDTH) {
                board = new Player[this.width][setHeight];
            }
            return true;
        }
    }

    /**
     * Get the current board N value.
     * @return the current board N value
     */
    public int getN() {
        return this.n;
    }

    /**
     * Attempt to set the current board N value.
     * @param newN the new N
     * @return true, if successful
     */
    public boolean setN(final int newN) {
        int max = 0;
        if (getWidth() > getHeight()) {
            max = getWidth() - 1;
        } else {
            max = getHeight() - 1;
        }
        if (started) {
            return false;
        } else if (newN < MIN_N || this.width < MIN_WIDTH
                || this.height < MIN_HEIGHT  || newN > max) {
            return false;
        } else {
            this.n = newN;
            return true;
        }
    }

    /**
     * Get the current board's id.
     * @return the current board's id
     */
    public int getID() {
        return this.id;
    }

    /**
     * Test whether the position is valid for the board.
     * @param setX the X coordinate that they are trying to place a title at
     * @param setY the Y coordinate that they are trying to place a title at
     * @return the test result
     */


    /**
     * Set the board at a specific position.
     * @param player the player attempting the move
     * @param setX the X coordinate that they are trying to place a tile at
     * @param setY the Y coordinate that they are trying to place a tile at
     * @return true if the move succeeds, false on error
     */
    public boolean setBoardAt(final Player player, final int setX, final int setY) {
        if (this.width < MIN_WIDTH || this.height < MIN_HEIGHT || this.n < MIN_N) {
            return false;
        } else if (setX < 0 || setY < 0
                || setX >= this.width || setY >= this.height) {
            return false;
        } else if (getWinner() != null) {
            return false;
        } else if (this.board[setX][setY] != null) {
            return false;

        } else {
            this.board[setX][setY] = player;
            started = true;
        }
        Player winner = getWinner();
        if (winner != null) {
            winner.addScore();
        }
        return true;
    }

    /**
     * Drop a tile in particular column.
     * @param player the player attempting the move
     * @param setX the X coordinate for the stack that they are trying to dop a tile in
     * @return true if the move succeeds, false on error
     */
    public boolean setBoardAt(final Player player, final int setX) {
        if (this.width < MIN_WIDTH || this.height < MIN_HEIGHT || this.n < MIN_N) {
            return false;
        } else if (setX < 0 || setX >= this.width) {
            return false;
        } else if (getWinner() != null) {
            return false;
        } else {
            int dropPosition = 0;
            for (int i = 0; i < this.height; i++) {
                if (this.board[setX][i] == null) {
                    dropPosition = i;
                    break;
                }
            }
            this.board[setX][dropPosition] = player;
            started = true;
        }
        Player winner = getWinner();
        if (winner != null) {
            winner.addScore();
        }
        return true;
    }

    /**
     * Get the player at a specific board position.
     * @param getX the X coordinate to get the player at
     * @param getY the Y coordinate to get the player at
     * @return the player whose tile is a that position,
     * or null or error of if nobody has played at the position
     */
    public Player getBoardAt(final int getX, final int getY) {
        if (!started) {
            return null;
        } else if (getX < 0 || getY < 0 || getX > this.width || getY > this.height) {
            return null;
        } else {
            return this.board[getX][getY];
        }
    }

    /**
     * Return a copy of the board.
     * @return a copy of the current board
     */
    public Player[][] getBoard() {
        if (this.width < MIN_WIDTH || this.height < MIN_HEIGHT) {
            return null;
        } else {
            Player[][] copy = new Player[this.width][this.height];
            for (int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[0].length; j++) {
                    if (this.board[i][j] != null) {
                        copy[i][j] = new Player(this.board[i][j]);
                    }
                }
            }
            return copy;
        }
    }

    /**
     * @return the winner of the game, or null if the game has not ended
     */
    public Player getWinner() {
        if (this.board == null || this.width < MIN_WIDTH
                || this.height < MIN_HEIGHT || this.n < MIN_N) {
            return null;
        }
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length - this.n; j++) {
                if (this.board[i][j] != null) {
                    int count = 0;
                    for (int k = 1; k < this.n; k++) {
                        if (this.board[i][j + k] != null) {
                            if ((this.board[i][j + k]).getID() == (this.board[i][j]).getID()) {
                                count++;
                            }
                        }
                    }
                    if (count == this.n - 1) {
                        return this.board[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < this.board[0].length; i++) {
            for (int j = 0; j < this.board.length - this.n; j++) {
                if (this.board[j][i] != null) {
                    int count = 0;
                    for (int k = 1; k < this.n; k++) {
                        if (this.board[j + k][i] != null) {
                            if ((this.board[j + k][i]).getID() == (this.board[j][i]).getID()) {
                                count++;
                            }
                        }
                    }
                    if (count == this.n - 1) {
                        return this.board[i][j];
                    }
                }
            }
        }
        /**
         * check the diagonal line.
         * Bottom left to top right
         */


        for (int i = 0; i <= this.board.length - this.n; i++) {
            for (int j = 0; j <= this.board[0].length - this.n; j++) {
                if (this.board[i][j] != null) {
                    int count = 0;
                    for (int k = 1; k < this.n; k++) {
                        if (this.board[i + k][j + k] != null) {
                            if ((this.board[i][j]).getID() == (this.board[i + k][j + k]).getID()) {
                                count++;
                            }
                        }
                    }

                    if (count == this.n - 1) {
                        return this.board[i][j];
                    }
                }
            }
        }

        /**
         *Top left to bottom right.
         *Check the diagonal line
         */


        for (int i = 0; i <= this.board[0].length - this.n; i++) {

            for (int j = this.board.length - 1; j >= this.n - 1; j--) {

                if (this.board[i][j] != null) {
                    int count = 0;
                    for (int k = 1; k < this.n; k++) {
                        if (this.board[i + k][j - k] != null) {
                            if ((this.board[i][j]).getID() == (this.board[i + k][j - k]).getID()) {
                                count++;
                            }
                        }
                    }

                    if (count == this.n - 1) {
                        return this.board[i][j];
                    }
                }
            }
        }


        return null;
    }

    /**
     * Class method to create a new ConnectN board.
     * @param width the width of the new ConnectN instance to create
     * @param height the height of the new ConnectN instance to create
     * @param n the n value of the new ConnectN instance to create
     * @return the new ConnectN instance, or null if the parameters are invalid
     */
    public static ConnectN create(final int width, final int height, final int n) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT || n < MIN_N
                || width > MAX_WIDTH || height > MAX_HEIGHT) {
            return null;
        }
        int max = 0;
        if (width > height) {
            max = width - 1;
        } else {
            max = height - 1;
        }
        if (n > max) {
            return null;
        }
        return new ConnectN(width, height, n);
    }

    /**
     * Creates multiple new ConnectN instances.
     * @param number the number of new ConnectN instance to create.
     * @param width the width of the new ConnectN instance to create
     * @param height the height of the new ConnectN instance to create
     * @param n the n value of the new ConnectN instance to create
     * @return an array of new ConnectN instances, or null if the parameters are invalid
     */
    public static ConnectN[] createMany(final int number, final int width,
            final int height, final int n) {
        if (number < 0 || width < MIN_WIDTH || height < MIN_HEIGHT || n < MIN_N
                || width > MAX_WIDTH || height > MAX_HEIGHT) {
            return null;
        }
        int max = 0;
        if (width > height) {
            max = width - 1;
        } else {
            max = height - 1;
        }
        if (n > max) {
            return null;
        }
        ConnectN[] result = new ConnectN[number];
        for (int i = 0; i < number; i++) {
            result[i] = new ConnectN(width, height, n);
        }
        return result;
    }

    /**
     * Compare two ConnectN boards.
     * @param firstBoard the first ConnectN board to compare
     * @param secondBoard the second ConnectN board to compare
     * @return true if the boards are the same, false otherwise
     */
    public static boolean compareBoards(final ConnectN firstBoard, final ConnectN secondBoard) {
        if (firstBoard == null || secondBoard == null) {
            return false;
        }
        if (firstBoard.width != secondBoard.width || firstBoard.height != secondBoard.height
                || firstBoard.n != secondBoard.n) {
            return false;
        }
        if (firstBoard.width == 0 && secondBoard.width == 0
                && firstBoard.height == secondBoard.height
                && firstBoard.n == secondBoard.n) {
            return true;
        }
        if (firstBoard.height == 0 && secondBoard.height == 0
                && firstBoard.width == secondBoard.width
                && firstBoard.n == secondBoard.n) {
            return true;
        }
        if (firstBoard.n == 0 && secondBoard.n == 0 && firstBoard.height == secondBoard.height
                && firstBoard.width == secondBoard.width) {
            return true;
        }
        for (int i = 0; i < firstBoard.board.length; i++) {
            for (int j = 0; j < firstBoard.board[0].length; j++) {
                if (firstBoard.board[i][j] == null) {
                    if (secondBoard.board[i][j] != null) {
                        return false;
                    }
                } else {
                    if (secondBoard.board[i][j] == null) {
                        return false;
                    } else if (!firstBoard.board[i][j].equals(secondBoard.board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Compare any number of ConnectN boards.
     * @param boards the array of ConnectN boards to compare
     * @return true if all passed boards are the same, false otherwise
     */
    public static boolean compareBoards(final ConnectN... boards) {
        for (int i = 1; i < boards.length; i++) {
            if (!compareBoards(boards[0], boards[i])) {
                return false;
            }
        }
        return true;
    }
}

