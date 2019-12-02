package model;

import utils.JSONLoader;
import controllers.HomePageController;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Class KanbanModel - the class stores the current state
 * of the model of the application. It holds a List of all boards
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

    public void loadJSON(MouseEvent e)
    {
        ArrayList<BoardModel> newBoardModels = JSONLoader.instance().loadFile();
        if(newBoardModels == null)
            return;

        for(BoardModel board : newBoardModels)
            homePageController.makeNewBoard(board, new Label(board.getName()));

        //boards.addAll(newBoardModels);
    }

    public void saveJSON(MouseEvent e)
    {
        JSONLoader.instance().saveFile(boards);
    }
}
