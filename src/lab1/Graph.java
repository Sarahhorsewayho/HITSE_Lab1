package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 */
public class Graph {
    /**
     */
    public int[][] edge;
    /**
     */
    public String[] strr; //原始字符串
    /**
     */
    public int n;
    /**
     */
    public HashMap<String, Integer> map;
    /**
     */
    public HashMap<Integer, Integer> repeat;
    /**
     */
    public static final int CON1 = 1000;
    /**
     */
    public static final int CON2 = 30;
    /**
     */
    public static final int CON3 = 65;
    /**
     */
    public static final int CON4 = 90;
    /**
     */
    public static final int CON5 = 97;
    /**
     */
    public static final int CON6 = 122;
    /**
     */
    public static final int CON7 = 100000;
    /**
     *@param x integer
     *@return integer
     */
    public final int isRepeat(final int x) {
        if (repeat.get(x - 1) == 1) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     *@param x integer
     *@param y integer
     *@return integer
     */
    public final int getedge(final int x, final int y) { //返回边x->y
        return edge[x][y];
    }
    /**
     *@return integer
     */
    public final int getn() {
        return n;
    }
    /**
     *@param str String
     *@return integer
     */
    public final int isingraph(final String str) {
        if (map.containsKey(str)) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     *@param i integer
     *@return String
     */
    public final String getstr(final int i) { //返回第i个原始字符串
        String key = null;
        Integer integ = null;
        Iterator<Entry<String, Integer>> iter =
        map.entrySet().iterator();
        while (iter.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) iter.next();
            key = (String) entry.getKey();
            integ = (Integer) entry.getValue();
            if (integ.intValue() == i) {
                return key;
            }
        }
        return null;
     }
    /**
     *@param str String
     *@return integer
     */
    public final int getpos(final String str) {
        if (map.containsKey(str)) {
            return (Integer) map.get(str);
        } else {
            return -1;
        }
    }
    /**
     */
    public Graph() {
        File file = new File("text.txt");
        Reader reader = null;
        try {
            //System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            char temp;
            String[] str = new String[CON1]; //存储原始字符串段
            strr = new String[CON1]; //存储除去字符的单词
            char[] chararr = new char[CON2];
            int m = -1;
            int h = -1;
            int k = -1;
            while ((tempchar = reader.read()) != -1) {
                temp = (char) tempchar;
                if (((tempchar >= CON3) && (tempchar <= CON4))
                    || ((tempchar >= CON5) && (tempchar <= CON6))) {
                    h++;
                    chararr[h] = temp;
                } else if (!(((tempchar >= CON3)
                    && (tempchar <= CON4))
                    || ((tempchar >= CON5) && (tempchar <= CON6)))) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i <= h; i++) {
                        builder.append(chararr[i]);
                        chararr[i] = '\u0000';
                        }
                    m++;
                    str[m] = builder.toString();
                    //System.out.println(m+":"+str[m]);
                    h = -1;
                    }
            }
            if (h != 1) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i <= h; i++) {
                    builder.append(chararr[i]);
                    chararr[i] = '\u0000';
                    }
                m++;
                str[m] = builder.toString();
            }
            for (int i = 0; i <= m; i++) {
                if (str[i].length() != 0) {
                    k++;
                    str[i] = str[i].toLowerCase(); //修改为小写单词
                    strr[k] = str[i];
                    //System.out.print(strr[k]);
                        //System.out.print(" ");
                    }
            }
            //System.out.print("\r\n");
            reader.close();
            this.n = k + 1;
            map = new HashMap<String, Integer>(); //map建立字符串和边的映射
            repeat = new HashMap<Integer, Integer>(); //repeat验证重复单词
            for (int i = 0; i <= k; i++) {
                map.put(strr[i], 0);
                repeat.put(i, 0);
            }
            for (int i = 0; i <= k; i++) {
                if (map.get(strr[i]) == 0) {
                    map.put(strr[i], i + 1);
                    //单词和结点序号之间建立映射，且保证单词不重复。
                    } else {
                        repeat.put(i, 1);
                        }
            }
            edge = new int[k + 2][k + 2];
            for (int i = 0; i <= k + 1; i++) {
                for (int j = 0; j <= k + 1; j++) {
                    edge[i][j] = 0;
                    }
            }
            for (int s = 0; s <= k - 1; s++) {
                edge[map.get(strr[s])][map.get(strr[s + 1])]++;
            }
            for (int i = 0; i <= k + 1; i++) {
                for (int j = 0; j <= k + 1; j++) {
                    if (edge[i][j] == 0) {
                        edge[i][j] = CON7;
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}