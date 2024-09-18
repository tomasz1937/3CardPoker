import java.io.Serializable;

public class PokerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;

    public PokerInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
   
    public void setMessage(String message) {
        this.message = message;
    }
    
 
    
 
    
  
}