package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoanFacilityPage extends BasePageLiability {
    private final By options = By.xpath("//a[@data-toggle='dropdown']");
    private final By addDrawdown = By.xpath("//a[text()='Add Drawdown']");
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Delete = By.xpath("//a[@data-original-title='Delete Loan Facility']");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit Loan Facility']");
    private final By btn_Close = By.xpath("//a[@data-original-title='Close']");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By btn_Close_submit = By.xpath("//input[@name='commit']");
    private final By close_note = By.xpath("//textarea[@id='loan_closure_closing_notes']");
    private final By loanFacilityLabel = By.xpath("(//a[text()='Loan Facility'])[1]");
    private final By lFExternal_ID = By.xpath("//span[text()='External ID']//parent::div//following-sibling::div/span");
    private final By lienDetails = By.id("lien-details-tab");
    private final By btn_addFD = By.xpath("(//a[@title='Add'])[1]");
    private final By btn_create = By.xpath("//input[@type='submit']");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By btn_close = By.xpath("//button[text()='Close']");
    private final By covenantsTab = By.xpath("//a[@id='covenants-tab']");
    private final By btn_addCovenants = By.xpath("//a[text()='Add Covenant']");
    private final By covenants_Template = By.xpath("//select[@id='covenant_covenant_template_id']//following-sibling::div/div[1]");
    private final By accountablePerson = By.id("covenant_accountable_person");
    private final By thirdParty = By.id("covenant_third_party");
    private final By targetValue = By.xpath("//input[@id='covenant_target_value2']");
    private final By targetDate = By.xpath("//input[@id='covenant_target_date']");
    private final By offsetDays = By.id("covenant_reminder_schedule_attributes_offset_days");
    private final By covenantsRemainderEndDate = By.xpath("//input[@id='covenant_reminder_schedule_attributes_end_date']");
    private final By covenants_MappingConditions = By.xpath("//select[@id='covenant_eval_type']//following-sibling::div/div[1]");
    private final By covenantsEntity = By.xpath("//select[@id='covenant_entity_id']//following-sibling::div/div[1]");
    private final By covenants_RatioName = By.xpath("//select[@id='covenant_covenant_ratio_id']//following-sibling::div/div[1]");
    private final By covenantThresholdLimit = By.id("covenant_threshold_limit");
    private final By principalUsed=By.id("principal_used");
    private final By facilityAvailable =By.id("principal_left");
    private final By draftedDrawdownEditOption =By.xpath("(//tbody)[2]/tr[1]/td[6]/a[1]");
    private final By draftedDrawdownDeleteOption =By.xpath("(//tbody)[2]/tr[1]/td[6]/a[2]");
    private final By primarySecurityAmount=By.xpath("//span[text()='Primary Security Amount:']//parent::div//following-sibling::div/span");
    private final By secondarySecurityAmount=By.xpath("//span[text()='Secondary Security Amount:']//parent::div//following-sibling::div/span");
    private final By totalSecurityAmount=By.xpath("//span[text()='Total Security Amount:']//parent::div//following-sibling::div/span");
    private final By personalGuaranteeAmount=By.xpath("//span[text()='Personal Guarantee Amount:']//parent::div//following-sibling::div/span");
    private final By corporateGuaranteeAmount=By.xpath("//span[text()='Corporate Guarantee Amount:']//parent::div//following-sibling::div/span");
    private final By totalGuaranteeAmount=By.xpath("//span[text()='Total Guarantee Amount:']//parent::div//following-sibling::div/span");


    final String[] loanType = {"General Secured Loan", "Unsecured Loan", "Specific Charge Loan"};
    final String[] sanctionedAmount = {"40000000", "100000000", "30000000", "50000000", "60000000", "70000000", "80000000", "90000000"};


    public LoanFacilityPage clickOptions() {
        clickk(options, WaitStrategy.CLICKABLE, "Drawdown option");
        return this;
    }

    public NewLFDrawdownPage clickAddDrawdown() {
        clickk(addDrawdown, WaitStrategy.VISIBLE, "Add Drawdown option");
        return new NewLFDrawdownPage();

    }

    public LoanFacilityPage clickHamburger() {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        scrollIntoView(hamburgerMenu,WaitStrategy.VISIBLE);
        jsClick(hamburgerMenu,WaitStrategy.CLICKABLE,"Hamburger menu");
        return this;
    }

    public LoanFacilityBlotterPage clickDeleteIcon() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(btn_Delete);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return new LoanFacilityBlotterPage();
    }

    public LoanFacilityPage clickClose() {
        clickk(btn_Close, WaitStrategy.CLICKABLE, "Close icon");
        return this;
    }

    public LoanFacilityPage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }

    public NewLoanFacilityPage clickContinue() {
        jsClick(continuee, "Continue button");
        return new NewLoanFacilityPage();
    }

    public LoanFacilityPage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }


    public void clickSubmitToClose() {
        clickk(btn_Close_submit, WaitStrategy.CLICKABLE, "submit button");
    }

    public LoanFacilityBlotterPage gotoLoanFacilityBlotter() {
        moveToElement(DriverManager.getDriver().findElement(loanFacilityLabel), "Label-Loan Facility");
        jsClick(loanFacilityLabel, "Label-Loan Facility");
        return new LoanFacilityBlotterPage();
    }

    public String getLfExrnlID() {
        return getText(lFExternal_ID, WaitStrategy.VISIBLE, "External ID");
    }

    public LoanFacilityPage clickLienDetails() {
        clickk(lienDetails, WaitStrategy.CLICKABLE, "Lien Details Tab");
        return this;
    }

    public LoanFacilityPage clickAddToLienFD() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, btn_addFD);
        scrollIntoView(btn_addFD);
        clickk(btn_addFD, WaitStrategy.CLICKABLE, " Add Button ");
        return this;
    }

    public LoanFacilityPage clickOnCreate() {
        clickk(btn_create, WaitStrategy.CLICKABLE, " Create button");
        return this;
    }

    public int getLiedFDNum() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[4]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr")).size();
    }

    public LoanFacilityPage clickAttachedDocumentsTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public LoanFacilityPage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }


    public LoanFacilityPage uploadAttachedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");

        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public void clickClosebtn() {
        clickk(btn_close, WaitStrategy.CLICKABLE, "Close button");

    }

    public int getAttachedDocSize() {
        for (int i = 0; i < 5; i++) {
            clickAttachedDocumentsTab();
            if (!isDisplayed(By.xpath("(//tbody)[2]"), "Attached doc table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[2]/tr")).size();
    }

    public LoanFacilityPage clickCovenantsTab(){
        clickk(covenantsTab,WaitStrategy.CLICKABLE,"Covenants Tab");
        return this;
    }
    public LoanFacilityPage clickAddCovenants(){
        clickk(btn_addCovenants,WaitStrategy.CLICKABLE,"Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public LoanFacilityPage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public LoanFacilityPage enterAccountablePerson(String text){
        sendText(accountablePerson,text,WaitStrategy.PRESENCE,"Accountable Person");
        return this;
    }
    public LoanFacilityPage enterThirdParty(String text){
        sendText(thirdParty,text,WaitStrategy.PRESENCE,"ThirdParty");
        return this;
    }
    public LoanFacilityPage enterTargetValue(String text){
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue,text,WaitStrategy.PRESENCE,"Target value");
        return this;
    }
    public LoanFacilityPage enterTargetDate(String text){
        clearDate(targetDate,WaitStrategy.PRESENCE)
                .sendText(targetDate,text,WaitStrategy.PRESENCE,"Target Date");
        return this;
    }
    public LoanFacilityPage enterOffset(String text){
        jsClick(offsetDays,"Offset Days");
        sendText(offsetDays,text,WaitStrategy.PRESENCE,"Offset Days");
        return this;
    }
    public LoanFacilityPage enterCovenantEndDate(String text){
        jsClick(covenantsRemainderEndDate," End Date");
        clearDate(covenantsRemainderEndDate,WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate,text,WaitStrategy.PRESENCE,"End Date");
        return this;
    }
    public LoanFacilityPage selectMappingConditions(String text){
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityPage selectCovenantsEntity(String text){
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityPage selectRatioName(String text){
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityPage enterThresholdPercentage(String text){
        WebElement ele = DriverManager.getDriver().findElement(covenantThresholdLimit);
        ele.clear();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        sendText(covenantThresholdLimit,text,WaitStrategy.PRESENCE,"Threshold Limit");
        return this;
    }
    public int getCovenantsSize() {
        for (int i = 0; i < 5; i++) {
            clickCovenantsTab();
            if (!isDisplayed(By.xpath("(//tbody)[3]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[3]/tr")).size();
    }
    public NewLFDrawdownPage clickDraftedDrawdownEditOption(){
        scrollIntoView(draftedDrawdownEditOption);
        clickk(draftedDrawdownEditOption,WaitStrategy.CLICKABLE,"Drafted drawdown Edit option");
        return new NewLFDrawdownPage();
    }
    public void clickDraftedDrawdownDeleteOption(){
        scrollIntoView(draftedDrawdownDeleteOption);
        doubleClick(draftedDrawdownDeleteOption);
        Alert al = DriverManager.getDriver().switchTo().alert();
        al.accept();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        new NewLFDrawdownPage();
    }
    public double getTotalPrincipalDrawn(){
        List<WebElement> list=  DriverManager.getDriver().findElements(By.xpath("(//tbody)[1]/tr"));
        double principalUsed= 0.00;
        for(int i=1;i<=list.size();i++){
            String[] ar =getText(By.xpath("(//tbody)[1]/tr["+i+"]" +
                    "/td[3]"),WaitStrategy.VISIBLE,"Principal amount").split(",");
            String principal=String.join("",ar);
            principalUsed= principalUsed+ Double.parseDouble(principal);

        }
        return principalUsed;
    }
    public double getPrincipalUsed(){
        return  Double.parseDouble(getAttribute(principalUsed,"value",
                WaitStrategy.PRESENCE,"Principal Drawn"));
    }
    public double getFacilityAmount(){
        String[] ar=getText(By.xpath("//span[text()='Facility Amount:']//parent::div//following-sibling::div/span"),WaitStrategy.VISIBLE,"Facility amount").split(",");
        String facilityAmount= String.join("",ar);
        return Double.parseDouble(facilityAmount);
    }
    public double getFacilityAvailable(){
        return Double.parseDouble(getAttribute(facilityAvailable,"value"
                ,WaitStrategy.PRESENCE,"Facility available"));
    }
    public double getPrimarySecurityAmount(){
         String[] ar=getText(primarySecurityAmount,WaitStrategy.VISIBLE,"Primary security amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public double getSecondarySecurityAmount(){
        String[] ar=getText(secondarySecurityAmount,WaitStrategy.VISIBLE,"secondary security amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public double getPersonalGuaranteeAmount(){
        String[] ar=getText(personalGuaranteeAmount,WaitStrategy.VISIBLE,"Personal Guarantee Amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public double getCorporateGuaranteeAmount(){
        String[] ar=getText(corporateGuaranteeAmount,WaitStrategy.VISIBLE,"corporate Guarantee Amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public double getTotalSecurityAmount(){
        String[] ar=getText(totalSecurityAmount,WaitStrategy.VISIBLE,"total security Amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public double getTotalGuaranteeAmount(){
        String[] ar=getText(totalGuaranteeAmount,WaitStrategy.VISIBLE,"total Guarantee Amount").split(",");
        return Double.parseDouble(String.join("",ar));
    }
    public void selectFirstDrawdown(){
        clickk(By.xpath("(//tbody)[1]/tr/td[1]"),WaitStrategy.CLICKABLE,"Loan Facility Drawdown");
        switchToNextWindow();
    }


    public LoanFacilityPage create_new_LoanFacility() {
        LiabilityDashboardsPage ldp = new LiabilityDashboardsPage();
        ldp.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(7).enterROC(7)
                .selectLoanFacilityType(loanType[(int) (Math.random() * loanType.length)])
                .selectEntity("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCounterparty("AUTOMATION_PARTY")
                .enterSanctionDate("11/01/2019")
                .enterEndDate("21/09/2028")
                .enterLFAvailableTill("21/09/2028")
                .enterFacilityAmount(sanctionedAmount[(int) (Math.random() * sanctionedAmount.length)])
                .selectArranger("ARRANGER_01").primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().enterTrustee("NA")
                .enterAdditionalInfo("Automated test").clickOnCreate();
        return this;
    }

}
