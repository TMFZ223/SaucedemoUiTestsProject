package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.admin.user"), PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withUnknownUser() {
        return new User(PropertyReader.getProperty("saucedemo.unknown.user"), PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedOutUser() {
        return new User(PropertyReader.getProperty("saucedemo.locked.user"), PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyUserName() {
        return new User("", PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("saucedemo.admin.user"), "");
    }
}
