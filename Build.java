/*
 * Assignment M6 Phase 3 Modified
 * Add Card Framework and create game "High Card".
 * Authors: Christian Guerrero, Jose Garcia, Grace Alvarez, Gabriel Loring
 * Last Changed: April 10th, 2018
 * 
 */

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Build
{
	   static int NUM_CARDS_PER_HAND = 7;
	   static int NUM_PLAYERS = 2;
	   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
	   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
	   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
	   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

	   static JButton[] humanCardButtons = new JButton[NUM_CARDS_PER_HAND];
	   static final int NUM_CARD_IMAGES = 56;
	   static GUICard cardGUI = new GUICard();

	   static CardTable newCardTable
	      = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);

	   static int numPacksPerDeck = 1;
	   static int numJokersPerPack = 0;
	   static int numUnusedCardsPerPack = 0;
	   static Card[] unusedCardsPerPack = null;
	   static Card[] winnings = new Card[NUM_CARDS_PER_HAND * 2];
	   static int winningTotal = 0;

	   static CardGameFramework highCardGame = new CardGameFramework(
	      numPacksPerDeck, numJokersPerPack,
	      numUnusedCardsPerPack, unusedCardsPerPack,
	      NUM_PLAYERS, NUM_CARDS_PER_HAND);

	   static Clock insertClock = new Clock();
	   
	  
	
	
 //main method ----------------------------------------------------------------------------------
   public static void main(String[] args)
   {
      //CardGameBuild model = new CardGameBuild();
      //CardGameBuild view = new CardGameBuild();
      
      //view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //view.setVisible(true);
      
      //---------------------------------------------------
      //Adding updated code into main method here----------
      //---------------------------------------------------
      
  
	   
	   
	   
	   
	   
      CardTable newCardTable = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      newCardTable.setSize(900,800);
      newCardTable.setLocationRelativeTo(null);
      newCardTable.setLayout(new GridLayout(3,1));
      newCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      GUICard.loadCardIcons();
           
      // CREATE LABELS ----------------------------------------------------
      
      //TOP PANEL
      for(int k = 0; k < NUM_CARDS_PER_HAND; k++)
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
      for(int k = 0; k < NUM_CARDS_PER_HAND; k++)
         newCardTable.pnlComputerHand.add(computerLabels[k]);
      
      // MIDDLE PANEL
      for(int k = 0; k < NUM_PLAYERS; k++)
         playedCardLabels[k] = new JLabel(GUICard.getIcon(generateRandomCard()));
      for(int k = 0; k < NUM_PLAYERS; k++)
         newCardTable.pnlPlayArea.add(playedCardLabels[k]);
      
      playLabelText[0] = new JLabel("Computer", JLabel.CENTER);
      playLabelText[1] = new JLabel("Player", JLabel.CENTER);   
      for(int k = 0; k < NUM_PLAYERS; k++)
         newCardTable.pnlPlayArea.add(playLabelText[k]);
      
      // BOTTOM PANEL
      for(int k = 0; k < NUM_CARDS_PER_HAND; k++)
         humanLabels[k] = new JLabel(GUICard.getIcon(generateRandomCard()));
      for(int k = 0; k < NUM_CARDS_PER_HAND; k++)
         newCardTable.pnlHumanHand.add(humanLabels[k]);
      
      // ADD LABELS TO PANELS -----------------------------------------
      newCardTable.add(newCardTable.pnlComputerHand);
      newCardTable.add(newCardTable.pnlPlayArea);
      newCardTable.add(newCardTable.pnlHumanHand);      
      
      
      //add timer
      newCardTable.pnlTimer.add(insertClock.timeText);
      newCardTable.pnlTimer.add(insertClock.startStopButton);
      insertClock.timeText.setFont(new Font("Aerial", Font.BOLD, 20));

      // show everything to the user
      newCardTable.setVisible(true);
   }
   
   static Card generateRandomCard()
   {
      int suit = (int)(Math.random() * 4);
      int value = (int)(Math.random() * 14);
      return new Card(Card.cardValue[value], Card.Suit.values()[suit]);
   }   
     
   
//END main method ---------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------

   public CardGameFramework cardFramework;

   public int getNumCards()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public int getNumPlayers()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public Card getLeftSideCard()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
//class Deck
class Deck
{
 //Deck capacity set to six packs by 52 cards per pack
 public static final int MAX_CARDS = 6 * 52;
 
 //Private static member data
 private static Card[] masterPack;
 private static boolean isExecuted;
 
