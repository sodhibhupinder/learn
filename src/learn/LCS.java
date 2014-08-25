/*
 * @LCS.java	@25 Aug 2014
 *
 * Copyright (c) 2014 SODHI. 
 * All rights reserved. 
 * 
 * No part of this document may be reproduced or transmitted in any form or by 
 * any means, electronic or mechanical, whether now known or later invented, 
 * for any purpose without the prior and express written consent of SODHI 
 * 
 */
package learn;

/**
 * @author KP84
 * @25 Aug 2014
 */
public class LCS {

	// private static int minLength;
	// private static int lineNumber;

	private static int lineNumber=0;
	private static int lineNumber2=0;


	public static void main(String[] args) {

		// String str1 = args[0];
		// String str2 = args[1];

		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";

		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();

		int m = charArr1.length;
		int n = charArr2.length;

		System.out.println("Length of LCS is computer by naive recursion is "
				+ lcs(charArr1, charArr2, m, n));
		
		System.out.println("Length of LCS computed by lcsdp is "
				+ lcsDP(charArr1, charArr2, m, n));
	}

	/* A Naive recursive implementation of LCS problem */
	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	private static int lcs(char[] X, char[] Y, int m, int n) {
		System.out.println("LineNumber#"+ lineNumber2++);
		if (m == 0 || n == 0)
			return 0;
		if (X[m - 1] == Y[n - 1])
			return 1 + lcs(X, Y, m - 1, n - 1);
		else
			return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
	}

	/**
	 * @param lcs
	 * @param lcs2
	 * @return
	 */
	private static int max(int a, int b) {

		return (a > b) ? a : b;
	}

	
	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	/* Dynamic Programming implementation of LCS problem - tabulated implementation*/
	private static int lcsDP(char[] X, char[] Y, int m, int n )
	{
	   int[][] L=new int[m+1][n+1];
	   int i, j;
	  
	   /* Following steps build L[m+1][n+1] in bottom up fashion. Note 
	      that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
	   for (i=0; i<=m; i++)
	   {
	     for (j=0; j<=n; j++)
	     {
	    	 System.out.println("LineNumber#"+lineNumber++);
	       if (i == 0 || j == 0)
	         L[i][j] = 0;
	  
	       else if (X[i-1] == Y[j-1])
	         L[i][j] = L[i-1][j-1] + 1;
	  
	       else
	         L[i][j] = max(L[i-1][j], L[i][j-1]);
	     }
	   }
	    
	   printLCS(L,X,Y,m,n);
	   /* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
	   return L[m][n];
	}
	
	
	
	// Following code is used to print LCS
	private static void printLCS(int[][] L,char[] X, char[] Y,int m ,int n)
	{
	   int index = L[m][n];
	 
	   // Create a character array to store the lcs string
	   char[] lcs = new char[index+1];
	   lcs[index] = '\0'; // Set the terminating character
	 
	   // Start from the right-most-bottom-most corner and
	   // one by one store characters in lcs[]
	   int i = m, j = n;
	   while (i > 0 && j > 0)
	   {
	      // If current character in X[] and Y are same, then
	      // current character is part of LCS
	      if (X[i-1] == Y[j-1])
	      {
	          lcs[index-1] = X[i-1]; // Put current character in result
	          i--; j--; index--;     // reduce values of i, j and index
	      }
	 
	      // If not same, then find the larger of two and
	      // go in the direction of larger value
	      else if (L[i-1][j] > L[i][j-1])
	         i--;
	      else
	         j--;
	   }
		System.out.println("LCS of " + new String(X) + " and " + new String(Y)
				+ " is " + new String(lcs));
	}
}
