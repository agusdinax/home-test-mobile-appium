package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public final class RegisterDataLoader {
    private static final RegisterData DATA = load();

    private static RegisterData load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream stream = RegisterDataLoader.class
                    .getClassLoader()
                    .getResourceAsStream("register-data.json");
            return mapper.readValue(stream, RegisterData.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load register-data.json", e);
        }
    }

    public static User getUser(String name) {
        User user = DATA.getUsers().get(name);
        if (user == null) {
            throw new RuntimeException("User not found: " + name);
        }
        return user;
    }
    
}