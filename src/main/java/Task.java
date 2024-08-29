public abstract class Task {
    private String taskname;
    private boolean done;
    public Task(String s) {
        taskname = s;
        done = false;
    }

    public static Task of(String userInput) throws TestamentException {
        String[] splitUserInput = userInput.split(" ", 2);
        String identifier = splitUserInput[0];

        if (identifier.equals("todo")) {
            if (splitUserInput.length < 2) {
                throw new TaskDescriptionEmptyException("todo");
            }
            return new ToDo(splitUserInput[1]);
        } else if (identifier.equals("deadline")) {
            if (splitUserInput.length < 2) {
                throw new TaskDescriptionEmptyException("deadline");
            }

            String[] details = splitUserInput[1].split("/by ", 2);
            if (details.length < 2) {
                throw new DeadlineNoDateException();
            }
            return new Deadline(details[0], details[1]);
        } else if (identifier.equals("event")) {
            if (splitUserInput.length < 2) {
                throw new TaskDescriptionEmptyException("event");
            }

            String[] details = splitUserInput[1].split("/from ", 2);
            if (details.length < 2) {
                throw new EventNoTimeException();
            }
            String info = details[0];
            String[] dates = details[1].split(" /to ", 2);
            if (dates.length < 2) {
                throw new EventNoTimeException();
            }
            return new Events(info, dates[0], dates[1]);
        } else {
            //task not recognised
            throw new CommandNotRecognisedException();
        }
    }

    public String getDetails() {
        String str = "";
        if (done) {
            str = "[D] / ";
        } else {
            str = "[N] / ";
        }
        return str + taskname;
    }

    public abstract String infoForFile();

    public void done() {
        done = true;
    }

    public void undone() {
        done = false;
    }

    @Override
    public String toString() {
        String str = "";
        if (done) {
            str = "[X] ";
        } else {
            str = "[ ] ";
        }
        str = str + taskname;
        return str;
    }
}
