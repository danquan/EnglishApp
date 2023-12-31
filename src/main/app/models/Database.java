package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database {

    public abstract String dictionaryLookup(String wordTarget) throws SQLException;

    public abstract ArrayList<Word> dictionarySearcher(String wordTarget) throws SQLException ;

    public abstract boolean dictionaryAddWord(String wordTarget, String wordExplain) throws SQLException;

    public abstract boolean dictionaryDeleteWord(String wordTarget) throws SQLException;

    public abstract ArrayList<Word> getAllWords() throws SQLException;
}
