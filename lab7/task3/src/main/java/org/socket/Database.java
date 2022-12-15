package org.socket;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*      - SELECT FROM [WHERE, ORDER BY]
        - INSERT INTO VALUES
        - DELETE FROM [WHERE]
        - UPDATE SET [WHERE]*/
public class Database {

    private ArrayList<DBRecord> records;
    private String fileName;

    Database(String filePath) throws IOException {
        records = new ArrayList<DBRecord>();
        fileName = filePath;
        this.updateList();
    }

    public void updateList() throws IOException {
        FileInputStream dbfin;
        try {
            dbfin = new FileInputStream(this.fileName);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        int tmp;
        String input = new String();
        while ((tmp = dbfin.read()) != -1) {
            input += (char) tmp;
        }
        dbfin.close();
        String[] rows = input.split(";");
        this.records.clear();
        try {
            for (String curRow : rows) {
                String[] cols = curRow.split("\\|");
                DBRecord curRec = new DBRecord(Integer.parseInt(cols[0]), cols[1], Integer.parseInt(cols[2]));
                this.records.add(curRec);
            }
        } catch (Exception e) {
//            System.out.println(e);
        }
    }

    public void updateFile() throws IOException {
        FileOutputStream dbfout;
        try {
            dbfout = new FileOutputStream(this.fileName);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        for (DBRecord curRec : this.records) {
            String outStr = Integer.toString(curRec.getId()) + "|" + curRec.getName() + "|" + Integer.toString(curRec.getAge()) + ";";
            dbfout.write(outStr.getBytes());
        }
        dbfout.close();
    }

    public static String cmdHandler(String command) throws IOException {
        Database database = new Database("/home/kraken/labs/kpo_lab/lab7/db");
        String[] words = command.split("\\s");
//        for (String word : words) {
//            System.out.println(word);
//        }
        try {
            switch (words[0]) {
                case "SELECT":
//                    System.out.println("SELECT");
                    return database.select(command);
                case "INSERT":
                    return database.insert(command);
                case "DELETE":
                    return database.delete(command);
                case "UPDATE":
                    return database.update(command);
                default:
                    return "Unknown command\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private String select(String command) {
        String output = new String("\n");
//        command.replace("\'", "");
//        command.replace("\"", "");
//        command.replace(",", "");
//        command.replace(";", "");

        String[] words = command.split("[, |\\s|\\;]");
        String cols = new String(), table = new String(), where = new String(), orderby = new String();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("SELECT")) {
                i++;
                for (; i < words.length && !words[i].equals("FROM"); i++) {
                    cols += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("FROM")) {
                i++;
                for (; i < words.length && !words[i].equals("WHERE") && !(words[i].equals("ORDER") && words[i + 1].equals("BY")); i++) {
                    table += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("WHERE")) {
                i++;
                for (; i < words.length && !(words[i].equals("ORDER") && words[i + 1].equals("BY")); i++) {
                    where += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("ORDER") && words[i + 1].equals("BY")) {
                i += 2;
                for (; i < words.length; i++) {
                    orderby += words[i] + " ";
                }
            }
        }
//        if (!table.equals("table")) {
//            output += "Unknown table.\n";
//            return output;
//        }
        System.out.println(cols);
        System.out.println(table);
        System.out.println(where);
        System.out.println(orderby);

        boolean col0 = false, col1 = false, col2 = false;
        words = cols.split("\\s");
        for (String col : words) {
            if (col.equals("id")) {
                output += "id\t";
                col0 = true;
            }
            if (col.equals("name")) {
                output += "name\t";
                col1 = true;
            }
            if (col.equals("age")) {
                output += "age\t";
                col2 = true;
            }
            if (col.equals("*")) {
                output += "id\tname\tage";
                col0 = col1 = col2 = true;
            }
        }
        output += "\n";
        if (orderby.equals("id"))
            Collections.sort(this.records, DBRecord.sortById);
        if (orderby.equals("name"))
            Collections.sort(this.records, DBRecord.sortByName);
        if (orderby.equals("age"))
            Collections.sort(this.records, DBRecord.sortByAge);

        for (DBRecord curRec : this.records) {
            if (!where.isEmpty()) {
                words = where.split(",\\s*");
                boolean isNeeded = true;
                for (String cur : words) {
                    String[] wh = cur.split("\\s*=\\s*");
                    if ((wh[0].equals("id") && curRec.getId() != Integer.parseInt(wh[1])) ||
                            (wh[0].equals("name") && !curRec.getName().equals(wh[1])) ||
                            (wh[0].equals("age") && curRec.getAge() != Integer.parseInt(wh[1]))) {
                        isNeeded = false;
                    }
                }
                if (!isNeeded) continue;
            }
            if (col0) {
                output += Integer.toString(curRec.getId()) + "\t";
            }
            if (col1) {
                output += curRec.getName() + "\t";
            }
            if (col2) {
                output += Integer.toString(curRec.getAge());
            }
            output += "\n";
        }
        return output;
    }

    private String insert(String command) {
//        command.replace("\'", "");
//        command.replace("\"", "");
//        command.replace(",", "");
//        command.replace(";", "");
//        command.replace("(", "");
//        command.replace(")", "");

        String[] words = command.split("[, |\\s|\\;]");
        for (String cur : words) {
            System.out.println(cur);
        }
        String table = new String(), values = new String();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("INSERT") && words[i + 1].equals("INTO")) {
                i += 2;
                for (; i < words.length && !words[i].equals("VALUES"); i++) {
                    table += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("VALUES")) {
                i++;
                for (; i < words.length; i++) {
                    values += words[i] + " ";
                }
            }
        }
//        if (!table.equals("table")) {
//            return "Unknown table.\n";
//        }

        words = values.split("\\s");
//        for (String cur : words) {
//            System.out.println(cur);
//        }
        if (words.length < 3) {
            return "Too few values.\n";
        }
        try {
            DBRecord tmp = new DBRecord(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[2]));
            this.records.add(tmp);
            this.updateFile();
            return "Record added.\n";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Something gone wrong.\n";
    }

    private String delete(String command) {
        String[] words = command.split("[, |\\s|\\;]");
//        for (String cur : words) {
//            System.out.println(cur);
//        }
        String table = new String(), where = new String();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("DELETE") && words[i + 1].equals("FROM")) {
                i += 2;
                for (; i < words.length && !words[i].equals("WHERE"); i++) {
                    table += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("WHERE")) {
                i++;
                for (; i < words.length; i++) {
                    where += words[i] + " ";
                }
            }
        }
        Iterator<DBRecord> itr = this.records.iterator();
        while (itr.hasNext()) {
            DBRecord curRec = itr.next();
            if (!where.isEmpty()) {
                words = where.split("\\s");
                boolean toDelete = true;
                for (String cur : words) {
                    String[] wh = cur.split("=");
                    if (!((wh[0].equals("id") && curRec.getId() == Integer.parseInt(wh[1])) ||
                            (wh[0].equals("name") && curRec.getName().equals(wh[1])) ||
                            (wh[0].equals("age") && curRec.getAge() == Integer.parseInt(wh[1])))) {
                        toDelete = false;
                    }
                }
                if (!toDelete) continue;
            }
            itr.remove();
        }
        try {
            this.updateFile();
        } catch (Exception e) {
            System.out.println(e);;
            return "Something gone wrong.\n";
        }
        return "Record(s) deleted.\n";
    }

    private String update(String command) {
        String[] words = command.split("[, |\\s|\\;]");
//        for (String cur : words) {
//            System.out.println(cur);
//        }
        String table = new String(), set = new String(), where = new String();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("UPDATE")) {
                i++;
                for (; i < words.length && !words[i].equals("SET"); i++) {
                    table += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("SET")) {
                i++;
                for (; i < words.length && !words[i].equals("WHERE"); i++) {
                    set += words[i] + " ";
                }
                i--;
            } else if (words[i].equals("WHERE")) {
                i++;
                for (; i < words.length; i++) {
                    where += words[i] + " ";
                }
            }
        }
        try {
            Iterator<DBRecord> itr = this.records.iterator();
            while (itr.hasNext()) {
                DBRecord curRec = itr.next();
                if (!where.isEmpty()) {
                    words = where.split("\\s");
                    boolean toUpdate = true;
                    for (String cur : words) {
                        String[] wh = cur.split("=");
                        if (!((wh[0].equals("id") && curRec.getId() == Integer.parseInt(wh[1])) ||
                                (wh[0].equals("name") && curRec.getName().equals(wh[1])) ||
                                (wh[0].equals("age") && curRec.getAge() == Integer.parseInt(wh[1])))) {
                            toUpdate = false;
                        }
                    }
                    if (!toUpdate) continue;
                }
                words = set.split("\\s");
                for (String cur : words) {
                    String[] wh = cur.split("=");
                    if (wh[0].equals("id")) curRec.setId(Integer.parseInt(wh[1]));
                    else if (wh[0].equals("name")) curRec.setName(wh[1]);
                    else if (wh[0].equals("age")) curRec.setAge(Integer.parseInt(wh[1]));
                }
            }
            this.updateFile();
        } catch (Exception e) {
            System.out.println(e);
            return "Something gone wrong.\n";
        }
        return "Record(s) updated.\n";
    }
}
