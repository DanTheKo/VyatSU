package org.example;
import org.example.animals.*;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        WhiteBear[] whiteBears = {
                new WhiteBear("Шатун", "7", 750, 250, FurType.SOFT),
                new WhiteBear("Белыш", "8",1000, 260, FurType.HARD),
                new WhiteBear("Клык", "19",1500, 500, FurType.SOFT),
                new WhiteBear("Норка", "1",250, 150, FurType.ORDINARY)
        };

        TableManager tableManager = new TableManager();

        tableManager.Connect();


        tableManager.CreateTable(WhiteBear.class);

        tableManager.Insert(whiteBears);
//        for (WhiteBear whiteBear : whiteBears) {
//
//        }

        //tableManager.ClearTable(WhiteBear.class);

        //tableManager.DropTable(WhiteBear.class);


        tableManager.PrintScript();

        tableManager.Disconnect();

    }


}