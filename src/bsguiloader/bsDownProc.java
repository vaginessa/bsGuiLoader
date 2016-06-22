/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsguiloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author david
 */
public class bsDownProc extends Thread {
    // Variablen
    private List<String> ProcessArgument;
    private List<String> DownloadQue;
    pbHandler pbHnd_pbar1;
    pbHandler pbHnd_pbar2;
    private String youtubeDlBinary;
    private String FileDir;
    private String SerieName;
    private String DownloadManagerPath;
    private String FileManagerMask;
    
    // Konstruktor
    public bsDownProc(
            //List<String> ProcessArgument, 
            List<String> DownloadQue,
            Object pbar1,
            Object pbar2,
            String youtubeDlBinary, 
            String FileDir, 
            String SerieName,
            String DownloadManagerPath,
            String FileManagerMask
    ) {
        this.ProcessArgument = ProcessArgument;
        pbHnd_pbar1 = new pbHandler(pbar1);
        pbHnd_pbar2 = new pbHandler(pbar2);
        this.youtubeDlBinary = youtubeDlBinary;
        this.FileDir = FileDir;
        this.SerieName = SerieName;
        this.DownloadManagerPath = DownloadManagerPath;
        this.FileManagerMask = FileManagerMask;
        this.DownloadQue = DownloadQue;
    }
    
    // Methoden
    private boolean checkIfNumber(String InputString) {
        if (InputString.length() > 0) {
            if (InputString.substring(
                    0, 
                    InputString.length()-1
            ).matches("[-+]?\\d*\\.?\\d+")) {
                return true;
            }
        }
        return false;
    }    
    private String replaceStringByParams(String[] episodeVariables) {
        String resultStr = FileManagerMask;
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
    private String userAgent = "Mozilla/5.0 Chrome/26.0.1410.64 Safari/537.31";
    private String getFrameFromPage(String URL, String SearchString) throws IOException {
        String res = "";
        Document doc = Jsoup.connect(URL)
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .timeout(30000)
                .userAgent(userAgent)
                .get();
        Element result = doc.select(SearchString).first();
        res = result.absUrl("src");        
        return res;
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
    public void run() {
        pbHnd_pbar1.setMinimum(0);
        pbHnd_pbar1.setMaximum(DownloadQue.size());
        for (int i = 0; i < DownloadQue.size(); i++) {
            String URL = DownloadQue.get(i);
            doDownload(URL);
            pbHnd_pbar1.setValue(i+1);
        }
    }
    private void doDownload(String URL) {
        try {
            int episodeNr;
            String[] episodeURLsplittedBySlash = URL.split("/");
            /*
                episodeVars[0] = Serientitel
                episodeVars[1] = Episode relativ zur Staffel
                episodeVars[2] = Serienstaffel
                episodeVars[3] = Episodentitel
            */
            String[] episodeVars = new String[4];
            String[] episodeURLsplittedByHyphen = episodeURLsplittedBySlash[6].split("-");
            episodeNr = Integer.parseInt(episodeURLsplittedByHyphen[0]);
            episodeVars[0] = "<name>" + "/" + SerieName;
            episodeVars[3] = "<title>" + "/";
            episodeVars[2] = "<season>" + "/" + getNulls(Integer.parseInt(episodeURLsplittedBySlash[5])) + episodeURLsplittedBySlash[5];
            for (int i = 1; i < episodeURLsplittedByHyphen.length; i++) {
                episodeVars[3] += " ";
                episodeVars[3] += episodeURLsplittedByHyphen[i];
            }
            // Dateinamenkorrektur nach dem Schema: <Serie> S<Staffel>E<Nummer> <Titel>.<ext>
            episodeVars[1] = "<episode>" + "/" + getNulls(episodeNr) + episodeURLsplittedByHyphen[0];
            List<String> DownloadURLresults = getLinksFromPage(URL, "div#sp_left div#video_actions div a");
            String DownloadURL;
            if (!DownloadURLresults.get(0).isEmpty()) {
                 DownloadURL = DownloadURLresults.get(0);
            } else {
                    // für OpenLoad verwendet, aber OpenLoad ist nicht nutzbar im Moment
                    DownloadURL = getFrameFromPage(URL, "div#root section.serie div#sp_left iframe");
            }
            
            
            List<String> pbuilder_arguments = new ArrayList<String>();
            pbuilder_arguments.add(youtubeDlBinary);
            pbuilder_arguments.add("--output");
            pbuilder_arguments.add(
                    FileDir 
                    + File.separator 
                    + replaceStringByParams(episodeVars)
            );
            pbuilder_arguments.add(DownloadURL);
            if (!DownloadManagerPath.isEmpty()) {
                pbuilder_arguments.add("--external-downloader"); 
                pbuilder_arguments.add(DownloadManagerPath);
            }
            ProcessBuilder pbuilder = new ProcessBuilder(pbuilder_arguments);
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
                String[] ProgramOutput = line.split(" ");

                pbHnd_pbar2.setMinimum(0);
                pbHnd_pbar2.setMaximum(1000);
                String ProgressStatus="";
                for (int i = 1; i < ProgramOutput.length; i++) {
                    ProgressStatus += ProgramOutput[i];
                }
                if (
                        ProgramOutput[0].equals("[download]") 
                        && !ProgramOutput[1].contains("Destination") 
                        && !ProgramOutput[1].contains("Resumin")
                    ) {
                    String StringToConvert="";
                    boolean skip = false;
                    if (checkIfNumber(ProgramOutput[2])) {
                        StringToConvert = ProgramOutput[2];
                    } else if (checkIfNumber(ProgramOutput[3])) {
                        StringToConvert = ProgramOutput[3];
                    } else {
                        skip = true;
                    }
                    if (!skip) {
                        if (!StringToConvert.isEmpty()) {
                            int value = (int)(Float.parseFloat(
                                        StringToConvert.substring(
                                                0,
                                                StringToConvert.length()-1
                                        )
                                    ) * 10.0f
                                );
                            pbHnd_pbar2.setValue(value);
                        }
                    }
                }
            }    
            
            br.close();
            p.waitFor();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }
}
