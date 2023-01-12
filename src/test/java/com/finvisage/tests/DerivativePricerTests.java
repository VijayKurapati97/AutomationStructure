package com.finvisage.tests;
import com.finvisage.drivers.DriverManager;
import com.finvisage.pages.*;
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

public class DerivativePricerTests extends BaseTest {

    private DerivativePricerTests() {
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardPage Fp = new NewFXForwardPage();
        Fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy")).
                enterNotional(data.get("Notional")).clickPrice().getPayoffGraph();

        String[] Str = Fp.getPriceSection();
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
        Fp.clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_002(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardPage Fp=new NewFXForwardPage();
        Fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction")).
                enterNotional(data.get("Notional")).clickPrice();

        String[] Str = Fp.getPriceSection();
        BigDecimal EquivalentNotional = CommonUtils.StringArrayToInt(Str, 0, 0);
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
        Assertions.assertThat(EquivalentNotional).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_003_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateAsk(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue("Buy")
                .enterNotional("40000021");
        for (int i = 0; i <= list2.size() - 1; i++){
            Fp.enterMaturityDate(list1.get(i)).clickPrice();
            String[] Str = Fp.getPriceSection();
            BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
            Assertions.assertThat(forwardRate).isCloseTo(BigDecimal.valueOf(Double.parseDouble(list2.get(i))), within(new BigDecimal("0.0001")));
            Fp.clearmaturityDate();
        }

    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_003_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateBid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue("Sell")
                .enterNotional("30000041");
        for (int i = 0; i <= list2.size() - 1; i++){
            Fp.enterMaturityDate(list1.get(i)).clickPrice();
            String[] Str = Fp.getPriceSection();
            BigDecimal forwardRate = CommonUtils.StringArrayToInt(Str, 1, 0);
            Assertions.assertThat(forwardRate).isCloseTo(BigDecimal.valueOf(Double.parseDouble(list2.get(i))), within(new BigDecimal("0.0001")));
            Fp.clearmaturityDate();
        }

    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++){
            Fp.enterMaturityDate(list1.get(i));
            Fp.getForwardRate();
            if (Fp.getForwardRate().isEmpty()) {
                Fp.clearmaturityDate();
                Fp.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Fp.getForwardRate());
            double forwardRate2 = Double.parseDouble(list2.get(i));
            Assertions.assertThat(forwardRate1).isCloseTo(forwardRate2,within(new Double("0.0001")));
            Fp.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_005_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Fp.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardTest_005_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Fp.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Fp.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardTest_006(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Fp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Fp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Fp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ForwardTest_007(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXForwardPage Fp= new NewFXForwardPage();
        Assertions.assertThat(Fp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Fp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

//------------------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke", "regression"})
    public void FX_ForwardStripTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardStripPage Fs = new NewFXForwardStripPage();
        Fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice();

        String Str1 = Fs.getWeightedAvgForward();
        String[] En = Str1.split(" ");
        Double WeightedAvgForward = Double.parseDouble(En[0]);
        Assertions.assertThat(WeightedAvgForward)
                .isPositive()
                .isNotZero()
                .isNotNull()
                .isGreaterThan(1);
        Fs.clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardStripPage Fs = new NewFXForwardStripPage();
        Fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice();
        Map<Long,Double> map= new HashMap<>();
        for(int i=1;i<=Integer.parseInt(data.get("NotionalIndex"));i++){

            map.put( CommonUtils.stringToint(Fs.getNotional(i)),Double.parseDouble(Fs.getForwardRate(i)));
        }
        Double weightedAverageForward1 = CommonUtils.calculateWeightedAverage(map);
        String Str1 = Fs.getWeightedAvgForward();
        String[] En = Str1.split(" ");
        Double WeightedAvgForward = Double.parseDouble(En[0]);
        Assertions.assertThat(WeightedAvgForward).isCloseTo(weightedAverageForward1,within(new Double("0.09")));

    }

    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_003_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage Fs= new NewFXForwardStripPage();
        Fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Fs.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_003_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage Fs= new NewFXForwardStripPage();
        Fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Fs.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Fs.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        NewFXForwardStripPage Fs= new NewFXForwardStripPage();
        Fs.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Fs.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Fs.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Fs.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ForwardStripTest_005(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXForwardStripPage Fs= new NewFXForwardStripPage();
        Assertions.assertThat(Fs.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Fs.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-----------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke", "regression"})
    public void FX_EuropeanOptionTest_001_1(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Er.priceSectionDisplayed();//main
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

        Er.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"smoke", "regression"})
    public void FX_EuropeanOptionTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Er.priceSectionDisplayed();//main
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

        Er.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike1")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));
        String ForwardRate1 = Er.getForwardRate();
        String ImpliedVol1 = Er.getImpliedvolatility();
        String Notional1 = Er.getNotional2();
        Er.clearTenure();
        Er.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Er.getForwardRate();
        String ImpliedVol2 = Er.getImpliedvolatility();
        String Notional2 = Er.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection()
                .selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike1")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = Er.priceSectionDisplayed();
        Er.clearTenure();
        Er.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Er.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Er.enterMaturityDate(list1.get(i));
            Er.getForwardRate();
            if (Er.getForwardRate().isEmpty()) {
                Er.clearmaturityDate();
                Er.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Er.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Er.clearmaturityDate();
        }

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Er.getATMVolatilityDate(i));
            list2.add(Er.getATMvolatilityMid(i));
        }
        Er.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Er.enterMaturityDate(list1.get(i));
            Er.enterStrike(Er.getForwardRate());
            Er.clickImpledVolatility();
            if (Er.getImpliedvolatility().isEmpty()) {
                Er.clearStrike();
                Er.clearmaturityDate();
                Er.enterMaturityDate(list1.get(i));
                Er.enterStrike(Er.getForwardRate());
                Er.clickImpledVolatility();
            }
            Assert.assertEquals(Er.getImpliedvolatility(), list2.get(i));
            Er.clearStrike();
            Er.clearmaturityDate();

        }

    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Er.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Er.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage Er= new NewFXEuropeanOptionPage();
        Er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Er.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage Er= new NewFXEuropeanOptionPage();
        Er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Er.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Er.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        NewFXEuropeanOptionPage Er= new NewFXEuropeanOptionPage();
        Er.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Er.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Er.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Er.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXEuropeanOptionPage Er= new NewFXEuropeanOptionPage();
        Assertions.assertThat(Er.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Er.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickImpledVolatility().clickNotionalCcy();
      String ImpliedVol=  Er.getImpliedvolatility();
       Er.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike").clickSeekStrike();
        Assertions.assertThat(data.get("Strike")).isNotEqualTo(Er.getStrikeValue());
        Assertions.assertThat(ImpliedVol).isNotEqualTo(Er.getImpliedvolatility());
    }

    @Test(groups = {"regression"})
    public void FX_EuropeanOptionTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er = new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Er.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

       Er.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
       String Total = Er.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
               Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }

    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_OptionSpreadTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os = new NewFXOptionSpreadPage();
        Os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = Os.priceSectionDisplayed();//main
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
        Os.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }
    @Test(groups = {"smoke", "regression"})
    public void FX_OptionSpreadTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os = new NewFXOptionSpreadPage();
        Os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = Os.priceSectionDisplayed();//main
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
        Os.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os = new NewFXOptionSpreadPage();
        Os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = Os.getForwardRate();
        String ImpliedVol1 = Os.getImpliedvolatility1();
        String ImpliedVol2 = Os.getImpliedvolatility2();
        String Notional1 = Os.getNotional2();
        Os.clearTenure();
        Os.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Os.getForwardRate();
        String ImpliedVol3 = Os.getImpliedvolatility1();
        String ImpliedVol4 = Os.getImpliedvolatility2();
        String Notional2 = Os.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol3);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os = new NewFXOptionSpreadPage();
        Os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] price1 = Os.priceSectionDisplayed();
        Os.clearTenure();
        Os.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Os.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage Os = new NewFXOptionSpreadPage();
        Os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Os.enterMaturityDate(list1.get(i));
            Os.getForwardRate();
            if (Os.getForwardRate().isEmpty()) {
                Os.clearmaturityDate();
                Os.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Os.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Os.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();
        NewFXOptionSpreadPage Op = new NewFXOptionSpreadPage();
        Op.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Op.getATMVolatilityDate(i));
            list2.add(Op.getATMvolatilityMid(i));
        }
        Op.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Op.enterMaturityDate(list1.get(i));
            Op.enterStrike1(Op.getForwardRate());
            Op.enterStrike2(Op.getForwardRate());
            Op.clickImpledVols2();
            Op.getImpliedvolatility1();
            Op.getImpliedvolatility2();
            if (Op.getImpliedvolatility1().isEmpty() || Op.getImpliedvolatility2().isEmpty()) {
                Op.clearStrike1();
                Op.clearStrike2();
                Op.clearmaturityDate();
                Op.enterMaturityDate(list1.get(i));
                Op.enterStrike1(Op.getForwardRate());
                Op.enterStrike2(Op.getForwardRate());
                Op.clickImpledVols2();
            }
            Assert.assertEquals(Op.getImpliedvolatility1(), list2.get(i));
            Assert.assertEquals(Op.getImpliedvolatility2(), list2.get(i));
            Op.clearStrike1();
            Op.clearStrike2();
            Op.clearmaturityDate();
        }
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os=new NewFXOptionSpreadPage();
        Os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure"))
                .enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Os.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Os.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage Os= new NewFXOptionSpreadPage();
        Os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Os.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage Os=new NewFXOptionSpreadPage();
        Os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Os.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Os.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        NewFXOptionSpreadPage Os= new NewFXOptionSpreadPage();
        Os.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Os.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Os.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Os.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXOptionSpreadPage Os= new NewFXOptionSpreadPage();
        Assertions.assertThat(Os.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Os.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os= new NewFXOptionSpreadPage();
        Os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickImpledVols2().clickNotionalCcy();
        String ImpliedVol1=  Os.getImpliedvolatility1();
        String ImpliedVol2= Os.getImpliedvolatility2();
        Os.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
       Assertions.assertThat(ImpliedVol1).isNotEqualTo(Os.getImpliedvolatility1());
       Assertions.assertThat(data.get("Strike1")).isNotEqualTo(Os.getStrike1Value());
       Os.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(Os.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(Os.getStrike2Value());
    }

    @Test(groups = {"regression"})
    public void FX_OptionSpreadTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os=new NewFXOptionSpreadPage();
        Os.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Os.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        Os.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = Os.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }

    //-------------------------------------------------------------------------------------------------------

    @Test(groups = {"smoke","regression"})
    public void FX_CollarTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Cp.priceSectionDisplayed();//main
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

        Cp.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");

    }
    @Test(groups = {"smoke","regression"})
    public void FX_CollarTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Cp.priceSectionDisplayed();//main
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

        Cp.graphIsDisplayed().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));
        String ForwardRate1 = Cp.getForwardRate();
        String ImpliedVol1 = Cp.getImpliedvolatility1();
        String ImpliedVol2 = Cp.getImpliedvolatility2();
        String Notional1 = Cp.getNotional2();
        Cp.clearTenure();
        Cp.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Cp.getForwardRate();
        String ImpliedVol3 = Cp.getImpliedvolatility1();
        String ImpliedVol4 = Cp.getImpliedvolatility2();
        String Notional2 = Cp.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol3);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = Cp.priceSectionDisplayed();
        Cp.clearTenure();
        Cp.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Cp.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);


    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Cp.enterMaturityDate(list1.get(i));
            Cp.getForwardRate();
            if (Cp.getForwardRate().isEmpty()) {
                Cp.clearmaturityDate();
                Cp.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Cp.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.01;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Cp.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp = new NewFXCollorPage();
        Cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Cp.getATMVolatilityDate(i));
            list2.add(Cp.getATMvolatilityMid(i));
        }
        Cp.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Cp.enterMaturityDate(list1.get(i));
            Cp.enterStrike1(Cp.getForwardRate());
            Cp.enterStrike2(Cp.getForwardRate());
            Cp.clickImpledVols2();
            Cp.getImpliedvolatility1();
            Cp.getImpliedvolatility2();
            if (Cp.getImpliedvolatility1().isEmpty() || Cp.getImpliedvolatility2().isEmpty()) {
                Cp.clearStrike1();
                Cp.clearStrike2();
                Cp.clearmaturityDate();
                Cp.enterMaturityDate(list1.get(i));
                Cp.enterStrike1(Cp.getForwardRate());
                Cp.enterStrike2(Cp.getForwardRate());
                Cp.clickImpledVols2();
            }
            String ImpliedVolatility3 = list2.get(i);
            Assert.assertEquals(Cp.getImpliedvolatility1(), ImpliedVolatility3);
            Assert.assertEquals(Cp.getImpliedvolatility2(), ImpliedVolatility3);
            Cp.clearmaturityDate();
            Cp.clearStrike1();
            Cp.clearStrike2();
        }
    }

    @Test(groups = {"regression"})
    public void FX_CollarTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Cp.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Cp.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Cp.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Cp.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Cp.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Cp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Cp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Cp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXCollorPage Cp=new NewFXCollorPage();
        Assertions.assertThat(Cp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Cp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();
        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickImpledVols2().clickNotionalCcy();
        String ImpliedVol1=  Cp.getImpliedvolatility1();
        String ImpliedVol2= Cp.getImpliedvolatility2();
        Cp.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(Cp.getImpliedvolatility1());
        Assertions.assertThat(data.get("Strike1")).isNotEqualTo(Cp.getStrike1Value());
        Cp.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(Cp.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(Cp.getStrike2Value());
    }
    @Test(groups = {"regression"})
    public void FX_CollarTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Cp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        Cp.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = Cp.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }
    //------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_001_1(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Tw.priceSectionDisplayed();//main
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

        Tw.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice().cashflowTableIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");

    }
    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_001_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Tw.priceSectionDisplayed();//main
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

        Tw.graphIsDisplayed().clickCheckBox()
                .enterPremium(data.get("Premium(mid)")).clickByChangingDropdown()
                .byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice().structureDetailsIsDisplayed();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Finvisage - Pricer -");
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = Tw.getForwardRate();
        String ImpliedVol1 = Tw.getImpliedvolatility1();
        String ImpliedVol2 = Tw.getImpliedvolatility2();
        String ImpliedVol3 = Tw.getImpliedvolatility3();
        String Notional1 = Tw.getNotional2();
        Tw.clearTenure();
        Tw.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Tw.getForwardRate();
        String ImpliedVol4 = Tw.getImpliedvolatility1();
        String ImpliedVol5 = Tw.getImpliedvolatility2();
        String ImpliedVol6 = Tw.getImpliedvolatility3();
        String Notional2 = Tw.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol4);
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(ImpliedVol5);
        Assertions.assertThat(ImpliedVol3).isNotEqualTo(ImpliedVol6);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] price1 = Tw.priceSectionDisplayed();
        Tw.clearTenure();
        Tw.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Tw.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);


    }

    @Test(groups = {"smoke", "regression"})
    public void FX_ThreeWayTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Tw.enterMaturityDate(list1.get(i));
            Tw.getForwardRate();
            if (Tw.getForwardRate().isEmpty()) {
                Tw.clearmaturityDate();
                Tw.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Tw.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Tw.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();


        NewFXThreeWayPage Tw = new NewFXThreeWayPage();
        Tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Tw.getATMVolatilityDate(i));
            list2.add(Tw.getATMvolatilityMid(i));
        }
        Tw.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Tw.enterMaturityDate(list1.get(i));
            Tw.enterStrike1(Tw.getForwardRate());
            Tw.enterStrike2(Tw.getForwardRate());
            Tw.enterStrike3(Tw.getForwardRate());
            Tw.clickImpledVols3();
            Tw.getImpliedvolatility1();
            Tw.getImpliedvolatility2();
            Tw.getImpliedvolatility3();
            if (Tw.getImpliedvolatility1().isEmpty() || Tw.getImpliedvolatility2().isEmpty() ||
                    Tw.getImpliedvolatility3().isEmpty()) {
                Tw.clearmaturityDate();
                Tw.clearStrike1();
                Tw.clearStrike2();
                Tw.clearStrike3();
                Tw.enterMaturityDate(list1.get(i));
                Tw.enterStrike1(Tw.getForwardRate());
                Tw.enterStrike2(Tw.getForwardRate());
                Tw.enterStrike3(Tw.getForwardRate());
                Tw.clickImpledVols3();
            }

            Assert.assertEquals(Tw.getImpliedvolatility1(), list2.get(i));
            Assert.assertEquals(Tw.getImpliedvolatility2(), list2.get(i));
            Assert.assertEquals(Tw.getImpliedvolatility3(), list2.get(i));
            Tw.clearmaturityDate();
            Tw.clearStrike1();
            Tw.clearStrike2();
            Tw.clearStrike3();
        }
    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw=new NewFXThreeWayPage();
        Tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Tw.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Tw.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage Tw=new NewFXThreeWayPage();
        Tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Tw.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage Tw= new NewFXThreeWayPage();
        Tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Tw.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Tw.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        NewFXThreeWayPage Tw= new NewFXThreeWayPage();
        Tw.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Tw.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Tw.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Tw.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXThreeWayPage Tw=new NewFXThreeWayPage();
        Assertions.assertThat(Tw.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Tw.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        NewFXThreeWayPage Tw= new NewFXThreeWayPage();
        Tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3")).clickImpledVols3().clickNotionalCcy();
        String ImpliedVol1=  Tw.getImpliedvolatility1();
        String ImpliedVol2= Tw.getImpliedvolatility2();
        String ImpliedVol3= Tw.getImpliedvolatility3();
        Tw.selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickCheckBox().enterPremium(data.get("Premium(mid)"))
                .clickByChangingDropdown().byChangingStrike("Strike 1").clickSeekStrike();
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(Tw.getImpliedvolatility1());
        Assertions.assertThat(data.get("Strike1")).isNotEqualTo(Tw.getStrike1Value());

        Tw.clickByChangingDropdown().byChangingStrike("Strike 2").clickSeekStrike();
        Assertions.assertThat(ImpliedVol2).isNotEqualTo(Tw.getImpliedvolatility2());
        Assertions.assertThat(data.get("Strike2")).isNotEqualTo(Tw.getStrike2Value());

        Tw.clickByChangingDropdown().byChangingStrike("Strike 3").clickSeekStrike();
        Assertions.assertThat(data.get("Strike3")).isNotEqualTo(Tw.getStrike3Value());
        Assertions.assertThat(ImpliedVol3).isNotEqualTo(Tw.getImpliedvolatility3());

    }

    @Test(groups = {"regression"})
    public void FX_ThreeWayTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();
        NewFXThreeWayPage Tw= new NewFXThreeWayPage();
        Tw.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Tw.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        Tw.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = Tw.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }

    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfAbsoluteTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Absolute Price")
                .isNotEmpty()
                .isNotNull();
        NewFXTarfAbsolutePage Ab = new NewFXTarfAbsolutePage();
        Ab.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyIndex")).clickKnockoutCcy().selectKnockoutCcyValue(data.get("KnockoutCcyIndex"))
                .enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Ab.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));


        Ab.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage Ab= new NewFXTarfAbsolutePage();
        Ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Ab.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage Ab= new NewFXTarfAbsolutePage();
        Ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Ab.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Ab.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }

    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        NewFXTarfAbsolutePage Ab=new NewFXTarfAbsolutePage();
        Ab.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Ab.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Ab.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Ab.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }

    @Test(groups = {"regression"})
    public void FX_TarfAbsoluteTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Absolute Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfAbsolutePage Ab= new NewFXTarfAbsolutePage();
        Assertions.assertThat(Ab.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Ab.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Ab.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-----------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfPointTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Point Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfPointsPage Tp = new NewFXTarfPointsPage();
        Tp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Tp.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        Tp.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }

    @Test(groups = {"regression"})
    public void FX_TarfPointTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage Tp=new NewFXTarfPointsPage();
        Tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Tp.getSoptRate()).contains(spotRate);
    }

    @Test(groups = {"regression"})
    public void FX_TarfPointTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage Tp=new NewFXTarfPointsPage();
        Tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Tp.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Tp.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfPointTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        NewFXTarfPointsPage Tp=new NewFXTarfPointsPage();
        Tp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Tp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Tp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Tp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_TarfPointTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Point Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfPointsPage Tp=new NewFXTarfPointsPage();
        Assertions.assertThat(Tp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Tp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Tp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //-------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_TarfLegTest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Leg Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfLegsPage Tl = new NewFXTarfLegsPage();
        Tl.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("KnockoutLegs")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Tl.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward = CommonUtils.StringArrayToInt(str, 1, 0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        Tl.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_002_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage Lp=new NewFXTarfLegsPage();
        Lp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Lp.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_002_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage Lp=new NewFXTarfLegsPage();
        Lp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Lp.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Lp.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_003(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        NewFXTarfLegsPage Lp=new NewFXTarfLegsPage();
        Lp.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Lp.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Lp.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Lp.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_TarfLegTest_004(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Leg Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NewFXTarfLegsPage Lp=new NewFXTarfLegsPage();
        Assertions.assertThat(Lp.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Lp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Lp.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }
    //--------------------------------------------------------------------------------------------------------


    @Test(groups = {"smoke", "regression"})
    public void FX_DigitalEKITest_001(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Eki.priceSectionDisplayed();//main
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

        Eki.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = Eki.getForwardRate();
        String ImpliedVol1 = Eki.getImpliedvolatility();
        String Notional1 = Eki.getNotional2();
        Eki.clearTenure();
        Eki.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Eki.getForwardRate();
        String ImpliedVol2 = Eki.getImpliedvolatility();
        String Notional2 = Eki.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = Eki.priceSectionDisplayed();
        Eki.clearTenure();
        Eki.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Eki.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Eki.enterMaturityDate(list1.get(i));
            Eki.getForwardRate();
            if (Eki.getForwardRate().isEmpty()) {
                Eki.clearmaturityDate();
                Eki.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Eki.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.0001;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Eki.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Eki.getATMVolatilityDate(i));
            list2.add(Eki.getATMvolatilityMid(i));
        }
        Eki.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Eki.enterMaturityDate(list1.get(i));
            Eki.enterStrike(Eki.getForwardRate());
            Eki.clickImpledVols();
            Eki.getImpliedvolatility();
            if (Eki.getImpliedvolatility().isEmpty()) {
                Eki.clearStrike();
                Eki.clearmaturityDate();
                Eki.enterMaturityDate(list1.get(i));
                Eki.enterStrike(Eki.getForwardRate());
                Eki.clickImpledVols();
            }
            Assert.assertEquals(Eki.getImpliedvolatility(), list2.get(i));
            Eki.clearStrike();
            Eki.clearmaturityDate();

        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_006(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki =new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Eki.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Eki.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage Eki=new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Eki.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage Eki =new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Eki.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Eki.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        NEWFXDigidtalEKIPage Eki=new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Eki.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Eki.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Eki.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NEWFXDigidtalEKIPage Eki=new NEWFXDigidtalEKIPage();
        Assertions.assertThat(Eki.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Eki.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Eki.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = Eki.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        Eki.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = Eki.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKITest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki = new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Eki.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Eki.clearBarrier();
        Eki.enterBarrier(data.get("Barrier2")).clickPricebutton();
        String[] strr = Eki.priceSectionDisplayed();
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
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Eko.priceSectionDisplayed();//main
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

        Eko.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_002(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).clickMarketData()
                .marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure1")).
                clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional"));

        String ForwardRate1 = Eko.getForwardRate();
        String ImpliedVol1 = Eko.getImpliedvolatility();
        String Notional1 = Eko.getNotional2();
        Eko.clearTenure();
        Eko.enterTenure(data.get("Tenure2"));
        String ForwardRate2 = Eko.getForwardRate();
        String ImpliedVol2 = Eko.getImpliedvolatility();
        String Notional2 = Eko.getNotional2();
        Assertions.assertThat(ForwardRate1).isNotEqualTo(ForwardRate2);
        Assertions.assertThat(ImpliedVol1).isNotEqualTo(ImpliedVol2);
        Assertions.assertThat(Notional1).isNotEqualTo(Notional2);
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_003(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();


        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure1")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] price1 = Eko.priceSectionDisplayed();
        Eko.clearTenure();
        Eko.enterTenure(data.get("Tenure2")).clickPricebutton();
        String[] price2 = Eko.priceSectionDisplayed();
        Assertions.assertThat(price1[0]).isNotEqualTo(price2[0]);
        Assertions.assertThat(price1[1]).isNotEqualTo(price2[1]);
        Assertions.assertThat(price1[2]).isNotEqualTo(price2[2]);
        Assertions.assertThat(price1[3]).isNotEqualTo(price2[3]);
        Assertions.assertThat(price1[4]).isNotEqualTo(price2[4]);
    }

    @Test(groups = { "regression"})
    public void FX_DigitalEKOTest_004(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Md.getDate(i));
            list2.add(Md.getForwardRateMid(i));
        }
        Md.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Eko.enterMaturityDate(list1.get(i));
            Eko.getForwardRate();
            if (Eko.getForwardRate().isEmpty()) {
                Eko.clearmaturityDate();
                Eko.enterMaturityDate(list1.get(i));
            }
            double forwardRate1 = Double.parseDouble(Eko.getForwardRate());
            String str = list2.get(i);
            double forwardRate2 = Double.parseDouble(str);
            final double del = 0.01;
            Assert.assertEquals(forwardRate1, forwardRate2, del);
            Assert.assertEquals("", "");
            Eko.clearmaturityDate();
        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_005(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().clickVolatility();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list1.add(Eko.getATMVolatilityDate(i));
            list2.add(Eko.getATMvolatilityMid(i));
        }
        Eko.clickNextButton();
        for (int i = 0; i <= list2.size() - 1; i++) {
            Eko.enterMaturityDate(list1.get(i));
            Eko.enterStrike(Eko.getForwardRate());
            Eko.clickImpledVols();
            Eko.getImpliedvolatility();
            if (Eko.getImpliedvolatility().isEmpty()) {
                Eko.clearStrike();
                Eko.clearmaturityDate();
                Eko.enterMaturityDate(list1.get(i));
                Eko.enterStrike(Eko.getForwardRate());
                Eko.clickImpledVols();
            }
            Assert.assertEquals(Eko.getImpliedvolatility(), list2.get(i));
            Eko.clearStrike();
            Eko.clearmaturityDate();

        }
    }

    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_006(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage Eko =new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).enterNotional(data.get("Notional"));
        BigDecimal notional2 = BigDecimal.valueOf(Double.parseDouble(Eko.getNotional2()));
        BigDecimal notional1 = BigDecimal.valueOf(Double.parseDouble(data.get("Notional")));
        BigDecimal forwardRate = BigDecimal.valueOf(Double.parseDouble(Eko.getForwardRate()));
        Assertions.assertThat(notional2).isCloseTo(notional1.multiply(forwardRate), within(new BigDecimal("0.02")));

    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_007_1(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");

        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String spotRate= Md.getMarketDataSpotRate();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage Eko= new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Eko.getSoptRate()).contains(spotRate);
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_007_2(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        String rate=Md.getMarketDataSpotRate();
        double spotRate= Double.parseDouble(rate);
        int number=(int)(Math.random()*(7-1+1)+1);
        String SpotRate1=String.valueOf(spotRate+number);
        Md.clearSpotRate();
        Md.enterSpotRate(SpotRate1).clickSaveButton();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage Eko= new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed()
                .clickNextButton();
        Assertions.assertThat(Eko.getSoptRate()).contains(SpotRate1);
        Dp.clickMarketData().clickRateCurves();
        Eko.acceptAlert();
        Md.selectunderlyingType(data.get("UnderlyingType")).
                selectUnderlying(data.get("Underlying")).selectScheduleTime();
        Md.clearSpotRate();
        Md.enterSpotRate(rate).clickSaveButton();
    }
    @Test(groups = {"smoke", "regression"})
    public void FX_DigitalEKOTest_008(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickMarketData().clickRateCurves();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Market Data")
                .isNotEmpty()
                .isNotNull();
        MarketDataRateCurves Md = new MarketDataRateCurves();
        Md.selectunderlyingType(data.get("UnderlyingType")).selectUnderlying(data.get("Underlying")).selectScheduleTime();
        double spotRate= Double.parseDouble(Md.getMarketDataSpotRate());
        List<Double> BidPoints = new ArrayList<>();
        List<Double> MidPoints = new ArrayList<>();
        List<Double> AskPoints = new ArrayList<>();
        for(int i=1;i<=12;i++){
            BidPoints.add(Double.parseDouble(Md.getForwardRateBid(i))-spotRate);
            MidPoints.add(Double.parseDouble(Md.getForwardRateMid(i))-spotRate);
            AskPoints.add(Double.parseDouble(Md.getForwardRateAsk(i))-spotRate);
        }
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        NEWFXDigitalEKOPage Eko= new NEWFXDigitalEKOPage();
        Eko.clickUnderlying().selectUnderlying(data.get("UnderlyingValue")).
                clickMarketData().marketDataIsDisplayed();
        for(int i=1;i<=12;i++){
            Assertions.assertThat( Double.parseDouble(Eko.getForwardPointsBid(i)))
                    .isCloseTo(BidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Eko.getForwardPointsMid(i)))
                    .isCloseTo(MidPoints.get(i-1),within(new Double("0.001")));
            Assertions.assertThat( Double.parseDouble(Eko.getForwardPointsAsk(i)))
                    .isCloseTo(AskPoints.get(i-1),within(new Double("0.001")));
        }
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_009(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp = new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        NEWFXDigitalEKOPage Eko= new NEWFXDigitalEKOPage();
        Assertions.assertThat(Eko.getMarketDate()).isEqualTo(dateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Eko.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton();
        Assertions.assertThat(Eko.getSpotDate()).isEqualTo(dateFormat.format(cal.getTime()));
    }


    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_010(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage EkO = new NEWFXDigitalEKOPage();
        EkO.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = EkO.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        EkO.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate();
        String Total = EkO.getDeffermentTotal();
        String [] ar=  Total.split(",");
        String value=String.join("",ar);
        Assertions.assertThat(Premium).isCloseTo(new BigDecimal(value),within(new BigDecimal("0.1")));
    }
    @Test(groups = {"regression"})
    public void FX_DigitalEKOTest_011(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp = new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigitalEKOPage Eko = new NEWFXDigitalEKOPage();
        Eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Eko.priceSectionDisplayed();//main
        BigDecimal Premium = CommonUtils.StringArrayToInt(str, 0, 1);
        BigDecimal ForwardDelta = CommonUtils.StringArrayToInt(str, 2, 0);
        BigDecimal Vega = CommonUtils.StringArrayToInt(str, 3, 0);
        Eko.clearBarrier();
        Eko.enterBarrier(data.get("Barrier2")).clickPricebutton();
        String[] strr = Eko.priceSectionDisplayed();
        BigDecimal Premium2 = CommonUtils.StringArrayToInt(strr, 0, 1);
        BigDecimal ForwardDelta2 = CommonUtils.StringArrayToInt(strr, 2, 0);
        BigDecimal Vega2 = CommonUtils.StringArrayToInt(strr, 3, 0);

        Assertions.assertThat(Premium).isNotEqualTo(Premium2);
        Assertions.assertThat(ForwardDelta).isNotEqualTo(ForwardDelta2);
        Assertions.assertThat(Vega).isNotEqualTo(Vega2);
    }
    //=========================================================================================================
}

