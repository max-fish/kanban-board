package data.db;

import utils.FileIO;
import controllers.HomePageController;
import data.model.BoardModel;

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
        List<BoardModel> newBoardModels = FileIO.instance().loadFromJson();
        if(newBoardModels == null)
            return;

        for(BoardModel board : newBoardModels)
        {
            board.init();
            homePageController.makeNewBoard(board, board.getName());
        }
    }

    public void loadSession()
    {
        List<BoardModel> newBoardModels = FileIO.instance().loadSession();
        if(newBoardModels == null)
            return;

        for(BoardModel board : newBoardModels)
        {
            board.init();
            homePageController.makeNewBoard(board, board.getName());
        }

        System.out.println(boards.size());

        //boards.addAll(newBoardModels);  <- not needed as the boards are added to the model in makeNewBoard method, left as a reminder in case of refactoring
    }

    public void saveJSON()
    {
        FileIO.instance().saveToJson(boards);
    }

    public void saveSession()
    {
        FileIO.instance().saveSession(boards);
    }
}
