package com.finvisage.tests;
import com.finvisage.drivers.DriverManager;
import com.finvisage.frmPages.*;
import com.finvisage.reports.ExtentManager;
import com.finvisage.utils.CommonUtils;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.within;

public class DerivativePricerFXTests extends BaseTest {

    private DerivativePricerFXTests() {
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardPage fp = new NewFXForwardPage();
        fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy")).
                enterNotional(data.get("Notional")).clickPrice().getPayoffGraph();

        String[] Str = fp.getPriceSection();
        BigDecimal EquivalentNotional = CommonUtils.StringArrayToInt(Str, 0, 0);
        Assertions.assertThat(EquivalentNotional)
                .isPositive()
                .isNotZero()
                .isNotNull()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
        Assertions.assertThat(forwardRate).isNotNull()
                .isNotZero()
                .isPositive()
                .isGreaterThan(BigDecimal.valueOf(1));
        fp.clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_002(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardPage fp=new NewFXForwardPage();
        fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction")).
                enterNotional(data.get("Notional")).clickPrice();

        String[] Str = fp.getPriceSection();
        BigDecimal EquivalentNotional = CommonUtils.StringArrayToInt(Str, 0, 0);
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
        Assertions.assertThat(EquivalentNotional).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_003_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateAsk(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue("Buy")
                .enterNotional("40000021");
        for (int i = 0; i <= list2.size() - 1; i++){
            fp.enterMaturityDate(list1.get(i)).clickPrice();
            String[] Str = fp.getPriceSection();
            BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
            Assertions.assertThat(forwardRate).isCloseTo(BigDecimal.valueOf(Double.parseDouble(list2.get(i))), within(new BigDecimal("0.0001")));
            fp.clearmaturityDate();
        }

    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_003_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateBid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue("Sell")
                .enterNotional("30000041");
        for (int i = 0; i <= list2.size() - 1; i++){
            fp.enterMaturityDate(list1.get(i)).clickPrice();
            String[] Str = fp.getPriceSection();
            BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
            Assertions.assertThat(forwardRate).isCloseTo(BigDecimal.valueOf(Double.parseDouble(list2.get(i))), within(new BigDecimal("0.0001")));
            fp.clearmaturityDate();
        }

    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++){
            fp.enterMaturityDate(list1.get(i));
            fp.getForwardRate();
            if (fp.getForwardRate().isEmpty()) {
                fp.clearmaturityDate();
                fp.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(fp.getForwardRate());
            double forwardRate2 = Double.parseDouble(list2.get(i));
            Assertions.assertThat(forwardRate1).isCloseTo(forwardRate2,within(new Double("0.0001")));
            fp.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_005_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(fp.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardTest_005_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(fp.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        fp.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_006(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage fp= new NewFXForwardPage();
        fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(fp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(fp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(fp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_007(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXForwardPage fp= new NewFXForwardPage();
        Assertions.assertThat(fp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(fp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

//------------------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardStripTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardStripPage fs = new NewFXForwardStripPage();
        fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice();

        String Str1 = fs.getWeightedAvgForward();
        String[] En = Str1.split(" ");
        Double WeightedAvgForward = Double.parseDouble(En[0]);
        Assertions.assertThat(WeightedAvgForward)
                .isPositive()
                .isNotZero()
                .isNotNull()
                .isGreaterThan(1);
        fs.clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardStripPage fs = new NewFXForwardStripPage();
        fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice();
        Map<Long,Double> map= new HashMap<>();
        for(int i=1;i<=Integer.parseInt(data.get("NotionalIndex"));i++){

            map.put( CommonUtils.stringToint(fs.getNotional(i)),Double.parseDouble(fs.getForwardRate(i)));
        }
        Double weightedAverageForward1 = CommonUtils.calculateWeightedAverage(map);
        String Str1 = fs.getWeightedAvgForward();
        String[] En = Str1.split(" ");
        Double WeightedAvgForward = Double.parseDouble(En[0]);
        Assertions.assertThat(WeightedAvgForward).isCloseTo(weightedAverageForward1,within(new Double("0.09")));

    }

    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_003_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage fs= new NewFXForwardStripPage();
        fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(fs.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_003_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage fs= new NewFXForwardStripPage();
        fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(fs.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        fs.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage fs= new NewFXForwardStripPage();
        fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(fs.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(fs.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(fs.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_005(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXForwardStripPage fs= new NewFXForwardStripPage();
        Assertions.assertThat(fs.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(fs.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-----------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke", "regression"})
    public void FX_EuropeanOptionTest_001_1(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = er.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        BigDecimal ForwardRate = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(ForwardRate).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        er.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_EuropeanOptionTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = er.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        BigDecimal ForwardRate = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(ForwardRate).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        er.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike1")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));
        String ForwardRate1 = er.getForwardRate();
        String ImpliedVol1 = er.getImpliedvolatility();
        String Notional1 = er.getNotional2();
        er.clearTenure();
        er.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = er.getForwardRate();
        String ImpliedVol2 = er.getImpliedvolatility();
        String Notional2 = er.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection()
                .selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike1")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = er.priceSectionDisplayed();
        er.clearTenure();
        er.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = er.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            er.enterMaturityDate(list1.get(i));
            er.getForwardRate();
            if (er.getForwardRate().isEmpty()) {
                er.clearmaturityDate();
                er.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(er.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            er.clearmaturityDate();
        }

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(er.getATMVolatilityDate(i));
            list2.add(er.getATMvolatilityMid(i));
        }
        er.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            er.enterMaturityDate(list1.get(i));
            er.enterStrike(er.getForwardRate());
            er.clickImpledVolatility();
            if (er.getImpliedvolatility().isEmpty()) {
                er.clearStrike();
                er.clearmaturityDate();
                er.enterMaturityDate(list1.get(i));
                er.enterStrike(er.getForwardRate());
                er.clickImpledVolatility();
            }
            Assert.assertEquals(er.getImpliedvolatility(), list2.get(i));
            er.clearStrike();
            er.clearmaturityDate();

        }

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(er.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(er.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage er= new NewFXEuropeanOptionPage();
        er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(er.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage er= new NewFXEuropeanOptionPage();
        er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(er.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        er.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage er= new NewFXEuropeanOptionPage();
        er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(er.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(er.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(er.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXEuropeanOptionPage er= new NewFXEuropeanOptionPage();
        Assertions.assertThat(er.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(er.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickImpledVolatility().clickNotionalCcy();
      String ImpliedVol=  er.getImpliedvolatility();
       er.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike").clickSeekStrike();
        Assertions.assertThat(data.get("Strike")).isNotEqualTo(er.getStrikeValue());
        Assertions.assertThat(ImpliedVol).isNotEqualTo(er.getImpliedvolatility());
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage er = new NewFXEuropeanOptionPage();
        er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = er.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

       er.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
       String Total = er.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
               Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }

    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_OptionSpreadTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = os.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();
        os.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }
    @Test(groups = {"smoke", "regression"})
    public void FX_OptionSpreadTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = os.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();
        os.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = os.getForwardRate();
        String ImpliedVol1 = os.getImpliedvolatility1();
        String ImpliedVol2 = os.getImpliedvolatility2();
        String Notional1 = os.getNotional2();
        os.clearTenure();
        os.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = os.getForwardRate();
        String ImpliedVol3 = os.getImpliedvolatility1();
        String ImpliedVol4 = os.getImpliedvolatility2();
        String Notional2 = os.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol3);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] price1 = os.priceSectionDisplayed();
        os.clearTenure();
        os.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = os.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            os.enterMaturityDate(list1.get(i));
            os.getForwardRate();
            if (os.getForwardRate().isEmpty()) {
                os.clearmaturityDate();
                os.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(os.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            os.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();
        NewFXOptionSpreadPage os = new NewFXOptionSpreadPage();
        os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(os.getATMVolatilityDate(i));
            list2.add(os.getATMvolatilityMid(i));
        }
        os.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            os.enterMaturityDate(list1.get(i));
            os.enterStrike1(os.getForwardRate());
            os.enterStrike2(os.getForwardRate());
            os.clickImpledVols2();
            os.getImpliedvolatility1();
            os.getImpliedvolatility2();
            if (os.getImpliedvolatility1().isEmpty() || os.getImpliedvolatility2().isEmpty()) {
                os.clearStrike1();
                os.clearStrike2();
                os.clearmaturityDate();
                os.enterMaturityDate(list1.get(i));
                os.enterStrike1(os.getForwardRate());
                os.enterStrike2(os.getForwardRate());
                os.clickImpledVols2();
            }
            Assert.assertEquals(os.getImpliedvolatility1(), list2.get(i));
            Assert.assertEquals(os.getImpliedvolatility2(), list2.get(i));
            os.clearStrike1();
            os.clearStrike2();
            os.clearmaturityDate();
        }
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os=new NewFXOptionSpreadPage();
        os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure"))
                .enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(os.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(os.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage os= new NewFXOptionSpreadPage();
        os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(os.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage os=new NewFXOptionSpreadPage();
        os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(os.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        os.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage os= new NewFXOptionSpreadPage();
        os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(os.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(os.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(os.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXOptionSpreadPage os= new NewFXOptionSpreadPage();
        Assertions.assertThat(os.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(os.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os= new NewFXOptionSpreadPage();
        os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickImpledVols2().clickNotionalCcy();
        String ImpliedVol1=  os.getImpliedvolatility1();
        String ImpliedVol2= os.getImpliedvolatility2();
        os.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
       Assertions.assertThat(ImpliedVol1).isNotEqualTo(os.getImpliedvolatility1());
       Assertions.assertThat(data.get("Strike1")).isNotEqualTo(os.getStrike1Value());
       os.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(os.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(os.getStrike2Value());
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage os=new NewFXOptionSpreadPage();
        os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = os.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        os.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = os.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }

    //-------------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke","regression"})
    public void FX_CollarTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = cp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        cp.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");

    }
    @Test(groups = {"smoke","regression"})
    public void FX_CollarTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = cp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        cp.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));
        String ForwardRate1 = cp.getForwardRate();
        String ImpliedVol1 = cp.getImpliedvolatility1();
        String ImpliedVol2 = cp.getImpliedvolatility2();
        String Notional1 = cp.getNotional2();
        cp.clearTenure();
        cp.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = cp.getForwardRate();
        String ImpliedVol3 = cp.getImpliedvolatility1();
        String ImpliedVol4 = cp.getImpliedvolatility2();
        String Notional2 = cp.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol3);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = cp.priceSectionDisplayed();
        cp.clearTenure();
        cp.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = cp.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);


    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            cp.enterMaturityDate(list1.get(i));
            cp.getForwardRate();
            if (cp.getForwardRate().isEmpty()) {
                cp.clearmaturityDate();
                cp.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(cp.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.01;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            cp.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp = new NewFXCollorPage();
        cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(cp.getATMVolatilityDate(i));
            list2.add(cp.getATMvolatilityMid(i));
        }
        cp.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            cp.enterMaturityDate(list1.get(i));
            cp.enterStrike1(cp.getForwardRate());
            cp.enterStrike2(cp.getForwardRate());
            cp.clickImpledVols2();
            cp.getImpliedvolatility1();
            cp.getImpliedvolatility2();
            if (cp.getImpliedvolatility1().isEmpty() || cp.getImpliedvolatility2().isEmpty()) {
                cp.clearStrike1();
                cp.clearStrike2();
                cp.clearmaturityDate();
                cp.enterMaturityDate(list1.get(i));
                cp.enterStrike1(cp.getForwardRate());
                cp.enterStrike2(cp.getForwardRate());
                cp.clickImpledVols2();
            }
            String ImpliedVolatility3 = list2.get(i);
            Assert.assertEquals(cp.getImpliedvolatility1(), ImpliedVolatility3);
            Assert.assertEquals(cp.getImpliedvolatility2(), ImpliedVolatility3);
            cp.clearmaturityDate();
            cp.clearStrike1();
            cp.clearStrike2();
        }
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(cp.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(cp.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(cp.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(cp.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        cp.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(cp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(cp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(cp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXCollorPage cp=new NewFXCollorPage();
        Assertions.assertThat(cp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(cp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();
        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickImpledVols2().clickNotionalCcy();
        String ImpliedVol1=  cp.getImpliedvolatility1();
        String ImpliedVol2= cp.getImpliedvolatility2();
        cp.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(cp.getImpliedvolatility1());
        Assertions.assertThat(data.get("Strike1")).isNotEqualTo(cp.getStrike1Value());
        cp.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(cp.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(cp.getStrike2Value());
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage cp=new NewFXCollorPage();
        cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = cp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        cp.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = cp.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }
    //------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = tw.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        tw.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");

    }
    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = tw.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        tw.graphIsDisplayed().clickCheckBox()
                .enterPremium(data.get("Premium(mid)")).clickByChangingDropdown()
                .byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = tw.getForwardRate();
        String ImpliedVol1 = tw.getImpliedvolatility1();
        String ImpliedVol2 = tw.getImpliedvolatility2();
        String ImpliedVol3 = tw.getImpliedvolatility3();
        String Notional1 = tw.getNotional2();
        tw.clearTenure();
        tw.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = tw.getForwardRate();
        String ImpliedVol4 = tw.getImpliedvolatility1();
        String ImpliedVol5 = tw.getImpliedvolatility2();
        String ImpliedVol6 = tw.getImpliedvolatility3();
        String Notional2 = tw.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol5);
        Assertions.assertThat(ImpliedVol3).isNotEqualTo(ImpliedVol6);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] price1 = tw.priceSectionDisplayed();
        tw.clearTenure();
        tw.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = tw.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);


    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            tw.enterMaturityDate(list1.get(i));
            tw.getForwardRate();
            if (tw.getForwardRate().isEmpty()) {
                tw.clearmaturityDate();
                tw.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(tw.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            tw.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();


        NewFXThreeWayPage tw = new NewFXThreeWayPage();
        tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(tw.getATMVolatilityDate(i));
            list2.add(tw.getATMvolatilityMid(i));
        }
        tw.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            tw.enterMaturityDate(list1.get(i));
            tw.enterStrike1(tw.getForwardRate());
            tw.enterStrike2(tw.getForwardRate());
            tw.enterStrike3(tw.getForwardRate());
            tw.clickImpledVols3();
            tw.getImpliedvolatility1();
            tw.getImpliedvolatility2();
            tw.getImpliedvolatility3();
            if (tw.getImpliedvolatility1().isEmpty() || tw.getImpliedvolatility2().isEmpty() ||
                    tw.getImpliedvolatility3().isEmpty()) {
                tw.clearmaturityDate();
                tw.clearStrike1();
                tw.clearStrike2();
                tw.clearStrike3();
                tw.enterMaturityDate(list1.get(i));
                tw.enterStrike1(tw.getForwardRate());
                tw.enterStrike2(tw.getForwardRate());
                tw.enterStrike3(tw.getForwardRate());
                tw.clickImpledVols3();
            }

            Assert.assertEquals(tw.getImpliedvolatility1(), list2.get(i));
            Assert.assertEquals(tw.getImpliedvolatility2(), list2.get(i));
            Assert.assertEquals(tw.getImpliedvolatility3(), list2.get(i));
            tw.clearmaturityDate();
            tw.clearStrike1();
            tw.clearStrike2();
            tw.clearStrike3();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage tw=new NewFXThreeWayPage();
        tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(tw.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(tw.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage tw=new NewFXThreeWayPage();
        tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tw.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage tw= new NewFXThreeWayPage();
        tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tw.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        tw.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage tw= new NewFXThreeWayPage();
        tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(tw.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tw.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tw.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXThreeWayPage tw=new NewFXThreeWayPage();
        Assertions.assertThat(tw.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(tw.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        NewFXThreeWayPage tw= new NewFXThreeWayPage();
        tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3")).clickImpledVols3().clickNotionalCcy();
        String ImpliedVol1=  tw.getImpliedvolatility1();
        String ImpliedVol2= tw.getImpliedvolatility2();
        String ImpliedVol3= tw.getImpliedvolatility3();
        tw.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(tw.getImpliedvolatility1());
        Assertions.assertThat(data.get("Strike1")).isNotEqualTo(tw.getStrike1Value());

        tw.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(tw.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(tw.getStrike2Value());

        tw.clickByChangingDropdown().byChangingStrike("Strike 3").clickSeekStrike();
        Assertions.assertThat(data.get("Strike3")).isNotEqualTo(tw.getStrike3Value());
        Assertions.assertThat(ImpliedVol3).isNotEqualTo(tw.getImpliedvolatility3());

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        NewFXThreeWayPage tw= new NewFXThreeWayPage();
        tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = tw.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        tw.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = tw.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }

    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfAbsoluteTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Absolute Price")
                .isNotEmpty()
                .isNotNull();
        NewFXTarfAbsolutePage ab = new NewFXTarfAbsolutePage();
        ab.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyIndex")).clickKnockoutCcy().selectKnockoutCcyValue(data.get("KnockoutCcyIndex"))
                .enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = ab.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));


        ab.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage ab= new NewFXTarfAbsolutePage();
        ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(ab.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage ab= new NewFXTarfAbsolutePage();
        ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(ab.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        ab.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage ab=new NewFXTarfAbsolutePage();
        ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(ab.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(ab.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(ab.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }

    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Absolute Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfAbsolutePage ab= new NewFXTarfAbsolutePage();
        Assertions.assertThat(ab.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        ab.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(ab.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-----------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfPointTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();

        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Point Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfPointsPage tp = new NewFXTarfPointsPage();
        tp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = tp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        tp.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }

    @Test(groups = {"regression"})
    public void FX_TarfPointTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage tp=new NewFXTarfPointsPage();
        tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tp.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"regression"})
    public void FX_TarfPointTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage tp=new NewFXTarfPointsPage();
        tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tp.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        tp.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfPointTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage tp=new NewFXTarfPointsPage();
        tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(tp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_TarfPointTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Point Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfPointsPage tp=new NewFXTarfPointsPage();
        Assertions.assertThat(tp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        tp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(tp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfLegTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Leg Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfLegsPage tl = new NewFXTarfLegsPage();
        tl.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("KnockoutLegs")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = tl.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        tl.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage tlp=new NewFXTarfLegsPage();
        tlp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tlp.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage tlp=new NewFXTarfLegsPage();
        tlp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(tlp.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        tlp.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage tlp=new NewFXTarfLegsPage();
        tlp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(tlp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tlp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(tlp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Leg Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfLegsPage tlp=new NewFXTarfLegsPage();
        Assertions.assertThat(tlp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        tlp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(tlp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //--------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_DigitalEKITest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = eki.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        eki.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = eki.getForwardRate();
        String ImpliedVol1 = eki.getImpliedvolatility();
        String Notional1 = eki.getNotional2();
        eki.clearTenure();
        eki.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = eki.getForwardRate();
        String ImpliedVol2 = eki.getImpliedvolatility();
        String Notional2 = eki.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = eki.priceSectionDisplayed();
        eki.clearTenure();
        eki.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = eki.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            eki.enterMaturityDate(list1.get(i));
            eki.getForwardRate();
            if (eki.getForwardRate().isEmpty()) {
                eki.clearmaturityDate();
                eki.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(eki.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            eki.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(eki.getATMVolatilityDate(i));
            list2.add(eki.getATMvolatilityMid(i));
        }
        eki.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            eki.enterMaturityDate(list1.get(i));
            eki.enterStrike(eki.getForwardRate());
            eki.clickImpledVols();
            eki.getImpliedvolatility();
            if (eki.getImpliedvolatility().isEmpty()) {
                eki.clearStrike();
                eki.clearmaturityDate();
                eki.enterMaturityDate(list1.get(i));
                eki.enterStrike(eki.getForwardRate());
                eki.clickImpledVols();
            }
            Assert.assertEquals(eki.getImpliedvolatility(), list2.get(i));
            eki.clearStrike();
            eki.clearmaturityDate();

        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_006(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki =new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(eki.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(eki.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage eki=new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(eki.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage eki =new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(eki.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        eki.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage eki=new NEWFXDigidtalEKIPage();
        eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(eki.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(eki.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(eki.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NEWFXDigidtalEKIPage eki=new NEWFXDigidtalEKIPage();
        Assertions.assertThat(eki.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(eki.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = eki.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        eki.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = eki.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage eki = new NEWFXDigidtalEKIPage();
        eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = eki.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        eki.clearBarrier();
        eki.enterBarrier(data.get("Barrier2")).clickPricebutton();
        String[] strr = eki.priceSectionDisplayed();
        BigDecimal Premium2 = CommonUtils.StringArrayToInt(strr, 0, 1);
        BigDecimal ForwardDelta2 = CommonUtils.StringArrayToInt(strr, 2, 0);
        BigDecimal Vega2 = CommonUtils.StringArrayToInt(strr, 3, 0);

        Assertions.assertThat(Premium).isNotEqualTo(Premium2);
        Assertions.assertThat(ForwardDelta).isNotEqualTo(ForwardDelta2);
        Assertions.assertThat(Vega).isNotEqualTo(Vega2);


    }

    //--------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_DigitalEKOTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = eko.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        eko.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).clickMarketData()
                .marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure1")).
                clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = eko.getForwardRate();
        String ImpliedVol1 = eko.getImpliedvolatility();
        String Notional1 = eko.getNotional2();
        eko.clearTenure();
        eko.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = eko.getForwardRate();
        String ImpliedVol2 = eko.getImpliedvolatility();
        String Notional2 = eko.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();


        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = eko.priceSectionDisplayed();
        eko.clearTenure();
        eko.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = eko.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = { "regression"})
    public void FX_DigitalEKOTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(md.getDate(i));
            list2.add(md.getForwardRateMid(i));
        }
        md.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            eko.enterMaturityDate(list1.get(i));
            eko.getForwardRate();
            if (eko.getForwardRate().isEmpty()) {
                eko.clearmaturityDate();
                eko.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(eko.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.01;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Assert.assertEquals("", "");
            eko.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(eko.getATMVolatilityDate(i));
            list2.add(eko.getATMvolatilityMid(i));
        }
        eko.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            eko.enterMaturityDate(list1.get(i));
            eko.enterStrike(eko.getForwardRate());
            eko.clickImpledVols();
            eko.getImpliedvolatility();
            if (eko.getImpliedvolatility().isEmpty()) {
                eko.clearStrike();
                eko.clearmaturityDate();
                eko.enterMaturityDate(list1.get(i));
                eko.enterStrike(eko.getForwardRate());
                eko.clickImpledVols();
            }
            Assert.assertEquals(eko.getImpliedvolatility(), list2.get(i));
            eko.clearStrike();
            eko.clearmaturityDate();

        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage eko =new NEWFXDigitalEKOPage();
        eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(eko.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(eko.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= md.getMarketDataSpotRate();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage eko= new NEWFXDigitalEKOPage();
        eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(eko.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        md.clearSpotRate();
        md.enterSpotRate(SpotRate1).clickSaveButton();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage eko= new NEWFXDigitalEKOPage();
        eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(eko.getSoptRate()).contains(SpotRate1);
        dp.clickMarketData().clickRateCurves();
        eko.acceptAlert();
        md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        md.clearSpotRate();
        md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"smoke", "regression"})
    public void FX_DigitalEKOTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves md = new MarketDataRateCurves();
        md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(md.getForwardRateAsk(i))-spotRate);
        }
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage eko= new NEWFXDigitalEKOPage();
        eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(eko.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(eko.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(eko.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NEWFXDigitalEKOPage eko= new NEWFXDigitalEKOPage();
        Assertions.assertThat(eko.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(eko.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }


    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = eko.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        eko.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = eko.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage eko = new NEWFXDigitalEKOPage();
        eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = eko.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        eko.clearBarrier();
        eko.enterBarrier(data.get("Barrier2")).clickPricebutton();
        String[] strr = eko.priceSectionDisplayed();
        BigDecimal Premium2 = CommonUtils.StringArrayToInt(strr, 0, 1);
        BigDecimal ForwardDelta2 = CommonUtils.StringArrayToInt(strr, 2, 0);
        BigDecimal Vega2 = CommonUtils.StringArrayToInt(strr, 3, 0);

        Assertions.assertThat(Premium).isNotEqualTo(Premium2);
        Assertions.assertThat(ForwardDelta).isNotEqualTo(ForwardDelta2);
        Assertions.assertThat(Vega).isNotEqualTo(Vega2);
    }
    //=========================================================================================================
}

