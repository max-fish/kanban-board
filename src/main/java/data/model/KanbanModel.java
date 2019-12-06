package data.model;

import data.db.JSONLoader;
import controllers.HomePageController;

import java.util.List;
import java.util.ArrayList;

/**
 * Class KanbanModel - the class stores the current state
 * of the data.model of the application. It holds a List of all boards
 * that currently exist in the application.
 *
 * The class is a Singleton.
 */
public class KanbanModel{
    private static KanbanModel instance = null;

    private List<BoardModel> boards;
    private HomePageController homePageController;

    public static KanbanModel instance()
    {
        if(instance == null)
            instance = new KanbanModel();

        return instance;
    }

    private KanbanModel()
    {
        boards = new ArrayList<>();
        homePageController = null;
    }

    public void setHomePageController(HomePageController controller)
    {
        homePageController = controller;
    }

    public void addBoard(BoardModel board)
    {
        boards.add(board);
    }

    public void loadJSON()
    {
        ArrayList<BoardModel> newBoardModels = JSONLoader.instance().loadFile();
        if(newBoardModels == null)
            return;

        for(BoardModel board : newBoardModels)
            homePageController.makeNewBoard(board, board.getName());
    }

    public void saveJSON()
    {
        JSONLoader.instance().saveFile(boards);
    }
}
