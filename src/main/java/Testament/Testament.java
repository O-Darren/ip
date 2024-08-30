package Testament;

import Tasks.TaskList;
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;

public class Testament {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public Testament(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = storage.load();
        parser = new Parser(ui, taskList, storage);
    }

    public void run() {
        ui.welcome();
        parser.getUserInput();
    }

    public static void main(String[] args) {
        new Testament("Memory.TaskList.txt").run();
    }
}