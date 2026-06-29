package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public final class LoginDataLoader {
    private static final LoginData DATA = load();

    private static LoginData load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream stream = LoginDataLoader.class
                    .getClassLoader().getResourceAsStream("login-data.json");
            return mapper.readValue(stream, LoginData.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load login-data.json", e);
        }
    }

    public static User getUser(String userName) {
        User user = DATA.getUsers().get(userName);
        if (user == null) {
            throw new RuntimeException("User not found: " + userName);
        }
        return user;
    }
}