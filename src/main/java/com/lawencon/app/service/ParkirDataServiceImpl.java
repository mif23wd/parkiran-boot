package com.lawencon.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.app.dao.ParkirDataDao;
import com.lawencon.app.dao.UserDao;
import com.lawencon.app.model.ParkirData;
import com.lawencon.app.model.User;

@Service
public class ParkirDataServiceImpl implements ParkirDataService {

	@Autowired
	@Qualifier("user_repo_hibernate")
	UserDao userdaoh;

	@Autowired
	@Qualifier("pd_repo_hibernate")
	ParkirDataDao parkirdaoh;
	
	@Autowired
	@Qualifier("pd_repo_jpa")
	ParkirDataDao parkirDataDaoj;
	
	@Autowired
	@Qualifier("user_repo_jpa")
	UserDao userDao;

	@Override
	public String checkInHibernate(String username, String password, String jenisKendaraan, String platNomor) throws Exception {
		// TODO Auto-generated method stub
		String notif = "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if (userdaoh.findByUsernamePassword(username, password) == true) {
			// loginNotif = "Berhasil Masuk";
			String[] platSplit = platNomor.split(" ", 3);
			if (subPlat1(platNomor.charAt(0)) && subPlat2(platSplit[1]) && subPlat3(platSplit[2])) {
				if (parkirdaoh.getTimeCheckIn(platNomor).equals("empty")) {
					ParkirData data = new ParkirData();
					data.setPlatNomor(platNomor);
					data.setJenisKendaraan(jenisKendaraan);
					data.setCheckIn(formatter.format(date));
					data.setCheckOut(null);
					parkirdaoh.checkIn(data);
					notif = "data telah dimasukan";
				} else {
					String[] temp = parkirdaoh.getTimeCheckIn(platNomor).split(" ", 2);
					if (temp[1].equals(formatter1.format(date))) {
						notif = "Kendaraan sudah parkir hari ini";
					} else {
						ParkirData data = new ParkirData();
						data.setPlatNomor(platNomor);
						data.setJenisKendaraan(jenisKendaraan);
						data.setCheckIn(formatter.format(date));
						data.setCheckOut(null);
						parkirdaoh.checkIn(data);
						notif = "data telah dimasukan";
					}
				}
			} else {
				notif = "Input plat nomor salah";
			}

		} else {
			notif = "Username & password salah";
		}
		return notif;
	}

	@Override
	public String checkOutHibernate(String username, String password, String platNomor) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		Date date = new Date();
		String notif = "";
		if (userdaoh.findByUsernamePassword(username, password) == true) {
			Boolean stat = parkirdaoh.checkOut(platNomor, formatter.format(date));
			if (stat) {
				notif = "Checkout telah diinput";
			}else {
				notif = "Data tidak ada";
			}
			
		}else {
			notif = "Username & password salah";
		}
		return notif;

	}

	@Override
	public List<ParkirData> listCheckInHibernate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		if (userdaoh.findByUsernamePassword(username, password) == true) {
			return parkirdaoh.findAllCheckIn();
		}else {
			return null;
		}
		
	}

	@Override
	public List<ParkirData> listCheckOutHibernate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		if (userdaoh.findByUsernamePassword(username, password) == true) {
			return parkirdaoh.findAllCheckOut();
		}else {
			return null;
		}
	}

	@Override
	public String insertUserHibernate(String username, String password, String user, String pass) throws Exception {
		// TODO Auto-generated method stub
		String notif = "";
		if (userdaoh.findByUsernamePassword(user, pass) == true) {
			User user1 = new User();
			user1.setUsername(username);
			user1.setPassword(password);
			userdaoh.insert(user1);
			notif = "User baru telah terdaftar";
		}else {
			notif = "Username & password salah";
		}
		return notif;
		

	}
	
	@Override
	public String checkInJpa(String username, String password, String jenisKendaraan, String platNomor) throws Exception {
		// TODO Auto-generated method stub
		String notif = "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if (userDao.findByUsernamePassword(username, password) == true) {
			// loginNotif = "Berhasil Masuk";
			String[] platSplit = platNomor.split(" ", 3);
			if (subPlat1(platNomor.charAt(0)) && subPlat2(platSplit[1]) && subPlat3(platSplit[2])) {
				if (parkirDataDaoj.getTimeCheckIn(platNomor).equals("empty")) {
					ParkirData data = new ParkirData();
					data.setPlatNomor(platNomor);
					data.setJenisKendaraan(jenisKendaraan);
					data.setCheckIn(formatter.format(date));
					data.setCheckOut(null);
					parkirDataDaoj.checkIn(data);
					notif = "data telah dimasukan";
				} else {
					String[] temp = parkirDataDaoj.getTimeCheckIn(platNomor).split(" ", 2);
					if (temp[1].equals(formatter1.format(date))) {
						notif = "Kendaraan sudah parkir hari ini";
					} else {
						ParkirData data = new ParkirData();
						data.setPlatNomor(platNomor);
						data.setJenisKendaraan(jenisKendaraan);
						data.setCheckIn(formatter.format(date));
						data.setCheckOut(null);
						parkirDataDaoj.checkIn(data);
						notif = "data telah dimasukan";
					}
				}
			} else {
				notif = "Input plat nomor salah";
			}

		} else {
			notif = "Username & password salah";
		}
		return notif;
	}

	@Override
	public String checkOutJpa(String username, String password, String platNomor) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		Date date = new Date();
		String notif = "";
		if (userDao.findByUsernamePassword(username, password) == true) {
			Boolean stat = parkirDataDaoj.checkOut(platNomor, formatter.format(date));
			if (stat) {
				notif = "Checkout telah diinput";
			}else {
				notif = "Data tidak ada";
			}
			
		}else {
			notif = "Username & password salah";
		}
		return notif;
	}

	@Override
	public List<ParkirData> listCheckInJpa(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		if (userDao.findByUsernamePassword(username, password) == true) {
			return parkirDataDaoj.findAllCheckIn();
		}else {
			return null;
		}
	}

	@Override
	public String insertUserJpa(String username, String password, String user, String pass) throws Exception {
		// TODO Auto-generated method stub
		String notif = "";
		if (userDao.findByUsernamePassword(user, pass) == true) {
			User user1 = new User();
			user1.setUsername(username);
			user1.setPassword(password);
			userDao.insert(user1);
			notif = "User baru telah terdaftar";
		}else {
			notif = "Username & password salah";
		}
		return notif;
	}	

	boolean subPlat1(char plat) {
		if (plat == 'B') {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	boolean subPlat2(String plat) {
		try {
			int cobaC = Integer.parseInt(plat);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	boolean subPlat3(String plat) {
		int countAngka = 0;
		int covInt = 0;
		String covString = null;
		for (int i = 0; i < plat.length(); i++) {
			try {
				covString = Character.toString(plat.charAt(i));
				covInt = Integer.parseInt(covString);
			} catch (Exception ex) {
				countAngka++;
			}
		}

		if (countAngka != plat.length()) {
			return false;
		} else {
			return true;
		}
	}

}
