package com.practice.algo.java;

public class LaunchingExternalApps
{
    public static void main(String[] args) throws Exception
    {
        Runtime runtime = Runtime.getRuntime();     //getting Runtime object

        Process process = runtime.exec("notepad I:\\sample.txt");        //opens "sample.txt" in notepad

        Thread.sleep(5000);

        process.destroy();
    }
}