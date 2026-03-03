package commands;

public class ExitCommand implements Command {

    public ExitCommand() {}

    /**
     * Ukonci hru
     * @param args - commandid
     * @return boolean
     */
    @Override
    public boolean execute(String[] args) {
        return false;
    }
}