package com.wallet.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class WalletTestService {
	WalletService walletService=new WalletServiceImpl();
	
	@BeforeAll
	public static void setupTestData() {
		System.out.println("Create all test data");
	}
	@Test
	
	public void registerWalletTest() {
		try {
			Wallet wallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
			assertNotNull(wallet);
			assertThrows(WalletException.class, ()->walletService.registerWallet(wallet));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
@Test
	
	public void loginTest() {
		try {
			Wallet wallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
			Boolean checkLogin=walletService.login(100, "134");
			assertNotNull(checkLogin);
			assertThrows(WalletException.class, ()->walletService.login(100, "1323"));
			
			assertNotEquals("1344", wallet.getPassword());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	
	public void addFundsToWalletTest() {
		try {
			Wallet wallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
			assertNotNull(walletService.showWalletBalance(100));
			assertThrows(WalletException.class,()->walletService.showWalletBalance(200));
			assertEquals(1200.0, walletService.addFundsToWallet(100, 200.0));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
@Test
	
	public void showWalletBalanceTest() {
		try {
			Wallet wallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
			assertNotNull(walletService.showWalletBalance(100));
			assertThrows(WalletException.class,()-> walletService.addFundsToWallet(100, -9.0));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
@Test

	public void fundTransferTest() {
		try {
			Wallet fromwallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
			Wallet towallet=walletService.registerWallet(new Wallet(200, "test name2", 1900.0, "124"));
			assertNotNull(fromwallet);
			assertNotNull(towallet);
			assertThrows(WalletException.class,()-> walletService.fundTransfer(100, 200, 1500.0));
			assertEquals(1000.0, fromwallet.getBalance());
			assertEquals(1900.0, towallet.getBalance());
			assertTrue(walletService.fundTransfer(100,200, 500.0));
			assertEquals(500.0, fromwallet.getBalance());
			assertEquals(2400.0, towallet.getBalance());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
@Test

public void unRegisterWalletTest() {
	try {
		Wallet wallet=walletService.registerWallet(new Wallet(100, "test name", 1000.0, "134"));
		
		assertEquals(wallet,walletService.unRegisterWallet(100, "134"));
		assertThrows(WalletException.class,()-> walletService.unRegisterWallet(200, "123"));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	@AfterAll
	public static void destroyTestData() {
		System.out.println("Clear all test data");
	}
}
