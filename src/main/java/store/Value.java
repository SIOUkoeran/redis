package store;

import java.time.LocalDateTime;
import java.util.Objects;

public class Value {
    String key;
    LocalDateTime mills;

    String expiry;

    public Value(String key, LocalDateTime mills) {
        this.key = key;
        this.mills = mills;
    }

    public Value(String key, LocalDateTime mills, String expiry) {
        this.key = key;
        this.mills = mills;
        this.expiry = expiry;
    }

    public String getKey() {
        return key;
    }

    public LocalDateTime getMills() {
        return mills;
    }

    public String getExpiry() {
        return expiry;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            String key = (String) obj;
            return key.equals(this.key);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, mills);
    }
}
