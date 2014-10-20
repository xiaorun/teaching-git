
/*
 *版本：八数码v1.0
 *学校：厦门大学嘉庚学院07级 计算机科学与技术系 计算机班
 *开发组员：江伟坚，林昕，陈东虹，郑毅
 *指导老师：康凯
 *联系QQ：116411600  邮箱：kongwaigin@hotmail.com    cst07048@stu.xujc.com
 */
 
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.lang.Math;
import java.util.Random;

//=======================================================================================
//         结点结构
//======================================================================================= 
 
class Node 
{  //定义NODE 结构
	int Id;//当前结点ID
	int[][] arr=new int[3][3];
	int FatherId;      //老爸的ID
	int g;//深度
	int f;//权值
	int h;//曼哈顿
	long hash;//哈希值

	public  Node(int Id,int FatherId,int[][] arr,int f,int g,int h,long hash) 
	{//结点参数包括这5个内容，做到的事情就是赋值
	 	 this.Id=Id;
		 this.FatherId=FatherId;
		 
		 for(int i=0;i<=2;i++)
		 {
		 	for(int j=0;j<=2;j++)
		    this.arr[i][j]=arr[i][j];
		 }
		 
		 this.f=f;
		 this.g=g;
		 this.h=h;
		 this.hash = hash;
	}
}
 
 
 
 
 
 

public class EightNumber 
{
	public  Vector open=new Vector(1,1);
	public  Vector close=new Vector(1,1);
	public  int current_deepth=0;//深度
	Vector Result=new Vector(1,1);//初始化放结果路径的Result表



public  Vector process(int[][] Source,int[][] Dest)
{
      boolean flag=true;
             
      Node Father;                //根据父节点找最佳路径
      
      int count=0;//记录表中有几个结点
      
      long start_hash =hashValue(Source);//开始节点的哈希值
      long end_hash =hashValue(Dest);//终点的哈希值

      Node source=new Node(0,0,Source,0,0,0,0); //定义初始结点5个变量：ID，老爸，源数组，F权值,G深度,H不同于目标的个数
      Node dest=new Node(0,0,Dest,0,0,0,0);
      
      source.h=caculatemove(source,dest);//计算起始到目标结点的不同个数
      source.f=source.g+source.h;//权值的计算
      
    
      push(source,open);//把第一个，也就是初始化的结点放入OPEN表
      Node current_node=source;
      
   	  while(flag)
      {   
                     //OPEN表为空退出
	      if(open.isEmpty())
	      {
	           flag=false;
	           break;
	      }
	      
	      
	      current_node=pop(open);//取出OPEN表的元素

	      push(current_node,close);
	    
	      
	      if (judgesame(current_node, dest)) //找到目标退出，括号里面是个判断函数，当前与目标是不是相等
	      {   	      
               System.out.println("恭喜，找到目标结点路径了");
	           break;
	      }
      	
	      
	      	  //放到CLOSE表里面
	       
	      extendnode(current_node,dest);//否则扩展结点

	      QueueOpen();
      }
   
   
      if(flag)
      {
        Result.add(current_node);        
        int m=current_node.FatherId;//当前ID的老爸的ID放m里面
        while(m>0)
        {
          Father=Found_list(m,close,source);
          m=Father.FatherId;
          Result.add(Father);
          count++;
        }
      }
      
      Result.add(source);//最初结点也要放进去
      
      return Result;              //即为结果
      

}


//=======================================================================================
//          寻找目标路径结点
//======================================================================================= 

public   Node Found_list(int k,Vector vec,Node Source)//在CLOSE表中ID号相同的NODE
{ 
   Node result=Source;
   int i;
   
   for(i=0;i<vec.size();i++)//当小于CLOSE表中个数推出循环
   {
     if(k==((Node)vec.get(i)).Id)//找到一个点的ID相同
     {
     	result=(Node)vec.get(i);//把那个结点给RESULT
     }
  }
  return result;
}

//=======================================================================================
//         判断是不是目标结点
//======================================================================================= 

public  boolean judgesame(Node s1,Node s2)//判断找到的NODE是否是目标NODE
{       
    int[][] arr=new int[3][3];
    for(int i=0;i<=2;i++)
    {
      for(int j=0;j<=2;j++)
      {
          if(!(s1.arr[i][j]==s2.arr[i][j]))
          return false;
      }
    }
    return true;
}



//=======================================================================================
//         OPEN表的PUSH操作和POP操作
//======================================================================================= 
 