 //Private member data
 private Card[] cards;
 private int topCard;
 private int numPacks;
 
 //Constructor to initialize masterpack
 public Deck(int numPacks)
 {
    allocateMasterPack();
    this.cards = masterPack;
    init(numPacks);
 } 
 
 //if no parameters are passed 1 pack is assumed
 public Deck()
 {
    this.numPacks = 1;
    allocateMasterPack();
    init(numPacks);
 }
 
 // Accessor for number of cards
 public int getNumCards()
 {
    return cards.length;
 }
 
 // 
 public void init(int numPacks)
 {
    this.numPacks = numPacks;
    getTopCard();
    if(topCard <= MAX_CARDS && numPacks != 0)
    {
       cards = new Card[topCard];
       for(int k = 0; k < cards.length; k++)
       {
          cards[k] = new Card();
          for(int k1 =0; k1 < numPacks; k1++)
          {
             for(int i = 52 * k1, j = 0; i < 52 * k1 + 52; i++, j++)
             {
                cards[i] = masterPack[j];
             }
          }
       }
    }
    else
    {
       return;
    }
 } 
 
 // Used to shuffle the cards[] 
 public void shuffle()
 {
    Random rand = new Random();
    for(int k = cards.length - 1; k > 0; k--)
    {
       int randomIndex = rand.nextInt(k + 1);
       Card tempCard = cards[randomIndex];
       cards[randomIndex] = cards[k];
       cards[k] = tempCard;
    }
 } 
 
 // Used to deal cards 
 public Card dealCard()
 {
    if(topCard == 0)
    {
       return null;
    }
    Card returnCard = cards[topCard -1];
    cards[topCard -1] = null;
    topCard--;
    return returnCard;
 }
 
 // Accessor returns the value of numPacks * 52 using topCard 
 public int getTopCard ()
 {
    topCard = numPacks * 52;
    return topCard;
 }

 
 public boolean addCard(Card card)
 {
    int numOfInstances = 0;
    for(int i = 0; i < topCard; i++)
    {
       if(cards[i].equals(card))
          numOfInstances++;
    }
    
    if(numOfInstances >= numPacks)
       return false;
    
    cards[topCard].set(card.getValue(), card.getSuit());
    topCard++;
    
    return true; 
 } 

 public boolean removeCard(Card card)
 {
    boolean matchFound = false;

    for (int i = 0; i < topCard; i++)
    {
       while ( cards[i].equals(card) )
       {
          cards[i] = cards[topCard - 1];
          topCard--;
          matchFound = true;
          if ( i >= topCard )
             break;
       }
    }
    return matchFound;
 }

 public void sort()
 {
    Card.arraySort(cards, topCard);
 }

 // Accessor inspects card and returns them or returns illegal message
 public Card inspectCard(int cardIndex) 
 {
    if(topCard == 0 || cardIndex < 0 || cardIndex > topCard)
    {
       return new Card('F', Card.Suit.spades);
    }
    else
    {
       return cards[cardIndex];
    }
 }
 
 // Place holder 
 private static void allocateMasterPack()
 {
    if(!isExecuted)
    {
       masterPack = new Card[52];
       Card.Suit suit;
       int k;
       int j;
       char value;
       
       for( k = 0; k < masterPack.length; k++)
       {
          masterPack[k] = new Card();
       }
       for(k = 0; k < 4; k++)
       {
          switch(k)
          {
             case 0: suit = Card.Suit.clubs;
                     break;
             case 1: suit = Card.Suit.diamonds;
                     break;
             case 2: suit = Card.Suit.hearts;
                     break;
             case 3: suit = Card.Suit.spades;
                     break;
             default: suit = Card.Suit.spades;
                     break;
          }
          masterPack[13 * k].set('A', suit);
          for(value = '2', j = 1; value <= '9'; value++, j++)
          {
             masterPack[13 * k + j].set(value, suit);
             masterPack[13 * k + 9].set('T', suit);
             masterPack[13 * k + 10].set('J', suit);
             masterPack[13 * k + 11].set('Q', suit);
             masterPack[13 * k + 12].set('K', suit);
          }
       }
       isExecuted = true;
    }
    else
    {
       return;
    }
 }
}

//End class Deck--------------------------------------------------------------------


//class CardGameFramework--------------------------------------------------------------

