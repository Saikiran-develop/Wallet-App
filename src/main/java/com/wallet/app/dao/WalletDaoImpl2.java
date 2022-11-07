package com.wallet.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletDaoImpl2 implements WalletDao{
	private Connection connection;
	
	public WalletDaoImpl2(Connection connection) {
		super();
		this.connection=connection;
	}
	public Wallet addWallet(Wallet newWallet) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wallet(id,name,balance,password) VALUES(?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, newWallet.getId());
			preparedStatement.setString(2, newWallet.getName());
			preparedStatement.setDouble(3, newWallet.getBalance());
			preparedStatement.setString(4, newWallet.getPassword());
//			System.out.println(preparedStatement);
			Integer count = preparedStatement.executeUpdate();
//			if (count == 1)
//				System.out.println("Wallet added successfully to DB.");
//			else
//				System.out.println("Wallet coud not be added to DB.");
			
			
			return getWalletById(newWallet.getId());
		}
		catch(WalletException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;//rethrow to service layer
		}
		return null;
	}

	public Wallet getWalletById(Integer walletId) throws WalletException,SQLException{
		String sql="SELECT * FROM wallet WHERE id=? ";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, walletId);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				if(result.getInt(1)==walletId) {
					Wallet wallet=new Wallet(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(4));
					return wallet;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return null;
	}

	public Wallet updateWallet(Wallet updateWallet) throws WalletException,SQLException {
		// TODO Auto-generated method stub
		String sql="UPDATE wallet set name=? ,balance=? ,password=? WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, updateWallet.getName());
			preparedStatement.setDouble(2, updateWallet.getBalance());
			preparedStatement.setString(3, updateWallet.getPassword());
			preparedStatement.setInt(4, updateWallet.getId());
			Integer count = preparedStatement.executeUpdate();
			if(count==1) {
				return getWalletById(updateWallet.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return null;
	}

	public Wallet deleteWalletById(Integer walletID) throws WalletException,SQLException  {
		// TODO Auto-generated method stub
		String sql="DELETE FROM wallet WHERE id=?";
		Wallet deletedWallet=getWalletById(walletID);
		Wallet returnWallet=new Wallet(walletID, deletedWallet.getName(), deletedWallet.getBalance(), deletedWallet.getPassword());
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,walletID);
			
			Integer count=preparedStatement.executeUpdate();
			
			if(count==1) {
				return returnWallet;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		
	}
	
}
