package com.example.android.blackjack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGameActivity extends AppCompatActivity {

    //A deck object is created so player can draw a card from deck
    private Deck m_deck = new Deck();

    //This variable keeps track if dealers card is facing down or not
    private boolean m_cardFacingDown = true;

    //This variable keeps track of maximum score limit of game which is 21
    private int m_maxScoreLimit = 21;

    //Once the game is over then we want our buttons to not work until game is reset
    private boolean m_isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        final Player player = new Player();
        player.addCash(300);

        final Player dealer = new Player();
        dealer.addCash(1000);

        updateCash(player, dealer);

        //Shuffles and Distributes cards among Player and Dealer
        initialStateOfGame(player, dealer);


        //Whenever Player taps Hit button he draws a card
        Button hit = findViewById(R.id.draw_card);
        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_isGameOver){
                    player.drawCard(m_deck);
                    playerTurn(player, dealer);
                    if(player.getScore() > 21){
                        // Here comes code for when player is busted
                        isBusted(player, dealer);
                    }
                }
                else{
                    alertGameResult("Game Over!", player, dealer);
                }
            }
        });

        //Whenever player taps Stand button Dealer draws a card
        Button stand = findViewById(R.id.stop_draw_card);
        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!m_isGameOver){
                    if(m_cardFacingDown){
                        displayDealerCard(dealer);
                    }
                    //Once player stands then dealer will pick cards until his points are more than 17
                    while(true){
                        if(dealer.getScore() >= 17 && dealer.getScore() <= 21){
                            m_isGameOver = true;
                            break;
                        }
                        else if(dealer.getScore() > 21){
                            // Here comes code for when Dealer is busted
                            player.addCash(100);
                            dealer.subtractCash(100);
                            updateCash(player, dealer);
                            m_isGameOver = true;
                            alertGameResult("Dealer Busted!", player, dealer);
                            break;
                        }
                        else{
                            dealer.drawCard(m_deck);
                            dealerTurn(dealer);
                        }
                    }
                    if(dealer.getScore() <= 21){
                        comparePoints(player, dealer);
                    }
                }
                else{
                    alertGameResult("Game Over!", player, dealer);
                }
            }
        });

    }


    //This function updates cash for player and dealer after every game

    private void updateCash(Player player, Player dealer){
        TextView playerCash = findViewById(R.id.player_cash);
        playerCash.setText("Cash $: " + player.getCash());
        TextView dealerCash = findViewById(R.id.dealer_cash);
        dealerCash.setText("Cash $: " + dealer.getCash());
    }

    //This function compare dealer and player points
    private void comparePoints(Player player, Player dealer){
        if(player.getScore() > dealer.getScore()){
            player.addCash(100);
            dealer.subtractCash(100);
            updateCash(player, dealer);
            alertGameResult("You Won!", player, dealer);
        }
        else if(player.getScore() < dealer.getScore()){
            alertGameResult("You Lose!", player, dealer);
            player.subtractCash(100);
            dealer.addCash(100);
            updateCash(player, dealer);
        }
        else{
            alertGameResult("It's Tie!", player, dealer);
        }
    }

    //This function will display alert of result of game
    private void alertGameResult(String result, final Player player, final Player dealer){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(result);
        alert.setMessage("Would you like to play again?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGame(player, dealer);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    //This function is called everytime player gets an ace
    //Player will choose if he want 11 or 1 points for ace
    private void handelAceForPlayer(final Player player, final Player dealer){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("You Drew an Ace");
        alert.setMessage("How many points you want to add for ace?");
        alert.setPositiveButton("1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                player.addScoreForAce(1);
                TextView playerScore = findViewById(R.id.player_card_score);
                playerScore.setText("Score: " + player.getScore());
                if(player.getScore() > 21){
                    isBusted(player, dealer);
                }
            }
        });
        alert.setNegativeButton("11", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                player.addScoreForAce(11);
                TextView playerScore = findViewById(R.id.player_card_score);
                playerScore.setText("Score: " + player.getScore());
                if(player.getScore() > 21){
                    isBusted(player, dealer);
                }
            }
        });
        alert.show();
    }

    //This function stimulate player's turn when he picks up a card
    private void playerTurn(Player player, Player dealer){
        LinearLayout playerCardLayout = findViewById(R.id.player_cards_view_layout);
        ImageView playerCard = new ImageView(this);
        playerCard.setImageResource(player.getCardDrawn());
        playerCardLayout.addView(playerCard);
        if(player.getHasAceCard()){
            handelAceForPlayer(player, dealer);
        }
        else{
            TextView playerScore = findViewById(R.id.player_card_score);
            playerScore.setText("Score: " + player.getScore());
        }
    }

    //This function is called everytime dealer gets an ace
    //Since dealer is computer we add logic accordingly
    private void handleAceForDealer(Player dealer){
        //If adding Ace as 11 exceeds maxScoreLimit then add 1
        if(dealer.getScore() + 11 > m_maxScoreLimit){
            dealer.addScoreForAce(1);
        }
        else{
            dealer.addScoreForAce(11);
        }

        TextView playerScore = findViewById(R.id.dealer_card_score);
        playerScore.setText("Score: " + dealer.getScore());
    }

    //This function stimulate dealer's turn when he picks up a card
    private void dealerTurn(Player dealer){
        LinearLayout dealerCardLayout = findViewById(R.id.dealer_cards_view_layout);
        ImageView dealerCard = new ImageView(this);
        dealerCard.setImageResource(dealer.getCardDrawn());
        dealerCardLayout.addView(dealerCard);
        if(dealer.getHasAceCard()){
            handleAceForDealer(dealer);
        }
        else{
            TextView playerScore = findViewById(R.id.dealer_card_score);
            playerScore.setText("Score: " + dealer.getScore());
        }
    }

    //This function is called for once every time player press Stand button
    //This function is used to display Dealers facing down card
    private void displayDealerCard(Player dealer){
        dealer.drawCard(m_deck);
        ImageView dealerCard = findViewById(R.id.card_back_side);
        dealerCard.setImageResource(dealer.getCardDrawn());
        if(dealer.getHasAceCard()){
            handleAceForDealer(dealer);
        }
        else{
            TextView playerScore = findViewById(R.id.dealer_card_score);
            playerScore.setText("Score: " + dealer.getScore());
        }
        m_cardFacingDown = false;
    }

    //Whenever a new game is started the first card of dealer
    //Will be faced down
    private void faceDownDealerCard(){
        ImageView dealerCard = findViewById(R.id.card_back_side);
        dealerCard.setImageResource(R.drawable.card_back_side);
        m_cardFacingDown = true;
    }

    //Whenever game is started some cards are given to player and dealer
    //That is initial state of game
    //So, That part is handled by this function.
    private void initialStateOfGame(Player player, Player dealer){
        if(player.getCash() < 100){
            Toast.makeText(this, "Player is out of cash", Toast.LENGTH_SHORT).show();
            m_isGameOver = true;
        }
        else if(dealer.getCash() < 100){
            Toast.makeText(this, "Dealer is out of cash", Toast.LENGTH_SHORT).show();
            m_isGameOver = true;
        }
        else{
            //We first shuffle deck of cards
            m_deck.shuffle();

            //Then two cards are given to Player
            player.drawCard(m_deck);
            playerTurn(player, dealer);
            player.drawCard(m_deck);
            playerTurn(player, dealer);

            //Then two cards are given to Dealer
            //Since one card is shown by dealer therefore
            //DealerTurn is called once
            dealer.drawCard(m_deck);
            dealerTurn(dealer);
            //Reseting means game is not over anymore
            m_isGameOver = false;
        }
    }

    //Whenever a game is complete and player wants to play again then this function is used
    void resetGame(Player player, Player dealer){
        //Resetting Player's score and Views
        player.reset();
        LinearLayout playerCardLayout = findViewById(R.id.player_cards_view_layout);
        playerCardLayout.removeAllViews();
        TextView playerScore = findViewById(R.id.player_card_score);
        playerScore.setText("Score: " + player.getScore());

        //Resetting Dealer's score and Views
        dealer.reset();
        LinearLayout dealerCardLayout = findViewById(R.id.dealer_cards_view_layout);
        dealerCardLayout.removeAllViews();
        TextView dealerScore = findViewById(R.id.dealer_card_score);
        dealerScore.setText("Score: " + dealer.getScore());
        faceDownDealerCard();

        //Shuffles and Distributes cards among Player and Dealer
        initialStateOfGame(player, dealer);

    }

    //When player clicks back button the following function pauses the game
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Paused");
        alert.setMessage("Would you like to Quit or Resume?");
        alert.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Here Comes code for resuming app
            }
        });
        alert.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    //This function is used whenever player is busted
    private void isBusted(Player player, Player dealer){
        player.subtractCash(100);
        dealer.addCash(100);
        updateCash(player, dealer);
        m_isGameOver = true;
        alertGameResult("You Busted!", player, dealer);
    }

}
