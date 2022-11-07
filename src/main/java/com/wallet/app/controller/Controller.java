package com.wallet.app.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller {

	public static void main(String[] args) {
		
		WalletService walletService = new WalletServiceImpl();
		System.out.println("----WALLET APP----");
		System.out.println("\n1.Register \n2.Login \n3.Add amount \n4.Show wallet balance \n5.Fund transfer \n6.Unregister \n7.quit");
	
		Scanner sc=new Scanner(System.in);
		Scanner strsc=new Scanner(System.in);
		
		boolean quit=false;
		do {
		int button =sc.nextInt();
		switch(button){
		case 1:
			try {
				System.out.println("\nEnter id:");
				Integer id=sc.nextInt();
				System.out.println("\nEnter name:");
				String name=strsc.nextLine();
				System.out.println("\nEnter openingbalance:");
				Double openbal=sc.nextDouble();
				System.out.println("\nEnter password:");
				String password=strsc.nextLine();
				Wallet wallet = walletService.registerWallet(new Wallet(id,name,openbal,password));
				System.out.println(wallet);
				
			}
			catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			break;
			
		case 2:
			try {
				System.out.println("\nEnter id:");
				Integer id=sc.nextInt();
				System.out.println("\nEnter password:");
				String password=strsc.nextLine();
				System.out.println(walletService.login(id, password));
				
			}catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				System.out.println("\nEnter id:");
				Integer id=sc.nextInt();
				System.out.println("\nEnter amount:");
				Double amount=sc.nextDouble();
				System.out.println(walletService.addFundsToWallet(id, amount));
			}
			catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				System.out.println("\nEnter id:");
				Integer id=sc.nextInt();
				System.out.println(walletService.showWalletBalance(id));
			}
			catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			try {
				System.out.println("\nEnter from id:");
				Integer id=sc.nextInt();
				System.out.println("\nEnter to id:");
				Integer id2=sc.nextInt();
				System.out.println("\nEnter amount:");
				Double amount=sc.nextDouble();
				System.out.println(walletService.fundTransfer(id,id2,amount));
			}
			catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				System.out.println("\nEnter id:");
				Integer id=sc.nextInt();
				System.out.println("\nEnter password:");
				String password=strsc.nextLine();
				System.out.println(walletService.unRegisterWallet(id, password));
			}catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			quit=true;
			break;
		default:
				System.out.println("please enter no. from 1 to 6");
				break;
			}
		
		}while(!quit);
	}
}
//		try {
//			
//			
//			Wallet wallet = walletService.registerWallet(new Wallet(1, "Ford", 1000.0, "123"));
//			System.out.println(wallet);
//			Wallet wallet2 = walletService.registerWallet(new Wallet(2, "Ford2", 90.0, "134"));
//			System.out.println(wallet2);
//			System.out.println(walletService.login(1, "123"));
//			Double bal=walletService.showWalletBalance(1);
//			
//			System.out.println(bal);
//			System.out.println(walletService.addFundsToWallet(1, 500.0));
//			System.out.println(wallet);
//			System.out.println(walletService.fundTransfer(1, 2, 1200.0));
//			System.out.println(wallet);
//			System.out.println(walletService.unRegisterWallet(2, "134"));
//			
//		}
//		 
//		catch (WalletException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
//	}
//
//}