class CardGameFramework
{
private static final int MAX_PLAYERS = 50;

private int numPlayers;
private int numPacks;            // # standard 52-card packs per deck
                              // ignoring jokers or unused cards
private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
private int numUnusedCardsPerPack;  // # cards removed from each pack
private int numCardsPerHand;        // # cards to deal each player
private Deck deck;               // holds the initial full deck and gets
                              // smaller (usually) during play
private Hand[] hand;             // one Hand for each player
private Card[] unusedCardsPerPack;   // an array holding the cards not used
                                  // in the game.  e.g. pinochle does not
                                  // use cards 2-8 of any suit

public CardGameFramework( int numPacks, int numJokersPerPack,
   int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
   int numPlayers, int numCardsPerHand)
{
int k;

// filter bad values
if (numPacks < 1 || numPacks > 6)
   numPacks = 1;
if (numJokersPerPack < 0 || numJokersPerPack > 4)
   numJokersPerPack = 0;
if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
   numUnusedCardsPerPack = 0;
if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
   numPlayers = 4;
// one of many ways to assure at least one full deal to all players
if  (numCardsPerHand < 1 ||
      numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
      / numPlayers )
   numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

// allocate
this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
this.hand = new Hand[numPlayers];
for (k = 0; k < numPlayers; k++)
   this.hand[k] = new Hand();
deck = new Deck(numPacks);

// assign to members
this.numPacks = numPacks;
this.numJokersPerPack = numJokersPerPack;
this.numUnusedCardsPerPack = numUnusedCardsPerPack;
this.numPlayers = numPlayers;
this.numCardsPerHand = numCardsPerHand;
for (k = 0; k < numUnusedCardsPerPack; k++)
   this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

// prepare deck and shuffle
newGame();
}

//constructor overload/default for game like bridge
public CardGameFramework()
{
this(1, 0, 0, null, 4, 13);
}

public Hand getHand(int k)
{
// hands start from 0 like arrays

// on error return automatic empty hand
if (k < 0 || k >= numPlayers)
   return new Hand();

return hand[k];
}

public Card getCardFromDeck() { return deck.dealCard(); }

public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

public void newGame()
{
int k, j;

// clear the hands
for (k = 0; k < numPlayers; k++)
   hand[k].resetHand();

// restock the deck
deck.init(numPacks);

// remove unused cards
for (k = 0; k < numUnusedCardsPerPack; k++)
   deck.removeCard( unusedCardsPerPack[k] );

// add jokers
for (k = 0; k < numPacks; k++)
   for ( j = 0; j < numJokersPerPack; j++)
      deck.addCard( new Card('X', Card.Suit.values()[j]) );

// shuffle the cards
deck.shuffle();
}

public boolean deal()
{
// returns false if not enough cards, but deals what it can
int k, j;
boolean enoughCards;

// clear all hands
for (j = 0; j < numPlayers; j++)
   hand[j].resetHand();

enoughCards = true;
for (k = 0; k < numCardsPerHand && enoughCards ; k++)
{
   for (j = 0; j < numPlayers; j++)
      if (deck.getNumCards() > 0)
         hand[j].takeCard( deck.dealCard() );
      else
      {
         enoughCards = false;
         break;
      }
}

return enoughCards;
}

void sortHands()
{
int k;

for (k = 0; k < numPlayers; k++)
   hand[k].sort();
}

Card playCard(int playerIndex, int cardIndex)
{
// returns bad card if either argument is bad
if (playerIndex < 0 ||  playerIndex > numPlayers - 1 ||
    cardIndex < 0 || cardIndex > numCardsPerHand - 1)
{
   //Creates a card that does not work
   return new Card('M', Card.Suit.spades);      
}

// return the card played
return hand[playerIndex].playCard(cardIndex);

}


boolean takeCard(int playerIndex)
{
// returns false if either argument is bad
if (playerIndex < 0 || playerIndex > numPlayers - 1)
   return false;

 // Are there enough Cards?
 if (deck.getNumCards() <= 0)
    return false;

 return hand[playerIndex].takeCard(deck.dealCard());
}

}

//------End Class CardGameFramework--------------------------------------------------------

