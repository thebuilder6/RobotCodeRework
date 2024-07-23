package frc.robot.Subsystems;

import java.lang.reflect.Method;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;

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

    /**
     * Executes a command string.
     *
     * A command string is in the format "!CommandName.MethodName Param1 Param2 Param3;"
     * where CommandName is the name of a subsystem or a class, MethodName is the name of a
     * method in the CommandName class, and Param1, Param2, Param3 are optional parameters to
     * the method.
     *
     * @param commandString The command string to execute.
     * @return True if the command was executed successfully, false otherwise.
     */
    public boolean executeCommand(String commandString) {
        Command command = parseCommand(commandString);

        String[] commandParts = command.command.split("\\.");

        if (commandParts.length == 2) {
            String subsystemName = commandParts[0];
            String methodName = commandParts[1];

            Subsystem subsystem = SubsystemManager.getSubsystem(subsystemName);

            if (subsystem != null) {
                return invokeMethod(subsystem, methodName, command.parameters);
            }
        } else if (commandParts.length == 1) {
            String className = commandParts[0];
            String methodName = commandParts[1];

            try {
                Class<?> clazz = Class.forName(className);
                Object object = clazz.getDeclaredConstructor().newInstance();

                return invokeMethod(object, methodName, command.parameters);
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    /**
     * Invokes a method on the given object with the given parameters. The method name
     * and parameters are taken from the command string.
     * 
     * @param object An object with a method of the same name as the command.
     * @param methodName The name of the method to invoke.
     * @param parameters The parameters to pass to the method, as a string.
     * @return True if the method was invoked successfully, false otherwise.
     */
    private boolean invokeMethod(Object object, String methodName, String parameters) {

        try {
            // If there are no parameters, create an empty parameter array.
            Class<?>[] parameterTypes = new Class<?>[0];
            String[] parameterValues = new String[0];

            // If there are parameters, split them into an array.
            if (parameters != null && !parameters.isEmpty()) {
                parameterTypes = new Class<?>[parameters.split(" ").length];
                parameterValues = parameters.split(" ");

                // Iterate over the parameter values and determine their types.
                for (int i = 0; i < parameterValues.length; i++) {
                    if (parameterValues[i].equalsIgnoreCase("true") || parameterValues[i].equalsIgnoreCase("false")) {
                        parameterTypes[i] = boolean.class;
                    } else if (parameterValues[i].matches("^-?\\d+$")) {
                        parameterTypes[i] = int.class;
                    } else {
                        parameterTypes[i] = String.class;
                    }
                }
            }

            // Get the method with the given name and parameter types.
            Method method = object.getClass().getMethod(methodName, parameterTypes);

            // Invoke the method with the given parameters.
            method.invoke(object, convertParameters(parameterValues, parameterTypes));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Converts an array of parameter values to an array of objects with the
     * corresponding types.
     * 
     * @param parameterValues An array of parameter values.
     * @param parameterTypes An array of parameter types.
     * @return An array of objects with the corresponding types.
     */
    private Object[] convertParameters(String[] parameterValues, Class<?>[] parameterTypes) {

        Object[] parameters = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterTypes[i] == boolean.class) {
                parameters[i] = Boolean.parseBoolean(parameterValues[i]);
            } else if (parameterTypes[i] == int.class) {
                parameters[i] = Integer.parseInt(parameterValues[i]);
            } else {
                parameters[i] = parameterValues[i];
            }
        }

        return parameters;
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
        return command;
    }

}
