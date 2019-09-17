package Server;

import java.io.Serializable;

public class Verify implements Serializable {
    private boolean verify;

    public Verify(boolean verify) {
        this.verify=verify;
    }

    public boolean isVerify() {
        return verify;
    }

    @Override
    public String toString() {
        return String.format("Verified= %b",verify);
    }
}
