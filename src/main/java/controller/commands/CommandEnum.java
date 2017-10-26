package controller.commands;

import controller.commands.adminCommands.NewPeriodicalCommand;
import controller.commands.adminCommands.NewPeriodicalPageCommand;
import controller.commands.authorizationCommands.*;
import controller.commands.adminCommands.CheckAllOrdersCommand;
import controller.commands.orderCommands.*;
import controller.commands.userCommands.*;

public enum CommandEnum {
    LOGIN {{
        this.command = LoginCommand.getInstance();
    }},
    LOGIN_PAGE {{
        this.command = LoginPageCommand.getInstance();
    }},
    LOGOUT {{
        this.command = LogoutCommand.getInstance();
    }},
    WELCOME {{
        this.command = WelcomeCommand.getInstance();
    }},
    CHANGE_LANGUAGE {{
        this.command = ChangeLanguageCommand.getInstance();
    }},
    SIGNUP {{
        this.command = SignUpCommand.getInstance();
    }},
    SIGNUP_PAGE {{
        this.command = SignUpPageCommand.getInstance();
    }},
    USER_DETAILS {{
        this.command = UserDetailsCommand.getInstance();
    }},
    USER_UPDATE {{
        this.command = UserUpdateCommand.getInstance();
    }},
    USER_UPDATE_PAGE {{
        this.command = UserUpdatePageCommand.getInstance();
    }},
    NEW_PERIODICAL {{
        this.command = NewPeriodicalCommand.getInstance();
    }},
    NEW_PERIODICAL_PAGE {{
        this.command = NewPeriodicalPageCommand.getInstance();
    }},
    MAIN {{
        this.command = MainCommand.getInstance();
    }},
    ADD_TO_CART {{
        this.command = AddToCartCommand.getInstance();
    }},
    DELETE_FROM_CART {{
        this.command = DeleteFromCartCommand.getInstance();
    }},
    CART {{
        this.command = CartCommand.getInstance();
    }},
    ORDER_PAGE {{
        this.command = OrderPageCommand.getInstance();
    }},
    ORDER {{
        this.command = OrderCommand.getInstance();
    }},
    ORDER_CONFIRM {{
        this.command = OrderConfirmCommand.getInstance();
    }},
    CHECK_ALL_ORDERS {{
        this.command = CheckAllOrdersCommand.getInstance();
    }},
    ERROR {{
        this.command = ErrorCommand.getInstance();
    }},
    EMPTY {{
        this.command = EmptyCommand.getInstance();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
