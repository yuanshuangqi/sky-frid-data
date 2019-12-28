package generator.model;

import lombok.Data;

@Data
public class DataBase {
    private String driver;
    private String url;
    private String user;
    private String password;

    public DataBase(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }
}
