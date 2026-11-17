package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final String APP_VERSION = "v0.5";

    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + APP_VERSION);
        this.characterList = characterList;

        initComponents();

        pack();
        setMinimumSize(new Dimension(560, 380));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createListArea(), BorderLayout.CENTER);
        add(createLegendPanel(), BorderLayout.EAST);
        add(createStatusPanel(), BorderLayout.SOUTH);
    }

    /** NORTH: judul di kiri, jumlah karakter di kanan. */
    private JPanel createHeaderPanel() {
        JPanel headerArea = new JPanel(new BorderLayout());
        headerArea.setBackground(AppTheme.BG_MAIN);

        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setBackground(AppTheme.BG_MAIN);
        titleRow.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        titleRow.add(title, BorderLayout.WEST);
        titleRow.add(count, BorderLayout.EAST);
        headerArea.add(titleRow, BorderLayout.NORTH);
        headerArea.add(createStatisticsPanel(), BorderLayout.SOUTH);
        return headerArea;
    }

    /** Ringkasan koleksi dalam empat sel berukuran seragam. */
    private JPanel createStatisticsPanel() {
        int totalLevel = 0;
        int highestLevel = 0;
        EnumSet<CharacterType> types = EnumSet.noneOf(CharacterType.class);
        for (Character character : characterList) {
            totalLevel += character.getLevel();
            highestLevel = Math.max(highestLevel, character.getLevel());
            types.add(character.getType());
        }

        double averageLevel = characterList.isEmpty()
                ? 0.0
                : (double) totalLevel / characterList.size();

        JPanel statistics = new JPanel(new GridLayout(2, 2, 8, 4));
        statistics.setBackground(AppTheme.BG_MAIN);
        statistics.setBorder(BorderFactory.createEmptyBorder(4, 16, 8, 16));
        statistics.add(createStatisticLabel("Total karakter: " + characterList.size()));
        statistics.add(createStatisticLabel(
                String.format(Locale.ROOT, "Rata-rata level: %.1f", averageLevel)));
        statistics.add(createStatisticLabel("Level tertinggi: " + highestLevel));
        statistics.add(createStatisticLabel("Variasi role: " + types.size()));
        return statistics;
    }

    private JLabel createStatisticLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_MONO);
        label.setForeground(AppTheme.TEXT_MUTED);
        return label;
    }

    /** CENTER: kartu karakter di dalam JScrollPane. */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));
        }

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280));
        return scroll;
    }

    /** Membuat satu kartu karakter. */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType())
                + " | HP:" + c.getHitPoints() + " | ATK:" + c.getAttackPower());
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppTheme.GOLD);
            lbl.setFont(AppTheme.FONT_BODY_BOLD);
        }
        if (c.getLevel() == 1) {
            lbl.setEnabled(false);
        }
        return lbl;
    }

    /** EAST: legenda warna per role, ditumpuk vertikal dengan BoxLayout. */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(java.awt.Font.BOLD));
        head.setForeground(AppTheme.TEXT_MAIN);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));

        for (CharacterType type : CharacterType.values()) {
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }
        legend.add(Box.createVerticalGlue());
        return legend;
    }

    /** SOUTH: status di kiri dan versi aplikasi di kanan. */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout(16, 0));
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        JLabel lbl = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        lbl.setFont(AppTheme.FONT_MONO);
        lbl.setForeground(AppTheme.TEXT_MUTED);

        JLabel version = new JLabel(APP_VERSION);
        version.setFont(AppTheme.FONT_MONO);
        version.setForeground(AppTheme.TEXT_MUTED);

        status.add(lbl, BorderLayout.WEST);
        status.add(version, BorderLayout.EAST);
        return status;
    }
}
