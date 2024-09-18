import java.io.Serializable;

public class PokerInfo implements Serializable {

	private static final long serialVersionUID = 2L;
	private String message;
	private String card;

    public PokerInfo(String message) {
        this.message = message;
    }
    
    public void PokerInfoCard(String card) {
        this.card = card;
    }
    
    public String getCard(String card) {
        return card;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
