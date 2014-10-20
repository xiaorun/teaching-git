
/*
 *�汾��������v1.0
 *ѧУ�����Ŵ�ѧ�θ�ѧԺ07�� �������ѧ�뼼��ϵ �������
 *������Ա����ΰ�ᣬ��꿣��¶��磬֣��
 *ָ����ʦ������
 *��ϵQQ��116411600  ���䣺kongwaigin@hotmail.com    cst07048@stu.xujc.com
 */
 
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.lang.Math;
import java.util.Random;

//=======================================================================================
//         ���ṹ
//======================================================================================= 
 
class Node 
{  //����NODE �ṹ
	int Id;//��ǰ���ID
	int[][] arr=new int[3][3];
	int FatherId;      //�ϰֵ�ID
	int g;//���
	int f;//Ȩֵ
	int h;//������
	long hash;//��ϣֵ

	public  Node(int Id,int FatherId,int[][] arr,int f,int g,int h,long hash) 
	{//������������5�����ݣ�������������Ǹ�ֵ
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
	public  int current_deepth=0;//���
	Vector Result=new Vector(1,1);//��ʼ���Ž��·����Result��



public  Vector process(int[][] Source,int[][] Dest)
{
      boolean flag=true;
             
      Node Father;                //���ݸ��ڵ������·��
      
      int count=0;//��¼�����м������
      
      long start_hash =hashValue(Source);//��ʼ�ڵ�Ĺ�ϣֵ
      long end_hash =hashValue(Dest);//�յ�Ĺ�ϣֵ

      Node source=new Node(0,0,Source,0,0,0,0); //�����ʼ���5��������ID���ϰ֣�Դ���飬FȨֵ,G���,H��ͬ��Ŀ��ĸ���
      Node dest=new Node(0,0,Dest,0,0,0,0);
      
      source.h=caculatemove(source,dest);//������ʼ��Ŀ����Ĳ�ͬ����
      source.f=source.g+source.h;//Ȩֵ�ļ���
      
    
      push(source,open);//�ѵ�һ����Ҳ���ǳ�ʼ���Ľ�����OPEN��
      Node current_node=source;
      
   	  while(flag)
      {   
                     //OPEN��Ϊ���˳�
	      if(open.isEmpty())
	      {
	           flag=false;
	           break;
	      }
	      
	      
	      current_node=pop(open);//ȡ��OPEN���Ԫ��

	      push(current_node,close);
	    
	      
	      if (judgesame(current_node, dest)) //�ҵ�Ŀ���˳������������Ǹ��жϺ�������ǰ��Ŀ���ǲ������
	      {   	      
               System.out.println("��ϲ���ҵ�Ŀ����·����");
	           break;
	      }
      	
	      
	      	  //�ŵ�CLOSE������
	       
	      extendnode(current_node,dest);//������չ���

	      QueueOpen();
      }
   
   
      if(flag)
      {
        Result.add(current_node);        
        int m=current_node.FatherId;//��ǰID���ϰֵ�ID��m����
        while(m>0)
        {
          Father=Found_list(m,close,source);
          m=Father.FatherId;
          Result.add(Father);
          count++;
        }
      }
      
      Result.add(source);//������ҲҪ�Ž�ȥ
      
      return Result;              //��Ϊ���
      

}


//=======================================================================================
//          Ѱ��Ŀ��·�����
//======================================================================================= 

public   Node Found_list(int k,Vector vec,Node Source)//��CLOSE����ID����ͬ��NODE
{ 
   Node result=Source;
   int i;
   
   for(i=0;i<vec.size();i++)//��С��CLOSE���и����Ƴ�ѭ��
   {
     if(k==((Node)vec.get(i)).Id)//�ҵ�һ�����ID��ͬ
     {
     	result=(Node)vec.get(i);//���Ǹ�����RESULT
     }
  }
  return result;
}

//=======================================================================================
//         �ж��ǲ���Ŀ����
//======================================================================================= 

public  boolean judgesame(Node s1,Node s2)//�ж��ҵ���NODE�Ƿ���Ŀ��NODE
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
//         OPEN���PUSH������POP����
//======================================================================================= 
 
  public  void push(Node node,Vector open)
  { 
    //��NODE�ӽ�OPEN��
    open.add(node);
  }

  public  Node pop(Vector open)
  {  //��OPEN��ĵ�һ��NODE����
    Node node=(Node)open.get(0);
    open.removeElementAt(0);
    return node;
  }


//=======================================================================================
//          ������չ
//=======================================================================================

public  void extendnode(Node current_node,Node des)
{  	 	

  //��OPEN���е�һ��NODE ��չ
	  int m=0;
	  int n=0;
	  int i=0;
	  int j=0;
	  int zeropos=0;
 //�ҵ�0��λ�ã�m��n   M�Ǻ����꣬n�������� =========
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
  


 //���·��ĸ�������չ���ϡ��¡����ң�������OPEN����



//=====================================================================================
//             0����
//=====================================================================================
 if(zeropos/3-1>=0)
 { 
 	      
     extend_node(current_node,des,m,n,m-1,n);
   	    	           
 }



//=====================================================================================
//             0����
//=====================================================================================

 if(zeropos/3+1<3)
 { 
	extend_node(current_node,des,m,n,m+1,n);
 }



//=====================================================================================
//             0����
//=====================================================================================

 if(zeropos%3-1>=0)
{ 
		extend_node(current_node,des,m,n,m,n-1);
}



//=====================================================================================
//             0����
//=====================================================================================

 if(zeropos%3+1<3)
{ 
	extend_node(current_node,des,m,n,m,n+1);
}


}



//=======================================================================================
//          ������չ������
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
				pnewnode.arr[m][n]=current_node.arr[change_row][change_col];  //0���ϣ�ֻҪ�ı������
				pnewnode.arr[change_row][change_col]=0;
			}		
		}	 
		
			long pnewnode_hash =hashValue(pnewnode.arr);
        	pnewnode.hash = pnewnode_hash;   
 		    
 		    //node_num++;
 		    //System.out.println("��չ��"+node_num);
 		    
 		     int  k=0;

             while(k<close.size())
             {
                 // System.out.println("���1");  
                  flag=false;           		
                if(pnewnode.hash==((Node)close.get(k)).hash)
                {
                	flag=true;
                	break;
                }
                
                //if(flag==false) break;
             k++;
             }
             
         

	          if(!flag)  //û������ͬ�ģ��ͽ��и��¸�ֵ
	           {
	               pnewnode.FatherId=current_node.Id;	               
	               current_deepth=current_deepth+1;//��ȼ�1	               
	               pnewnode.Id=current_deepth;
	               pnewnode.g=current_node.g+1;
	               pnewnode.h=caculatemove(pnewnode,des);
	               pnewnode.f=pnewnode.g+pnewnode.h;
	               pnewnode.hash = pnewnode_hash;
	               open.add(pnewnode);
	           }
}




