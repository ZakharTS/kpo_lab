package org.socket;

import java.io.DataOutputStream;

/*      - SELECT FROM [WHERE, ORDER BY]
        - INSERT INTO VALUES
        - DELETE FROM [WHERE]
        - UPDATE SET [WHERE]*/
public class Database {
    public static void cmdHandler(String command, DataOutputStream socketOut) {
        String[] words = command.split("\\s");
//        for (String word : words) {
//            System.out.println(word);
//        }
        try {
            switch (words[0]) {
                case "SELECT":
                    System.out.println("SELECT");
                    socketOut.writeUTF(select(command));
                    break;
                case "INSERT":
                    socketOut.writeUTF(insert(command));
                    break;
                case "DELETE":
                    socketOut.writeUTF(delete(command));
                    break;
                case "UPDATE":
                    socketOut.writeUTF(update(command));
                    break;
                default:
                    socketOut.writeUTF("Unknown command\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String select(String command) {
        String[] cols = command.substring(command.indexOf("SELECT") + 6,
                command.indexOf("FROM", command.indexOf("SELECT") + 6)).split("\\s");
        System.out.println(cols.toString());
        String tableName;
//        int i;
//        for (i = 1; i < words.length; i++) {
//            if (words[i] == "FROM") break;
//            cols += words[i];
//        }
//        tableName = words[i+1];
//        i++;
        String conds;
        String orderby;

//        for (;i < words.length; i++) {
//            if (words[i] == "WHERE") {
//                for (int j = i + 1; j < words.length; j++) {
//                    if (words[j] == "ORDER" && words[j+1] == "BY") {
//                        i = j - 1;
//                        break;
//                    }
//                    conds[j-i-1] = words[j];
//                }
//                continue;
//            }
//            if (words[i] == "ORDER" && words[i+1] == "BY") {
//                for (int j = i + 2; j < words.length; j++) {
//                    orderby[j-i-1] = words[j];
//                }
//                break;
//            }
//        }

        return cols.toString();
    }

    private static String insert(String command) {

        return "";
    }

    private static String delete(String command) {

        return "";
    }

    private static String update(String command) {

        return "";
    }
}
