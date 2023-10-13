import java.awt.*;
import java.util.Date;

public class PanelEvent extends AWTEvent {
    private Date date;
    private String key;

    public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 5555;


    public PanelEvent(CustomPanel panel, String key) {
        super(panel, EVENT_ID);
        date = new Date();
        this.key = key;
    }

    public Date getDate() {
        return date;
    }

    public String getKey() {
        return key;
    }

}
