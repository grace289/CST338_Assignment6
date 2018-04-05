/*
 * Assignment M6 Phase 1
 * Model-View-Controller Design Pattern
 * Authors: Christian Guerrero, Jose Garcia, Grace Alvarez, Gabriel Loring
 * Last Changed: April 4th, 2018
 *
 */

/*
 * Assignment M6 Phase 3 Modified

 * Add Card Framework and create game "High Card".
 * Authors: Christian Guerrero, Jose Garcia, Grace Alvarez, Gabriel Loring
 * Last Changed: March 30th, 2018
 * 
 */


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Assignment6 extends JFrame implements ActionListener
{  
   //phase 2 declarations
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   static JButton[] humanCardButtons = new JButton[NUM_CARDS_PER_HAND];
   static final int NUM_CARD_IMAGES = 56;
   static GUICard cardGUI = new GUICard();
	
	
   static CardTable gameTable;// = new CardTable();
   static CardGameFramework highCardGame;
   
   //phase 3 declarations
   static Integer numPacksPerDeck = 1;
   static Integer numJokersPerPack = 0;
   static Integer numUnusedCardsPerPack = 0;
   static Card[] unusedCardsPerPack = null;
   static Card winnings[] = new Card[numUnusedCardsPerPack*numPacksPerDeck];
   static int winningTotal = 0;

   static JButton[] playerHandButton = new JButton[NUM_CARDS_PER_HAND];
   static JLabel[] computerHandLabel = new JLabel[NUM_CARDS_PER_HAND];
   static JButton playerCard = new JButton();
   static JLabel playerWin = new JLabel("You Win");
   static JLabel computerWin = new JLabel("Computer Wins");
   
   //Clock object for timer
   Clock insertClock = new Clock();
   
   
   //main method------------------------------------------------------------------
   public static void main(String[] args)
   {
     
	   
	   System.out.println("TO DO code in main method");
	   
   }


   
//class CardTable---------------------------------------------------------------------------

static class CardTable extends JFrame
{
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
   
//END class CardTable--------------------------------------------------------------------



//class GUICard--------------------------------------------------------------------

static class GUICard{   
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
    private static Icon iconBack;
    static boolean iconsLoaded = false;
   
   public GUICard(){
      loadCardIcons();
   }

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.
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
   
   static public Icon getIcon(Card card) {
      loadCardIcons();
      return iconCards[valueAsInt(card)][suitAsInt(card)]; 
   }
   
   private static int suitAsInt(Card card)
   {
      Card.Suit suiteOfCard = card.getSuit();
      for(int i = 0; i <= 13; i++)
      {
         if(Card.suitValues[i] == suiteOfCard)
            return i;
      }
      return 0;//return default suit clubs.
   }

   private static int valueAsInt(Card card)
   {
      char cardsValue = card.getValue();

      if(cardsValue == 'A')
         return 0;
      if(cardsValue == 'X')
         return 13;
      for(int i = 0; i <= 13; i++)
      {
         if(Card.cardValue[i] == cardsValue)
            return i;
      }
      return 0;
   }

   static public Icon getBackCardIcon(){
      return iconBack;
   }

}    

static class Card
{
   //A Public enum Type with added members
   public enum Suit{clubs, diamonds, hearts, spades};
   static Suit[] suitValues = {Suit.clubs, Suit.diamonds, Suit.hearts, Suit.spades};
   
   //card values, T for ten
   public static final char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'T', 'J', 'Q', 'K', 'X'};
   
   //private member data
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   
   //card values, T for ten
   public static final char[] valueRanks = {'A', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'T', 'J', 'Q', 'K', 'X'};
   
   
   //Constructor with parameters value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   public char getchar() {
	// TODO Auto-generated method stub
	return 0;
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
   
   static private int suitAsInt(Card card){
      int returnVal = 0;
      String[] value = {"C", "D", "H", "S"};
      for(int i = 0; i < value.length; i++){
         if(value[i].equals(String.valueOf(card.getSuit())))
            returnVal = i;
         }   
      return returnVal; 
   }

   public boolean isGreater(Card secondCard)
   {     
      System.out.println(" Card 1: " + this.toString());
      System.out.println(" Card 2: " + secondCard.toString());
      System.out.println(" Card 1: " + Card.valueAsInt(this));
      System.out.println(" Card 2: " + Card.valueAsInt(secondCard));

      if(Card.valueAsInt(this) > Card.valueAsInt(secondCard))
         return true;
      return false;
   }

   //Will sort the incoming array of cards using a bubble sort routine.
   //You can break this up into smaller methods if it gets over 20 lines
   public static void arraySort(Card[] cards, int arraySize)
   {
	   boolean flag = true; //set flag to true for first pass
	   Card temp; //holds variables
	   
	   while (flag)
	      {
	         flag = false;    //set flag to false awaiting a possible swap
	         for (int i = 0; i < arraySize; i++)
	         {
	            if (cards[i].isGreater(cards[i + 1]))
	            {
	               temp = cards[i];   //swap elements
	               cards[i] = cards[i + 1];
	               cards[i + 1] = temp;
	               flag = true;    //shows a swap occurred
	            }
	         }
	      }
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
   
   public Card playCard(int k)
   {
        Card errorReturn = new Card('F', Card.Suit.spades); 

        if (numCards == 0)
           return errorReturn;
        else
           return myCards[--numCards];
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

static class Deck
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

 // constructor overload/default for game like bridge
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





//class Clock--------------------------------------------------------------------
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

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}


//END class Clock-------------------------------------------------------------------






