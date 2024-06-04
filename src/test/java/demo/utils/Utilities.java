import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Utilities {
    public static Boolean sendKeysWrapper(WebDriver driver, By locator,String textToSend){
        System.out.println("Sending Keys");
        Boolean success;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement textInput = driver.findElement(locator);
            textInput.clear();
            textInput.sendKeys(textToSend);
            textInput.sendKeys(Keys.ENTER);
            success = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            success=false;
        }
        return success;
    }

    public static ArrayList<String> searchStarAndPrint(WebDriver driver, By locatorOfTitle, By locatorOfRating, double starRating){
        List<WebElement> titles = driver.findElements(locatorOfTitle);
        List<WebElement> attr = driver.findElements(locatorOfRating);
        ArrayList<String> results = new ArrayList<>();
        if(attr.size() != titles.size()){
            if(attr.size() > titles.size()){
                attr = sliceArrayList(attr, titles.size());
            }else{
                attr = sliceArrayList(titles,attr.size());
            }
        }
        for(int i=0;i<titles.size();i++){
            String title = titles.get(i).getText();
            String attribute = attr.get(i).getText();
            attribute = removeSubstring(attribute,"% off");
            float attributeFloat = 0;
            try{
                //convert rating from String to float
                attributeFloat = Float.parseFloat(attribute);
                //check if rating is greater than or equal to 4.0
                if(attributeFloat >= starRating ){
                    results.add(title+" has a attribute of "+attributeFloat);
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid rating format for: "+title+", rating: "+attributeFloat);
            }
        }
        return results;
    }

    public static String removeSubstring(String originalString, String substringToRemove){
        if(originalString.contains(substringToRemove)){
            //remove substring from original string
            return originalString.replace(substringToRemove,"").trim();
        }
        return originalString;
    }

    public static <T> List<T> sliceArrayList(List<T> list, int n){
        n = Math.min(n,list.size());
        return new ArrayList<>(list.subList(0,n));
    }

    public static Boolean clickWrapper(WebDriver driver, By locator){
        Boolean success;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(visibilityOfElementLocated(locator));
            WebElement button = driver.findElement(locator);
            button.click();
            success=true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }
}
