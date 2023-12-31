import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SlangwordManagement {
    private static String FILE_SLANGWORD_SRC = "slang.txt";
    private static String FILE_SLANGWORD = "slangword_temp.txt";
    static String FILE_SLANGWORD_HISTORY = "slangword_history.txt";
    private TreeMap<String, List<String>> mapSlang = new TreeMap<>();
    private static SlangwordManagement object = new SlangwordManagement();
    static long HistorySize = 0;


    private SlangwordManagement() {
        try {
            String userDirectory = new File("").getAbsolutePath();
            FILE_SLANGWORD = userDirectory + "\\" + FILE_SLANGWORD;
            readFile(FILE_SLANGWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SlangwordManagement getInstance() {
        if (object == null) {
            synchronized (SlangwordManagement.class) {
                if (object == null) {
                    object = new SlangwordManagement();
                }
            }
        }
        return object;
    }


    public void readFile(String file) throws IOException {
        mapSlang.clear();
        BufferedReader reader = null;
        String[] slangPart = new String[2];
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String slang = "";
            String[] meaning_arr;
            List<String> meaning;
            while (line != null) {
                if (line.contains("`")) {
                    slangPart = line.split("`");
                } else {
                    slangPart[0] = slang;
                    slangPart[1] += "| " + line;
                }
                slang = slangPart[0].trim();
                meaning_arr = new String[]{};
                meaning = new ArrayList<String>();
                if (slangPart[1].contains("|")) {
                    meaning_arr = slangPart[1].split("\\|");
                    for (String i : meaning_arr) {
                        meaning.add(i.trim());
                    }
                } else {
                    meaning.add(slangPart[1]);
                }
                mapSlang.put(slang, meaning);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }


    public int getSize() {
        int size = 0;
        Set<String> keySet = mapSlang.keySet();
        for (String key : keySet) {
            for (int i = 0; i < mapSlang.get(key).size(); i++) {
                size++;
            }
        }
        return size;
    }

    public int getIndexMeaningofSW(String sw, String meaning) {
        String[][] data = getSW_Data();
        for (int i = 0; i < data.length; i++) {
            if (data[i][1].equals(sw) && data[i][2].equals(meaning)) {
                return i;
            }
        }
        return 0;
    }


    public String[][] getSW_Data() {
        String s[][] = new String[getSize()][3];
        Set<String> keySet = mapSlang.keySet();
        int i = 1, k = 0;
        for (String key : keySet) {
            for (int jj = 0; jj < mapSlang.get(key).size(); jj++) {
                s[k][0] = String.valueOf(i++).toString();
                s[k][1] = key;
                s[k][2] = mapSlang.get(key).get(jj);
                k++;
            }
        }
        return s;
    }


    public String[][] findSlangWord(String slangword) {
        List<String> listDef = mapSlang.get(slangword);
        if (listDef != null) {
            int sizeListDef = listDef.size();
            String[][] result = new String[sizeListDef][3];
            for (int i = 0; i < sizeListDef; i++) {
                result[i][0] = String.valueOf(i + 1).toString();
                result[i][1] = slangword;
                result[i][2] = listDef.get(i);
            }
            return result;
        }
        return null;
    }


    public String[][] findDef(String slangword) {
        String[][] data = getSW_Data();
        List<String> slang = new ArrayList<String>();
        List<String> meaning = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            if (data[i][2].toLowerCase().startsWith(slangword.toLowerCase())) {
                slang.add(data[i][1]);
                meaning.add(data[i][2]);
            }
        }
        if (slang.size() > 0) {
            String[][] result = new String[slang.size()][3];
            for (int i = 0; i < slang.size(); i++) {
                result[i][0] = String.valueOf(i + 1).toString();
                result[i][1] = slang.get(i);
                result[i][2] = meaning.get(i);
            }
            return result;
        } else return null;
    }


    public void writeFileHistory(String slangword, String meaning) throws Exception {
        try (FileWriter writer = new FileWriter(FILE_SLANGWORD_HISTORY, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String line = slangword + "`" + meaning + "`" + dtf.format(now).toString() + "\n";
            bw.write(line);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }


    public void viewHistory(String slangword) throws Exception {
        String[][] findSlangWord = findSlangWord(slangword);
        for (int i = 0; i < findSlangWord.length; i++) {
            writeFileHistory(findSlangWord[i][1], findSlangWord[i][2]);
        }
    }


    public String[][] readFileHistory(String file) throws IOException {
        List<String> temp = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                temp.add(line);
                line = reader.readLine();
            }
            HistorySize = temp.size();
            String[][] result = new String[temp.size()][3];
            int k = 0;
            for (String i : temp) {
                String[] split_ = i.split("`");
                result[k][0] = split_[0];
                result[k][1] = split_[1];
                result[k][2] = split_[2];
                k++;
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return null;
    }


    public void writeFile(String file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileWriter writer = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(writer);
        String fileWrite[][] = new String[mapSlang.size()][3];
        Object[] keyArray = mapSlang.keySet().toArray();
        for (int i = 0; i < mapSlang.size(); i++) {
            fileWrite[i][0] = String.valueOf(i + 1).toString();
            fileWrite[i][1] = (String) keyArray[i];
            List<String> meaning = mapSlang.get(keyArray[i]);
            stringBuilder.append(fileWrite[i][1] + "`" + meaning.get(0));
            for (int j = 1; j < meaning.size(); j++) {
                stringBuilder.append("| " + meaning.get(j));
            }
            stringBuilder.append("\n");
        }
        printWriter.write(stringBuilder.toString());
        printWriter.close();
    }


    public String[][] getMeaning(String slang) {
        List<String> getMeaning_ = mapSlang.get(slang);
        String[][] result = new String[getMeaning_.size()][2];
        for (int i = 0; i < getMeaning_.size(); i++) {
            result[i][0] = String.valueOf(i + 1).toString();
            result[i][1] = getMeaning_.get(i);
        }
        return result;
    }


    public void addSlangWord(String slang, String meaning, int condition, int index) throws IOException {
        if (condition == 1) {
            List<String> meaningList = new ArrayList<>();
            meaningList.add(meaning);
            mapSlang.put(slang, meaningList);
            writeFile(FILE_SLANGWORD);
        } else if (condition == 2) {
            Boolean c = false;
            List<String> check_ = mapSlang.get(slang);
            for (String i : check_) {
                if (i.equals(meaning)) c = true;
            }
            if (c == false) {
                List<String> meanList = mapSlang.get(slang);
                meanList.add(meaning);
                mapSlang.put(slang, meanList);
                writeFile(FILE_SLANGWORD);
            }
        } else if (condition == 3) {
            List<String> meanList = mapSlang.get(slang);
            meanList.set(index - 1, meaning);
            mapSlang.put(slang, meanList);
            writeFile(FILE_SLANGWORD);
        }
    }


    public boolean checkSlang(String slang) {
        for (String keyIro : mapSlang.keySet()) {
            if (keyIro.equals(slang))
                return true;
        }
        return false;
    }


    public void deleteSlangWord(String slang) throws IOException {
        mapSlang.remove(slang);
        writeFile(FILE_SLANGWORD);
    }


    public void resetSlangWord() throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(new File(FILE_SLANGWORD_SRC));
            outStream = new FileOutputStream(new File(FILE_SLANGWORD));
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inStream.close();
            outStream.close();
        }
        readFile(FILE_SLANGWORD);
    }


    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


    public String[] RandomSlangWord() {
        int rand = getRandomNumberUsingNextInt(0, mapSlang.size() - 1);
        String wordRand[] = new String[2];
        int i = 0;
        Set<String> keySet = mapSlang.keySet();
        for (String key : keySet) {
            if (i == rand) {
                int sizeKey = mapSlang.get(key).size();
                int randOther = 0;
                if (sizeKey != 1) randOther = getRandomNumberUsingNextInt(0, sizeKey - 1);
                wordRand[0] = key;
                wordRand[1] = mapSlang.get(key).get(randOther);
                break;
            }
            i++;
        }
        return wordRand;
    }


    public void UpdateSlangWord(String sw, String oldVal, String newVal) {
        List<String> meaning = mapSlang.get(sw);
        int i_Old = meaning.indexOf(oldVal);
        meaning.set(i_Old, newVal);
        try {
            writeFile(FILE_SLANGWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String[] randomMultipleChoice(int type) {
        String[] arrMTPChoice = new String[6];
        String[] randomSW = RandomSlangWord();
        int rand = getRandomNumberUsingNextInt(1, 4);
        if (type == 1) {
            arrMTPChoice[0] = randomSW[0];
            arrMTPChoice[rand] = randomSW[1];
            for (int i = 1; i <= 4; i++) {
                if (i != rand) {
                    String[] slangRand = RandomSlangWord();
                    while (slangRand[0].equals(arrMTPChoice[0])) {
                        slangRand = RandomSlangWord();
                    }
                    arrMTPChoice[i] = slangRand[1];
                }
            }
            arrMTPChoice[5] = randomSW[1];
        } else {
            arrMTPChoice[0] = randomSW[1];
            arrMTPChoice[rand] = randomSW[0];
            for (int i = 1; i <= 4; i++) {
                if (i != rand) {
                    String[] slangRand = RandomSlangWord();
                    while (slangRand[0].equals(arrMTPChoice[0])) {
                        slangRand = RandomSlangWord();
                    }
                    arrMTPChoice[i] = slangRand[0];
                }
            }
            arrMTPChoice[5] = randomSW[0];
        }
        return arrMTPChoice;
    }
}
