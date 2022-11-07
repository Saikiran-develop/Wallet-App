package com.wallet.app.service;

import java.sql.SQLException;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dao.WalletDaoImpl2;
import com.wallet.app.dao.WalletDaoUtility;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.exception.WalletRepositoryException;

public class WalletServiceImpl implements WalletService {

	//private WalletDao walletRepository = new WalletDaoImpl();
	private WalletDao walletRepository = new WalletDaoImpl2(WalletDaoUtility.getConnectionToMysql());
	
	public Wallet registerWallet(Wallet newWallet) throws WalletException,SQLException {
		
		return this.walletRepository.addWallet(newWallet);
		
	}

	public Boolean login(Integer walletId, String password) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		 Wallet loginWallet=this.walletRepository.getWalletById(walletId);
		 if(loginWallet==null) {
			 throw new WalletException("Can't login ,wallet doesn't exist");
		 }
		 if(!loginWallet.getPassword().equals(password)) {
			 throw new WalletException("Your password is incorrect");
		 }
		 return true;
		
	}

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		if(amount<0 ||amount==0) {
			throw new WalletException("Cannot add negative amount"+amount);
		}
		Wallet showWallet=this.walletRepository.getWalletById(walletId);
		 showWallet.setBalance(showWallet.getBalance()+ amount);
		 this.walletRepository.updateWallet(showWallet);
		 return showWallet.getBalance();
	}

	public Double showWalletBalance(Integer walletId) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		 Wallet showWallet=this.walletRepository.getWalletById(walletId);
		 if (showWallet==null) {
			 throw new WalletException("Wallet doesn't exist for id: "+walletId);
		 }
		 return showWallet.getBalance();
	}

	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		Wallet fromWallet=this.walletRepository.getWalletById(fromId);
		 Wallet toWallet=this.walletRepository.getWalletById(toId);
		if(amount<=0) {
			throw new WalletException("Cannot add negative amount"+amount);
		}
		if(fromWallet==null) {
			throw new WalletException("From wallet doesn't exist");
		}
		if(toWallet==null) {
			throw new WalletException("To wallet doesn't exist");
		}
		 if(fromWallet.getBalance()<amount) {
			 throw new WalletException("Fund can't be transfered ,check the balance");
		}
		 fromWallet.setBalance(fromWallet.getBalance()-amount);
		 toWallet.setBalance(toWallet.getBalance()+amount);
		 this.walletRepository.updateWallet(fromWallet);
		 this.walletRepository.updateWallet(toWallet);
		 return true;
		 
		 
	}

	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException,SQLException  {
		// TODO Auto-generated method stub

		Wallet foundWallet = this.walletRepository.getWalletById(walletId);
		if(foundWallet == null)
			throw new WalletException("Wallet not found to unregister");
		
		if(!foundWallet.getPassword().equals(password))
			throw new WalletException("Password does't match to unregister your account.");
		
		Wallet deletedWallet;
		deletedWallet = this.walletRepository.deleteWalletById(walletId);
		return deletedWallet;
		
	}

}
