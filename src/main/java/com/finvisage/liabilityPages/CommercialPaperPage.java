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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommercialPaperPage extends BasePageLiability {

    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By options = By.xpath("//a[@data-toggle='dropdown']");
    private final By addDrawdown = By.xpath("//a[text()='Add Tranche']");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit']/i");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By loanExternal_ID = By.xpath("//p[text()='External ID']//parent::div//parent::div//following-sibling::div/div/h5");
    private final By archivedTrancheTab = By.id("archived-tranches-data-tab");
    private final By archivedTabSearchBox = By.xpath("//input[@aria-controls='DataTables_Table_0']");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By close = By.xpath("//a[@data-original-title='Close']");
    private final By closeButton = By.xpath("//button[text()='Close']");
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
    private final By btn_create = By.xpath("//input[@type='submit']");
    private final By close_note = By.xpath("//textarea[@id='loan_closure_closing_notes']");
    private final By btn_Delete = By.xpath("//a[@data-confirm='Delete this Commercial Paper and associated data?']/i");


    public CommercialPaperPage clickOptions() {
        scrollIntoView(options);
        clickk(options, WaitStrategy.CLICKABLE, "Tranche option");
        return this;
    }

    public NewCPTranchePage clickAddTranche() {
        clickk(addDrawdown, WaitStrategy.VISIBLE, "Add Tranche option");
        return new NewCPTranchePage();

    }

    public CommercialPaperPage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }

    public NewCpPage clickContinue() {
        jsClick(continuee, "Continue button");
        return new NewCpPage();
    }

    public CommercialPaperPage clickHamburger() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public String getTrancheStatus() {
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        Set<String> allWindows = DriverManager.getDriver().getWindowHandles();
        for (String windowhandle : allWindows) {
            if (!windowhandle.equals(mainWindow)) {
                DriverManager.getDriver().switchTo().window(windowhandle);
                break;
            }
        }
        return getText(By.xpath("(//tbody)[1]/tr/td[1]"), WaitStrategy.VISIBLE, "Tranche Status");
    }

    public String getLoanID() {
        return getText(loanExternal_ID, WaitStrategy.VISIBLE, "External ID");
    }

    public CommercialPaperPage clickArchivedTrancheTab() {
        clickk(archivedTrancheTab, WaitStrategy.CLICKABLE, "Archived Tranche Tab");
        return this;
    }

    public CommercialPaperPage searchExtId(String ID) {
        sendText(archivedTabSearchBox, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }

    public String getFirstLoan() {
        return getText(By.xpath("(//tbody)[2]/tr/td[2]"), WaitStrategy.VISIBLE, "Loan EXT ID");
    }

    public CommercialPaperPage clickAttachedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public CommercialPaperPage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }

    public CommercialPaperPage uploadAttachedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");
        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public CommercialPaperPage clickClose() {
        clickk(close, WaitStrategy.CLICKABLE, "Close button");
        return this;

    }

    public void clickToClose() {
        clickk(closeButton, WaitStrategy.CLICKABLE, "Close button");

    }

    public int getAttachedDocSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[2]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[2]/tr")).size();
    }

    public CommercialPaperPage clickCovenantsTab() {
        clickk(covenantsTab, WaitStrategy.CLICKABLE, "Covenants Tab");
        return this;
    }

    public CommercialPaperPage clickAddCovenants() {
        clickk(btn_addCovenants, WaitStrategy.CLICKABLE, "Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public CommercialPaperPage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public CommercialPaperPage enterAccountablePerson(String text) {
        sendText(accountablePerson, text, WaitStrategy.PRESENCE, "Accountable Person");
        return this;
    }

    public CommercialPaperPage enterThirdParty(String text) {
        sendText(thirdParty, text, WaitStrategy.PRESENCE, "ThirdParty");
        return this;
    }

    public CommercialPaperPage enterTargetValue(String text) {
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue, text, WaitStrategy.PRESENCE, "Target value");
        return this;
    }

    public CommercialPaperPage enterTargetDate(String text) {
        clearDate(targetDate, WaitStrategy.PRESENCE)
                .sendText(targetDate, text, WaitStrategy.PRESENCE, "Target Date");
        return this;
    }

    public CommercialPaperPage enterOffset(String text) {
        jsClick(offsetDays, "Offset Days");
        sendText(offsetDays, text, WaitStrategy.PRESENCE, "Offset Days");
        return this;
    }

    public CommercialPaperPage enterCovenantEndDate(String text) {
        jsClick(covenantsRemainderEndDate, " End Date");
        clearDate(covenantsRemainderEndDate, WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate, text, WaitStrategy.PRESENCE, "End Date");
        return this;
    }

    public CommercialPaperPage selectMappingConditions(String text) {
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CommercialPaperPage selectCovenantsEntity(String text) {
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CommercialPaperPage selectRatioName(String text) {
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CommercialPaperPage enterThresholdPercentage(String text) {
        WebElement ele = DriverManager.getDriver().findElement(covenantThresholdLimit);
        ele.clear();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        sendText(covenantThresholdLimit, text, WaitStrategy.PRESENCE, "Threshold Limit");
        return this;
    }

    public int getCovenantsSize() {
        for (int i = 0; i < 5; i++) {
            clickCovenantsTab();
            if (!isDisplayed(By.xpath("(//tbody)[2]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[2]/tr")).size();
    }

    public CommercialPaperPage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }


    public void clickSubmitToClose() {
        clickk(btn_create, WaitStrategy.CLICKABLE, "submit button");
    }

    public CPBlotterPage clickDeleteIcon() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(btn_Delete);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return new CPBlotterPage();
    }
    public CommercialPaperPage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return this;
    }

    public CommercialPaperPage create_CommercialPaper() {
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickCommercialPaper().moveToHamburgerMenu()
                .clickAdd().enterExternalID(6)
                .enterLedgerID(6).enterISIN()
                .enterMaturityDate("12/07/2026")
                .selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectRTA("AUTOMATION_PARTY").selectIPA("TEST_BANK_01")
                .selectCurrency("INR").enterIssueCurrentAccount("ICA")
                .enterIssueDematAccount("IDA").selectArranger("ARRANGER_01")
                .clickCreate();
        return this;
    }
}