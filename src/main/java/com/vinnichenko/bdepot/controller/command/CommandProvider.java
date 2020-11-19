package com.vinnichenko.bdepot.controller.command;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.router.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Command provider.
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private CommandProvider() {
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command
     */
    public static Command getCommand(String commandName) {
        Command command = (req, resp) -> new Router(PagePath.ERROR_404);
        try {
            if (commandName != null) {
                command = CommandName.valueOf(commandName.toUpperCase()).getCommand();
            }
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
        return command;
    }
}