class CardGameBuild
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   
   public Hand computerHand;
   public Hand playerHand;
   public CardGameFramework cardFramework;
   
   private Card leftCard;
   private Card rightCard;
   private boolean computerCannotPlay;
   private boolean playerCannotPlay;
   private int compPass;
   private int playerPass;
   
   public CardGameBuild()
   {
      newGame();
   }
   public void setVisible(boolean b)
   {
      // TODO Auto-generated method stub
      
   }
   public void setDefaultCloseOperation(int exitOnClose)
   {
      // TODO Auto-generated method stub
      
   }
   private void newGame()
   {
      this.cardFramework = new CardGameFramework(1, 0, 0, null, 
            NUM_PLAYERS, NUM_CARDS_PER_HAND);
      this.cardFramework.deal();
      this.computerHand = cardFramework.getHand(0);
      this.playerHand = cardFramework.getHand(1);
      this.computerHand.sort();
      this.playerHand.sort();
      this.leftCard = cardFramework.getCardFromDeck();
      this.rightCard = cardFramework.getCardFromDeck();
      this.compPass = 0;
      this.playerPass = 0;
      this.computerCannotPlay = false;
      this.playerCannotPlay = false;
   }
   public int getNumCards()
   {
      return NUM_CARDS_PER_HAND;
   }
   public int getNumPlayers()
   {
      return NUM_PLAYERS;
   }
   public boolean playerPasses() {
      // Player cannot play
      this.playerPass++;
      playerCannotPlay = true;
      chooseNextMove("player");
      return playerCannotPlay;
   }

   private boolean computerPasses() {
      // Computer cannot play
      this.compPass++;
      computerCannotPlay = true;
      chooseNextMove("computer");
      return computerCannotPlay;
   }

   private boolean addToTheStacks() {
      boolean continuePlaying = false;

      if (this.cardFramework.getNumCardsRemainingInDeck() > 1) 
      {
         this.leftCard = this.cardFramework.getCardFromDeck();
         this.rightCard = this.cardFramework.getCardFromDeck();
         continuePlaying = true;
      }

      return continuePlaying;
   }

   public void playCardOnLeft(Card card) {
      // Card selection placed left
      this.leftCard = card;
   }

   public void playCardOnRight(Card card) {
      // Card selection placed right
      this.rightCard = card;
   }

   public boolean playableCard(Card cardOnTable, Card cardToBePlayed) {
      // Check card for placement compatibility
      boolean isPlayable = false;
      int valueOfBoard = cardOnTable.getValue();
      int valueOfCard = cardToBePlayed.getValue();

      switch (valueOfCard) {
      case 13:
         if (valueOfBoard == 1 || valueOfBoard == 12) {
            isPlayable = true;
         }
         break;
      case 1:
         if (valueOfBoard == 13 || valueOfBoard == 2) {
            isPlayable = true;
         }
         break;
      default:
         if (Math.abs(valueOfBoard - valueOfCard) == 1) {
            isPlayable = true;
         }
         break;
      }
      return isPlayable;

   }

   public void computerChoosesAPlay() {

      boolean canPlay = false;

      for (int x = 0; x < this.computerHand.getNumCards(); x++) {
         if (playableCard(this.leftCard, this.computerHand.inspectCard(x))) {
            canPlay = true;
            playCardOnLeft(cardFramework.playCard(0, x));
            cardFramework.takeCard(0);
         } else if (playableCard(this.rightCard,
                  this.computerHand.inspectCard(x))) {
            canPlay = true;
            playCardOnRight(cardFramework.playCard(0, x));
            cardFramework.takeCard(0);
         }
      }
      if (!canPlay) {
         computerPasses();
      }
   }

   private void chooseNextMove(String caller) {
      if (this.playerCannotPlay && this.computerCannotPlay) {
         if (addToTheStacks()) {
            this.playerCannotPlay = false;
            this.computerCannotPlay = false;
         } else {
            endTheGame();
         }
      } else if (playerCannotPlay) {
         computerChoosesAPlay();
         this.playerCannotPlay = true;
      }
   }

   public Card getLeftSideCard() {
      return leftCard;
   }

   public Card getRghtSideCard() {
      return rightCard;
   }

   public void endTheGame() 
   {
      if (this.compPass < this.playerPass) {
         JOptionPane.showMessageDialog(null, "Computer Wins!", "End of game",
                  JOptionPane.INFORMATION_MESSAGE);
      } else if (this.compPass > this.playerPass) {
         JOptionPane.showMessageDialog(null, "You Win!", "End of game",
                  JOptionPane.INFORMATION_MESSAGE);
      } else {
         JOptionPane.showMessageDialog(null, "It's a tie!", "End of game",
                  JOptionPane.INFORMATION_MESSAGE);
      }
      System.exit(0);
   }
}

//Class CardTableBuild
class CardTableBuild extends CardTable
{
   private static final long serialVersionUID = 1L;
   private JPanel leftSide;
   private JPanel rghtSide;

   // private JRadioButton[] rb;
   public ButtonGroup cardSelected;

