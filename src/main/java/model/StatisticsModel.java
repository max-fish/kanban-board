package model;

import data.model.BoardModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class StatisticsModel
{
    private BoardModel board;

    public StatisticsModel(BoardModel board){
        this.board = board;
    }

    public ArrayList<LocalDate> getCardDates(){ return board.getCardDates(); }

    public BoardModel getBoard(){ return board; }
}
