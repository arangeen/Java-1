package com.company;

public class TripleString {
    public static final int MAX_Len = 20;
    String string1;
    String string2;
    String string3;

    TripleString()
    {
        string1 = "";
        string2 = "";
        string3 = "";
    }
    boolean validString( String str )
    {
        if (str!=null && str.length() <= MAX_Len) {
            return true;
        } else {
            return false;
        }
    }

    public void setString1(String S1)
    {
        string1 = S1;
    }
    public void setString2(String S2)
    {
        string2 = S2;
    }
    public void setString3(String S3)
    {
        string3 = S3;
    }
    public String gettingString1()
    {
        return string1;
    }
    public String gettingString2()
    {
        return string2;
    }
    String gettingString3()
    {
        return string3;
    }
}
