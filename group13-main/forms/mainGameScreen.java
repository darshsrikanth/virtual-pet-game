
package forms;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import classes.*;

public class mainGameScreen extends javax.swing.JFrame{

    private final int petContainerWIDTH = 650;
    private final int petContainerHEIGHT = 460;
    private saveManager manager;
    private int xV = 1;
    private int yV = 2;
    private int spriteX = 0;
    private int spriteY = 0;
    private int spriteWIDTH = 550;
    private int spriteHEIGHT = 389;
    private Timer animationTimer;
    private Timer statsTimer;
    private GameInstance game;
    private parentalControls gameParentControls;
    private Pet myPet;
    private Inventory inventory;
    private sprite spriteManager;
    private Commands command;
    private Timer ssc;
    private Shop shop;
    private int saveSlot;
    
    
    
    public mainGameScreen(saveManager manager, GameInstance curGame, int saveSlot) {
        this.manager = manager;
        this.gameParentControls = manager.PCReader();
        game = curGame;
        myPet = game.getPet();
        inventory = game.getInventory();
        spriteManager = new sprite(myPet);
        command = new Commands(myPet);
        shop = game.getShop();
        this.saveSlot = saveSlot;
        gameParentControls.logStartTime();
        
        // Initializing Java Swing components
        initComponents();
        foodInvPopup.setVisible(false);
        giftInvPopup.setVisible(false);
        
        //Initializing Stats Panel values.
        healthBar.setValue(myPet.getHealth());
        sleepBar.setValue(myPet.getSleep());
        hungerBar.setValue(myPet.getHunger());
        happinessBar.setValue(myPet.getHappiness());

        //Initializing Inventory Quantities
        carrotQuant.setText("" + inventory.getFoodItemQuantity(0));
        shrimpQuant.setText("" + inventory.getFoodItemQuantity(1));
        fishQuant.setText("" + inventory.getFoodItemQuantity(2));
        coralQuant.setText("" + inventory.getGiftItemQuantity(0));
        bottleQuant.setText("" + inventory.getGiftItemQuantity(1));
        pearlQuant.setText("" + inventory.getGiftItemQuantity(2));

        //Initializing Top Bar
        score.setText("Score: " + game.getPlayerScore());
        sandDollars.setText("Sand Dollars: $" + game.getSandDollars());

        //Decor Items
        if (game.getShop().decorEquippedStatus(0)){
            backgroundEffects.setSandCastle(true);
        }
        else {
            backgroundEffects.setSandCastle(false);
        }

        if (game.getShop().decorEquippedStatus(1)){
            backgroundEffects.setTreasure(true);
        }
        else {
            backgroundEffects.setTreasure(false);
        }

        if (game.getShop().decorEquippedStatus(2)){
            backgroundEffects.setPineapple(true);
        }
        else {
            backgroundEffects.setPineapple(false);
        }

        
        animationTimer = new Timer();
        TimerTask spriteUpdate = new TimerTask(){
            @Override public void run(){
                animation();
            }
        };
        animationTimer.schedule(spriteUpdate, 0, 20);

        this.statsClock();
        this.sscClock();

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        this.getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "feedPressed");
        this.getRootPane().getActionMap().put("feedPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "giftPressed");
        this.getRootPane().getActionMap().put("giftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giftButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "bedPressed");
        this.getRootPane().getActionMap().put("bedPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bedButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), "vetPressed");
        this.getRootPane().getActionMap().put("vetPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vetButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "playPressed");
        this.getRootPane().getActionMap().put("playPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "exercisePressed");
        this.getRootPane().getActionMap().put("exercisePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciseButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "shopPressed");
        this.getRootPane().getActionMap().put("shopPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "savePressed");
        this.getRootPane().getActionMap().put("savePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "carrotPressed");
        this.getRootPane().getActionMap().put("carrotPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrotButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "fishPressed");
        this.getRootPane().getActionMap().put("fishPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fishButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "shrimpPressed");
        this.getRootPane().getActionMap().put("shrimpPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shrimpButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "coralPressed");
        this.getRootPane().getActionMap().put("coralPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coralButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "bottlePressed");
        this.getRootPane().getActionMap().put("bottlePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottleButtonMousePressed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "pearlPressed");
        this.getRootPane().getActionMap().put("pearlPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pearlButtonMousePressed(null);
            }
        });


        // Initializing Emojis
        if (myPet.getState() instanceof deadState){
            command.disableCommand(0);
            command.disableCommand(1);
            command.disableCommand(2);
            command.disableCommand(3);
            command.disableCommand(4);
            command.disableCommand(5);
            spriteManager.setState(1);
            stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deadEmoji.png")));
        }
        else if (myPet.getState() instanceof sleepState){
            command.putToBed();
            stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sleepEmoji.png")));
        }
        else if (myPet.getState() instanceof angryState){
            command.disableCommand(0);
            command.disableCommand(1);
            command.disableCommand(2);
            command.disableCommand(4);
            command.enableCommand(3);
            command.enableCommand(5);
            stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/angryEmoji.png")));
        }
        else if (myPet.getState() instanceof hungryState){
            command.enableCommand(0);
            command.enableCommand(1);
            command.enableCommand(2);
            command.enableCommand(3);
            command.enableCommand(4);
            command.enableCommand(5);
            stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hungryEmoji.png")));
        }
        else {
            command.enableCommand(0);
            command.enableCommand(1);
            command.enableCommand(2);
            command.enableCommand(3);
            command.enableCommand(4);
            command.enableCommand(5);
            spriteManager.setState(5);
            stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/defaultEmoji.png")));
        }


    }
    
    public mainGameScreen() {
        
    }
    
    private void animation(){
        if (spriteX >= (petContainerWIDTH - spriteWIDTH) || spriteX<0){
            xV = xV * -1;
        }
        if (spriteY >= (petContainerHEIGHT - spriteHEIGHT) || spriteY<0){
            yV = yV * -1;
        }
        spriteX = spriteX + xV;
        spriteY = spriteY + yV;
        sprite.setIcon(new javax.swing.ImageIcon(getClass().getResource(spriteManager.getCurSprite(myPet))));
        sprite.setLocation(spriteX, spriteY);
    }
    
    
    private void statsClock(){
        
        statsTimer = new Timer();
        TimerTask declineStats = new TimerTask(){
            @Override public void run(){
                myPet.setSleep(myPet.getSleep() - myPet.getPetType().getSleepDecay());
                myPet.setHunger(myPet.getHunger() - myPet.getPetType().getHungerDecay());
                myPet.setHappiness(myPet.getHappiness() - myPet.getPetType().getHappinessDecay());
                
                sleepBar.setValue(myPet.getSleep());
                hungerBar.setValue(myPet.getHunger());
                happinessBar.setValue(myPet.getHappiness());
                

            }
        };
        statsTimer.schedule(declineStats, 45000, 45000);
    }



    public void sscClock() {

        ssc = new Timer();
        TimerTask checkStates = new TimerTask(){
            @Override public void run(){

                // Part 1 - States
                // 1. Dead state
                if (myPet.getState() instanceof deadState){
                    // Leave as is
                }
                else if (myPet.getHealth() < 1){
                    myPet.changeState(new deadState(myPet));
                    // disable all commands
                    command.disableCommand(0);
                    command.disableCommand(1);
                    command.disableCommand(2);
                    command.disableCommand(3);
                    command.disableCommand(4);
                    command.disableCommand(5);
                    spriteManager.setState(1);
                    stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deadEmoji.png")));
                }
                // 2. Sleep State - Current
                else if (myPet.getState() instanceof sleepState){
                    //leave as is - is on timer
                    stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sleepEmoji.png")));
                }
                else if (myPet.getSleep() == 0){
                    // Apply health penalty
                    myPet.setHealth(myPet.getHealth() - 25);
                    // Apply Score deduction
                    game.setPlayerScore(game.getPlayerScore() - 10);
                    command.putToBed();

                }
                // 3. Angry State
                else if (myPet.getState() instanceof angryState && myPet.getHappiness() < 49){
                    // Leave as is
                }
                // Put into angry state
                else if (myPet.getHappiness() < 1){
                    myPet.changeState(new angryState(myPet));
                    //commands
                    command.disableCommand(0);
                    command.disableCommand(1);
                    command.disableCommand(2);
                    command.disableCommand(4);
                    command.enableCommand(3);
                    command.enableCommand(5);
                    spriteManager.setState(3);
                    // Apply Score deduction
                    game.setPlayerScore(game.getPlayerScore() - 10);
                    stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/angryEmoji.png")));
                }
                // 4. Hungry State
                else if (myPet.getState() instanceof hungryState && myPet.getHunger() < 1){
                    // Leave as is
                }
                //put into hungry state
                else if (myPet.getHunger() < 1){
                    myPet.changeState(new hungryState(myPet));
                    //commands
                    command.enableCommand(0);
                    command.enableCommand(1);
                    command.enableCommand(2);
                    command.enableCommand(3);
                    command.enableCommand(4);
                    command.enableCommand(5);
                    command.declineHunger();
                    spriteManager.setState(4);
                    // Apply Score deduction
                    game.setPlayerScore(game.getPlayerScore() - 10);
                    stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hungryEmoji.png")));
                }
                // 5. Deafult States
                else if (myPet.getState() instanceof defaultState){
                    //leave as is
                }
                // put into default state
                else {
                    myPet.changeState(new defaultState(myPet));
                    command.enableCommand(0);
                    command.enableCommand(1);
                    command.enableCommand(2);
                    command.enableCommand(3);
                    command.enableCommand(4);
                    command.enableCommand(5);
                    spriteManager.setState(5);
                    stateEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/defaultEmoji.png")));
                }


                // Part 2 - Stats Bars
                healthBar.setValue(myPet.getHealth());
                sleepBar.setValue(myPet.getSleep());
                hungerBar.setValue(myPet.getHunger());
                happinessBar.setValue(myPet.getHappiness());
                if (myPet.getHealth() < 25){
                    healthLabel.setForeground(Color.RED);
                }
                else {
                    healthLabel.setForeground(Color.WHITE);
                }

                if (myPet.getSleep() < 25){
                    sleepLabel.setForeground(Color.RED);
                }
                else {
                    sleepLabel.setForeground(Color.WHITE);
                }

                if (myPet.getHunger() < 25){
                    hungerLabel.setForeground(Color.RED);
                }
                else {
                    hungerLabel.setForeground(Color.WHITE);
                }

                if (myPet.getHappiness() < 25){
                    happinessLabel.setForeground(Color.RED);
                }
                else {
                    happinessLabel.setForeground(Color.WHITE);
                }

                //Part 3 - Available Commands
                if (command.isDisabled(0)){
                    feedButton.setBackground(Color.lightGray);
                }
                else {
                    feedButton.setBackground(new Color(229,145,214));
                }

                if (command.isDisabled(1)){
                    bedButton.setBackground(Color.lightGray);
                }
                else {
                    bedButton.setBackground(new Color(229,145,214));
                }

                if (command.isDisabled(2)){
                    vetButton.setBackground(Color.lightGray);
                }
                else {
                    vetButton.setBackground(new Color(229,145,214));
                }

                if (command.isDisabled(3)){
                    playButton.setBackground(Color.lightGray);
                }
                else {
                    playButton.setBackground(new Color(229,145,214));
                }

                if (command.isDisabled(4)){
                    exerciseButton.setBackground(Color.lightGray);
                }
                else {
                    exerciseButton.setBackground(new Color(229,145,214));
                }

                if (command.isDisabled(5)){
                    giftButton.setBackground(Color.lightGray);
                }
                else {
                    giftButton.setBackground(new Color(229,145,214));
                }

                // Part 4 - Score and Sanddollars
                score.setText("Score: " + game.getPlayerScore());
                sandDollars.setText("Sand Dollars: $" + game.getSandDollars());






            }
        };
        ssc.schedule(checkStates, 0, 500);


    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border = new javax.swing.JPanel();
        statsPanel = new statsPanel();
        healthLabel = new javax.swing.JLabel();
        healthBar = new javax.swing.JProgressBar();
        sleepLabel = new javax.swing.JLabel();
        sleepBar = new javax.swing.JProgressBar();
        hungerLabel = new javax.swing.JLabel();
        hungerBar = new javax.swing.JProgressBar();
        happinessLabel = new javax.swing.JLabel();
        happinessBar = new javax.swing.JProgressBar();
        feedButton = new javax.swing.JPanel();
        feedLabel = new javax.swing.JLabel();
        giftButton = new javax.swing.JPanel();
        giftLabel = new javax.swing.JLabel();
        bedButton = new javax.swing.JPanel();
        bedLabel = new javax.swing.JLabel();
        vetButton = new javax.swing.JPanel();
        vetLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JPanel();
        playLabel = new javax.swing.JLabel();
        exerciseButton = new javax.swing.JPanel();
        exerciseLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JPanel();
        exitLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JPanel();
        saveLabel = new javax.swing.JLabel();
        topBar = new javax.swing.JPanel();
        sandDollarIcon = new javax.swing.JLabel();
        sandDollars = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        topBarImg = new javax.swing.JLabel();
        shopButton = new javax.swing.JPanel();
        shopLabel = new javax.swing.JLabel();
        giftInvPopup = new statsPanel();
        closeGiftInv = new javax.swing.JLabel();
        pearlQuant = new javax.swing.JLabel();
        coralQuant = new javax.swing.JLabel();
        bottleQuant = new javax.swing.JLabel();
        pearlButton = new javax.swing.JLabel();
        coralButton = new javax.swing.JLabel();
        bottleButton = new javax.swing.JLabel();
        foodInvPopup = new statsPanel();
        closeFoodInv = new javax.swing.JLabel();
        fishQuant = new javax.swing.JLabel();
        shrimpQuant = new javax.swing.JLabel();
        fishButton = new javax.swing.JLabel();
        carrotQuant = new javax.swing.JLabel();
        carrotButton = new javax.swing.JLabel();
        shrimpButton = new javax.swing.JLabel();
        petContainer = new javax.swing.JPanel();
        sprite = new javax.swing.JLabel();
        stateEmoji = new javax.swing.JLabel();
        backgroundEffects = new backgroundEffects();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setMaximumSize(new Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        border.setBackground(new Color(204, 204, 204));
        border.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 10));
        border.setName("border"); // NOI18N
        border.setOpaque(false);

        healthLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        healthLabel.setForeground(new Color(255, 255, 255));
        healthLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        healthLabel.setText("Health");
        healthLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        healthBar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        healthBar.setAlignmentX(0.0F);
        healthBar.setAlignmentY(0.0F);
        healthBar.setPreferredSize(new Dimension(146, 20));
        healthBar.setStringPainted(true);

        sleepLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        sleepLabel.setForeground(new Color(255, 255, 255));
        sleepLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sleepLabel.setText("Sleep");
        sleepLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        sleepBar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        sleepBar.setPreferredSize(new Dimension(159, 20));
        sleepBar.setStringPainted(true);

        hungerLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        hungerLabel.setForeground(new Color(255, 255, 255));
        hungerLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hungerLabel.setText("Hunger");
        hungerLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        hungerBar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        hungerBar.setPreferredSize(new Dimension(159, 20));
        hungerBar.setStringPainted(true);

        happinessLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        happinessLabel.setForeground(new Color(255, 255, 255));
        happinessLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        happinessLabel.setText("Happiness");
        happinessLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        happinessBar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        happinessBar.setPreferredSize(new Dimension(159, 20));
        happinessBar.setStringPainted(true);

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(healthBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sleepBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hungerBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(healthLabel)
                            .addComponent(sleepLabel)
                            .addComponent(hungerLabel)
                            .addComponent(happinessLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(happinessBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(healthLabel)
                .addGap(1, 1, 1)
                .addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sleepLabel)
                .addGap(1, 1, 1)
                .addComponent(sleepBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hungerLabel)
                .addGap(0, 0, 0)
                .addComponent(hungerBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(happinessLabel)
                .addGap(0, 0, 0)
                .addComponent(happinessBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        feedButton.setBackground(new Color(229, 145, 214));
        feedButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        feedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        feedButton.setPreferredSize(new Dimension(160, 60));
        feedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                feedButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                feedButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                feedButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                feedButtonMouseReleased(evt);
            }
        });

        feedLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        feedLabel.setForeground(new Color(255, 255, 255));
        feedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feedLabel.setText("Feed");
        feedLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        feedLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout feedButtonLayout = new javax.swing.GroupLayout(feedButton);
        feedButton.setLayout(feedButtonLayout);
        feedButtonLayout.setHorizontalGroup(
            feedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(feedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        feedButtonLayout.setVerticalGroup(
            feedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(feedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        giftButton.setBackground(new Color(229, 145, 214));
        giftButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        giftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        giftButton.setPreferredSize(new Dimension(160, 60));
        giftButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                giftButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                giftButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                giftButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                giftButtonMouseReleased(evt);
            }
        });

        giftLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        giftLabel.setForeground(new Color(255, 255, 255));
        giftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        giftLabel.setText("Give Gift");
        giftLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        giftLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout giftButtonLayout = new javax.swing.GroupLayout(giftButton);
        giftButton.setLayout(giftButtonLayout);
        giftButtonLayout.setHorizontalGroup(
            giftButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(giftLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        giftButtonLayout.setVerticalGroup(
            giftButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(giftLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        bedButton.setBackground(new Color(229, 145, 214));
        bedButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        bedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bedButton.setPreferredSize(new Dimension(160, 60));
        bedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bedButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bedButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bedButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bedButtonMouseReleased(evt);
            }
        });

        bedLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        bedLabel.setForeground(new Color(255, 255, 255));
        bedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bedLabel.setText("Go To Bed");
        bedLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bedLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout bedButtonLayout = new javax.swing.GroupLayout(bedButton);
        bedButton.setLayout(bedButtonLayout);
        bedButtonLayout.setHorizontalGroup(
            bedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        bedButtonLayout.setVerticalGroup(
            bedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        vetButton.setBackground(new Color(229, 145, 214));
        vetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        vetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vetButton.setPreferredSize(new Dimension(160, 60));
        vetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vetButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vetButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vetButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vetButtonMouseReleased(evt);
            }
        });

        vetLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        vetLabel.setForeground(new Color(255, 255, 255));
        vetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vetLabel.setText("Take To Vet");
        vetLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vetLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout vetButtonLayout = new javax.swing.GroupLayout(vetButton);
        vetButton.setLayout(vetButtonLayout);
        vetButtonLayout.setHorizontalGroup(
            vetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vetLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        vetButtonLayout.setVerticalGroup(
            vetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vetLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        playButton.setBackground(new Color(229, 145, 214));
        playButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.setPreferredSize(new Dimension(160, 60));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playButtonMouseReleased(evt);
            }
        });

        playLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        playLabel.setForeground(new Color(255, 255, 255));
        playLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playLabel.setText("Play");
        playLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout playButtonLayout = new javax.swing.GroupLayout(playButton);
        playButton.setLayout(playButtonLayout);
        playButtonLayout.setHorizontalGroup(
            playButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        playButtonLayout.setVerticalGroup(
            playButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        exerciseButton.setBackground(new Color(229, 145, 214));
        exerciseButton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(179, 96, 164), 4));
        exerciseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exerciseButton.setPreferredSize(new Dimension(160, 60));
        exerciseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exerciseButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exerciseButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exerciseButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exerciseButtonMouseReleased(evt);
            }
        });

        exerciseLabel.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        exerciseLabel.setForeground(new Color(255, 255, 255));
        exerciseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exerciseLabel.setText("Exercise");
        exerciseLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exerciseLabel.setPreferredSize(new Dimension(160, 60));

        javax.swing.GroupLayout exerciseButtonLayout = new javax.swing.GroupLayout(exerciseButton);
        exerciseButton.setLayout(exerciseButtonLayout);
        exerciseButtonLayout.setHorizontalGroup(
            exerciseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exerciseLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        exerciseButtonLayout.setVerticalGroup(
            exerciseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exerciseLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        exitButton.setBackground(new Color(255, 153, 153));
        exitButton.setBorder(new javax.swing.border.LineBorder(new Color(255, 255, 255), 4, true));
        exitButton.setPreferredSize(new Dimension(72, 32));
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitButtonMouseReleased(evt);
            }
        });

        exitLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        exitLabel.setForeground(new Color(255, 255, 255));
        exitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitLabel.setText("EXIT");

        javax.swing.GroupLayout exitButtonLayout = new javax.swing.GroupLayout(exitButton);
        exitButton.setLayout(exitButtonLayout);
        exitButtonLayout.setHorizontalGroup(
            exitButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );
        exitButtonLayout.setVerticalGroup(
            exitButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        saveButton.setBackground(new Color(141, 220, 141));
        saveButton.setBorder(new javax.swing.border.LineBorder(new Color(255, 255, 255), 4, true));
        saveButton.setPreferredSize(new Dimension(72, 32));
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                saveButtonMousePressed(evt);
            }
        });

        saveLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        saveLabel.setForeground(new Color(255, 255, 255));
        saveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveLabel.setText("SAVE");

        javax.swing.GroupLayout saveButtonLayout = new javax.swing.GroupLayout(saveButton);
        saveButton.setLayout(saveButtonLayout);
        saveButtonLayout.setHorizontalGroup(
            saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );
        saveButtonLayout.setVerticalGroup(
            saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        topBar.setOpaque(false);
        topBar.setPreferredSize(new Dimension(710, 50));
        topBar.setRequestFocusEnabled(false);
        topBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sandDollarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sandDollar.png"))); // NOI18N
        topBar.add(sandDollarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 3, 40, 40));

        sandDollars.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        sandDollars.setForeground(new Color(255, 255, 255));
        sandDollars.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sandDollars.setText("Sand Dollars: $");
        sandDollars.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topBar.add(sandDollars, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        score.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        score.setForeground(new Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        score.setText("Score: #");
        score.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topBar.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        topBarImg.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        topBarImg.setForeground(new Color(255, 255, 255));
        topBarImg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        topBarImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/topBar.png"))); // NOI18N
        topBarImg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topBar.add(topBarImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 50));

        shopButton.setBackground(new Color(255, 204, 153));
        shopButton.setBorder(new javax.swing.border.LineBorder(new Color(255, 255, 255), 4, true));
        shopButton.setPreferredSize(new Dimension(72, 32));
        shopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shopButtonMousePressed(evt);
            }
        });

        shopLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        shopLabel.setForeground(new Color(255, 255, 255));
        shopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shopLabel.setText("SHOP");

        javax.swing.GroupLayout shopButtonLayout = new javax.swing.GroupLayout(shopButton);
        shopButton.setLayout(shopButtonLayout);
        shopButtonLayout.setHorizontalGroup(
            shopButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );
        shopButtonLayout.setVerticalGroup(
            shopButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        giftInvPopup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeGiftInv.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        closeGiftInv.setForeground(new Color(255, 204, 204));
        closeGiftInv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        closeGiftInv.setText("X");
        closeGiftInv.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        closeGiftInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeGiftInvMousePressed(evt);
            }
        });
        giftInvPopup.add(closeGiftInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        pearlQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        pearlQuant.setForeground(new Color(255, 255, 255));
        pearlQuant.setText("#");
        giftInvPopup.add(pearlQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 20, -1));

        coralQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        coralQuant.setForeground(new Color(255, 255, 255));
        coralQuant.setText("#");
        giftInvPopup.add(coralQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 20, -1));

        bottleQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        bottleQuant.setForeground(new Color(255, 255, 255));
        bottleQuant.setText("#");
        giftInvPopup.add(bottleQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 20, -1));

        pearlButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pearl.png"))); // NOI18N
        pearlButton.setText("pearl");
        pearlButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pearlButtonMousePressed(evt);
            }
        });
        giftInvPopup.add(pearlButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 80, 80));

        coralButton.setBackground(new Color(204, 255, 204));
        coralButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coral.png"))); // NOI18N
        coralButton.setText("coral");
        coralButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                coralButtonMousePressed(evt);
            }
        });
        giftInvPopup.add(coralButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 80, 80));

        bottleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bottle.png"))); // NOI18N
        bottleButton.setText("bottle");
        bottleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bottleButtonMousePressed(evt);
            }
        });
        giftInvPopup.add(bottleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 80, 80));

        foodInvPopup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeFoodInv.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        closeFoodInv.setForeground(new Color(255, 204, 204));
        closeFoodInv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        closeFoodInv.setText("X");
        closeFoodInv.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        closeFoodInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeFoodInvMousePressed(evt);
            }
        });
        foodInvPopup.add(closeFoodInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        fishQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        fishQuant.setForeground(new Color(255, 255, 255));
        fishQuant.setText("#");
        foodInvPopup.add(fishQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 20, -1));

        shrimpQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        shrimpQuant.setForeground(new Color(255, 255, 255));
        shrimpQuant.setText("#");
        foodInvPopup.add(shrimpQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 20, -1));

        fishButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fish.png"))); // NOI18N
        fishButton.setText("fish");
        fishButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fishButtonMousePressed(evt);
            }
        });
        foodInvPopup.add(fishButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 80, 80));

        carrotQuant.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        carrotQuant.setForeground(new Color(255, 255, 255));
        carrotQuant.setText("#");
        foodInvPopup.add(carrotQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 20, -1));

        carrotButton.setBackground(new Color(204, 255, 204));
        carrotButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrot.png"))); // NOI18N
        carrotButton.setText("carrot");
        carrotButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                carrotButtonMousePressed(evt);
            }
        });
        foodInvPopup.add(carrotButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 80, 80));

        shrimpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shrimp.png"))); // NOI18N
        shrimpButton.setText("shrimp");
        shrimpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shrimpButtonMousePressed(evt);
            }
        });
        foodInvPopup.add(shrimpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 80, 80));

        petContainer.setOpaque(false);
        petContainer.setPreferredSize(new Dimension(650, 460));
        petContainer.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                petContainerComponentAdded(evt);
            }
        });

        sprite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprite1.png"))); // NOI18N

        javax.swing.GroupLayout petContainerLayout = new javax.swing.GroupLayout(petContainer);
        petContainer.setLayout(petContainerLayout);
        petContainerLayout.setHorizontalGroup(
            petContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(petContainerLayout.createSequentialGroup()
                .addComponent(sprite)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        petContainerLayout.setVerticalGroup(
            petContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(petContainerLayout.createSequentialGroup()
                .addComponent(sprite)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(borderLayout.createSequentialGroup()
                        .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateEmoji, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117)
                        .addComponent(petContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(borderLayout.createSequentialGroup()
                        .addComponent(giftInvPopup, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(borderLayout.createSequentialGroup()
                        .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(borderLayout.createSequentialGroup()
                                .addComponent(feedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(borderLayout.createSequentialGroup()
                                        .addComponent(exerciseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(bedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(vetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                                        .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))))
                            .addComponent(foodInvPopup, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                                .addComponent(giftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))))))
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(borderLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(borderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodInvPopup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(giftInvPopup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(borderLayout.createSequentialGroup()
                        .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(borderLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(petContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(borderLayout.createSequentialGroup()
                                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(stateEmoji, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(feedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exerciseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        getContentPane().add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
        getContentPane().add(backgroundEffects, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        background.setName("background"); // NOI18N
        background.setOpaque(true);
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void feedButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedButtonMousePressed
        if (!command.isDisabled(0)){
            SoundManager.playSound("sound/button_click.wav");
            feedButton.setBackground(new Color(179,96,164));
            foodInvPopup.setVisible(true);

            if (inventory.getFoodItemQuantity(0) == 0){
                carrotButton.setEnabled(false);
            }
            else {
                carrotButton.setEnabled(true);
            }

            if (inventory.getFoodItemQuantity(1) == 0){
                shrimpButton.setEnabled(false);
            }
            else {
                shrimpButton.setEnabled(true);
            }

            if (inventory.getFoodItemQuantity(2) == 0){
                fishButton.setEnabled(false);
            }
            else {
                fishButton.setEnabled(true);
            }
        }

    }//GEN-LAST:event_feedButtonMousePressed

    private void feedButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedButtonMouseReleased
        feedButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_feedButtonMouseReleased

    private void feedButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedButtonMouseEntered
        if (!command.isDisabled(0)){
            feedButton.setBackground(new Color(241,179,230));
        }
    }//GEN-LAST:event_feedButtonMouseEntered

    private void feedButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedButtonMouseExited
        if (!command.isDisabled(0)) {
            feedButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_feedButtonMouseExited

    private void petContainerComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_petContainerComponentAdded
                                  
    }//GEN-LAST:event_petContainerComponentAdded

    private void giftButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giftButtonMouseEntered
        if (!command.isDisabled(5)) {
            giftButton.setBackground(new Color(241, 179, 230));
        }
    }//GEN-LAST:event_giftButtonMouseEntered

    private void giftButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giftButtonMouseExited
        if (!command.isDisabled(5)) {
            giftButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_giftButtonMouseExited

    private void giftButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giftButtonMousePressed
        if (!command.isDisabled(5)){
            SoundManager.playSound("sound/button_click.wav");
            giftButton.setBackground(new Color(179,96,164));
            giftInvPopup.setVisible(true);

        }
    }//GEN-LAST:event_giftButtonMousePressed

    private void giftButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giftButtonMouseReleased
        giftButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_giftButtonMouseReleased

    private void bedButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bedButtonMouseEntered
        if (!command.isDisabled(2)) {
            bedButton.setBackground(new Color(241, 179, 230));
        }
    }//GEN-LAST:event_bedButtonMouseEntered

    private void bedButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bedButtonMouseExited
        if (!command.isDisabled(2)) {
            bedButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_bedButtonMouseExited

    private void bedButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bedButtonMousePressed
        if (!command.isDisabled(1)){
            SoundManager.playSound("sound/button_click.wav");
            bedButton.setBackground(new Color(179,96,164));
            command.putToBed();
            game.setPlayerScore(game.getPlayerScore() + 10);
            game.setSandDollars(game.getSandDollars() + 20);
        }
    }//GEN-LAST:event_bedButtonMousePressed

    private void bedButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bedButtonMouseReleased
        bedButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_bedButtonMouseReleased

    private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseEntered
        if (!command.isDisabled(4)) {
            playButton.setBackground(new Color(241, 179, 230));
        }
    }//GEN-LAST:event_playButtonMouseEntered

    private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
        if (!command.isDisabled(4)) {
            playButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_playButtonMouseExited

    private void playButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMousePressed
        if (!command.isDisabled(3)) {
            SoundManager.playSound("sound/button_click.wav");
            playButton.setBackground(new Color(179, 96, 164));
            command.play();
            game.setPlayerScore(game.getPlayerScore() + 10);
            game.setSandDollars(game.getSandDollars() + 15);

            //sprite happy
            Timer timer = new Timer();
            TimerTask playCooldown = new TimerTask(){
                @Override public void run(){
                    command.enableCommand(3);
                    //sprite return
                }
            };
            timer.schedule(playCooldown, 20000);
        }
    }//GEN-LAST:event_playButtonMousePressed

    private void playButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseReleased
        playButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_playButtonMouseReleased

    private void vetButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vetButtonMouseEntered
        if (!command.isDisabled(3)) {
            vetButton.setBackground(new Color(241, 179, 230));
        }
    }//GEN-LAST:event_vetButtonMouseEntered

    private void vetButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vetButtonMouseExited
        if (!command.isDisabled(3)) {
            vetButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_vetButtonMouseExited

    private void vetButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vetButtonMousePressed
        if (!command.isDisabled(2)) {
            SoundManager.playSound("sound/button_click.wav");
            vetButton.setBackground(new Color(179, 96, 164));
            // sprite mad
            command.vet();
            Timer timer = new Timer();
            TimerTask vetCooldown = new TimerTask(){
                @Override public void run(){
                    command.enableCommand(3);
                    //sprite return
                }
            };
            timer.schedule(vetCooldown, 120000);
            game.setSandDollars(game.getSandDollars() - 10);
        }
    }//GEN-LAST:event_vetButtonMousePressed

    private void vetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vetButtonMouseReleased
        vetButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_vetButtonMouseReleased

    private void exerciseButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exerciseButtonMouseEntered
        if (!command.isDisabled(1)) {
            exerciseButton.setBackground(new Color(241, 179, 230));
        }
    }//GEN-LAST:event_exerciseButtonMouseEntered

    private void exerciseButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exerciseButtonMouseExited
        if (!command.isDisabled(1)) {
            exerciseButton.setBackground(new Color(229, 145, 214));
        }
    }//GEN-LAST:event_exerciseButtonMouseExited

    private void exerciseButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exerciseButtonMousePressed
        if (!command.isDisabled(4)) {
            SoundManager.playSound("sound/button_click.wav");
            exerciseButton.setBackground(new Color(179, 96, 164));
            command.exercise();
            game.setPlayerScore(game.getPlayerScore() + 10);
            game.setSandDollars(game.getSandDollars() + 10);
        }
    }//GEN-LAST:event_exerciseButtonMousePressed

    private void exerciseButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exerciseButtonMouseReleased
        exerciseButton.setBackground(new Color(229,145,214));
    }//GEN-LAST:event_exerciseButtonMouseReleased

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        exitButton.setBackground(new Color(250,198,198));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_exitButtonMouseExited

    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMousePressed
        SoundManager.playSound("sound/button_click.wav");
        exitButton.setBackground(new Color(255,117,117));
        game.setVariables();
        manager.saveGameInstance(saveSlot, game);
        mainMenuScreen nextScreen = new mainMenuScreen(manager);
        nextScreen.setVisible(true);
        Timer switchTimer = new Timer();
        TimerTask closeCurrent = new TimerTask() {
            @Override
            public void run() {
                mainGameScreen.this.dispose();
                switchTimer.cancel();
            }
        };
        switchTimer.schedule(closeCurrent, 3000);
    }//GEN-LAST:event_exitButtonMousePressed

    private void exitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseReleased
        exitButton.setBackground(new Color(255,153,153));

        gameParentControls.logEndTime();
    }//GEN-LAST:event_exitButtonMouseReleased

    private void carrotButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carrotButtonMousePressed
        foodInvPopup.setVisible(false);
        if (inventory.useItem(0, 0)){
            carrotButton.setEnabled(true);
            command.feed(0);
            SoundManager.playSound("sound/button_click.wav");
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        carrotQuant.setText("" + inventory.getFoodItemQuantity(0));

    }//GEN-LAST:event_carrotButtonMousePressed

    private void closeFoodInvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFoodInvMousePressed
        SoundManager.playSound("sound/button_click.wav");
        foodInvPopup.setVisible(false);
    }//GEN-LAST:event_closeFoodInvMousePressed

    private void closeGiftInvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeGiftInvMousePressed
        SoundManager.playSound("sound/button_click.wav");
        giftInvPopup.setVisible(false);
    }//GEN-LAST:event_closeGiftInvMousePressed

    private void shopButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shopButtonMousePressed
        game.setVariables();
        SoundManager.playSound("sound/button_click.wav");
        shopScreen nextScreen = new shopScreen(manager, game, saveSlot);
        nextScreen.setVisible(true);
        Timer switchTimer = new Timer();
        TimerTask closeCurrent = new TimerTask() {
            @Override
            public void run() {
                mainGameScreen.this.dispose();
                switchTimer.cancel();
            }
        };
        switchTimer.schedule(closeCurrent, 3000);
    }//GEN-LAST:event_shopButtonMousePressed

    private void saveButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMousePressed
        SoundManager.playSound("sound/button_click.wav");
        game.setVariables();
        manager.saveGameInstance(saveSlot, game);
    }//GEN-LAST:event_saveButtonMousePressed

    private void shrimpButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shrimpButtonMousePressed
        foodInvPopup.setVisible(false);
        if (inventory.useItem(0, 1)){
            shrimpButton.setEnabled(true);
            SoundManager.playSound("sound/button_click.wav");
            command.feed(1);
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        shrimpQuant.setText("" + inventory.getFoodItemQuantity(1));
    }//GEN-LAST:event_shrimpButtonMousePressed

    private void fishButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fishButtonMousePressed
        foodInvPopup.setVisible(false);
        if (inventory.useItem(0, 2)){
            fishButton.setEnabled(true);
            SoundManager.playSound("sound/button_click.wav");
            command.feed(2);
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        fishQuant.setText("" + inventory.getFoodItemQuantity(2));
    }//GEN-LAST:event_fishButtonMousePressed

    private void coralButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coralButtonMousePressed
        giftInvPopup.setVisible(false);
        if (inventory.useItem(1, 0)){
            coralButton.setEnabled(true);
            SoundManager.playSound("sound/button_click.wav");
            command.giveGift(0);
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        coralQuant.setText("" + inventory.getGiftItemQuantity(0));
    }//GEN-LAST:event_coralButtonMousePressed

    private void bottleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bottleButtonMousePressed
        giftInvPopup.setVisible(false);
        if (inventory.useItem(1, 1)){
            bottleButton.setEnabled(true);
            SoundManager.playSound("sound/button_click.wav");
            command.giveGift(1);
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        bottleQuant.setText("" + inventory.getGiftItemQuantity(1));
    }//GEN-LAST:event_bottleButtonMousePressed

    private void pearlButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pearlButtonMousePressed
        giftInvPopup.setVisible(false);
        if (inventory.useItem(1, 2)){
            pearlButton.setEnabled(true);
            SoundManager.playSound("sound/button_click.wav");
            command.giveGift(2);
            game.setPlayerScore(game.getPlayerScore() + 10);
        }
        pearlQuant.setText("" + inventory.getGiftItemQuantity(2));
    }//GEN-LAST:event_pearlButtonMousePressed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private backgroundEffects backgroundEffects;
    private javax.swing.JPanel bedButton;
    private javax.swing.JLabel bedLabel;
    private javax.swing.JPanel border;
    private javax.swing.JLabel bottleButton;
    private javax.swing.JLabel bottleQuant;
    private javax.swing.JLabel carrotButton;
    private javax.swing.JLabel carrotQuant;
    private javax.swing.JLabel closeFoodInv;
    private javax.swing.JLabel closeGiftInv;
    private javax.swing.JLabel coralButton;
    private javax.swing.JLabel coralQuant;
    private javax.swing.JPanel exerciseButton;
    private javax.swing.JLabel exerciseLabel;
    private javax.swing.JPanel exitButton;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JPanel feedButton;
    private javax.swing.JLabel feedLabel;
    private javax.swing.JLabel fishButton;
    private javax.swing.JLabel fishQuant;
    private statsPanel foodInvPopup;
    private javax.swing.JPanel giftButton;
    private statsPanel giftInvPopup;
    private javax.swing.JLabel giftLabel;
    private javax.swing.JProgressBar happinessBar;
    private javax.swing.JLabel happinessLabel;
    private javax.swing.JProgressBar healthBar;
    private javax.swing.JLabel healthLabel;
    private javax.swing.JProgressBar hungerBar;
    private javax.swing.JLabel hungerLabel;
    private javax.swing.JLabel pearlButton;
    private javax.swing.JLabel pearlQuant;
    private javax.swing.JPanel petContainer;
    private javax.swing.JPanel playButton;
    private javax.swing.JLabel playLabel;
    private javax.swing.JLabel sandDollarIcon;
    private javax.swing.JLabel sandDollars;
    private javax.swing.JPanel saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JLabel score;
    private javax.swing.JPanel shopButton;
    private javax.swing.JLabel shopLabel;
    private javax.swing.JLabel shrimpButton;
    private javax.swing.JLabel shrimpQuant;
    private javax.swing.JProgressBar sleepBar;
    private javax.swing.JLabel sleepLabel;
    private javax.swing.JLabel sprite;
    private javax.swing.JLabel stateEmoji;
    private statsPanel statsPanel;
    private javax.swing.JPanel topBar;
    private javax.swing.JLabel topBarImg;
    private javax.swing.JPanel vetButton;
    private javax.swing.JLabel vetLabel;
    // End of variables declaration//GEN-END:variables

    
}
