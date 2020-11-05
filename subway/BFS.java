package subway;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	public void bfs(int s,int t,Graph graph) {
		if(s<0||t<0) {
			return;
		}
		if(s==t) {
			System.out.println("��վ����ͬ");
			return;
		}
		int v=graph.getV();
		LinkedList<Integer>[] adj=graph.getAdj();
		boolean[] visited = new boolean[v];
		visited[s] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		int[] prev = new int[v];
		for(int i=0; i<v;i++) {
			prev[i] = -1;
		}
		while(queue.size() != 0) {
			int w =queue.poll();
			for(int i=0; i<adj[w].size();++i) {
				int q = adj[w].get(i);
				while(!visited[q]) {
					prev[q] = w;
					if(q == t) {
						print(prev,s,t);
						return;
					}
					visited[q] = true;
					queue.add(q);
				}
			}
		}
	}
	public void print(int[] prev, int s,int t) {
		int tt=t;
		int num=2;
		Stack<Integer> stack=new Stack<Integer>();
		while(prev[t]!=-1&&s!=t){
			stack.push(prev[t]);
			t=prev[t];
		}
		List<String> line = new ArrayList<String>();
		while(!stack.empty()){
			int e=stack.pop();
			String name=new init().station_name.get(e);
			line.add(name);
		}
		String name=new init().station_name.get(tt);
		line.add(name);
		System.out.print(line.get(0));
		for(int i=1;i<line.size()-1;i++) {
			int x=transfer(line.get(i-1),line.get(i));
			int y=transfer(line.get(i),line.get(i-1));
			int x1=transfer(line.get(i),line.get(i+1));
			int y1=transfer(line.get(i+1),line.get(i));
			if(x<=0) {
				x=y;
			}
			if(x1<=0) {
				x1=y1;
			}
			if(x==x1) {
				System.out.print("->"+line.get(i));
				num++;
			}
			else {
				System.out.print("->"+line.get(i)+"(����"+num(x1)+")");
				num++;
			}
		}
		System.out.print("->"+line.get(line.size()-1));
		System.out.println();
		System.out.println("��"+num+"վ");
	}
	public int transfer(String n,String m) {
		int p=-1;
		int flag=0;
		int o=0;
		for(List<String> lline:new init().lineSet) {
			for(int i=0;i<lline.size()-1;i++) {
				if(n.equals(lline.get(i))&&m.equals(lline.get(i+1))||
				   n.equals(lline.get(0))&&m.equals(lline.get(lline.size()-1))||
				   m.equals(lline.get(0))&&n.equals(lline.get(lline.size()-1))) {
					p=o;
					flag=1;
					break;
				}
			}
			if (flag==1) break;
			o++;
		}
		return p;
	}
	public String num(int p) {
		String n=null;
		switch(p) {
		case 0:n=("1����");break;
		case 1:n=("2����");break;
		case 2:n=("4����");break;
		case 3:n=("������");break;
		case 4:n=("5����");break;
		case 5:n=("6����");break;
		case 6:n=("7����");break;
		case 7:n=("8���߱�");break;
		case 8:n=("8������");break;
		case 9:n=("9����");break;
		case 10:n=("10����");break;
		case 11:n=("13����");break;
		case 12:n=("14������");break;
		case 13:n=("14���߶�");break;
		case 14:n=("15����");break;
		case 15:n=("16����");break;
		case 16:n=("��ͨ��");break;
		case 17:n=("��ɽ��");break;
		case 18:n=("��ƽ��");break;
		case 19:n=("��ׯ��");break;
		case 20:n=("�෿��");break;
		case 21:n=("S1��");break;
		case 22:n=("������");break;
		case 23:n=("�׶�������");break;
		}
		return n;
	}
}
