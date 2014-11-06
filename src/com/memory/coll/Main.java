package com.memory.coll;
import java.math.BigInteger;
public class Main
{
    public static void main(String[] args)
    {
        int m = 3;
        int n = 2;
        BigInteger dp[][] = new BigInteger[m+1][n+1];
        for(int i = 0; i <= m; ++i)
        {
            for(int j = 0; j <= n; ++j)
            {
                dp[i][j] = BigInteger.ZERO;
            }
        }
        dp[1][1] = BigInteger.ONE;
        for(int i = 1; i <= m; ++i)
        {
            for(int j = 1; j <= n; ++j)
            {
                if(dp[i][j].equals(BigInteger.ZERO))
                    dp[i][j] = dp[i-1][j].add(dp[i][j-1]);
            }
        }
        System.out.println(dp[m][n]);
//        System.out.println(dfs(dp, m, n));
    }
    
    public static int dfs(int dp[][], int x, int y)
    {
        if(x == 0 || y == 0 || dp[x][y] > 0)
            return dp[x][y];
        return dp[x][y] = dfs(dp, x-1, y) + dfs(dp, x, y-1);
    }
}