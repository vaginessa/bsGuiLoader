/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsguiloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

/**
 *
 * @author david
 */
public class bsBackgroundWorker {
    /*--- Variables ---*/
    protected String FileNameMask;
    protected boolean preferPackagedDl = false; // prefer youtubedl from Distributor
    protected boolean debugMode = true;
    protected String youtubeDlBinary;
    protected static String userDir;
    protected static String guiDesign;
    protected String userAgent = "Mozilla/5.0 Chrome/26.0.1410.64 Safari/537.31";
    private List<String> DownloadQue = new ArrayList<String>();

    
    /*--- Methods   ---*/
    public String getTitleFromPage(String URL) throws IOException {
        String SearchResult="";
        Document doc = Jsoup.connect(URL)
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .timeout(30000)
                .userAgent(userAgent)
                .get();
        Element result = doc.select("div#root section#serie div#sp_left div#epiInfo h2#titleGerman").first();
        SearchResult = result.text();
        return SearchResult;
    }
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
    public void debugPrint(String msg) {
        if (debugMode) {
            System.out.println(msg);
        }
    }
    private void setUserDir() {
        String DirName;
        if (System.getProperty("os.name").contains("Windows")) {
            DirName = "bsGuiLoader";
        } else {
            DirName = ".bsGuiLoader";
        }
        String HomeDir = System.getProperty("user.home") + File.separator + DirName;
        File bsDirectory = new File(HomeDir);
        if (!bsDirectory.exists()) {
            bsDirectory.mkdir();
        }
        userDir = HomeDir;
    }
    private void loadSqlite() {
        try {
            File dbFile = new File("test.db");
            dbFile.delete();
            
            SqlJetDb db = SqlJetDb.open(dbFile, true);
            db.getOptions().setAutovacuum(true);
            db.beginTransaction(SqlJetTransactionMode.WRITE);
            db.commit();
        } catch (Exception e) {
            System.err.println(e.getClass() + ": " + e.getMessage());
        }
    }
    protected void loadFromConfig() {
        File f = new File("settings.conf");
        //loadSqlite();
        //System.exit(0);
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
    private void checkYouTubeDl() {
        String DefaultPath = "";
        boolean isWindows = System.getProperty("os.name").contains("Windows");
        if (isWindows) {
            DefaultPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent().toString()
                + File.separator
                + "tools"
                + File.separator
                + "youtube-dl-windows" 
                + File.separator 
                + "youtube-dl.exe";
        } else {
            if (preferPackagedDl) {
                String TestPathString = "/usr/bin/youtube-dl";
                File TestPath = new File(TestPathString);
                if (TestPath.exists() && !TestPath.isDirectory() && preferPackagedDl) {
                    DefaultPath = TestPathString;
                }
            } else {
                DefaultPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent().toString()
                    + File.separator
                    + "tools"
                    + File.separator
                    + "youtube-dl-linux"
                    + File.separator
                    + "youtube-dl";
                System.out.println(DefaultPath);
            }
        }
        File youtubeDl = new File(DefaultPath);
        if (youtubeDl.exists() && !youtubeDl.isDirectory()) {
            youtubeDlBinary = DefaultPath;
        } else {
            System.err.println("YouTubeDlBinary not found!");
            System.exit(1);
        }
    }
    private void updateYouTubeDl() {
        try {
            List<String> arguments = new ArrayList<String>();
            arguments.add(youtubeDlBinary);
            arguments.add("-U");
            System.out.println("Check for Update of YouTubeDl");
            ProcessBuilder pbuilder = new ProcessBuilder(arguments);
            pbuilder.redirectErrorStream(true);
            Process p = pbuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = bre.readLine()) != null) {
                System.err.println(line);
            }
            bre.close();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Process finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    /*--- Constructor ---*/
    public bsBackgroundWorker() {
        loadFromConfig();
        checkYouTubeDl();
        updateYouTubeDl();
        setUserDir();
    }
    
}
