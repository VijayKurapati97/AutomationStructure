package com.finvisage.liabilityPages;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import java.util.concurrent.TimeUnit;

public class LoanFacilityPage extends BasePageLiability {
    private final By options = By.xpath("//section[@class='mb-5']/div/div/div[2]/a");
    private final By addDrawdown = By.xpath("//a[@class='dropdown-item']");
    private final By hamburgerMenu = By.xpath("//i[@class='fal fa-tasks-alt']");
    private final By btn_Delete = By.xpath("//section[@id='fixed-buttons']/div/ul/li[3]/a/i");
    private final By btn_Close = By.xpath("//section[@id='fixed-buttons']/div/ul/li[1]/a/i");
    private final By btn_Close_submit = By.xpath("//input[@name='commit']");
    private final By close_note = By.xpath("//textarea[@id='loan_closure_closing_notes']");
    private final By loanFacilityLabel = By.xpath("(//a[text()='Loan Facility'])[1]");
    private final By lFExternal_ID = By.xpath("//span[text()='External ID']//parent::div//following-sibling::div/span");
    final String[] loanType = {"General Secured Loan", "Unsecured Loan", "Specific Charge Loan"};
    final String[] sanctionedAmount = {"40000000", "100000000", "30000000", "50000000", "60000000", "70000000", "80000000", "90000000"};


    public LoanFacilityPage clickOptions() {
        clickk(options, WaitStrategy.CLICKABLE, "Drawdown option");
        return this;
    }

    public NewDrawdownPage clickAddDrawdown() {
        clickk(addDrawdown, WaitStrategy.VISIBLE, "Add Drawdown option");
        return new NewDrawdownPage();

    }

    public LoanFacilityPage clickHamburgur() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
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
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return new LoanFacilityBlotterPage();
    }

    public LoanFacilityPage clickClose() {
        clickk(btn_Close, WaitStrategy.CLICKABLE, "Close icon");
        return this;
    }

    public LoanFacilityPage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }

    public void clickSubmitToClose() {
        clickk(btn_Close_submit, WaitStrategy.CLICKABLE, "submit button");
    }

    public LoanFacilityBlotterPage gotoLoanFacilityBlotter() {
        moveToElement(DriverManager.getDriver().findElement(loanFacilityLabel),"Label-Loan Facility");
        jsClick(loanFacilityLabel, "Label-Loan Facility");
        return new LoanFacilityBlotterPage();
    }

    public String getLfExrnlID() {
        return getText(lFExternal_ID, WaitStrategy.VISIBLE, "External ID");
    }

    public LoanFacilityPage create_new_LoanFacility() {
                LiabilityDashboardsPage ldp = new LiabilityDashboardsPage();
        ldp.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(7).enterROC(7)
                .selectLoanFacilityType(loanType[(int) (Math.random() * loanType.length)])
                .selectEntity("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCounterparty("AUTOMATION_PARTY")
                .enterSanctionDate("11/01/19")
                .enterEndDate("21/09/28")
                .enterFacilityAmount(sanctionedAmount[(int) (Math.random() * sanctionedAmount.length)])
                .selectArranger("ARRANGER_01")./*primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().*/enterTrustee("NA")
                .enterAdditionalInfo("Automated test").clickOnCreate();
        return this;
    }
}