  public  void push(Node node,Vector open)
  { 
    //将NODE加进OPEN表
    open.add(node);
  }

  public  Node pop(Vector open)
  {  //将OPEN表的第一个NODE弹出
    Node node=(Node)open.get(0);
    open.removeElementAt(0);
    return node;
  }


//=======================================================================================
//          结点的扩展
//=======================================================================================

public  void extendnode(Node current_node,Node des)
{  	 	

  //将OPEN表中第一个NODE 扩展
	  int m=0;
	  int n=0;
	  int i=0;
	  int j=0;
	  int zeropos=0;
 //找到0的位置，m和n   M是横坐标，n是纵坐标 =========
   for(i=0;i<=2;i++)
   { 
    for(j=0;j<=2;j++)
    {
      if(current_node.arr[i][j]==0)
      {
	      m=i;
	      n=j;
      }
    }
   }
   zeropos=3*m+n;
  


 //以下分四个方向扩展：上、下、左、右，并加如OPEN表中



//=====================================================================================
//             0向上
//=====================================================================================
 if(zeropos/3-1>=0)
 { 
 	      
     extend_node(current_node,des,m,n,m-1,n);
   	    	           
 }



//=====================================================================================
//             0向下
//=====================================================================================

 if(zeropos/3+1<3)
 { 
	extend_node(current_node,des,m,n,m+1,n);
 }



//=====================================================================================
//             0向左
//=====================================================================================

 if(zeropos%3-1>=0)
{ 
		extend_node(current_node,des,m,n,m,n-1);
}



//=====================================================================================
//             0向右
//=====================================================================================

 if(zeropos%3+1<3)
{ 
	extend_node(current_node,des,m,n,m,n+1);
}


}



//=======================================================================================
//          结点的扩展方向处理
//=======================================================================================


public void extend_node(Node current_node,Node des,int m,int n,int change_row,int change_col)
{
		int i=0;
		int j=0;
		
		int[][] arr=new int[3][3];
		boolean flag=true;
		Node pnewnode=new Node(0,0,arr,0,0,0,0);
		
       	
		for(i=0;i<=2;i++)
		{
			for(j=0;j<=2;j++)
			{
				pnewnode.arr[i][j]=current_node.arr[i][j];
				pnewnode.arr[m][n]=current_node.arr[change_row][change_col];  //0向上，只要改变横坐标
				pnewnode.arr[change_row][change_col]=0;
			}		
		}	 
		
			long pnewnode_hash =hashValue(pnewnode.arr);
        	pnewnode.hash = pnewnode_hash;   
 		    
 		    //node_num++;
 		    //System.out.println("扩展了"+node_num);
 		    
 		     int  k=0;

             while(k<close.size())
             {
                 // System.out.println("标记1");  
                  flag=false;           		
                if(pnewnode.hash==((Node)close.get(k)).hash)
                {
                	flag=true;
                	break;
                }
                
                //if(flag==false) break;
             k++;
             }
             
         

	          if(!flag)  //没发现相同的，就进行更新赋值
	           {
	               pnewnode.FatherId=current_node.Id;	               
	               current_deepth=current_deepth+1;//深度加1	               
	               pnewnode.Id=current_deepth;
	               pnewnode.g=current_node.g+1;
	               pnewnode.h=caculatemove(pnewnode,des);
	               pnewnode.f=pnewnode.g+pnewnode.h;
	               pnewnode.hash = pnewnode_hash;
	               open.add(pnewnode);
	           }
}




//=======================================================================================
//          进行OPEN表排序操作
//=======================================================================================

public  void QueueOpen()
{       
     
 //======OPEN表排序，只用找F值最小的设为第一个NODE============
      
     int min_flag=0;//最小的位置
     int[][] arr=new int[3][3];//初始化一个2维数组放结点
     Node temp=new Node(0,0,arr,0,0,0,0);//初始化一个结点结构，都是0，当然只是初始化
     Node node_flag=(Node)open.get(0);//把OPEN表的第一个拿出来赋值给node_flag 	
     int minvalue=node_flag.f;//然后看看第一个的权值，暂且放MINVALUE，把第一个默认是最小的，然后进行更新
     
   
    for(int i=0;i<open.size();i++)
    {//开始比较了   
       Node opei=(Node)open.get(i);//这里的get只是查看，所以没有修改OPEN表，我们目的是看哪个最小所以不要POP         
       if(opei.f<minvalue)    //找到一个比第一个更小的
       {      
         minvalue=opei.f;//来了，进行更新了
         min_flag=i;//记录这个最小的位置在OPEN表的哪个地方
       }
    }


      temp=(Node)open.get(0);//把OPEN表的第一个放到temp里面===也就是先记录第一个结点    
      open.setElementAt((Node)open.elementAt(min_flag),0);//好好讲下这个函数方法了
      //这个括号里面什么意思呢？  就是把OPEN表里面min_flag我们记录的那个结点放到第一个位置（0代表OPEN第一个），前面<NODE>指明
      //了他是一个结点，是不是很明了了？  
      open.setElementAt(temp,min_flag);//然后这个干嘛用呢？你把那个第一个放了最小的，我这个原本第一个放哪里？
      //当然是放到那个min_flag的位置啦
      
  }



//=======================================================================================
//          进行H的计算，H就是不同目标结点的个数，这里采用哈夫曼距离，效率140
//=======================================================================================
 
  
  public  int caculatemove(Node nod,Node destin)
 {
 	 
   
	int count=0;
	for(int i=0;i<=2;i++)
	for(int j=0;j<=2;j++)
	for(int g=0;g<=2;g++)
	for(int k=0;k<=2;k++)
	{   
		if(nod.arr[i][j]==destin.arr[g][k])       //a[][]为初始的，b[][]为目标
		{
			count+=Math.abs(i-g)+Math.abs(j-k);
		}
		
	        	
	} 
 	return count;
 }
    

   
	
//===================================================================
//随机生成数码
//===================================================================	
	