   private CardGameBuild gamedata = new CardGameBuild();

   public JButton leftPileBtn;
   public JButton rightPileBtn;
   public JButton playerPassBtn;

   public CardTableBuild(CardGameBuild gamedata) {
      super("Build Card Game", gamedata.getNumCards(),
               gamedata.getNumPlayers());
      this.gamedata = gamedata;
      GUICard.loadCardIcons();

      // Create a buttongroup for the player's hand
      cardSelected = new ButtonGroup();

      // The center panel has 3 columns
      pnlPlayArea.setLayout(new GridLayout(1, 3));
      // Initialize the Left and Right Panels
      leftSide = new JPanel();
      rghtSide = new JPanel();
      // Create the "Pass Turn" and the Timer for the center
      JPanel centerPanel = new JPanel(new BorderLayout());
      
      Clock clock = new Clock();
      JPanel clockPnl = new JPanel();
      clockPnl.add(clock.timerPanel);
      centerPanel.add(clockPnl, BorderLayout.PAGE_START);
      centerPanel.add(clock.startStopButton, BorderLayout.CENTER);
      playerPassBtn = new JButton("Pass Turn");
      playerPassBtn.addActionListener(new CardController(gamedata, this));

      centerPanel.add(playerPassBtn, BorderLayout.PAGE_END);

      pnlPlayArea.add(leftSide);
      pnlPlayArea.add(centerPanel);
      pnlPlayArea.add(rghtSide);
      // Two rows of for the player
      pnlHumanHand.setLayout(new GridLayout(2, gamedata.getNumCards()));

      updateTable();
      pack();
   }

   public void updateTable() {
      Hand cmpHand = ((CardGameFramework) gamedata.cardFramework).getHand(0);
      Hand plrHand = ((CardGameFramework) gamedata.cardFramework).getHand(1);
      // Display card backs for the computer's hand
      pnlComputerHand.removeAll();
      for (int x = 0; x < cmpHand.getNumCards(); x++) {
         JLabel label = new JLabel(GUICard.getBackCardIcon());
         this.pnlComputerHand.add(label);
      }
      // Create buttons for the left and right card piles
      this.leftPileBtn = new JButton(
               GUICard.getIcon(gamedata.getLeftSideCard()));
      this.leftPileBtn.setActionCommand("getLeftSideCard");
      this.leftPileBtn
               .addActionListener(new CardController(gamedata, this));
      this.rightPileBtn = new JButton(
               GUICard.getIcon(gamedata.getRghtSideCard()));
      this.rightPileBtn.setActionCommand("getRightSideCard");
      this.rightPileBtn
               .addActionListener(new CardController(gamedata, this));
      this.leftSide.removeAll();
      this.leftSide.add(this.leftPileBtn);
      this.rghtSide.removeAll();
      this.rghtSide.add(this.rightPileBtn);

      this.pnlHumanHand.removeAll();
      // Display the players cards
      for (int x = 0; x < plrHand.getNumCards(); x++) {
         JLabel label = new JLabel(GUICard.getIcon(plrHand.inspectCard(x)));
         this.pnlHumanHand.add(label);
      }
      // Install radio buttons for the user to select a card
      for (int x = 0; x < plrHand.getNumCards(); x++) {

         JRadioButton rb = new JRadioButton();
         if (x == 0) {
            rb.setSelected(true);
         }
         rb.setActionCommand(Integer.toString(x));
         cardSelected.add(rb);
         JPanel panel = new JPanel();
         panel.add(rb);
         this.pnlHumanHand.add(panel);
      }

      // Redraw the table
      this.pnlComputerHand.validate();
      this.pnlComputerHand.repaint();
      this.pnlPlayArea.validate();
      this.pnlPlayArea.repaint();
      this.pnlHumanHand.validate();
      this.pnlHumanHand.repaint();

   }
}
//class CardTable---------------------------------------------------------------------------

