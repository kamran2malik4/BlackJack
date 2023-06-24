package com.example.android.blackjack;

//This class creates card properties and return those
public class Card {
    //Private Card properties

    private int m_cardResourceID; //Card Image Resource
    private int m_points = 0; //Points of card according to card value
    private boolean m_isAce = false; //If card is Ace then let user to chose score

    //Public Constructors
    public Card(int cardResourceID, int points){
        m_cardResourceID = cardResourceID;
        m_points = points;
    }

    //Public constructor to keep track of Ace
    public Card(int cardResourceID, boolean isAce){
        m_cardResourceID = cardResourceID;
        m_isAce = isAce;
    }

    //Public Getters
    public int getPoints(){
        return m_points;
    }

    public int getCardImage(){
        return m_cardResourceID;
    }

    public boolean getIsAce(){ return m_isAce; }
}
