package com.wallet.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletTestDao {
	WalletDao walletDao=new WalletDaoImpl();
	
	
	@BeforeAll
	public static void setupTestData() {
		System.out.println("Create all test data");
	}
	
	@Test
	public void addWalletTest() {
		Wallet wallet=new Wallet(100, "test", 300.0, "900");
		try {
			assertEquals(wallet, walletDao.addWallet(wallet));
			
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getWalletByIdTest() {
		Wallet wallet=new Wallet(100, "test", 300.0, "900");
		
		try {
			walletDao.addWallet(wallet);
			assertEquals(wallet, walletDao.getWalletById(100));
			
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void updateWalletTest() {
		Wallet wallet=new Wallet(100, "test", 300.0, "900");
		Wallet updatewallet=new Wallet(100, "test", 600.0, "900");
		try {
			walletDao.addWallet(wallet);
			assertEquals(wallet, walletDao.updateWallet(updatewallet));
			
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void deleteWalletByIdTest() {
		Wallet wallet=new Wallet(100, "test", 300.0, "900");
		
		try {
			walletDao.addWallet(wallet);
			assertEquals(wallet, walletDao.deleteWalletById(100));
			assertThrows(WalletException.class, ()->walletDao.deleteWalletById(200));
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
