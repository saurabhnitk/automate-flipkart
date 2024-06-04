import javax.swing.text.Utilities;

import com.google.common.annotations.VisibleForTesting;

public class TestCases {

    public void setup(){
        
    }

    @Test(priority=0,description="Search Washing Machine.Sort by popularity and print the count of items with rating less than or equal to 4 stars")
    public void testCase01() throws InterruptedException{
        System.out.println("Beginning Test Case 01");
        String sortByString = "Popularity";
        double starRating = 4.3;
        //Go to Flipkart
        driver.get("https://www.flipkart.com/");
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        Boolean flow1Result = Utilities.sendKeysWrapper(driver,By.xpath("//button[@aria-label='Search for Products, Brands and More']/../div/input"),"Washing Machine");
        if(flow1Result){
            System.out.println("Flow 1 success");
        }else{
            System.out.println("Failure in flow 1");
        }
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        //Sort by popularity
        Boolean flow2Result = Utilities.clickWrapper(driver,By.xpath("//span[text()='Sort By']/../div[text()='"+sortByString+"']"));
        if(flow2Result){
            System.out.println("Flow 2 success");
        }else{
            System.out.println("Failure in flow 2");
        }
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        ArrayList<String> flow3Result = Utilities.searchStarAndPrint(driver,By.xpath("//*[contains(text(),'Ratings') and not(./*) and not(contains(text(),'Customer'))]/../../../span[1]/div[1]"),starRating);
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        for(String res : flow3Result){
            System.out.println(res);
        }
        System.out.println("Ending Test Case 01");
    }

    @Test
    public void testCase02() throws InterruptedException{
        System.out.println("Beginning Test Case 02");
        double discountPercent = 10.0;
        // go to Flipkart
        driver.get("https://www.flipkart.com/");
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        //search iphone
        Boolean flow1Result = Utilities.sendKeysWrapper(driver,By.xpath("//button[@aria-label='Search for Products, Brands and More']/../div/input"),"iPhone");
        if(flow1Result){
            System.out.println("Flow 1 success");
        }
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
        //print titles with more than 17% discount
        ArrayList<String> flow2Result = Utilities.searchStarAndPrint(driver,By.xpath("//*[contains(text(),'Ratings') and not(./*) and not(contains(text(),'Customer'))]/../../../span[1]/div[1]"),starRating);
        for(String s : flow2Result){
            System.out.println(s);
        }
        System.out.println("Ending Test Case 02");
        Thread.sleep((new Java.util.Random().nextInt(3) + 2) * 1000);
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Destroying driver instance");
        driver.close();
        driver.quit();
    }
}
