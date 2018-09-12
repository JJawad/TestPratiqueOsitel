package api.ositel.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import api.ositel.classe.FileExcel;
import api.ositel.classe.Result;

public class Utils {

	

	public static Long getMaxIdFile(List<FileExcel> listFile) {

		Long idMax = 0L;
		if(listFile != null && listFile.size() > 0) {
			for (FileExcel fileExcel : listFile) {
				if(idMax < fileExcel.getId()) {
					idMax = fileExcel.getId();
				}
			}
		}
		 
		
		return idMax;
	}
	
	public static FileExcel getFileByNameList(String nameFile ,List<FileExcel> listFile) {

		FileExcel fileExcel = null;
		for (FileExcel file : listFile) {
			if(nameFile.equals(file.getNameFile())) {
				fileExcel = file;
			}
		} 
		
		return fileExcel;
	}
	
	public static FileExcel getFileByIdList(Long idFile ,List<FileExcel> listFile) {

		FileExcel fileExcel = null;
		for (FileExcel file : listFile) {
			if(idFile == file.getId()) {
				fileExcel = file;
			}
		} 
		
		return fileExcel;
	}

	public static FileOutputStream writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();
		return fop;

	}

	public static Result GetResult(FileExcel fileExcel) throws Exception {
		Result result = new Result(fileExcel.getNameFile());

		/******************************************************/
		if(ExtFileEnum.xls.equals(fileExcel.getExtensionFile())) {
			result = ReadWriteExcelFile.readXLSFile(fileExcel.getFile());
			
		}else if(ExtFileEnum.xlsx.equals(fileExcel.getExtensionFile())) {
			result = ReadWriteExcelFile.readXLSXFile(fileExcel.getFile());
			
		} 
		/******************************************************/
		return result;
	}

	public static ExtFileEnum getExtensionByString(String ext) {
		
		if(ExtFileEnum.xls.toString().equals(ext)) {
			return ExtFileEnum.xls;
		}else if(ExtFileEnum.xlsx.toString().equals(ext)) {
			return ExtFileEnum.xlsx;
		}
		return null;
	}

	public static File AddStreamToFile(String NameFile, InputStream inputStream) {
		// TODO Auto-generated method stub
		OutputStream outputStream = null;
		File file = new File("pages/"+NameFile);
		try {
			
			// write the inputStream to a FileOutputStream
			
			outputStream = new FileOutputStream(file);
			
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return file;
	}

	public static void setValue(File file, int line, int colonne, String valeur) {
		
		
	}
	
	
}
