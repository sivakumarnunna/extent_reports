package com.example.utils;

import java.io.File;

public class FileUtils {
	
	
	 public static boolean deleteDirectory(File directory) {
	     if (directory.isDirectory()) {
	         for (File f : directory.listFiles()) {
	             boolean success = deleteDirectory(f);
	             if (!success) {
	                 return false;
	             }
	         }
	     }
	     return directory.delete();
	 }
	 
	 public static void deleteFiles(File directory, String prefix) {
	     if (directory.isDirectory()) {
	         for (File f : directory.listFiles()) {
	             if (f.getName().startsWith(prefix)) {
	                 deleteDirectory(f);
	             }
	         }
	     }
	 }
	 public static String getFile(File directory, String regex) {
	     if (directory.isDirectory()) {
	         for (File f : directory.listFiles()) {
	             if (f.getName().contains(regex)) {
	            	 return f.getName();
	             }
	         }
	     }
	    return "no file/folder found";
	 }
	 

}
