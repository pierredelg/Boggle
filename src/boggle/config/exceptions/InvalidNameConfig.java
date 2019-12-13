package boggle.config.exceptions;

import java.io.IOException;

public class InvalidNameConfig extends IOException {

    public InvalidNameConfig(String message){
        super(message);
    }
}
