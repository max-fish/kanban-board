package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Class KanbanModel - the class stores the current state
 * of the model of the application. It holds a List of all boards
 * that currently exist in the application.
 *
 * The class is a Singleton.
 */
public class KanbanModel{
    private static KanbanModel instance = null;

    private List<Board> boards;

    public static KanbanModel instance()
    {
        if(instance == null)
            instance = new KanbanModel();

        return instance;
    }

    private KanbanModel()
    {
        boards = new ArrayList<>();
    }

    public void addBoard(Board board)
    {
        boards.add(board);
    }

}
