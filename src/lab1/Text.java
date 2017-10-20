package lab1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * @author lusho aaa
 *
 */

public class Text {

    /**
     * aaa.
     */
    private int t = 0;
    /**
     * aaaaa.
     */
    public StringBuilder mp = new StringBuilder();

    /**
     * aaaa.
     */
    public Graph g;
    /**
     * 就是宏.
     */
    private static final int J = 100000;
    /**
     * aaa.
     */
    private static final int K = 30;

    /**
     * @param d
     *            aaa
     */
    Text(final Graph d) {
        this.g = d;
    }

    /**
     * @param n
     *            结点个数
     */
    public void showDirectedGraph(final int n) {
        try {
            String gvout = "F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\dot.dot";
            FileOutputStream fos = new FileOutputStream(gvout);
            OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");
            fw.write("digraph G {");
            fw.write("\r\n");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (g.getedge(i, j) != J) {
                        fw.write('\t');
                        fw.write(g.getstr(i));
                        fw.write(" -> ");
                        fw.write(g.getstr(j));
                        fw.write(";\r\n");
// System.out.println(i+"，"+j+"："+g.Getstr(i)+"->"+g.Getstr(j));
                    }
                }

            }
            fw.write("}");
            fw.close();
            String path = "dot Kdot -Tpng dot.dot -o dot.png";
            try {
Runtime.getRuntime().exec(path, null, new File("F:\\Java\\eclipse_workspace\\lab1"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param word1
     *            aaa
     * @param word2
     *            aaa
     * @return aaaa
     */
public final String queWords(final String word1, final String word2) {
	    StringBuilder result = new StringBuilder();
	    int x = g.getpos(word1);
        int y = g.getpos(word2);
        int n = g.getn();
        int u = 100;
        int[] pos = new int[u];
        int k = -1;
        if ((x != -1) && (y != -1)) {
            for (int i = 0; i <= n; i++) {
                if (((g.getedge(x, i) != J) && (g.getedge(i, y) != J))
                        || (g.getedge(y, i) != J) && (g.getedge(i, x) != J)) {
                    result.append(g.getstr(i));
                    k++;
                    pos[k] = i;
                    result.append(' ');
                }
            }
            try {
String gvout = "F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\bridge.dot";
                FileOutputStream fos = new FileOutputStream(gvout);
                String path = "dot Kdot -Tpng bridge.dot -o bridge.png";
                try {
Runtime.getRuntime().exec(path, null, new File("F:\\Java\\eclipse_workspace\\lab1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result.toString();
        } else {
            return result.toString();
        }

    }

    /**
     * @param inputText
     *            aaa
     * @return aaaa
     */
    public final String genText(final String inputText) {
        StringBuilder builder = new StringBuilder();
        try {
String gvout = "F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\NewWord.dot";
            FileOutputStream fos = new FileOutputStream(gvout);

            char[] charray = inputText.toCharArray();
            int rrr = 1000;
            String[] strarray = new String[rrr];
            String[] strr = new String[J];
            String temp;
            final int eee = 30;
            char[] chararr = new char[eee];
            int n;
            int m;
            int w;
            n = -1;
            m = -1;
            w = -1;
            int u1 = 65;
            int u2 = 90;
            int u3 = 97;
            int u4 = 122;
            for (int l = 0; l < charray.length; l++) {
if (((charray[l] >= u1) && (charray[l] <= u2)) || ((charray[l] >= u3) && (charray[l] <= u4))) {
                    n++;
                    chararr[n] = charray[l];
                } else if (!(((charray[l] >= u1) && (charray[l] <= u2))
                        || ((charray[l] >= u3) && (charray[l] <= u4)))) {

                    StringBuilder strbuilder = new StringBuilder();
                    for (int i = 0; i <= n; i++) {

                        strbuilder.append(chararr[i]);
                        chararr[i] = '\u0000';
                    }

                    m++;
                    strarray[m] = strbuilder.toString();
                    // System.out.println(m+":"+str_array[m]);
                    n = -1;

                }
            }

            if (n != -1) {
                StringBuilder strbuilder = new StringBuilder();
                for (int i = 0; i <= n; i++) {

                    strbuilder.append(chararr[i]);
                    chararr[i] = '\u0000';
                }

                m++;
                strarray[m] = strbuilder.toString();
            }

            for (int h = 0; h <= m; h++) {

                if (strarray[h].length() != 0) {
                    w++;
                    strarray[h] = strarray[h].toLowerCase(); // 修改为小写单词
                    strr[w] = strarray[h];

                }

            }
            for (int i = 0; i < w; i++) {
                builder.append(strr[i]);
                builder.append(' ');
                temp = queWords(strr[i], strr[i + 1]);
                String[] array2 = temp.split(" ");
                // System.out.println(i+strr[i]+"->"+strr[i+1]);
                // for(int k=0;k<array2.length;k++)
                // {
                // System.out.println(k+":="+array2[k]);
                // } 

            }
            builder.append(strr[w]);
            String path = "dot Kdot -Tpng NewWord.dot -o NewWord.png";
            try {
Runtime.getRuntime().exec(path, null, new File("F:\\Java\\eclipse_workspace\\lab1"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * @param word1
     *            henhao
     * @param word2
     *            henhao
     * @return henhao
     */
public final String calPath(final String word1, final String word2) {
        if (g.isingraph(word1) == 0) {
            return "我们的图里不存在这样的单词";
        }
        if ((!word2.equals("")) && g.isingraph(word2) == 0) {
            return "我们的图里不存在这样的单词";
        }
        int x = g.getpos(word1);
        int y = 0;
        	int muti = 0;
        if (!word2.equals("")) {
            y = g.getpos(word2);
        }
        int n = g.getn();
        int flag;
        int[][] leastDis = new int[n + 2][n + 2];
        int[][] minPath = new int[n + 2][n + 2];
        StringBuilder builder = new StringBuilder();
        String returnarr;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                leastDis[i][j] = g.getedge(i, j);
                minPath[i][j] = 0;
            }
        }
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (leastDis[i][k] + leastDis[k][j] <= leastDis[i][j]) {
                        leastDis[i][j] = leastDis[i][k] + leastDis[k][j];
                        minPath[i][j] = k;
                    }
                }
            }
        }

        if (!word2.equals("")) {
            String res = null;
            res = printminPath(x, y, minPath);
            String res1;
            if (t == 1 && g.getedge(x, y) == J) {
                return "不存在两个单词之间的路径";
            } else {
                builder.append(g.getstr(x));
                builder.append(res);
            }
            String str1 = builder.toString();
            int s0 = minPath[x][y];
            for (int q = 1; q <= n + 1; q++) {
                if (q != s0) {
                    if (leastDis[x][q] + leastDis[q][y] == leastDis[x][y]) {
                        StringBuilder sb = new StringBuilder();
                        mp.setLength(0);
                        sb.append(g.getstr(x));
                        String res11 = printminPath(x, q, minPath);
                        mp.setLength(0);
                        String res2 = printminPath(q, y, minPath);
                        sb.append(res11);
                        sb.append(res2);
                        String str2 = sb.toString();
                        if (!(str2.equals(str1))) {
                            builder.append('\n');
                            builder.append(str2);
                        }
                    }
                }
            }
            flag = 1;
        } else { // 单元
            String mutipath = null;
            for (int j = 1; j <= g.n; j++) {
                if (g.isRepeat(j) != 1) {
                    mutipath = printminPath(x, j, minPath);
                    if (!(t == 1 && g.getedge(x, j) == J)) {
                        builder.append(g.getstr(x));
                        builder.append(mutipath);
                        builder.append('\n');
                        muti++;

                    }
                    mp.setLength(0);
                    t = 0;
                }

            }
            if (muti == 0) {
                return "从该点出发没有路";
            }

            flag = 2;
        }

        returnarr = builder.toString();
        shorPh(returnarr, flag);
        return returnarr;
    }

    /**
     * @param str
     *            aaa
     * @param flag
     *            aaa
     */
    final void shorPh(final String str, final int flag) {
        //StringBuilder builder = new StringBuilder();
        int n = g.getn();

        if (flag == 1) {
            try {
String gvout = "F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\shortest2.dot";
                FileOutputStream fos = new FileOutputStream(gvout);

                String[] strarray = str.split("->");
                String path = "dot Kdot -Tpng shortest2.dot -o shortest2.png";
                try {
Runtime.getRuntime().exec(path, null, new File("F:\\Java\\eclipse_workspace\\lab1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
 String gvout = "F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\shortest.dot";
                FileOutputStream fos = new FileOutputStream(gvout);
                String[] strarray = str.split("\n");
                String path = "dot Kdot -Tpng shortest.dot -o shortest.png";
                try {
Runtime.getRuntime().exec(path, null, new File("F:\\Java\\eclipse_workspace\\lab1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param i
     *            aa
     * @return aa
     */
    final String printminPath(final int i, final int j, final int[][] minPath) {
        if (minPath[i][j] == 0) {
            mp.append("->");
            mp.append(g.getstr(j));
            t = t + 1;
        } else {
            int k = minPath[i][j];
            printminPath(i, k, minPath);
            printminPath(k, j, minPath);
        }
        return mp.toString();
    }

    /**
     * @return aaa
     */
    public final String ranwalk() {
        StringBuilder randomPath = new StringBuilder();
        int min = 1;

        int s;
        int p;
        int[][] putPath = new int[g.n + 1][g.n + 1];
        int rr = 500;
        int[] calRep = new int[rr];
        int cal = 1;
        for (int w = 1; w < g.n + 1; w++) {
            if (g.isRepeat(w) != 1) {
                calRep[cal] = w;
                cal++;
            }

        }
        int max = cal;
        for (int i = 0; i <= g.n; i++) {
            for (int j = 0; j < g.n; j++) {
                putPath[i][j] = 0;
            }
        }
        Random random = new Random();
        Random rep = new Random();
        String ranstr;
        s = random.nextInt(max - min) + min;
        s = calRep[s];
        randomPath.append(g.getstr(s));
        if (havePath(s) == 0) {
            ranstr = randomPath.toString();
            return ranstr;
        }

        do {
            p = rep.nextInt(max - min) + min;
            p = calRep[p];
        } while (g.getedge(s, p) == J);
        // p=calRep[p];
        do {
            putPath[s][p] = 1;
            s = p;
            randomPath.append("->");
            randomPath.append(g.getstr(p));
            if (havePath(p) == 0) {
                ranstr = randomPath.toString();
                return ranstr;
            }
            do {
                p = rep.nextInt(max - min) + min;
                p = calRep[p];
            } while (g.getedge(s, p) == J);
        } while (putPath[s][p] == 0);
        randomPath.append("->");
        randomPath.append(g.getstr(p));
        ranstr = randomPath.toString();
        return ranstr;
    }

    /**
     * @param k
     *            dd
     * @return dd
     */
    public final int havePath(final int k) {
        int h = 0;
        for (int i = 0; i <= g.n; i++) {
            if (g.getedge(k, i) != J) {
                h++;
            }
        }
        return h;
    }

    /**
     * @param args
     *            aaa
     */
    public static void main(final String[] args) {
        int time = 0;
        int tt = 3;
        int whi = 50;
        System.out.println("show—展示有向图" + '\n' + "br+word1+word2—查询桥接词" + '\n' + "getnew—生成新文本" + '\n'
                + "shortest+word1—单源最短路径" + '\n' + "shortest+word1+word2—两点间最短路径" + '\n' + "random—随机游走");
        do {
            // 展示有向图
            Graph g = new Graph();
            Text t = new Text(g);
            String commandLine;
            Scanner cmd = new Scanner(System.in);
            commandLine = cmd.nextLine();
            String[] cmdarray = new String[tt];
            cmdarray = commandLine.split(" ");
            String word1, word2 = null, re;
            Scanner sc = new Scanner(System.in);
            if (cmdarray[0].equals("show")) {
                System.out.print('\n');
                t.showDirectedGraph(t.g.getn());
            } else if (cmdarray[0].equals("br")) {
                // 查询桥连接
                int a = 0;
                final int jj = 3;
                for (int k = 0; k < cmdarray.length; k++) {
                    if (null != cmdarray[k]) {
                        a++;
                    }
                }
                if (a != jj) {
                    System.out.println("非法输入！");
                } else {
                    word1 = cmdarray[1];
                    word2 = cmdarray[2];
if ((g.isingraph(word1) == 0) && (g.isingraph(word2) == 0)) {
System.out.println("No “" + word1 + "” and “" + word2 + "” in the graph!");
                    } else if (g.isingraph(word1) == 0) {
System.out.println("No “" + word1 + "” in the graph!");
                    } else if (g.isingraph(word2) == 0) {
System.out.println("No “" + word2 + "” in the graph!");
                    } else {
                        re = t.queWords(word1, word2);
                        if (re.length() == 0) {
System.out.println("No bridge words from “" + word1 + "” to “" + word2 + "!");
                        } else {
System.out.println("The bridge words from ?“" + word1 + "”?to?“" + word2 + "” is:" + re);
                        }
                    }

                }
            } else if (cmdarray[0].equals("getnew")) {
                // 根据bridge生成新文本
                int a = 0;
                for (int k = 0; k < cmdarray.length; k++) {
                    if (null != cmdarray[k]) {
                        a++;
                    }
                }
                if (a != 1) {
                    System.out.println("非法输入！");
                } else {
                    System.out.println("输入新文本");
                    String inputText;
                    String outputText;
                    inputText = sc.nextLine();
                    System.out.println("输出插入桥连接词文本");
                    outputText = t.genText(inputText);
                    System.out.println(outputText);
                }
            } else if (cmdarray[0].equals("shortest")) {

                int a = 0;
                int h = 3;
                String min;
                for (int k = 0; k < cmdarray.length; k++) {
                    if (null != cmdarray[k]) {
                        a++;
                    }
                }
                if (a == 2) {
                    word1 = cmdarray[1];
                    word2 = "";
                    min = t.calPath(word1, word2);
                    System.out.println(min);
                } else if (a == h) {
                    word1 = cmdarray[1];
                    word2 = cmdarray[2];
                    min = t.calPath(word1, word2);
                    System.out.println(min);
                } else {
                    System.out.println("非法输入！");
                }

            } else if (cmdarray[0].equals("random")) {
                // System.out.println("随机游走：");
                int a = 0;
                for (int k = 0; k < cmdarray.length; k++) {
                    if (null != cmdarray[k]) {
                        a++;
                    }
                }
                if (a != 1) {
                    System.out.println("非法输入！");
                } else {
                    String randompath;
                    randompath = t.ranwalk();

                    System.out.println(randompath);
                }
            } else if (cmdarray[0].equals("quit")) {
                System.exit(0);
            } else {
                System.out.println("非法输入！");
            }
            time++;

        } while (time != whi);
    }

}
