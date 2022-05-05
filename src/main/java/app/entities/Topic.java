package app.entities;

import java.io.Serializable;
/**
 * @author Danil Shatov
 */

public class Topic implements Serializable {

    /**
     * name of topic
     */
    private String name;

    /**
     * status of topic:
     * '0' - blocked;
     * '1' - unblock
     */
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
