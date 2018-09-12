package api.ositel.classe;

import java.io.File;

import api.ositel.utils.ExtFileEnum;

public class FileExcel {
	
	Long id;
	
	String nameFile;
	
	ExtFileEnum extensionFile;
	
	File file;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nameFile
	 */
	public String getNameFile() {
		return nameFile;
	}
	/**
	 * @param nameFile the nameFile to set
	 */
	public void setNameFile(String nameFileIn) {
		nameFile = nameFileIn;
	}
	
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the extensionFile
	 */
	public ExtFileEnum getExtensionFile() {
		return extensionFile;
	}
	/**
	 * @param extensionFile the extensionFile to set
	 */
	public void setExtensionFile(ExtFileEnum extensionFile) {
		this.extensionFile = extensionFile;
	}
	
	
	
	

}
