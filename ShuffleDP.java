import java.util.ArrayList;

public class ShuffleDP {
    private Content[][] table;
    private String[] x;
    private String[] y;
    private String[] z;
    private static String xString;
    private static String yString;
    private static String zString;
    private static int debug;
    private int tableRef;

    public ShuffleDP() {
        x = xString.split("");
        y = yString.split("");
        z = zString.split("");
        table = new Content[x.length][y.length];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = new Content();
            }
        }

        buildTable();

        if (debug == 1) {
            printTable();
        }

        if (table[x.length - 1][y.length - 1].getInt() == 1) {
            System.out.println("yes");
            System.out.println("Number of table references " + tableRef);
        }
        else {
            System.out.println("no");
        }
    }

    private boolean t(int i, int j) {
        boolean shuffle = false;

        if (i == 0 && j == 0) {
            shuffle = true;
        }
        if (i > 0 && j == 0) {
            shuffle = b(x[i], z[i]) && t(i-1, 0);
        }
        if (i == 0 && j > 0) {
            shuffle = b(y[j], z[j]) && t(0, j-1);
        }
        if (i > 0 && j > 0) {
            shuffle = b(x[i], z[i+j]) && t(i-1, j) || b(y[j], z[i+j]) && t(i, j-1);
        }

        if (shuffle)
            table[i][j].setInt(1);
        else
            table[i][j].setInt(0);

        return shuffle;
    }

    private boolean b(String c1, String c2) {
        if (!c1.equals(c2)) {
            return false;
        }
        else {
            return true;
        }
    }

    private void buildTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                t(i,j);
            }
        }
    }

    private void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printUsage() {
        System.out.println("java ShuffleDP <X> <Y> <Z> [<debug level>]");
    }

    private static void parseArgs(String[] args) {
        if (args.length != 4) {
            printUsage();
            System.exit(1);
        }

        xString = " " + args[0];
        yString = " " + args[1];
        zString = " " + args[2];

        if (args[0].length() + args[1].length() != args[2].length()) {
            System.out.println("X length + Y length must equal Z length");
            printUsage();
            System.exit(1);
        }

        debug = Integer.parseInt(args[3]);
    }

    private class Content {
        boolean ref;
        int i;
        private Content() {
            ref = false;
            i = -1;
        }

        private boolean getRef() {
            return ref;
        }

        private void setRef(boolean ref) {
            this.ref = ref;
        }

        private int getInt() {
            return i;
        }

        private void setInt(int i) {
            this.i = i;
        }
    }

    public static void main(String[] args) {
        parseArgs(args);
        ShuffleDP shuffleDP = new ShuffleDP();
    }
}