	public static int[] randnumber()
	{
		int[] rand = new int[9];
		int[] num = {0,1,2,3,4,5,6,7,8};
		int local = 0;
		int length = 9;
		
		Random random = new Random();
		for(int i = 0;i<9;i++)
		{
			local = random.nextInt(length);			
			rand[i] = num[local];
			num[local] = num[length-1];
			length--;						
		}
		return  rand;
	}
    
    
//===================================================================
//          哈希值计算（详细看配套报告算法）
//===================================================================	    
public long hashValue(int [][]arr)
{
	int one[]=new int[9];
	int opp[]=new int[9];
	int n=0;
	int k=0;
	int pos=0;
	int count=0;
	long hashvalue=0;
	long jiechen[] ={1,1,2,6,24,120,720,5040,40320,362880};
	
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			one[n]=arr[i][j];
			if(one[n]==0)
				pos=n;
			n++;
			
		}
	}
	
	for(int i=0;i<9;i++)
	{
		for(int j=0;j<i;j++)
		{
			if(one[i]<one[j])
			{
				count++;
			
			}
		
		}
		opp[k]=count;
		k++;
		count=0;
	}
	
	for(int i=0;i<9;i++)
	{
		if(i==pos)
		hashvalue+=(9-pos)*jiechen[pos];
		else
		hashvalue+=opp[i]*jiechen[i];
	}
	return hashvalue;
		
}
    
    
     

}







