/*
 * @LCSResult.java	@25 Aug 2014
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
public class LCSLongestCommonSubsequence {

	public static void main(String[] args) {
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		int m = str1.length();
		int n = str2.length();
		System.out.println("Length of LCS is n " + lcs(str1, str2, m, n));
	}

	public static Result lcs(String x, String y, int m, int n) {
		Result L[][] = new Result[m + 1][n + 1];
		int i, j;
		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that
		 * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */

		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = new Result("", 0);
				} else if (x.charAt(i - 1) == y.charAt(j - 1)) {
					L[i][j] = new Result();
					L[i][j].count = (L[i - 1][j - 1]).count + 1;
					L[i][j].match = L[i - 1][j - 1].match + x.charAt(i - 1);
				} else
					L[i][j] = max(L[i - 1][j], L[i][j - 1]);
			}
		}
		/* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
		return L[m][n];
	}

	public static Result max(Result a, Result b) {
		return (a.count > b.count) ? a : b;
	}
}

class Result {
	public String match;
	public int count;

	public Result() {
	}

	public Result(String s, int i) {
		match = s;
		count = i;
	}

	@Override
	public String toString() {
		return "match =" + match + " count= " + count;
	}
}