class CardTable extends JFrame
{
 /**
    * 
    */
   private static final long serialVersionUID = 1L;
public JFrame frame = new JFrame();
 static int MAX_CARDS_PER_HAND = 56;
 static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
 private int numCardsPerHand;
 private int numPlayers;
 public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlTimer;

 
 public CardTable(String title, int numCardsPerHand, int numPlayers){

    // Make sure the cards and players do not exceed max
    if(numCardsPerHand <= MAX_CARDS_PER_HAND && numPlayers <= MAX_PLAYERS) {
      this.numPlayers = numPlayers;
       this.numCardsPerHand = numCardsPerHand;
    }
    else
       return;

    // Layout the table
    frame = new JFrame(title);
    frame.setSize(900,600);
    frame.setLayout(new GridLayout(2,2));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pnlComputerHand  = new JPanel();
    //pnlComputerHand.setLayout(new GridLayout(numCardsPerHand,numPlayers));
    pnlComputerHand.setBorder(BorderFactory.createTitledBorder("Computer Hand"));

    pnlPlayArea = new JPanel();
    pnlPlayArea.setLayout(new GridLayout(2,numPlayers));
    pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Hand"));

    pnlHumanHand = new JPanel();
    //pnlHumanHand.setLayout(new GridLayout(numCardsPerHand,numPlayers));
    pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
    
    pnlTimer = new JPanel();
    //pnlTimer.setLayout
    pnlTimer.setBorder(BorderFactory.createTitledBorder("Timer"));

    frame.add(pnlComputerHand);
    frame.add(pnlPlayArea);
    frame.add(pnlHumanHand);
    frame.add(pnlTimer);

    frame.setVisible(true);

 }
 public int getNumCardsPerHand()
 {
    return numCardsPerHand;
 }

 public int getNumplayers()
 {
    return numPlayers;
 }
}
 
//END class CardTable-------------

//class GUICard--------------------------------------------------------------------

class GUICard{
	   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
	   private static Icon iconBack;
	   static boolean iconsLoaded = false;

	   static void loadCardIcons()
	   {
	      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
	      // in a SHORT loop.  For each file name, read it in and use it to
	      // instantiate each of the 57 Icons in the icon[] array.
	      if(!iconsLoaded) {
	         for(int j = 0; j < 4; j++)
	         {
	            for(int k = 0; k <= 13; k++)
	            {
	               iconCards[k][j] = new ImageIcon("images/" + turnIntIntoCardValue(k)
	               + turnIntIntoCardSuit(j) + ".gif");
	            }
	         }
	         iconBack = new ImageIcon("images/BK.gif");
	      }
	   } 
	      
	   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
	   static String turnIntIntoCardValue(int k)
	   {
	      // an idea for a helper method (do it differently if you wish)
	      String returnVal = null;
	      String[] value = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
	                        "T", "J", "Q", "K", "X"};
	      if(k >= 0 && k <= 13)
	      {
	         returnVal = value[k];
	      }
	      else
	      {
	         System.out.println("returning default value A");
	         return value[0];
	      }
	      return returnVal;
	   }


	   static private int valueAsInt(Card card){
	      int returnVal = 0;
	      String[] value = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
	            "T", "J", "Q", "K", "X"};
	      
	      for(int i = 0; i < value.length; i++){
	         if(value[i].equals(String.valueOf(card.getValue())))
	            returnVal = i;
	      }
	      return returnVal;
	   }


	   // turns 0 - 3 into "C", "D", "H", "S"
	   static String turnIntIntoCardSuit(int j)
	   {
	      // an idea for another helper method (do it differently if you wish)
	      String returnSuit = null;
	      String[] value = {"C", "D", "H", "S"};

	      if(j >= 0 && j <= 3)
	      {
	         returnSuit = value[j];
	      }
	      else
	      {
	         System.out.println("returning default suit C");
	         return value[0];
	      }
	      return returnSuit;
	   }

	   static private int suitAsInt(Card card){
	      int returnVal = 0;
	      String[] value = {"C", "D", "H", "S"};
	      for(int i = 0; i < value.length; i++){
	         if(value[i].equals(String.valueOf(card.getSuit())));
	            returnVal = i;
	      }
	      return returnVal;
	   }

	   static public Icon getIcon(Card card) {
	      return iconCards[valueAsInt(card)][suitAsInt(card)];  //TOD Check ME
	   }

	   static public Icon getBackCardIcon(){
	      return iconBack;
	   }

	}

//END of class GUICard--------------------------------------------------------------------
   
