package controller.commands;

import controller.commands.adminCommands.NewPeriodicalCommand;
import controller.commands.adminCommands.NewPeriodicalPageCommand;
import controller.commands.authorizationCommands.*;
import controller.commands.adminCommands.CheckAllOrdersCommand;
import controller.commands.orderCommands.OrderCommand;
import controller.commands.orderCommands.OrderConfirmCommand;
import controller.commands.orderCommands.OrderPageCommand;
import controller.commands.userCommands.*;

public enum CommandEnum {
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGIN_PAGE {{
        this.command = new LoginPageCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    WELCOME {{
        this.command = new WelcomeCommand();
    }},
    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    SIGNUP {{
        this.command = new SignUpCommand();
    }},
    SIGNUP_PAGE {{
        this.command = new SignUpPageCommand();
    }},
    USER_DETAILS {{
        this.command = new UserDetailsCommand();
    }},
    USER_UPDATE {{
        this.command = new UserUpdateCommand();
    }},
    USER_UPDATE_PAGE {{
    this.command=new UserUpdatePageCommand();
    }},
    NEW_PERIODICAL {{
        this.command = new NewPeriodicalCommand();
    }},
    NEW_PERIODICAL_PAGE {{
        this.command = new NewPeriodicalPageCommand();
    }},
    MAIN {{
        this.command = new MainCommand();
    }},
    ADD_TO_CART {{
        this.command = new AddToCartCommand();
    }},
    DELETE_FROM_CART {{
        this.command = new DeleteFromCartCommand();
    }},
    CART {{
        this.command = new CartCommand();
    }},
    ORDER_PAGE {{
        this.command = new OrderPageCommand();
    }},
    ORDER {{
        this.command = new OrderCommand();
    }},
    ORDER_CONFIRM {{
        this.command = new OrderConfirmCommand();
    }},
    CHECK_ALL_ORDERS{{
        this.command=new CheckAllOrdersCommand();
    }},ERROR{{
        this.command = new ErrorCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
