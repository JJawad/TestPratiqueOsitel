package api.ositel.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import api.ositel.classe.FileExcel;
import api.ositel.utils.Utils;

public class FileDao {
	
	static List<FileExcel> listFileExcel;
	
	public static List<FileExcel> getAllFile() {
		return listFileExcel;
	}

	public static void getAddFile(FileExcel fileExcel) {
		if(listFileExcel == null) {
			listFileExcel = new ArrayList<FileExcel>();
		}
		listFileExcel.add(fileExcel);
		
	}

	public static void UpDateFile(Long id, InputStream inputStream) {
		// TODO Auto-generated method stub
		if(listFileExcel != null && listFileExcel.size() > 0) {
			for (FileExcel fileExcel : listFileExcel) {
				if(fileExcel.getId() == id) {
					
					fileExcel.setFile(Utils.AddStreamToFile(fileExcel.getNameFile(),inputStream));
//					fileExcel.setInputStream(inputStream);
					break;
				}
			}
		}
	}


	
}