//=======================================================================================
//          ����OPEN���������
//=======================================================================================

public  void QueueOpen()
{       
     
 //======OPEN������ֻ����Fֵ��С����Ϊ��һ��NODE============
      
     int min_flag=0;//��С��λ��
     int[][] arr=new int[3][3];//��ʼ��һ��2ά����Ž��
     Node temp=new Node(0,0,arr,0,0,0,0);//��ʼ��һ�����ṹ������0����Ȼֻ�ǳ�ʼ��
     Node node_flag=(Node)open.get(0);//��OPEN��ĵ�һ���ó�����ֵ��node_flag 	
     int minvalue=node_flag.f;//Ȼ�󿴿���һ����Ȩֵ�����ҷ�MINVALUE���ѵ�һ��Ĭ������С�ģ�Ȼ����и���
     
   
    for(int i=0;i<open.size();i++)
    {//��ʼ�Ƚ���   
       Node opei=(Node)open.get(i);//�����getֻ�ǲ鿴������û���޸�OPEN������Ŀ���ǿ��ĸ���С���Բ�ҪPOP         
       if(opei.f<minvalue)    //�ҵ�һ���ȵ�һ����С��
       {      
         minvalue=opei.f;//���ˣ����и�����
         min_flag=i;//��¼�����С��λ����OPEN����ĸ��ط�
       }
    }


      temp=(Node)open.get(0);//��OPEN��ĵ�һ���ŵ�temp����===Ҳ�����ȼ�¼��һ�����    
      open.setElementAt((Node)open.elementAt(min_flag),0);//�úý����������������
      //�����������ʲô��˼�أ�  ���ǰ�OPEN������min_flag���Ǽ�¼���Ǹ����ŵ���һ��λ�ã�0����OPEN��һ������ǰ��<NODE>ָ��
      //������һ����㣬�ǲ��Ǻ������ˣ�  
      open.setElementAt(temp,min_flag);//Ȼ������������أ�����Ǹ���һ��������С�ģ������ԭ����һ�������
      //��Ȼ�Ƿŵ��Ǹ�min_flag��λ����
      
  }



//=======================================================================================
//          ����H�ļ��㣬H���ǲ�ͬĿ����ĸ�����������ù��������룬Ч��140
//=======================================================================================
 
  
  public  int caculatemove(Node nod,Node destin)
 {
 	 
   
	int count=0;
	for(int i=0;i<=2;i++)
	for(int j=0;j<=2;j++)
	for(int g=0;g<=2;g++)
	for(int k=0;k<=2;k++)
	{   
		if(nod.arr[i][j]==destin.arr[g][k])       //a[][]Ϊ��ʼ�ģ�b[][]ΪĿ��
		{
			count+=Math.abs(i-g)+Math.abs(j-k);
		}
		
	        	
	} 
 	return count;
 }
    

   
	
//===================================================================
//�����������
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
//          ��ϣֵ���㣨��ϸ�����ױ����㷨��
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







