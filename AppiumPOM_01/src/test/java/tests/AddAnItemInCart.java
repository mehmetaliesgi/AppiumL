package tests;

import org.testng.annotations.Test;
import screens.DashboardScreen;

public class AddAnItemInCart extends BaseTest {

    @Test
    public void AddAnItemInCart() {
        DashboardScreen dashboardScreen = new DashboardScreen(driver);

        dashboardScreen.notificationAlert()
                .notificationAlert()
                .todaysDeals()
                .search()
                .searchInput("Ayakkabi")
                .resultProduct()
                .filterButton()
                .clickBrand()
                .searchBrand("Adidas")
                .clickBrand()
                .clickApply()
                .enterMinPrice("1500")
                .enterMaxPrice("6000")
                .clickShowResults()
                .listingSortButton()
                .listingIncreasingPriceButton()
                .clickProduct()
                .clickAddToCart()
                .checkCart("Sepetim (1)");

    }
}
