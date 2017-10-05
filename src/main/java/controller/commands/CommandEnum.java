package controller.commands;

import controller.commands.adminCommands.NewPeriodicalCommand;
import controller.commands.adminCommands.NewPeriodicalPageCommand;
import controller.commands.authorizationCommands.*;
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
    SIGNUP {{
        this.command = new SignUpCommand();
    }},
    SIGNUP_PAGE {{
        this.command = new SignUpPageCommand();
    }},
    USER_DETAILS {{
        this.command = new UserDetails();
    }},
    NEW_PERIODICAL {{
        this.command = new NewPeriodicalCommand();
    }},
    NEW_PERIODICAL_PAGE {{
        this.command=new NewPeriodicalPageCommand();
    }},
    MAIN {{
        this.command = new MainCommand();
    }},
    ADD_TO_CART {{
        this.command = new AddToCartCommand();
    }},
    DELETE_FROM_CART {{
        this.command = new DeleteFromCart();
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
    SUCCESS_PAGE {{
        this.command = new SuccessPageCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
