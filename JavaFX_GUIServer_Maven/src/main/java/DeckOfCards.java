import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
	
    public ArrayList<Card> deck;
    public Image backOfCardImage;

   
    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;
        backOfCardImage = new Image("back.png");
    }

    // Constructor, generate the deck 
    public DeckOfCards()
    {
        List<String> suits = Card.getValidSuits(); 
        List<String> faceNames = Card.getValidFaceNames();

        deck = new ArrayList<>();

        for (String suit:suits)
        {
            for (String faceName:faceNames)
                deck.add(new Card(faceName,suit));
        }
    }

    // Deal the card at the top of the deck 
    public Card dealTopCard()
    {
        if (deck.size()>0) {
            return deck.remove(0);
        }
        else
            return null;
    }


    // Shuffle the deck of cards
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
}
