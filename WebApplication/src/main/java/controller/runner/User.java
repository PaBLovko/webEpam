package controller.runner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int id;
    String login;
    String password;
    String role;
    int inSystem;
}
