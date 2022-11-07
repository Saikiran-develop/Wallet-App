package com.wallet.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletDaoImpl implements WalletDao {

	// Create collection to store the Wallet information.
	Map<Integer, Wallet> wallets = new HashMap<Integer, Wallet>();

	public Wallet addWallet(Wallet newWallet) throws WalletException {
		if(wallets.containsKey(newWallet.getId())) {
			throw new WalletException("This id is already present: "+newWallet.getId());
		}
		 this.wallets.put(newWallet.getId(), newWallet);
		 return this.wallets.get(newWallet.getId());

	}

	public Wallet getWalletById(Integer walletId) throws WalletException {
		// TODO Auto-generated method stub
		
		return this.wallets.get(walletId);
		
		
	}

	public Wallet updateWallet(Wallet updateWallet) throws WalletException {
		// TODO Auto-generated method stub
		this.wallets.replace(updateWallet.getId(), updateWallet);
		return this.wallets.get(updateWallet.getId());
	}

	public Wallet deleteWalletById(Integer walletID) throws WalletException {
		// TODO Auto-generated method stub
		if(!wallets.containsKey(walletID)) {
			throw new WalletException("Can't delete because id is not there: "+walletID);
		}
		return this.wallets.remove(walletID);
	}

}
