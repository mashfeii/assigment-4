import Classes.Board;
import Classes.EntityPosition;
import Enums.InsectColor;
import Exceptions.*;
import Insects.Ant;
import Insects.Butterfly;
import Insects.Grasshopper;
import Insects.Spider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String INPUTFILE = "input.txt";
    private static final int BOARDUPPERBOUND = 1000;
    private static final int BOARDLOWERBOUND = 4;
    private static final int INSECTSUPPERBOUND = 16;
    private static final int FOODUPPERBOUND = 200;
    private static Board gameBoard;
    private static int boardSize;
    private static int numberOfInsects;
    private static int numberOfFoodPoints;
    private static Map<String, String> boardInsects = new HashMap<>();
    private static List<List<Integer>> entitiesCoordinates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInitialData();
    }

    /**
     * Creating game board, add there insects and food points from inputs
     * @throws IOException in case there is no input file
     */
    private static void getInitialData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUTFILE))) {
            boardSize = readIntegerValue(reader, "boardSize");
            gameBoard = new Board(boardSize);
            numberOfInsects = readIntegerValue(reader, "numberOfInsects");
            numberOfFoodPoints = readIntegerValue(reader, "numberOfFoodPoints");
            for (int i = 0; i < numberOfInsects; i++) {
                readInsectFromFile(reader);
            }
            for (int i = 0; i < numberOfFoodPoints; i++) {
                readFoodPointsFromFile(reader);
            }
        } catch (IOException e) {
            Execution.terminate("Where is the file! :(");
        }
    }

    /**
     * @param reader input stream
     * @throws IOException validating for entities positions, duplicating
     */
    private static void readFoodPointsFromFile(BufferedReader reader) throws IOException {
        try {
            List<String> currentFoodPoint = Arrays.asList(reader.readLine().split("\\s+"));
            int foodAmount = Integer.parseInt(currentFoodPoint.get(0));
            int xCoordinate = Integer.parseInt(currentFoodPoint.get(1));
            int yCoordinate = Integer.parseInt(currentFoodPoint.get(2));
            if (xCoordinate < 1 || xCoordinate > boardSize || yCoordinate < 1 || yCoordinate > boardSize) {
                throw new InvalidEntityPositionException();
            }

            EntityPosition foodPosition = new EntityPosition(xCoordinate, yCoordinate);
            if (entitiesCoordinates.contains(foodPosition.getCoordinates())) {
                throw new TwoEntitiesOnSamePositionException();
            } else {
                entitiesCoordinates.add(foodPosition.getCoordinates());
            }

        } catch (InvalidEntityPositionException e) {
            Execution.terminate(e.getMessage());
        } catch (TwoEntitiesOnSamePositionException e) {
            Execution.terminate(e.getMessage());
        } catch (IOException e) {
            Execution.terminate("Something wrong in food inputs");
        }
    }

    /**
     * @param reader input stream
     * @throws IOException validating for insect color, type, position, duplicating
     */
    private static void readInsectFromFile(BufferedReader reader) throws IOException {
        try {
            List<String> currentInsect = Arrays.asList(reader.readLine().split("\\s+"));
            String color = currentInsect.get(0);
            InsectColor currentColor = InsectColor.toColor(color);
            String currentType = validateInsectType(currentInsect.get(1));
            int xCoordinate = Integer.parseInt(currentInsect.get(2));
            int yCoordinate = Integer.parseInt(currentInsect.get(3));
            if (xCoordinate < 1 || xCoordinate > boardSize || yCoordinate < 1 || yCoordinate > boardSize) {
                throw new InvalidEntityPositionException();
            }
            if (!Objects.equals(boardInsects.get(color), currentType) || !boardInsects.containsKey(color)) {
                boardInsects.put(color, currentType);
            } else {
                throw new DuplicateInsectException();
            }

            EntityPosition currentPosition = new EntityPosition(xCoordinate, yCoordinate);

            if (entitiesCoordinates.contains(currentPosition.getCoordinates())) {
                throw new TwoEntitiesOnSamePositionException();
            } else {
                entitiesCoordinates.add(currentPosition.getCoordinates());
            }

            switch (currentType) {
                case "Grasshopper":
                    Grasshopper newGrasshopper = new Grasshopper(currentPosition,
                            currentColor);
                    gameBoard.addEntity(newGrasshopper);
                    break;
                case "Ant":
                    Ant newAnt = new Ant(currentPosition, currentColor);
                    gameBoard.addEntity(newAnt);
                    break;
                case "Butterfly":
                    Butterfly newButterfly = new Butterfly(currentPosition, currentColor);
                    gameBoard.addEntity(newButterfly);
                    break;
                case "Spider":
                    Spider newSpider = new Spider(currentPosition, currentColor);
                    gameBoard.addEntity(newSpider);
                    break;
                default:
                    Execution.terminate("Something goes wrong! :(");
            }

        } catch (InvalidInsectColorException e) {
            Execution.terminate(e.getMessage());
        } catch (InvalidInsectTypeException e) {
            Execution.terminate(e.getMessage());
        } catch (DuplicateInsectException e) {
            Execution.terminate(e.getMessage());
        } catch (InvalidEntityPositionException e) {
            Execution.terminate(e.getMessage());
        } catch (TwoEntitiesOnSamePositionException e) {
            Execution.terminate(e.getMessage());
        } catch (IOException e) {
            Execution.terminate("Something wrong in insects inputs :(");
        }
    }

    /**
     * @param type insect's type from inputs
     * @return the entered type if it matches one of the available
     * @throws InvalidInsectTypeException entered type does not match possible
     */
    private static String validateInsectType(String type) throws InvalidInsectTypeException {
        ArrayList<String> possibleTypes = new ArrayList<String>() {{
            add("Grasshopper");
            add("Butterfly");
            add("Ant");
            add("Spider");
        }};
        if (!possibleTypes.contains(type)) {
            throw new InvalidInsectTypeException();
        }
        return type;
    }

    /**
     * @param inputStream input (file) stream
     * @param type type of value to validate
     * @return the entered value if it satisfies the conditions
     * @throws IOException validate for board, food points, and insects boundaries + number format
     */
    private static int readIntegerValue(BufferedReader inputStream, String type) throws IOException {
        int localVariable = 0;
        try {
            localVariable = Integer.parseInt(inputStream.readLine());
            switch (type) {
                case "boardSize":
                    if (localVariable < BOARDLOWERBOUND | localVariable > BOARDUPPERBOUND) {
                        throw new InvalidBoardSizeException();
                    }
                    break;
                case "numberOfInsects":
                    if (localVariable < 1 | localVariable > INSECTSUPPERBOUND) {
                        throw new InvalidNumberOfInsectsException();
                    }
                    break;
                case "numberOfFoodPoints":
                    if (localVariable < 1 | localVariable > FOODUPPERBOUND) {
                        throw new InvalidNumberOfFoodPointsException();
                    }
                    break;
                default:
                    return 0;
            }
        } catch (InvalidBoardSizeException e) {
            Execution.terminate(e.getMessage());
        } catch (InvalidNumberOfFoodPointsException e) {
            Execution.terminate(e.getMessage());
        } catch (InvalidNumberOfInsectsException e) {
            Execution.terminate(e.getMessage());
        } catch (NumberFormatException e) {
            Execution.terminate(new InvalidBoardSizeException().getMessage());
        } catch (IOException e) {
            Execution.terminate("Something wrong in initial integers :(");
        }
        return localVariable;
    }
}
