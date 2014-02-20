package com.belcci.carnet.builder;

public class PDFBuilderFactory {
	  public static final String PAGE_MAIN = "Main";
	  public static final String PAGE_MAINBACK = "MainBack";
	  public static final String PAGE_MAINADDITIONAL = "MainAdditional";
	  public static final String PAGE_MAINADDITIONALBACK = "MainAdditionalBack";
	  public static final String PAGE_REIMPORT = "Reimport";
	  public static final String PAGE_REIMPORTBACK = "ReimportBack";
	  public static final String PAGE_REIMPORTADDITIONAL = "ReimportAdditional";
	  public static final String PAGE_REIMPORTADDITIONALBACK = "ReimportAdditionalBack";
	  public static final String PAGE_REEXPORT = "Reexport";
	  public static final String PAGE_REEXPORTBACK = "ReexportBack";
	  public static final String PAGE_REEXPORTADDITIONAL = "ReexportAdditional";
	  public static final String PAGE_REEXPORTADDITIONALBACK = "ReexportAdditionalBack";
	  public static final String PAGE_TRANSIT = "Transit";
	  public static final String PAGE_TRANSITBACK = "TransitBack";
	  public static final String PAGE_TRANSITADDITIONAL = "TransitAdditional";
	  public static final String PAGE_TRANSITADDITIONALBACK = "TransitAdditionalBack";
	  
      public static PDFBuilder getPADFBuilder(String pagename) {
    	  PDFBuilder builder = null; 
    	  
    	  if (pagename.equals(PAGE_MAIN)) {
    		  builder = new MainPDFBuilder();
    	  } else if (pagename.equals(PAGE_MAINBACK)) {
    		  builder = new MainBackPDFBulder();    		  
    	  } else if (pagename.equals(PAGE_MAINADDITIONAL)) {
    		  builder = new MainAdditionalPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_MAINADDITIONALBACK)) {
    		  builder = new MainAdditionalBackPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_REIMPORT)) {
    		  builder = new VoucherPDFBuilder();
    	  } else if (pagename.equals(PAGE_REIMPORTBACK)) {
    		  builder = new VoucherBackPDFBulder();    		  
    	  } else if (pagename.equals(PAGE_REIMPORTADDITIONAL)) {
    		  builder = new VoucherAdditionalPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_REIMPORTADDITIONALBACK)) {
    		  builder = new VoucherAdditionalBackPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_REEXPORT)) {
    		  builder = new VoucherPDFBuilder();
    	  } else if (pagename.equals(PAGE_REEXPORTBACK)) {
    		  builder = new VoucherBackPDFBulder();    		  
    	  } else if (pagename.equals(PAGE_REEXPORTADDITIONAL)) {
    		  builder = new VoucherAdditionalPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_REEXPORTADDITIONALBACK)) {
    		  builder = new VoucherAdditionalBackPDFBuilder();    		
    	  } else if (pagename.equals(PAGE_TRANSIT)) {
    		  builder = new VoucherPDFBuilder();
    	  } else if (pagename.equals(PAGE_TRANSITBACK)) {
    		  builder = new VoucherBackPDFBulder();    		  
    	  } else if (pagename.equals(PAGE_TRANSITADDITIONAL)) {
    		  builder = new VoucherAdditionalPDFBuilder();    		  
    	  } else if (pagename.equals(PAGE_TRANSITADDITIONALBACK)) {
    		  builder = new VoucherAdditionalBackPDFBuilder();    		
    	  }
    	  
    	  return builder;
      }
}
