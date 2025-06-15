
package forms;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import classes.*;


public class shopScreen extends javax.swing.JFrame {
    private saveManager manager;
    private Shop shop;
    private GameInstance game;
    private int saveSlot;

    /**
     * Creates new form shopScreen
     */
    public shopScreen() {
        initComponents();
        
    }

    public shopScreen(saveManager manager, GameInstance game, int saveSlot) {
        initComponents();
        this.manager = manager;
        this.game = game;
        this.shop = game.getShop();
        this.saveSlot = saveSlot;

        // checking if sandcastle item is owned and modifying its corresponding button if so
        if (shop.decorOwnedStatus(0)) {
            // if sandcastle owned, check if its equipped
            if (shop.decorEquippedStatus(0)) {
                sandcastleBuyButton.setText("Unequip");
            } else {
                sandcastleBuyButton.setText("Equip");
            }
        }

        // checking if treasure chest item is owned and modifying its corresponding button if so
        if (shop.decorOwnedStatus(1)) {
            // if treasure chest owned, check if its equipped
            if (shop.decorEquippedStatus(1)) {
                treasurechestBuyButton.setText("Unequip");
            } else {
                treasurechestBuyButton.setText("Equip");
            }
        }

        // checking if pineapple house item is owned and modifying its corresponding button if so
        if (shop.decorOwnedStatus(2)) {
            // if pineapple house owned, check if its equipped
            if (shop.decorEquippedStatus(2)) {
                pineappleHouseBuyButton.setText("Unequip");
            } else {
                pineappleHouseBuyButton.setText("Equip");
            }
        }

        // checking if treasure chest item is owned and modifying its corresponding button if so
        if (shop.decorOwnedStatus(1)) {
            // if treasure chest owned, check if its equipped
            if (shop.decorEquippedStatus(1)) {
                treasurechestBuyButton.setText("Unequip");
            } else {
                treasurechestBuyButton.setText("Equip");
            }
        }

        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());

        // mapping keyboard shortcuts to mouse buttons
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        this.getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMainMenuMouseClicked(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "buyFishPressed");
        this.getRootPane().getActionMap().put("buyFishPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fishBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "buyCarrotPressed");
        this.getRootPane().getActionMap().put("buyCarrotPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carrotBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "buyShrimpPressed");
        this.getRootPane().getActionMap().put("buyShrimpPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shrimpBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "buyPearlPressed");
        this.getRootPane().getActionMap().put("buyPearlPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pearlBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "buyBottlePressed");
        this.getRootPane().getActionMap().put("buyBottlePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottleBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "buyCoralPressed");
        this.getRootPane().getActionMap().put("buyCoralPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coralBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "buyPineapplePressed");
        this.getRootPane().getActionMap().put("buyPineapplePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pineappleHouseBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "buySandcastlePressed");
        this.getRootPane().getActionMap().put("buySandcastlePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sandcastleBuyButtonActionPerformed(null);
            }
        });

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "buyTreasurechestPressed");
        this.getRootPane().getActionMap().put("buyTreasurechestPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treasurechestBuyButtonActionPerformed(null);
            }
        });

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
        exitFrame = new javax.swing.JPanel();
        exitMainMenu = new javax.swing.JLabel();
        merchantPanel = new javax.swing.JPanel();
        octopusImage = new javax.swing.JLabel();
        sandDollarPanel = new javax.swing.JPanel();
        sanddollarImage = new javax.swing.JLabel();
        currentSandDollars = new javax.swing.JLabel();
        foodPanel = new javax.swing.JPanel();
        carrotImage = new javax.swing.JLabel();
        fishImage = new javax.swing.JLabel();
        shrimpImage = new javax.swing.JLabel();
        carrotBuyButton = new javax.swing.JButton();
        fishBuyButton = new javax.swing.JButton();
        shrimpBuyButton = new javax.swing.JButton();
        carrotPriceLabel = new javax.swing.JLabel();
        fishPriceLabel = new javax.swing.JLabel();
        shrimpFishLabel = new javax.swing.JLabel();
        foodLabel = new javax.swing.JLabel();
        giftItemPanel = new javax.swing.JPanel();
        pearllmage = new javax.swing.JLabel();
        bottleImage = new javax.swing.JLabel();
        coralImage = new javax.swing.JLabel();
        pearlBuyButton = new javax.swing.JButton();
        bottleBuyButton = new javax.swing.JButton();
        coralBuyButton = new javax.swing.JButton();
        pearlPriceLabel = new javax.swing.JLabel();
        bottlePriceLabel = new javax.swing.JLabel();
        coralPriceLabel = new javax.swing.JLabel();
        giftLabel = new javax.swing.JLabel();
        decorItemPanel = new javax.swing.JPanel();
        houseImage = new javax.swing.JLabel();
        castleImage = new javax.swing.JLabel();
        chestImage = new javax.swing.JLabel();
        pineappleHouseBuyButton = new javax.swing.JButton();
        sandcastleBuyButton = new javax.swing.JButton();
        treasurechestBuyButton = new javax.swing.JButton();
        javax.swing.JLabel pineappleHousePriceLabel = new javax.swing.JLabel();
        sandcastlePriceLabel = new javax.swing.JLabel();
        chestPriceLabel = new javax.swing.JLabel();
        decorLabel = new javax.swing.JLabel();
        backgroundEffects = new forms.backgroundEffects();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        border.setBackground(new java.awt.Color(204, 204, 204));
        border.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        border.setName("border"); // NOI18N
        border.setOpaque(false);

        exitFrame.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        exitFrame.setPreferredSize(new java.awt.Dimension(72, 32));

        exitMainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exitButton.png"))); // NOI18N
        exitMainMenu.setName("exitMainMenu"); // NOI18N
        exitMainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMainMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout exitFrameLayout = new javax.swing.GroupLayout(exitFrame);
        exitFrame.setLayout(exitFrameLayout);
        exitFrameLayout.setHorizontalGroup(
            exitFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitMainMenu)
        );
        exitFrameLayout.setVerticalGroup(
            exitFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitMainMenu)
        );

