package com.example.android.blackjack;

import java.util.Random;


//This class Creates a Deck of 52 cards and Behaviour of Deck
public class Deck {
    //Private Attributes

    //Deck contains 52 cards so Array of 52 cards is made
    private Card m_deck[] = new Card[52];

    //When a card is drawn from deck then this variable
    //Moves to next card in deck
    private int m_index = 0;

    //Public Constructor for initializing our Deck of Cards
    //For Ace true is passed instead of points so player can choose points according to its needs
    public Deck(){
        m_deck[0] = new Card(R.drawable.two_of_clubs, 2);
        m_deck[1] = new Card(R.drawable.three_of_clubs, 3);
        m_deck[2] = new Card(R.drawable.four_of_clubs, 4);
        m_deck[3] = new Card(R.drawable.five_of_clubs, 5);
        m_deck[4] = new Card(R.drawable.six_of_clubs, 6);
        m_deck[5] = new Card(R.drawable.seven_of_clubs, 7);
        m_deck[6] = new Card(R.drawable.eight_of_clubs, 8);
        m_deck[7] = new Card(R.drawable.nine_of_clubs, 9);
        m_deck[8] = new Card(R.drawable.ten_of_clubs, 10);
        m_deck[9] = new Card(R.drawable.jack_of_clubs, 10);
        m_deck[10] = new Card(R.drawable.queen_of_clubs, 10);
        m_deck[11] = new Card(R.drawable.king_of_clubs, 10);
        m_deck[12] = new Card(R.drawable.ace_of_clubs, true);
        m_deck[13] = new Card(R.drawable.two_of_diamonds, 2);
        m_deck[14] = new Card(R.drawable.three_of_diamonds, 3);
        m_deck[15] = new Card(R.drawable.four_of_diamonds, 4);
        m_deck[16] = new Card(R.drawable.five_of_diamonds, 5);
        m_deck[17] = new Card(R.drawable.six_of_diamonds, 6);
        m_deck[18] = new Card(R.drawable.seven_of_diamonds, 7);
        m_deck[19] = new Card(R.drawable.eight_of_diamonds, 8);
        m_deck[20] = new Card(R.drawable.nine_of_diamonds, 9);
        m_deck[21] = new Card(R.drawable.ten_of_diamonds, 10);
        m_deck[22] = new Card(R.drawable.jack_of_diamonds, 10);
        m_deck[23] = new Card(R.drawable.queen_of_diamonds, 10);
        m_deck[24] = new Card(R.drawable.king_of_diamonds, 10);
        m_deck[25] = new Card(R.drawable.ace_of_diamonds, true);
        m_deck[26] = new Card(R.drawable.two_of_hearts, 2);
        m_deck[27] = new Card(R.drawable.three_of_hearts, 3);
        m_deck[28] = new Card(R.drawable.four_of_hearts, 4);
        m_deck[29] = new Card(R.drawable.five_of_hearts, 5);
        m_deck[30] = new Card(R.drawable.six_of_hearts, 6);
        m_deck[31] = new Card(R.drawable.seven_of_hearts, 7);
        m_deck[32] = new Card(R.drawable.eight_of_hearts, 8);
        m_deck[33] = new Card(R.drawable.nine_of_hearts, 9);
        m_deck[34] = new Card(R.drawable.ten_of_hearts, 10);
        m_deck[35] = new Card(R.drawable.jack_of_hearts, 10);
        m_deck[36] = new Card(R.drawable.queen_of_hearts, 10);
        m_deck[37] = new Card(R.drawable.king_of_hearts, 10);
        m_deck[38] = new Card(R.drawable.ace_of_hearts, true);
        m_deck[39] = new Card(R.drawable.two_of_spades, 2);
        m_deck[40] = new Card(R.drawable.three_of_spades, 3);
        m_deck[41] = new Card(R.drawable.four_of_spades, 4);
        m_deck[42] = new Card(R.drawable.five_of_spades, 5);
        m_deck[43] = new Card(R.drawable.six_of_spades, 6);
        m_deck[44] = new Card(R.drawable.seven_of_spades, 7);
        m_deck[45] = new Card(R.drawable.eight_of_spades, 8);
        m_deck[46] = new Card(R.drawable.nine_of_spades, 9);
        m_deck[47] = new Card(R.drawable.ten_of_spades, 10);
        m_deck[48] = new Card(R.drawable.jack_of_spades, 10);
        m_deck[49] = new Card(R.drawable.queen_of_spades, 10);
        m_deck[50] = new Card(R.drawable.king_of_spades, 10);
        m_deck[51] = new Card(R.drawable.ace_of_spades, true);
    }

    //Whenever a new game is started the cards are shuffled and m_index is set to 0
    public void shuffle() {
        m_index = 0;
        Random rd = new Random();
        for(int i = m_deck.length - 1; i >= 0; i--) {
            int index = rd.nextInt(i+1);
            Card temp = m_deck[i];
            m_deck[i] = m_deck[index];
            m_deck[index] = temp;
        }
    }

    //When ever player Draws a card then it returns card and increase index
    Card getCard(){
        return m_deck[m_index++];
    }
}
