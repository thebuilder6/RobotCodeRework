package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandManager implements Subsystem {

    private static CommandManager instance = null;

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    private CommandManager() {
        if (Settings.getSetting("subsystem_commandmanager_enabled", true)) {
            SubsystemManager.registerSubsystem(this);
        } else {
            isActive = false;
        }
    }

    private boolean isActive;

    @Override
    public String getName() {
        return "CommandManager";
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void initialize() {
        isActive = true;
    }

    @Override
    public void log() {
        SmartDashboard.putBoolean(getName() + "/isActive", isActive);
    }

    @Override
    public void update() {

    }

    public boolean executeCommand(String command) {
        //parse command
        //commands start with !
        //commands end with ;
        //commands can have spaces
        //commands can have parameters
        //"!command param1 param2 param3;"
        //command can be a subsystem method
        //"!SubsystemName.methodName param1 param2 param3;"
        //command can be a Static method
        //"!ClassName.methodName param1 param2 param3;"
        //"example: !Settings.setSetting key value;"

    }

    private class Command {
        public String operator = "";
        public String command = "";
        public String parameters = "";

    }

    private Command parseCommand(String commandString) {
        //parse command
        //commands start with !
        //commands end with ;
        //commands can have spaces
        //commands can have parameters
        //"!command param1 param2 param3;"
        //command can be a subsystem method
        //"!SubsystemName.methodName param1 param2 param3;"
        //command can be a Static method
        //"!ClassName.methodName param1 param2 param3;"
        //"example: !Settings.setSetting key value;"
        //"example: !DriveBase.setSpeed 0.5;"
        Command command = new Command();
        command.operator = commandString.substring(0, 1);
        command.command = commandString.substring(1, commandString.indexOf(" ", 1));
        command.parameters = commandString.substring(commandString.indexOf(" ", 1) + 1, commandString.length() - 1);

    }

}
