package com.example.android.blackjack;

//This class defines how a player interact with Deck of cards
public class Player {
    //Private Attributes

    //This variable keeps track of player's current score
    private int m_score = 0;

    //This variable keeps track of players current card
    private int m_card = 0;

    //Keeps the track of Ace card drawn by Players
    private boolean m_hasAceCard;

    //Keeps track of Player's Cash
    private int m_cash = 0;

    //Everytime Player draws a card then he show that card and his score is updated
    public void drawCard(Deck deck){
        Card card = deck.getCard();
        m_score += card.getPoints();
        m_card = card.getCardImage();
        m_hasAceCard = card.getIsAce();
    }

    //Everytime Player gets an Ace he chooses how much score to Add
    public void addScoreForAce(int score){
        m_score += score;
    }

    //If Player Wins Cash will be added to him
    public void addCash(int cash){
        m_cash += cash;
    }

    //If Player losses then his cash is lost
    public void subtractCash(int cash){
        m_cash -= cash;
    }

    //Reset the scores of Player when new
    void reset(){
        m_score = 0;
        m_card = 0;
    }

    //Getters
    public int getScore(){ return m_score; }
    public int getCardDrawn(){ return m_card; }
    public boolean getHasAceCard(){ return m_hasAceCard; }
    public int getCash(){ return m_cash; }

}
