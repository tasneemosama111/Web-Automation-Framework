package com.swaglabs.Utils;

/**
 * PURPOSE: This class is a utility to execute operating system terminal commands directly from Java.
 * It automates manual tasks like opening Allure reports, clearing folders, or running shell scripts
 * without the need for manual typing in the terminal/CMD.
 */
public class TerminalUtils {

    // Define a static method that accepts a variable number of string arguments as a command
        public static void executeTerminalCommand(String... command) {
            try {
                // Initialize ProcessBuilder with the command parts to be executed
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                // Link the terminal's input and output to the console so we can see the results
                processBuilder.inheritIO(); //redirect input/output to the current process
                // Start the actual execution of the command in the background
                Process process = processBuilder.start();
                // Force the Java code to pause and wait until the command finishes its task
                process.waitFor();
                // Log a success message in the console showing the exact command that was run
                LogsUtil.info("Command executed successfully : " +  String.join(" " , command));

                } catch (Exception e) {
                // Catch and log any errors, such as an invalid command or path issues
              LogsUtil.error("Failed to execute command " +  e.getMessage());            }
        }

}
