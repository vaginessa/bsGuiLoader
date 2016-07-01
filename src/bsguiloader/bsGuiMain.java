/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsguiloader;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
/**
 *
 * @author David Bitterlich
 */
public class bsGuiMain extends javax.swing.JFrame implements ItemListener {

    /**
     * Creates new form bsGuiMain
     */
    private final TableRowSorter<TableModel> rowSorter;
    public bsGuiMain() {
        checkYouTubeDL();
        initComponents();
                
        addSeries();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setColumnSelectionInterval(0, 2);
        jTable1.setAutoCreateRowSorter(true);
        rowSorter = new TableRowSorter<>(jTable1.getModel());
        jComboBox1.addItemListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jCheckBox1 = new JCheckBox();
        jButton2 = new JButton();
        jLabel2 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jLabel3 = new JLabel();
        jProgressBar1 = new JProgressBar();
        jLabel4 = new JLabel();
        jProgressBar2 = new JProgressBar();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Serie:");
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setName("textSearch"); // NOI18N

        jButton1.setLabel("Suche");
        jButton1.setName("btnSearch"); // NOI18N
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serie", "Episoden", "Filme", "URL"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, Boolean.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jTable1.setDoubleBuffered(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jCheckBox1.setLabel("Filme herunterladen");
        jCheckBox1.setName("checkMovies"); // NOI18N

        jButton2.setText("Download starten");
        jButton2.setEnabled(false);
        jButton2.setName("btnDownload"); // NOI18N
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Hoster:");
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setEnabled(false);
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel3.setText("Gesamtfortschritt:");
        jLabel3.setName("jLabel3"); // NOI18N

        jProgressBar1.setName("jProgressBar1"); // NOI18N
        jProgressBar1.setStringPainted(true);

        jLabel4.setText("Fortschritt aktuell:");
        jLabel4.setName("jLabel4"); // NOI18N

        jProgressBar2.setName("jProgressBar2"); // NOI18N
        jProgressBar2.setStringPainted(true);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProgressBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton2)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jProgressBar2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String txt = jTextField1.getText();
        jTable1.setRowSorter(rowSorter);
        if (txt.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txt));
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void doTableEvent() {
        jComboBox1.setEnabled(false);
        int SelectedRow = jTable1.getSelectedRow();
        if (SelectedRow > -1 && SelectedRow < jTable1.getRowCount()) {
            try {
                int RowCount = jTable1.getRowCount();
                String SeriesURL = jTable1.getModel()
                        .getValueAt(jTable1.convertRowIndexToModel(SelectedRow), 3)
                        .toString();
                int EpisodeNumbers = getSerieEpisodes(SeriesURL);
                boolean MoviesAvailable=false;
                if (MovieLinks.size() > 0) {
                    MoviesAvailable = true;
                }
                String CellValue = String.valueOf(EpisodeNumbers);
                jTable1.setValueAt(CellValue, SelectedRow, 1);
                jTable1.setValueAt(MoviesAvailable, SelectedRow, 2);
                ListSelectionModel selectionModel = jTable1.getSelectionModel();
                selectionModel.setSelectionInterval(SelectedRow, SelectedRow);
                jComboBox1.setEnabled(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void jTable1MousePressed(MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        doTableEvent();
    }//GEN-LAST:event_jTable1MousePressed

    private String replaceStringByParams(String[] episodeVariables) {
        String resultStr = FileNameMask;
        for (String toReplaceString: episodeVariables)
        {
            String[] toReplaceArray = toReplaceString.split("/");
            resultStr = resultStr.replaceAll(toReplaceArray[0], toReplaceArray[1]);
        }
        resultStr = resultStr.replaceAll("<ext>", "%(ext)s");
        return resultStr;
    }
    private String getNulls(int input) {
        String returnString="";    
        if (input < 10) {
                returnString = "0";
        } 
        return returnString;
    }
    public String youtubeDlBinary="";
    private String correctify(String Input) {
        String toRemove = "-.,:;/'_<>|";
        for (int i = 0; i < toRemove.length(); i++) {
            Input = Input.replace(Character.toString(toRemove.charAt(i)), "");
        }
        return Input;
    }
    private boolean isDownloading = false;
    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jButton2.isEnabled()) {
            JDialog bsDownloadQue = new bsGuiDownloadQue(this, true);
            for (int i = 0; i < 5; i++) {
                new bsGuiDownloadQue(this, true).addDownloadFromQue(DownloadQue.get(i));
            }
            new bsGuiDownloadQue(this, true).setVisible(true);
        } else {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Verzeichnis zum Herunterladen auswählen...");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        int returnValue = chooser.showOpenDialog(null);
        String FileDir="";
        jButton2.setEnabled(false);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Wechsle Ordner " + chooser.getSelectedFile().toString());
            FileDir = chooser.getSelectedFile().toString();
        } else {
            FileDir = "Downloads";
        }
        FileDir += File.separator + correctify(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        File DownDir = new File(FileDir);
        try {
            DownDir.mkdir();
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        System.setProperty("user.dir", FileDir);
        final List<String> dq = DownloadQue;
        final String fd = FileDir;
        DownloadProcess = new bsDownProc(
                dq,
                getJProgressBar1(),
                getJProgressBar2(),
                youtubeDlBinary,
                fd,
                jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(),
                DownloadManagerPath,
                FileNameMask
        );
        DownloadProcess.start();
        isDownloading = false;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            EpisodeListWithHoster.addAll(MovieLinks);
            if (jTable1.getSelectedRow() > -1) {
                doTableEvent();
                updateHosterComboBox();
            }
        } else {
            for (String MovieURL: MovieLinks) {
                if (EpisodeListWithHoster.contains(MovieURL)) {
                    EpisodeListWithHoster.remove(EpisodeListWithHoster.indexOf(MovieURL));
                }
                doTableEvent();
                updateHosterComboBox();
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void formWindowOpened(WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //this.pbHnd_pbar1 = new pbHandler(this.getJProgressBar1());
    }//GEN-LAST:event_formWindowOpened
    public Object getJProgressBar1() {
        return this.jProgressBar1;
    }
    public Object getJProgressBar2() {
        return this.jProgressBar2;
    }
    private void updateComboBoxEvent() {
        jButton2.setEnabled(false);
        if (jComboBox1.isEnabled() && jComboBox1.getSelectedIndex() > 0 && (!isDownloading)) {
            int SelectedRow = jTable1.getSelectedRow();
            int CellValue = Integer.parseInt(jTable1.getModel()
                    .getValueAt(jTable1.convertRowIndexToModel(SelectedRow), 1).toString());
            String[] ComboCaption = jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).split(":");
            int ComboValue = Integer.parseInt(ComboCaption[1].trim());
            if (!DownloadQue.contains(ComboCaption[0].trim().toLowerCase())) {
                jTable1.setValueAt(ComboValue, SelectedRow, 1);
                getAllLinksByHoster(ComboCaption[0]);
            }
            jButton2.setEnabled(true);
        }
    }
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            updateComboBoxEvent();
        }
    }
    /**
     * @param args the command line arguments
     */
    private List<String> EpisodeListWithHoster;
    private String userAgent = "Mozilla/5.0 Chrome/26.0.1410.64 Safari/537.31";
    private List<String> MovieLinks;
    public List<String> getLinksFromPage(String URL, String SearchString) throws IOException {
        List<String> SearchResults = new ArrayList<String>();
        Document doc = Jsoup.connect(URL)
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .timeout(30000)
                .userAgent(userAgent)
                .get();
        Elements Links = doc.select(SearchString);
        for (Element Link: Links) {
            SearchResults.add(Link.attr("abs:href"));
        }
        return SearchResults;
    }
    private boolean SupportedHosterByURL(String URL) {
        String[] SplittedURL = URL.split("/");
        String Hoster = SplittedURL[SplittedURL.length-1];
        String[] HosterCleanedFromHyphen = Hoster.split("-");
        String HosterCore = HosterCleanedFromHyphen[0];
        if (hosters.contains(HosterCore.toLowerCase().trim())) {
            return true;
        }
        return false;
    }
    private int getAllLinksByHoster(String hoster) {
        DownloadQue.clear();
        int NumberOfSearchResults = 0;
        for (String EpisodeURL: EpisodeListWithHoster) {
            String EpisodeURLLowerCase = EpisodeURL.toLowerCase();
            if (EpisodeURLLowerCase.contains(hoster.toLowerCase())) {
                NumberOfSearchResults++;
                DownloadQue.add(EpisodeURL);
            }
        }
        return NumberOfSearchResults;
    }
    public List<String> DownloadQue = new ArrayList<String>();
    private void updateHosterComboBox() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("");
        List<String> HostersAdded = new ArrayList<String>();
        for (String SupportedHosterFromList: EpisodeListWithHoster) {
            String[] SplittedLink = SupportedHosterFromList.split("/");
            String[] SplittedHoster = SplittedLink[SplittedLink.length-1].split("-");
            String HosterCore = SplittedHoster[0];
            
            for (String HosterFromList: hosters) {
                if (HosterFromList.contains(HosterCore.toLowerCase())) {
                    // Check, ob Hoster bereits hinzugefügt
                    if (!HostersAdded.contains(HosterCore.toLowerCase()) && !HosterCore.matches("^[0-9]+$")) {
                        jComboBox1.addItem(
                                HosterCore.toUpperCase() 
                                + ": " 
                                + getAllLinksByHoster(HosterCore)
                        );
                        HostersAdded.add(HosterCore.toLowerCase());
                    }                
                }
            }
        }
    }
    private int getSerieEpisodes(String url) throws IOException {
        EpisodeListWithHoster = new ArrayList<String>();
        MovieLinks = new ArrayList<String>();
        Document doc = Jsoup.connect(url)
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .timeout(30000)
                .userAgent(userAgent)
                .get();
        Elements seasons = doc.select("ul.pages li:not(.button) a");
        for (Element SeasonPage: seasons) {
            List<String> TempEpisodes = new ArrayList<String>();
            TempEpisodes = getLinksFromPage(SeasonPage.attr("abs:href"), "div#sp_left table tbody tr td a");
            EpisodeListWithHoster.addAll(TempEpisodes);
        }
        Elements Movies = doc.select("section.serie ul.pages li.button a");
        if (Movies.size() > 0 && Movies.get(0).text().contains("Film")) {
            for (String Movie: getLinksFromPage(Movies.get(0).attr("abs:href"), "div#sp_left table tbody tr td a")) {
                if (SupportedHosterByURL(Movie)) {
                    MovieLinks.add(Movie);
                }
            }
        }
        updateHosterComboBox();
        return EpisodeListWithHoster.size();
    }
    private void addSeries()  {
        String url = "http://bs.to/andere-serien";
        try {
            Elements series;
            int repeat=0;
            TableColumnModel tcm = jTable1.getColumnModel();
            tcm.removeColumn(tcm.getColumn(3));
            do {
                Document doc = Jsoup.connect(url)
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .timeout(30000)
                    .userAgent(userAgent)
                    .get();
                series = doc.select("div.genre ul > li > a");
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                for (int i = 0; i < series.size(); i++) {
                        Element node = series.get(i);
                        String SeriesURL = node.attr("abs:href");
                        if (!SeriesURL.isEmpty()) {
                            //SeriesPages.add(SeriesURL);
                            model.addRow(new Object[]{
                                node.text(), // Serientitel
                                "", // Anzahl Serien
                                false, // Filme verfügbar
                                SeriesURL // URL
                            });
                        }
                }
                if (series.isEmpty()) { 
                    System.err.println("Probleme beim Holen der Serien... wiederhole");
                    if (repeat > 150) {
                        System.err.println("Kann keine Verbindung zu BurningSeries aufbauen... breche ab.");
                        System.exit(1);
                    }
                    repeat++;
                }
            }
            while (series.size() == 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    private List<String> hosters;
    private void loadHosters() {
        hosters = new ArrayList<String>();
        try {
            Process p = new ProcessBuilder(
            youtubeDlBinary, "--list-extractors").start();
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {
                hosters.add(line.toLowerCase().trim());
            }
            p.destroy();
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // Suche nach unterstützten Download-Managern
    private String DownloadManagerPath = "";
    private void checkForManagers() {
        // Zurzeit wird nur aria2c unterstützt (youtube-dl unterstützt theoretisch mehr,
        // jedoch macht derzeit ausschließlich aria2 Sinn.
        String[] managers = { "aria2c" };
        // TODO: Windows Installationspfade hinzufügen
        String[] searchPaths = { "/usr/bin", "/usr/local/bin" };
        for (String searchPath: searchPaths) {
            for (String manager: managers) {
                File managerFile = new File(searchPath + File.separator + manager);
                if (managerFile.exists() && !managerFile.isDirectory()) {
                    DownloadManagerPath = searchPath + File.separator + manager;
                    break;
                }
            }
        }
    }
    // Settings
    private String FileNameMask;
    private void loadFromConfig() {
        File f = new File("settings.conf");
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            do {
                line = br.readLine();
                String[] SplittedSetting = line.split("=");
                switch (SplittedSetting[0]) {
                    case "FileNameMask":
                        FileNameMask = SplittedSetting[1];
                        break;
                    // TODO: weitere Settings einstellen
                }
            } while (line != null);
            br.close();
        } catch (Exception e) {
            /*
                <name> = Name der Serie
                <episode> = Episodennummer
                <season> = Staffel
                <title> = Titel der Episode
                <ext> = Dateityp (wird für youtube-dl durch %(ext) ersetzt)
            */
            FileNameMask = "<name> S<season>E<episode> <title>.<ext>";
        }
    }
    private void checkYouTubeDL() {
        String DefaultPath = System.getProperty("user.dir") 
                + File.separator
                + "tools"
                + File.separator;
        if (System.getProperty("os.name").contains("Windows")) {
            DefaultPath += "youtube-dl-windows" 
                    + File.separator 
                    + "youtube-dl.exe";
        } else {
            DefaultPath += "youtube-dl-linux"
                    + File.separator
                    + "youtube-dl";
        }
        System.out.println(DefaultPath);
        File DlPath = new File(DefaultPath);
        if (!DlPath.exists() || DlPath.isDirectory()) {
            System.err.println("YouTube-Dl Binary not found!");
            System.exit(1);
        }
        youtubeDlBinary = DefaultPath;
    }
    // <editor-fold defaultstate="collapsed" desc="Creating List of supported Hosters">      
    /*private void saveList() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("hosters.txt"));
            for (String line: hosters) {
                pw.println(line);
            }
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void formatList() {
        hosters = new ArrayList<String>();
        JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try
            {
                FileReader fr = new FileReader(selectedFile);
                BufferedReader textReader = new BufferedReader(fr);
                String line;
                while ((line = textReader.readLine()) != null) {
                    String[] LineSeperated = line.split(":");
                    if (!hosters.contains(LineSeperated[0].toLowerCase())) {
                        hosters.add(LineSeperated[0].toLowerCase());
                    }
                }
                fr.close();
                saveList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }*/
    // </editor-fold>    

    
    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        String Design = "Nimbus";
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "--design":
                case "-d": 
                    if (!args[1].isEmpty()) {
                        Design = args[1];
                }
            }
        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (Design.contains(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(bsGuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bsGuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bsGuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bsGuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                bsGuiMain MainWindow = new bsGuiMain();
                MainWindow.loadFromConfig();
                MainWindow.setTitle("Burning Series Loader");
                ImageIcon img = new ImageIcon("icon");
                MainWindow.setIconImage(img.getImage());
                MainWindow.setVisible(true);
                MainWindow.loadHosters();
                MainWindow.checkForManagers();
                //MainWindow.formatList();
                //new bsGuiMain().setVisible(true);
                //new comment
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JCheckBox jCheckBox1;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JProgressBar jProgressBar1;
    private JProgressBar jProgressBar2;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    //private pbHandler pbHnd_pbar1;
    private bsDownProc DownloadProcess;
}
