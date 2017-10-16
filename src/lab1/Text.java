package lab1;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Text {
	
	private int t=0;
	public StringBuilder mp=new StringBuilder();
	public Graph g;
	Text(Graph g)
	{
		this.g=g;
	}
	public void showDirectedGraph(int n)//展示有向图
	{
		try
		{
			String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\dot.dot";
			FileOutputStream fos = new FileOutputStream(gvout);
			OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
			fw.write("digraph G {");
			fw.write("\r\n");
			for(int i=1;i<=n;i++)
			{
				for(int j=1;j<=n;j++)
				{
					if(g.Getedge(i, j) != 100000)
					{
						fw.write('\t');
						fw.write(g.Getstr(i));
						fw.write(" -> ");
						fw.write(g.Getstr(j));
						fw.write(";\r\n");						
						//System.out.println(i+"，"+j+"："+g.Getstr(i)+"->"+g.Getstr(j));
					}
				}
				
			}
			fw.write("}");
			fw.close();
			 String path = "dot Kdot -Tpng dot.dot -o dot.png";
		        try {
		        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public String queryBridgeWords(String word1,String word2)//查询桥连接
	{
		int x=g.Getpos(word1);
		int y=g.Getpos(word2);
		int n=g.Getn();
		int[] pos = new int[100];
		int k=-1;
		StringBuilder result = new StringBuilder();
		if((x!= -1)&&(y!=-1))
		{
			for(int i=0;i<=n;i++)
			{
				if(((g.Getedge(x, i)!=100000)&&(g.Getedge(i, y)!=100000))||(g.Getedge(y,i)!=100000)&&(g.Getedge(i,x)!=100000))
				{
					result.append(g.Getstr(i));
					k++;
					pos[k]=i;
					result.append(' ');
				}
			}
			try
			{
				String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\bridge.dot";
				FileOutputStream fos = new FileOutputStream(gvout);
				OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
				fw.write("digraph G {");
				for(int i=1;i<=n;i++)
				{
					for(int j=1;j<=n;j++)
					{
						if((g.Getedge(i, j) != 100000))
						{
							fw.write('\t');
							fw.write(g.Getstr(i));
							fw.write(" -> ");
							fw.write(g.Getstr(j));
							fw.write(";\r\n");					
							//System.out.println(i+"，"+j+"："+g.Getstr(i)+"->"+g.Getstr(j));
						}
					}
					
				}
				fw.write("\r\n");
				fw.write(g.Getstr(x));
				fw.write("[fillcolor=yellow,style=filled,Label=\"");
				fw.write(g.Getstr(x));
				fw.write("\"];");
				fw.write("\r\n");
				fw.write(g.Getstr(y));
				fw.write("[fillcolor=yellow,style=filled,Label=\"");
				fw.write(g.Getstr(y));
				fw.write("\"];");
				fw.write("\r\n");
				for(int i=0;i<=k;i++)
				{
					fw.write(g.Getstr(pos[i]));
					fw.write("[fillcolor=red,style=filled,Label=\"");
					fw.write(g.Getstr(pos[i]));
					fw.write("\"];");
					fw.write("\r\n");
				}
				fw.write("}");
				fw.close();
				 String path = "dot Kdot -Tpng bridge.dot -o bridge.png";
			        try {
			        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return result.toString();
		}
		else
			return result.toString();
		
	}
	public String generateNewText(String inputText)//根据bridge生成新文本
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\NewWord.dot";
			FileOutputStream fos = new FileOutputStream(gvout);
			OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
			fw.write("digraph G {");
			fw.write("\r\n");
			
			char ch_array[] = inputText.toCharArray();
			String str_array[] = new String[1000];
			String strr[] = new String[1000];
			String temp;
			char[] chararr = new char[30];
			int n,m,w;
			n=-1;
			m=-1;
			w=-1;
			for(int l=0;l<ch_array.length;l++)
			{
				if(((ch_array[l]>=65)&&(ch_array[l] <= 90))||((ch_array[l] >= 97)&&(ch_array[l] <= 122)))
	            {
	            		n++;
	        			chararr[n]=ch_array[l];
	        		}
	            	else if(!(((ch_array[l]>=65)&&(ch_array[l] <= 90))||((ch_array[l] >= 97)&&(ch_array[l] <= 122))))
	            	{
	            	    
	            		StringBuilder strbuilder = new StringBuilder();
	            		for(int i=0;i<=n;i++)
	            		{
	            			
	            			strbuilder.append(chararr[i]);
	            			chararr[i]='\u0000';
	            		}
	            	
	        			m++;
	            		str_array[m]=strbuilder.toString();
	            		//System.out.println(m+":"+str_array[m]);
	            		n=-1;
	            	    
	            	}
			}
			
			if(n!=-1)
			{
				StringBuilder strbuilder = new StringBuilder();
        		for(int i=0;i<=n;i++)
        		{
        			
        			strbuilder.append(chararr[i]);
        			chararr[i]='\u0000';
        		}
        	
    			m++;
        		str_array[m]=strbuilder.toString();
			}
			
			 for(int h=0;h<=m;h++)
	            {
	            	
		            	if(str_array[h].length() != 0)
		        		{ 
		            		w++;
		            		str_array[h]=str_array[h].toLowerCase();//修改为小写单词
		            		strr[w] = str_array[h];

		        		}
	            	
	            }
			for(int i=0;i<w;i++)
			{
				builder.append(strr[i]);
				builder.append(' ');
				temp = queryBridgeWords(strr[i],strr[i+1]);
				String array2[]=temp.split(" ");
				//System.out.println(i+strr[i]+"->"+strr[i+1]);
//				for(int k=0;k<array2.length;k++)
//				{
//					System.out.println(k+":="+array2[k]);
//				}
				if(temp.length()!=0)
				{
					Random ran = new Random();
					int q=ran.nextInt(array2.length);
					builder.append(array2[q]);
					builder.append(" ");
					fw.write("\r\n");
					fw.write(array2[q]);
					fw.write("[fillcolor=yellow,style=filled,Label=\"");
					fw.write(array2[q]);
					fw.write("\"];");
					
					fw.write('\t');
					fw.write(strr[i]);
					fw.write(" -> ");
					fw.write(array2[q]);
					fw.write(";\r\n");
					
					fw.write('\t');
					fw.write(array2[q]);
					fw.write(" -> ");
					fw.write(strr[i+1]);
					fw.write(";\r\n");
				}
				else
				{

					fw.write('\t');
					fw.write(strr[i]);
					fw.write(" -> ");
					fw.write(strr[i+1]);
					fw.write(";\r\n");
				}
							
			}
			builder.append(strr[w]);
			fw.write("}");
			fw.close();
			 String path = "dot Kdot -Tpng NewWord.dot -o NewWord.png";
		        try {
		        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}
	public String calcShortestPath(String word1,String word2)//计算两个单词之间的最短路径
	{
		if(g.Isingraph(word1)==0)
			return "我们的图里不存在这样的单词";
		if((!word2.equals(""))&&g.Isingraph(word2)==0)
			return "我们的图里不存在这样的单词";
		int x=g.Getpos(word1);
		int y = 0,muti=0;
		if(!word2.equals(""))
			y=g.Getpos(word2);
		int n=g.Getn();
		int flag;
		int[][] leastDis = new int[n+2][n+2];
		int[][] minPath =new int[n+2][n+2];
		StringBuilder builder = new StringBuilder();
		String returnarr;
		for(int i=0;i<=n;i++)
		{
			for(int j=0;j<=n;j++)
			{
				leastDis[i][j]=g.Getedge(i,j);
				minPath[i][j]=0;
			}
		}
		for(int k=0;k<=n;k++)
		{
			for(int i=0;i<=n;i++)
			{
				for(int j=0;j<=n;j++)
				{
					if(leastDis[i][k]+leastDis[k][j]<=leastDis[i][j])
					{
						leastDis[i][j]=leastDis[i][k]+leastDis[k][j];
						minPath[i][j]=k;
					}
				}
			}
		}

			if(!word2.equals(""))
			{
				String res=null;
				res=printminPath(x,y,minPath);
				String res1;
				if(t == 1 && g.Getedge(x,y)==100000)
				{
						return "不存在两个单词之间的路径";
				}
				else
				{
					builder.append(g.Getstr(x));
					builder.append(res);
				}
				String str1=builder.toString();
				int s0=minPath[x][y];
				for(int q=1;q<=n+1;q++)
				{
					if(q!=s0)
					{
						if(leastDis[x][q]+leastDis[q][y]==leastDis[x][y])
						{
							StringBuilder sb=new StringBuilder();
							mp.setLength(0);
							sb.append(g.Getstr(x));
							String res11=printminPath(x,q,minPath);
							mp.setLength(0);
							String res2=printminPath(q,y,minPath);
							sb.append(res11);
							sb.append(res2);
							String str2=sb.toString();
							if(!(str2.equals(str1)))
							{
								builder.append('\n');
								builder.append(str2);
							}
						}
					}
				}
				flag = 1;
			}
			else//单元
			{
				String mutipath = null;
				for(int j=1;j<=g.n;j++)
				{
					if(g.isRepeat(j)!=1)
					{
						mutipath=printminPath(x,j,minPath);
						if(!(t==1 && g.Getedge(x,j)==100000))
						{
							builder.append(g.Getstr(x));
							builder.append(mutipath);
							builder.append('\n');
							muti++;
							
						}
						mp.setLength(0);
						t=0;
					}
					
				}
				if(muti==0)
					return "从该点出发没有路";
				
				flag = 2;
			}
			
			returnarr = builder.toString();
			shortestphoto(returnarr,flag);
		return returnarr;
	}
	void shortestphoto(String str,int flag)
	{
		StringBuilder builder = new StringBuilder();
		int n=g.Getn();

			if(flag == 1)
			{
				try
				{
					String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\shortest2.dot";
					FileOutputStream fos = new FileOutputStream(gvout);
					OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
					fw.write("digraph G {");
					fw.write("\r\n");
					
					for(int i=1;i<=n;i++)
					{
						for(int j=1;j<=n;j++)
						{
							if(g.Getedge(i, j) != 100000)
							{
								fw.write('\t');
								fw.write(g.Getstr(i));
								fw.write(" -> ");
								fw.write(g.Getstr(j));
								fw.write(";\r\n");						
								//System.out.println(i+"，"+j+"："+g.Getstr(i)+"->"+g.Getstr(j));
							}
						}
						
					}
				
				String str_array[] = str.split("->");
				for(int i=0;i<str_array.length;i++)
				{
					
					fw.write("\r\n");
					fw.write(str_array[i]);
					fw.write("[fillcolor=red,style=filled,Label=\"");
					fw.write(str_array[i]);
					fw.write("\"];");
					fw.write("\r\n");
					
				}
				
				for(int i=0;i<str_array.length-1;i++)
				{
					
					fw.write('\t');
					fw.write(str_array[i]);
					fw.write(" -> ");
					fw.write(str_array[i+1]);
					fw.write("[color = \"blue\"]");
					fw.write(";\r\n");
					
				}
				
				fw.write("}");
				fw.close();
				 String path = "dot Kdot -Tpng shortest2.dot -o shortest2.png";
			        try {
			        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			else
			{
				try
				{
					String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\shortest.dot";
					FileOutputStream fos = new FileOutputStream(gvout);
					OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
					fw.write("digraph G {");
					fw.write("\r\n");
					
					for(int i=1;i<=n;i++)
					{
						for(int j=1;j<=n;j++)
						{
							if(g.Getedge(i, j) != 100000)
							{
								fw.write('\t');
								fw.write(g.Getstr(i));
								fw.write(" -> ");
								fw.write(g.Getstr(j));
								fw.write("[color = \"blue\"]");
								fw.write(";\r\n");						
								//System.out.println(i+"，"+j+"："+g.Getstr(i)+"->"+g.Getstr(j));
							}
						}
						
					}
				
					String str_array[] = str.split("\n");
					for(int i=0;i<str_array.length;i++)
					{
						String str_array1[] = str_array[i].split("->");
						for(int j=0;j<str_array1.length;j++)
						{
							fw.write("\r\n");
							fw.write(str_array1[j]);
							fw.write("[fillcolor=red,style=filled,Label=\"");
							fw.write(str_array1[j]);
							fw.write("\"];");
							fw.write("\r\n");
						}
					}
				
				
				fw.write("}");
				fw.close();
				 String path = "dot Kdot -Tpng shortest.dot -o shortest.png";
			        try {
			        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			

		
	}
	String printminPath(int i,int j,int minPath[][])
	{
		if(minPath[i][j]==0)
		{
			mp.append("->");
			mp.append(g.Getstr(j));
			t=t+1;
		}
		else
		{
			int k=minPath[i][j];
			printminPath(i,k,minPath);
			printminPath(k,j,minPath);
		}
		return mp.toString();
	}
	@SuppressWarnings("null")
	public String randomwalk()//随机游走
	{
		StringBuilder randomPath=new StringBuilder();
		int min=1;
		
		int s,p;
		int[][] putPath=new int[g.n+1][g.n+1];
		int[] calRep = new int[500];
		int cal=1;
		for(int w=1;w<g.n+1;w++)
		{
			if(g.isRepeat(w)!=1)
			{
				calRep[cal]=w;
				cal++;
			}
				
		}
		int max=cal;
		for(int i=0;i<=g.n;i++)
		{
			for(int j=0;j<g.n;j++)
				putPath[i][j]=0;
		}
		Random random=new Random();
		Random rep=new Random();
		String ranstr;
		s = random.nextInt(max-min)+ min;
		s=calRep[s];
		randomPath.append(g.Getstr(s));
		if(havePath(s)==0)
		{
			ranstr = randomPath.toString();
			showrandom(ranstr);
			return ranstr;
		}
			
		do {
			p= rep.nextInt(max-min)+min;
			p=calRep[p];
		}while(g.Getedge(s, p)==100000);
			//p=calRep[p];
			do{
				putPath[s][p]=1;
				s=p;
				randomPath.append("->");
				randomPath.append(g.Getstr(p));
				if(havePath(p)==0)
				{
					ranstr = randomPath.toString();
					showrandom(ranstr);
					return ranstr ;
				}
				do {
					p= rep.nextInt(max-min)+min;
					p=calRep[p];
				}while(g.Getedge(s, p)==100000);
			}while(putPath[s][p]==0);
			randomPath.append("->");
			randomPath.append(g.Getstr(p));
			ranstr = randomPath.toString();
			
		showrandom(ranstr);
		return ranstr;		
	}
	public void showrandom(String ranstr)
	{
		int n=g.Getn();
		try
		{
			String ranarray[] = ranstr.split("->");
			for(int w = 0;w<ranarray.length;w++)
			{
				System.out.println(w+"="+ranarray[w]);
			}
			String gvout ="F:\\\\Java\\\\eclipse_workspace\\\\lab1\\\\random.dot";
			FileOutputStream fos = new FileOutputStream(gvout);
			OutputStreamWriter fw = new OutputStreamWriter(fos,"UTF-8");
			fw.write("digraph G {");
			fw.write("\r\n");
			
			for(int i=1;i<=n;i++)
			{
				for(int j=1;j<=n;j++)
				{
					if((g.Getedge(i, j) != 100000))
					{
						fw.write('\t');
						fw.write(g.Getstr(i));
						fw.write(" -> ");
						fw.write(g.Getstr(j));
						fw.write(";\r\n");						
						
						
					}
				}
				
			}
		if(ranarray.length-1 > 0)
		{
			for(int i=0;i<ranarray.length-1;i++)
			{
				fw.write('\t');
				fw.write(ranarray[i]);
				fw.write(" -> ");
				fw.write(ranarray[i+1]);
				fw.write("[color = \"blue\"]");
				fw.write(";\r\n");	

			}
		}
		
		fw.write("\r\n"); 
		fw.write(ranarray[0]);
		 fw.write("[fillcolor=yellow,style=filled,Label=\"");
		 fw.write(ranarray[0]);
		 fw.write("\"];");
		 fw.write("\r\n");
		 if(ranarray.length > 0)
		 {
			 for(int i=1;i<ranarray.length-1;i++)
				{
					fw.write("\r\n"); 
					fw.write(ranarray[i]);
					 fw.write("[fillcolor=red,style=filled,Label=\"");
					 fw.write(ranarray[i]);
					 fw.write("\"];");
					 fw.write("\r\n");
					
				}
		 }
		
		 fw.write("\r\n"); 
		 fw.write(ranarray[ranarray.length-1]);
		 fw.write("[fillcolor=yellow,style=filled,Label=\"");
		 fw.write(ranarray[ranarray.length-1]);
		 fw.write("\"];");
		 fw.write("\r\n");
		 fw.write("}");
		 fw.close();
		 String path = "dot Kdot -Tpng random.dot -o random.png";
	        try {
	        	Runtime.getRuntime().exec(path,null,new File("F:\\Java\\eclipse_workspace\\lab1"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
		
	}
	public int havePath(int k)
	{
		int h=0;
		for(int i=0;i<=g.n;i++)
		{
			if(g.Getedge(k, i)!=100000)
				h++;
		}
		return h;
	}
	
	public static void main(String args[])
	{
		int time=0;
		System.out.println("show—展示有向图"
		+'\n'+"br+word1+word2—查询桥接词"+'\n'
				+"getnew—生成新文本"+'\n'
		+"shortest+word1—单源最短路径"+'\n'
				+"shortest+word1+word2—两点间最短路径"
		+'\n'+"random—随机游走");
		do{
		//展示有向图
			Graph g = new Graph();
			Text t = new Text(g);
			String commandLine;
			Scanner cmd=new Scanner(System.in);
			commandLine=cmd.nextLine();
			String[] cmd_array = new String[3] ;
			cmd_array=commandLine.split(" ");
			String word1,word2 = null,re;
			Scanner sc = new Scanner(System.in);
		if(cmd_array[0].equals("show"))
		{
			System.out.print('\n');
			t.showDirectedGraph(t.g.Getn());
		}
		else if(cmd_array[0].equals("br"))
		{
		//查询桥连接
		int a=0;
		for(int k=0;k<cmd_array.length;k++)
		{
			if(null!=cmd_array[k]) a++;
		}
		if(a!=3)
			System.out.println("非法输入！");
		else
		{
			word1=cmd_array[1];
			word2=cmd_array[2];			
			if((g.Isingraph(word1) == 0)&&(g.Isingraph(word2)==0))
			{
				System.out.println("No “"+word1+"” and “"+word2+"” in the graph!");
			}
			else if(g.Isingraph(word1) == 0)
			{
				System.out.println("No “"+word1+"” in the graph!");
			}
			else if(g.Isingraph(word2) == 0)
			{
				System.out.println("No “"+word2+"” in the graph!");
			}
			else
			{
				re= t.queryBridgeWords(word1, word2);
				if(re.length() == 0)
					System.out.println("No bridge words from “"+word1+"” to “"+word2+"!");
				else
				System.out.println("The bridge words from  “"+word1+"” to “"+word2+"” is:"+re);
			}
				
			  
			
		}
		}
		else if(cmd_array[0].equals("getnew"))
		{
		//根据bridge生成新文本
		int a=0;
		for(int k = 0; k < cmd_array.length; k++)
		{
			if(null != cmd_array[k]) a++;
		}
		if(a!=1)
			System.out.println("非法输入！");
		else{
		System.out.println("输入新文本");
		String inputText;
		String outputText;
		inputText = sc.nextLine();
		System.out.println("输出插入桥连接词文本");
        outputText = t.generateNewText(inputText);
		System.out.println(outputText);
		}
		}
		else if(cmd_array[0].equals("shortest"))
		{
			
			int a=0;
			String min;
		for(int k = 0; k < cmd_array.length; k++)
		{
			if(null != cmd_array[k]) a++;
		}
               if(a==2)
		{
            	   word1=cmd_array[1];
            	   word2="";
			min=t.calcShortestPath(word1,word2);
			System.out.println(min);
		}
		else if(a==3)
		{
			word1=cmd_array[1];
			word2=cmd_array[2];
			min=t.calcShortestPath(word1,word2);
			System.out.println(min);
		}
		else
			System.out.println("非法输入！");
		}
		
		//随机游走
		else if(cmd_array[0].equals("random"))
			{
				//System.out.println("随机游走：");
				int a=0;
				for(int k = 0; k < cmd_array.length; k++)
				{
					if(null != cmd_array[k]) a++;
				}
				if(a!=1)
				{
					System.out.println("非法输入！");
				}
				else 
				{
					String randompath;
					randompath=t.randomwalk();
					
					System.out.println(randompath);
				}
			}
			else if(cmd_array[0].equals("quit"))
				System.exit(0);
			else
				System.out.println("非法输入！");
		time++;
		
	}while(time!=50);
	}

}