//Card class -----------------------------------------------------------------------------
//MODEL ================================================================
class Card
{

//A Public enum Type with added members
public enum Suit{clubs, diamonds, hearts, spades};

//card values, T for ten, X for joker
public static final char[] cardValue = {'A', '2', '3', '4', '5', '6', '7',
   '8', '9', 'T', 'J', 'Q', 'K', 'X'};

//put the order of the card values in here with the smallest first, include 'X' for joker
public static char[] valueRanks = {'A', '2', '3', '4', '5', '6', '7',
         '8', '9', 'T', 'J', 'Q', 'K', 'X'};

//private member data
private char value;
private Suit suit;
private boolean errorFlag;

//Constructor with parameters value and suit
public Card(char value, Suit suit)
{
   set(value, suit);
}

//Default constructor sets value to "A" and suit to "spades" when value and suit not supplied
public Card()
{
   set('A', Suit.spades);
}

/*
 * Stringizer that client can use prior to displaying card
 * provides clean representation of the card
 */
public String toString()
{
   if (errorFlag == true)
   {
      return("** illegal **");
   }
   return String.valueOf(value) + " of " + suit;
}

/*
 * Mutator that accepts legal values.
 * When bad values are passed, errorFlag is set to true
 * When good values are passed they are stored and errorFlag is set to false
*/
public boolean set(char value, Suit suit)
{
   if (isValid(value, suit))
   {
      this.value = value;
      this.suit = suit;
      errorFlag = false;
      return true;
   }
   else
   {
      errorFlag = true;
      return false;
   }
}

//Accessor that returns a cards value
public char getValue()
{
   return value;
}

 //Accessor that returns a cards suit
public Suit getSuit()
{
   return suit;
}

//Accessor that returns errorFlag
public boolean getErrorFlag()
{
   return errorFlag;
}

//Returns true if all the fields(members) are identical and false, otherwise
public boolean equals(Card card)
{
   if (suit.equals(card.getSuit()) && value == card.getValue())
   {
      return true;
   }
   return false;
}

/*
 * Private method that returns true or false, depending on the legality of the parameters.
 * Note that, although it may be impossible for suit to be illegal (due to its enum-ness),
 * we pass it, anyway, in anticipation of possible changes to the type from enum to, say,
 * char or int, someday.  We only need to test value, at this time.
 * */
private boolean isValid(char value, Suit suit)
{
   for (int i = 0; i < cardValue.length; i++)
   {
      if (value == cardValue[i])
         return true;
   }
   return false;
}

// needs to be tested
static void arraySort(Card[] card, int arraySize) {
   char char1, char2;
   for (int i = 0; i < arraySize - 1; i++)
      for (int j = 0; j < arraySize - i - 1; j++) {
         char1 = card[j].value;
         char2 = card[j + 1].value;
         if (findIndex(char1) > findIndex(char2)) {
            Card temp = card[j];
            card[j] = card[j + 1];
            card[j + 1] = temp;
         }
      }
}

private static int findIndex(char cardValue) {
   for (int i = 0; i < valueRanks.length; i++)
      if (valueRanks[i] == cardValue)
         return i;
   return -1;
}
}
//END class Card--------------------------------------------------------------------

//class Hand--------------------------------------------------------------------

class Hand
{
 public static final int MAX_CARDS = 52;
 private Card[] myCards = new Card[MAX_CARDS];
 private int numCards = 0;

 // Default constructor
 public Hand()
 {
    Deck deck = new Deck();
    myCards = new Card[MAX_CARDS];
    for (int i = 1; i <= MAX_CARDS; i++)
    {
       takeCard(deck.dealCard());
    }
 }

 // Remove all cards from the hand
 void resetHand()
 {
    myCards = new Card[MAX_CARDS];
    numCards = 0;
    return;
 }

 // Adds a card to the next available position in the myCards array
 public boolean takeCard(Card card)
 {
    if(numCards == MAX_CARDS)
       return false;
    myCards[numCards] = card;
    numCards++;
    return true;
 }
 
 public Card playCard(int cardIndex)
 {
    if(numCards == 0) //error
    {
       //Creates a card that does not work
       return new Card('M', Card.Suit.spades);
    }
    //Decreases numCards.
    Card card = myCards[cardIndex];
    numCards--;
    for(int i = cardIndex; i < numCards; i++)
    {
       myCards[i] = myCards[i+1];
    }
   myCards[numCards] = null;
   return card;
 }
 
 // Returns and removes the card in the top occupied position of the myCards
 // array
 public Card playCard()
 {
    Card handTopCard = new Card();
    handTopCard = myCards[numCards - 1];
    System.out.println("Playing " + handTopCard);
    myCards[numCards - 1] = null;
    numCards--;
    return handTopCard;
 }

 // Prints out the array of cards.
 public String toString()
 {
    System.out.print("Hand = ( ");
    for (int index = 0; index <= numCards - 1; index++)
       System.out.print(myCards[index] + ", ");
    System.out.println(")");
    return "";
 }

 // Accessor for number of cards
 public int getNumCards()
 {
    return numCards;
 }
 
 

