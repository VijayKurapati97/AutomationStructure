package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoanFacilityBlotterPage extends BasePageLiability {
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Add = By.xpath("//a[@data-original-title='Add']/i");
    private final By archivedTab = By.id("archived-data-tab");
    private final By closedTab = By.id("closed-data-tab");
    private final By activeTab = By.id("active-loan-facilities-tab");
    private final By searchBox = By.xpath("//div[@id='DataTables_Table_1_filter']//input");
    private final By searchLoan = By.xpath("//div[@id='DataTables_Table_0_filter']//input");
    private final By firstExtId = By.xpath("(//tbody)[2]/tr[1]/td[1]");
    private final By draftedLoanEditOption =By.xpath("(//tbody)[2]/tr[1]/td[11]/a[1]");
    private final By draftedLoanDeleteOption =By.xpath("(//tbody)[2]/tr[1]/td[11]/a[2]");
    private final By draftTab=By.id("drafted-data-tab");
    private final By fileOperationsTab=By.id("file-operations-tab");
    private final By fileOperationOptions=By.xpath("//p[text()='File Operations']//parent::div/following-sibling::div/div/a/i");
    private final By bulkPaymentsUpload=By.xpath("//a[text()='Bulk payments upload']");
    private final By bulk_data_upload_file_name=By.id("bulk_data_upload_file_name");
    private final By btn_ClickToUpload = By.id("generic_dropzone");
    private final By btn_Submit = By.xpath("(//input[@type='submit'])[2]");


    public LoanFacilityBlotterPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public NewLoanFacilityPage clickAdd() {
        jsClick(btn_Add, WaitStrategy.CLICKABLE, "Add Button");
        return new NewLoanFacilityPage();
    }

    public LoanFacilityBlotterPage clickClosedTab() {
        clickk(closedTab, WaitStrategy.CLICKABLE, "Closed tab");
        return this;
    }

    public LoanFacilityBlotterPage clickArchivedTab() {
        clickk(archivedTab, WaitStrategy.CLICKABLE, "Archived tab");
        return this;
    }
    public LoanFacilityBlotterPage clickFileOperationsTab() {
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        clickk(fileOperationsTab, WaitStrategy.CLICKABLE, "File Operations tab");
        return this;
    }
    public LoanFacilityBlotterPage selectFileOperationOptions(){
        clickk(fileOperationOptions,WaitStrategy.CLICKABLE,"File Operations Options");
        return this;
    }
    public LoanFacilityBlotterPage selectBulkPaymentsUpload(){
        clickk(bulkPaymentsUpload,WaitStrategy.CLICKABLE,"Bulk payments upload");
        return this;
    }
    public LoanFacilityBlotterPage uploadBulkPayments(){
        try{
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement dropzoneElement = wait.until(ExpectedConditions.elementToBeClickable(btn_ClickToUpload));
            dropzoneElement.click();
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display = 'block';", fileInput);
            String filePath = FrameworkConstants.get_LF_UploadPaymentsFilePath();
            File file = new File(filePath);
            if (file.exists()) {
                fileInput.sendKeys(filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }
    public LoanFacilityBlotterPage enterLiability_uploadPayments_name(String FileName) {
        clickk(bulk_data_upload_file_name, WaitStrategy.CLICKABLE, "bulk upload name");
        sendText(bulk_data_upload_file_name, FileName, WaitStrategy.PRESENCE, "liability upload name");
        clickk(btn_Submit, WaitStrategy.CLICKABLE, "proceed button");
        return this;
    }

    public LoanFacilityBlotterPage searchExtId(String ID) {
        sendText(searchBox, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }
    public LoanFacilityBlotterPage searchLoan(String ID) {
        scrollIntoView(searchLoan);
        sendText(searchLoan, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }
    public LoanFacilityPage selectFirstLoan(){
        scrollIntoView(By.xpath("(//tbody)[1]/tr/td[4]"));
        clickk(By.xpath("(//tbody)[1]/tr/td[4]"),WaitStrategy.CLICKABLE,"Loan Facility");
        switchToNextWindow();
        return new LoanFacilityPage();
    }


    public String getFirstLoan() {
        return getText(firstExtId, WaitStrategy.VISIBLE, "Loan External ID");
    }
    public LoanFacilityBlotterPage clickDraftsTab(){
        clickk(draftTab,WaitStrategy.CLICKABLE,"Draft tab");
        return new  LoanFacilityBlotterPage();
    }
    public String getPaymentUploadStatus(){
        return getText(By.xpath("(//tbody)[2]/tr/td[5]"),WaitStrategy.VISIBLE,"Status");
    }
    public LoanFacilityBlotterPage checkPaymentsUploadIsCompleted(String name){
        for (int i=0;i<6;i++){
            clickFileOperationsTab().searchExtId(name);
            if(!getPaymentUploadStatus().equalsIgnoreCase("pending")){
                clickk(activeTab,WaitStrategy.CLICKABLE,"Active tab");
                break;
            }else{
                Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            }
        }
        return this;
    }
    public NewLoanFacilityPage clickDraftedLoanEdit(){
        scrollHorizontally(draftedLoanEditOption);
        jsClick(draftedLoanEditOption,WaitStrategy.CLICKABLE,"Drafted loan edit button");
        return new NewLoanFacilityPage();
    }
    public LoanFacilityBlotterPage clickDraftedLoanDelete(){
        scrollIntoView(draftedLoanDeleteOption);
        scrollHorizontally(draftedLoanDeleteOption);
        doubleClick(draftedLoanDeleteOption);
        Alert al = DriverManager.getDriver().switchTo().alert();
        al.accept();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

}
