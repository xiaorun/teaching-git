

import java.util.*;
import java.lang.Math;
import java.util.Random;
//=========================================================
//  随机生成数
//=========================================================
public class Random_Status
{
	
	int[][] starts_status = new int[3][3];
	int[] num = {0,1,2,3,4,5,6,7,8};
	int local = 0;
	int length = 9;
	
	
	public Random_Status()
	{	
		Random random = new Random();
		
		for(int i = 0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
			local = random.nextInt(length);			
			starts_status[i][j] = num[local];
			num[local] = num[length-1];
			length--;		
			}				
		}
	
	}		
}