package api.ositel.service;

import java.io.IOException;
import java.io.InputStream;

import api.ositel.classe.FileExcel;
import api.ositel.classe.Result;
import api.ositel.dao.FileDao;
import api.ositel.utils.ConstantsManager;
import api.ositel.utils.ReadWriteExcelFile;
import api.ositel.utils.Utils;

public class FileService {

	public static Long AddNewFileByName(String fileName) throws Exception{
		
		FileExcel fileExcel = new FileExcel();
		Long maxId = Utils.getMaxIdFile(FileDao.getAllFile());
		fileExcel.setId(maxId+1);
		fileExcel.setNameFile(fileName);
		String[] tabNom = fileName.split("\\.");
		fileExcel.setExtensionFile(Utils.getExtensionByString(tabNom[tabNom.length-1]));
		FileDao.getAddFile(fileExcel);
		return fileExcel.getId();
	}

	public static Result getResultByNameFile(String nameFile) throws Exception {
		Result result= null;
		FileExcel fileExcel = Utils.getFileByNameList(nameFile, FileDao.getAllFile());
		if(fileExcel != null) {				
			result = Utils.GetResult(fileExcel);
		}	
		return result;
	}
	
	public static Result getResultNewFile(String nameFile) throws Exception {
		Result result= null;
		FileExcel fileExcel = Utils.getFileByNameList(nameFile, FileDao.getAllFile());
		if(fileExcel != null) {				
			result = new Result(nameFile);
			result.setId(fileExcel.getId());
		}	
		return result;
	}
	
	public static void UpDateBinaryFileById(Long idExcelFile, InputStream inputStream) throws Exception{
		// TODO Auto-generated method stub
		FileExcel fileExcel = Utils.getFileByIdList(idExcelFile, FileDao.getAllFile());
		FileDao.UpDateFile(fileExcel.getId(), inputStream);	
			
	}

	public static void UpDateValueFileExcel(int line, int colonne, String valeur) throws IOException {
		// TODO Auto-generated method stub
		FileExcel fileExcel = Utils.getFileByNameList(ConstantsManager.NOM_FILE, FileDao.getAllFile());
		if(fileExcel != null) {				
			fileExcel.setFile(ReadWriteExcelFile.setValueInXLSXFile(fileExcel.getFile(),line,colonne,valeur));
		}
	}
	
}