        merchantPanel.setOpaque(false);

        octopusImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FINALOCTPUS-01.png"))); // NOI18N

        javax.swing.GroupLayout merchantPanelLayout = new javax.swing.GroupLayout(merchantPanel);
        merchantPanel.setLayout(merchantPanelLayout);
        merchantPanelLayout.setHorizontalGroup(
            merchantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(octopusImage)
        );
        merchantPanelLayout.setVerticalGroup(
            merchantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(octopusImage)
        );

        sandDollarPanel.setOpaque(false);

        sanddollarImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SANDDOLLAR-01.png"))); // NOI18N

        currentSandDollars.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        currentSandDollars.setForeground(new java.awt.Color(255, 255, 255));
        currentSandDollars.setText("Sand Dollars: $");

        javax.swing.GroupLayout sandDollarPanelLayout = new javax.swing.GroupLayout(sandDollarPanel);
        sandDollarPanel.setLayout(sandDollarPanelLayout);
        sandDollarPanelLayout.setHorizontalGroup(
            sandDollarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sandDollarPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(currentSandDollars, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(sanddollarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        sandDollarPanelLayout.setVerticalGroup(
            sandDollarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sanddollarImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(sandDollarPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(currentSandDollars))
        );

        foodPanel.setOpaque(false);

        carrotImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Carrot1-01.png"))); // NOI18N

        fishImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fish2-01.png"))); // NOI18N

        shrimpImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Shrimp2-01.png"))); // NOI18N

        carrotBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        carrotBuyButton.setText("Buy X 1");
        carrotBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrotBuyButtonActionPerformed(evt);
            }
        });

        fishBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fishBuyButton.setText("Buy X 1");
        fishBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fishBuyButtonActionPerformed(evt);
            }
        });

        shrimpBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        shrimpBuyButton.setText("Buy X 1");
        shrimpBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shrimpBuyButtonActionPerformed(evt);
            }
        });

        carrotPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        carrotPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        carrotPriceLabel.setText("Carrot ($10)");

        fishPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fishPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        fishPriceLabel.setText("Fish ($10)");

        shrimpFishLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        shrimpFishLabel.setForeground(new java.awt.Color(255, 255, 255));
        shrimpFishLabel.setText("Shrimp ($10)");

        foodLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        foodLabel.setForeground(new java.awt.Color(255, 255, 255));
        foodLabel.setText("Food");

        javax.swing.GroupLayout foodPanelLayout = new javax.swing.GroupLayout(foodPanel);
        foodPanel.setLayout(foodPanelLayout);
        foodPanelLayout.setHorizontalGroup(
            foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(foodLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carrotPriceLabel)
                    .addGroup(foodPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(carrotImage)))
                .addGap(66, 66, 66)
                .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fishPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fishImage))
                .addGap(51, 51, 51)
                .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shrimpFishLabel)
                    .addGroup(foodPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(shrimpImage)))
                .addGap(26, 26, 26))
            .addGroup(foodPanelLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(carrotBuyButton)
                .addGap(95, 95, 95)
                .addComponent(fishBuyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(shrimpBuyButton)
                .addGap(39, 39, 39))
        );
        foodPanelLayout.setVerticalGroup(
            foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(foodPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(fishImage))
                    .addComponent(fishPriceLabel)
                    .addGroup(foodPanelLayout.createSequentialGroup()
                        .addComponent(shrimpFishLabel)
                        .addGap(3, 3, 3)
                        .addComponent(shrimpImage))
                    .addGroup(foodPanelLayout.createSequentialGroup()
                        .addComponent(carrotPriceLabel)
                        .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(foodPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(carrotImage))
                            .addGroup(foodPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(foodLabel)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(foodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carrotBuyButton)
                    .addComponent(fishBuyButton)
                    .addComponent(shrimpBuyButton)))
        );

        giftItemPanel.setOpaque(false);

        pearllmage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PEARL1-01.png"))); // NOI18N

        bottleImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BOTTLE-1-01.png"))); // NOI18N

        coralImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CORAL1-01.png"))); // NOI18N

        pearlBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pearlBuyButton.setText("Buy X 1");
        pearlBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pearlBuyButtonActionPerformed(evt);
            }
        });

        bottleBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bottleBuyButton.setText("Buy X 1");
        bottleBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottleBuyButtonActionPerformed(evt);
            }
        });

        coralBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coralBuyButton.setText("Buy X 1");
        coralBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coralBuyButtonActionPerformed(evt);
            }
        });

        pearlPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pearlPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        pearlPriceLabel.setText("Pearl ($100)");

        bottlePriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bottlePriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        bottlePriceLabel.setText("Bottle ($50)");

        coralPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        coralPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        coralPriceLabel.setText("Coral ($25)");

        giftLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        giftLabel.setForeground(new java.awt.Color(255, 255, 255));
        giftLabel.setText("Gifts");

        javax.swing.GroupLayout giftItemPanelLayout = new javax.swing.GroupLayout(giftItemPanel);
        giftItemPanel.setLayout(giftItemPanelLayout);
        giftItemPanelLayout.setHorizontalGroup(
            giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giftItemPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(giftLabel)
                .addGap(43, 43, 43)
                .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pearlPriceLabel)
                    .addGroup(giftItemPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pearllmage)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bottlePriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottleImage))
                .addGap(43, 43, 43)
                .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coralPriceLabel)
                    .addComponent(coralImage))
                .addGap(28, 28, 28))
            .addGroup(giftItemPanelLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(pearlBuyButton)
                .addGap(95, 95, 95)
                .addComponent(bottleBuyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(coralBuyButton)
                .addGap(39, 39, 39))
        );
        giftItemPanelLayout.setVerticalGroup(
            giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giftItemPanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(giftItemPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(bottleImage))
                    .addComponent(bottlePriceLabel)
                    .addGroup(giftItemPanelLayout.createSequentialGroup()
                        .addComponent(pearlPriceLabel)
                        .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(giftItemPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pearllmage))
                            .addGroup(giftItemPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(giftLabel))))
                    .addGroup(giftItemPanelLayout.createSequentialGroup()
                        .addComponent(coralPriceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(coralImage)))
                .addGap(18, 18, 18)
                .addGroup(giftItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pearlBuyButton)
                    .addComponent(bottleBuyButton)
                    .addComponent(coralBuyButton)))
        );

        decorItemPanel.setOpaque(false);

        houseImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/house-01.png"))); // NOI18N

        castleImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/castle-01.png"))); // NOI18N

        chestImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chest-01.png"))); // NOI18N

        pineappleHouseBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pineappleHouseBuyButton.setText("Buy");
        pineappleHouseBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pineappleHouseBuyButtonActionPerformed(evt);
            }
        });

        sandcastleBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sandcastleBuyButton.setText("Buy");
        sandcastleBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sandcastleBuyButtonActionPerformed(evt);
            }
        });

        treasurechestBuyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        treasurechestBuyButton.setText("Buy");
        treasurechestBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treasurechestBuyButtonActionPerformed(evt);
            }
        });

        pineappleHousePriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pineappleHousePriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        pineappleHousePriceLabel.setText("House ($200)");

        sandcastlePriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sandcastlePriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        sandcastlePriceLabel.setText("Castle ($150)");

        chestPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chestPriceLabel.setForeground(new java.awt.Color(255, 255, 255));
        chestPriceLabel.setText("Chest ($100)");

        decorLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        decorLabel.setForeground(new java.awt.Color(255, 255, 255));
        decorLabel.setText("Décor");

        javax.swing.GroupLayout decorItemPanelLayout = new javax.swing.GroupLayout(decorItemPanel);
        decorItemPanel.setLayout(decorItemPanelLayout);
        decorItemPanelLayout.setHorizontalGroup(
            decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decorItemPanelLayout.createSequentialGroup()
                .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pineappleHousePriceLabel))
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(decorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(houseImage)
                        .addGap(6, 6, 6)))
                .addGap(52, 52, 52)
                .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sandcastlePriceLabel)
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(castleImage)))
                .addGap(48, 48, 48)
                .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chestPriceLabel)
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chestImage)))
                .addContainerGap())
            .addGroup(decorItemPanelLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(pineappleHouseBuyButton)
                .addGap(94, 94, 94)
                .addComponent(sandcastleBuyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(treasurechestBuyButton)
                .addGap(27, 27, 27))
        );
        decorItemPanelLayout.setVerticalGroup(
            decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decorItemPanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addComponent(pineappleHousePriceLabel)
                        .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(decorItemPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(houseImage))
                            .addGroup(decorItemPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(decorLabel))))
                    .addGroup(decorItemPanelLayout.createSequentialGroup()
                        .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chestPriceLabel)
                            .addComponent(sandcastlePriceLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(castleImage)
                            .addComponent(chestImage))))
                .addGap(18, 18, 18)
                .addGroup(decorItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pineappleHouseBuyButton)
                    .addComponent(sandcastleBuyButton)
                    .addComponent(treasurechestBuyButton)))
        );

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foodPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giftItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decorItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                        .addComponent(sandDollarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderLayout.createSequentialGroup()
                        .addComponent(merchantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(sandDollarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(merchantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(exitFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(foodPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(giftItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(decorItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));
        getContentPane().add(backgroundEffects, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        background.setName("background"); // NOI18N
        background.setOpaque(true);
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMainMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMainMenuMouseClicked
        game.setVariables();
        SoundManager.playSound("sound/button_click.wav");
        mainGameScreen nextScreen = new mainGameScreen(manager, game, saveSlot);
        nextScreen.setVisible(true);
        Timer switchTimer = new Timer();
        TimerTask closeCurrent = new TimerTask() {
            @Override
            public void run() {
                shopScreen.this.dispose();
                switchTimer.cancel();
            }
        };
        switchTimer.schedule(closeCurrent, 3000);
    }//GEN-LAST:event_exitMainMenuMouseClicked

    private void fishBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fishBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(0,2);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_fishBuyButtonActionPerformed

    private void shrimpBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shrimpBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(0,1);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_shrimpBuyButtonActionPerformed

    private void bottleBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottleBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(1,1);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_bottleBuyButtonActionPerformed

    private void coralBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coralBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(1,0);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_coralBuyButtonActionPerformed

    private void sandcastleBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandcastleBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        if (sandcastleBuyButton.getText().equals("Buy")) {
            if (game.buyItem(2,0)) {
                game.setVariables();
                currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
                sandcastleBuyButton.setText("Equip");
            }
        } else if (sandcastleBuyButton.getText().equals("Equip")) {
            sandcastleBuyButton.setText("Unequip");
            shop.equipItem(0);
            game.setVariables();
        } else { // text set to unequip
            sandcastleBuyButton.setText("Equip");
            shop.unequipItem(0);
        }
    }//GEN-LAST:event_sandcastleBuyButtonActionPerformed

    private void treasurechestBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treasurechestBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        if (treasurechestBuyButton.getText().equals("Buy")) {
            if (game.buyItem(2,1)) {
                game.setVariables();
                currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
                treasurechestBuyButton.setText("Equip");
            }
        } else if (treasurechestBuyButton.getText().equals("Equip")) {
            treasurechestBuyButton.setText("Unequip");
            shop.equipItem(1);
            game.setVariables();
        } else { // text set to unequip
            treasurechestBuyButton.setText("Equip");
            shop.unequipItem(1);
        }
    }//GEN-LAST:event_treasurechestBuyButtonActionPerformed

    private void pineappleHouseBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pineappleHouseBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        if (pineappleHouseBuyButton.getText().equals("Buy")) {
            if (game.buyItem(2,2)) {
                game.setVariables();
                currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
                pineappleHouseBuyButton.setText("Equip");
            }
        } else if (pineappleHouseBuyButton.getText().equals("Equip")) {
            pineappleHouseBuyButton.setText("Unequip");
            shop.equipItem(2);
            game.setVariables();
        } else { // text set to unequip
            pineappleHouseBuyButton.setText("Equip");
            shop.unequipItem(2);
        }
    }//GEN-LAST:event_pineappleHouseBuyButtonActionPerformed

    private void carrotBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrotBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(0,0);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_carrotBuyButtonActionPerformed

    private void pearlBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pearlBuyButtonActionPerformed
        SoundManager.playSound("sound/button_click.wav");
        game.buyItem(1,2);
        game.setVariables();
        currentSandDollars.setText("Sand Dollars: $" + game.getSandDollars());
    }//GEN-LAST:event_pearlBuyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private forms.backgroundEffects backgroundEffects;
    private javax.swing.JPanel border;
    private javax.swing.JButton bottleBuyButton;
    private javax.swing.JLabel bottleImage;
    private javax.swing.JLabel bottlePriceLabel;
    private javax.swing.JButton carrotBuyButton;
    private javax.swing.JLabel carrotImage;
    private javax.swing.JLabel carrotPriceLabel;
    private javax.swing.JLabel castleImage;
    private javax.swing.JLabel chestImage;
    private javax.swing.JLabel chestPriceLabel;
    private javax.swing.JButton coralBuyButton;
    private javax.swing.JLabel coralImage;
    private javax.swing.JLabel coralPriceLabel;
    private javax.swing.JLabel currentSandDollars;
    private javax.swing.JPanel decorItemPanel;
    private javax.swing.JLabel decorLabel;
    private javax.swing.JPanel exitFrame;
    private javax.swing.JLabel exitMainMenu;
    private javax.swing.JButton fishBuyButton;
    private javax.swing.JLabel fishImage;
    private javax.swing.JLabel fishPriceLabel;
    private javax.swing.JLabel foodLabel;
    private javax.swing.JPanel foodPanel;
    private javax.swing.JPanel giftItemPanel;
    private javax.swing.JLabel giftLabel;
    private javax.swing.JLabel houseImage;
    private javax.swing.JPanel merchantPanel;
    private javax.swing.JLabel octopusImage;
    private javax.swing.JButton pearlBuyButton;
    private javax.swing.JLabel pearlPriceLabel;
    private javax.swing.JLabel pearllmage;
    private javax.swing.JButton pineappleHouseBuyButton;
    private javax.swing.JPanel sandDollarPanel;
    private javax.swing.JButton sandcastleBuyButton;
    private javax.swing.JLabel sandcastlePriceLabel;
    private javax.swing.JLabel sanddollarImage;
    private javax.swing.JButton shrimpBuyButton;
    private javax.swing.JLabel shrimpFishLabel;
    private javax.swing.JLabel shrimpImage;
    private javax.swing.JButton treasurechestBuyButton;
    // End of variables declaration//GEN-END:variables
}