 // Inspect a single card
 Card inspectCard(int k)
 {
    if(k >= numCards)
    {
       Card x = new Card();
       x.set('F', Card.Suit.clubs);
       return x;
    }
    else
    {
       return myCards[k];
    }
 }
 
 public void sort()
 {
    Card.arraySort(myCards, numCards);
 }
 
 
}
//END Hand class

//CardController 
class CardController extends JFrame implements ActionListener
{

 private CardGameBuild aModel;
 private CardTableBuild aView;


 @Override
 public void actionPerformed(ActionEvent arg0) 
 {
 // TODO Auto-generated method stub
   int cardIndex;
   Card cardToPlay;
 
   switch (arg0.getActionCommand()) {
   case "getLeftSideCard":
      cardIndex = Integer.parseInt(
               aView.cardSelected.getSelection().getActionCommand());
      cardToPlay = aModel.playerHand.inspectCard(cardIndex);
      if (aModel.playableCard(aModel.getLeftSideCard(), cardToPlay)) {
         aModel.playCardOnLeft(aModel.cardFramework.playCard(1, cardIndex));
         aModel.cardFramework.takeCard(1);
         aModel.computerChoosesAPlay();
      } else {
         JOptionPane.showMessageDialog(null,
                  "You cannot place this card here", "Bad Play",
                  JOptionPane.INFORMATION_MESSAGE);
      }
 
      aView.updateTable();
      break; // Don't allow to flow to next case statement.
   case "getRightSideCard":
      cardIndex = Integer.parseInt(
               aView.cardSelected.getSelection().getActionCommand());
      cardToPlay = aModel.playerHand.inspectCard(cardIndex);
      if (aModel.playableCard(aModel.getRghtSideCard(), cardToPlay)) {
         aModel.playCardOnRight(aModel.cardFramework.playCard(1, cardIndex));
         aModel.cardFramework.takeCard(1);
         aModel.computerChoosesAPlay();
      } else {
         JOptionPane.showMessageDialog(null,
                  "You cannot place this card here", "Bad Play",
                  JOptionPane.INFORMATION_MESSAGE);
      }
 
      aView.updateTable();
      break; // Don't allow to flow to next case statement.
 
   case "Pass Turn":
      aModel.playerPasses();
      aView.updateTable();
      break;
   }
 
    }

 CardController(CardGameBuild theModel, CardTableBuild theView) {

    this.aModel = theModel;
    this.aView = theView;
 }
}
//END of CardController

//class Clock--------------------------------------------------------------------
//-------------------------------------------------------------------------------
class Clock extends JFrame
{
private int counter = 0;
private boolean runTimer = false;
private final int PAUSE = 100;
private String start = "START";
private String stop = "STOP";

public Timer clockTimer;
public JButton startStopButton;
public JLabel timeText;
public JPanel timerPanel;


//Constructor creates GUI timer
public Clock()
{
   clockTimer = new Timer(1000, timerEvent);
   timeText = new JLabel("" + formatToTime(counter));
   startStopButton = new JButton(start);
   startStopButton.addActionListener(buttonEvent);

   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(200, 200);
}

//Timer output should be set to minutes:seconds
public String formatToTime(long seconds)
{
   long s = seconds % 60;
   long m = (seconds / 60) % 60;
   return String.format("%01d:%02d", m, s);
}

//Increment Timer
private ActionListener timerEvent = new ActionListener()
{
   public void actionPerformed(ActionEvent e)
   {
      counter++;
      timeText.setText("" + formatToTime(counter));
   }
};

//Creates Timer object
private ActionListener buttonEvent = new ActionListener()
{
   public void actionPerformed(ActionEvent e)
   {
      TimerClass timerThread = new TimerClass();
      timerThread.start();
   }
};

//Start & Stop buttons to control timer
private class TimerClass extends Thread
{
   public void run()
   {
      if (runTimer)
      {
         startStopButton.setText(start);
         clockTimer.stop();
         runTimer = false;
         timeText.setText("" + formatToTime(counter));
      }
      else if (!runTimer)
      {
         startStopButton.setText(stop);
         clockTimer.start();
         runTimer = true;
         counter = 0;
         timeText.setText("" + formatToTime(counter));
      }
      doNothing(PAUSE);
   }

   //Method Thread.sleep
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      } catch (InterruptedException e)
      {
         System.out.println("Interrupted unexpectedly");
         System.exit(0);
      }
   }
}

}





//END class Clock-------------------------------------------------------------------
